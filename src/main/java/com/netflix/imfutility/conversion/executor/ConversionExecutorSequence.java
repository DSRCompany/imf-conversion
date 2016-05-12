package com.netflix.imfutility.conversion.executor;

import com.netflix.imfutility.conversion.executor.strategy.ExecuteOnceStrategy;
import com.netflix.imfutility.conversion.executor.strategy.ExecutePipeStrategy;
import com.netflix.imfutility.conversion.executor.strategy.OperationInfo;
import com.netflix.imfutility.conversion.executor.strategy.PipeOperationInfo;
import com.netflix.imfutility.conversion.templateParameter.ContextInfo;
import com.netflix.imfutility.conversion.templateParameter.ContextInfoBuilder;
import com.netflix.imfutility.conversion.templateParameter.context.TemplateParameterContextProvider;
import com.netflix.imfutility.xsd.conversion.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 5/12/2016.
 */
public class ConversionExecutorSequence implements IConversionExecutor {

    private final TemplateParameterContextProvider contextProvider;
    private final ExecEachSequenceSegmentType execEachSeq;
    private final SequenceType seqType;
    private final int seqNum;

    public ConversionExecutorSequence(TemplateParameterContextProvider contextProvider, ExecEachSequenceSegmentType execEachSeq) {
        this.contextProvider = contextProvider;
        this.execEachSeq = execEachSeq;
        this.seqType = execEachSeq.getType();
        this.seqNum = contextProvider.getSequenceContext().getSequenceCount(seqType);
    }

    @Override
    public void execute() throws IOException {
        for (int seq = 0; seq < seqNum; seq++) {
            for (Object operation : execEachSeq.getPipeOrExecOnceOrExecEachSegment()) {
                if (operation instanceof PipeSequenceType) {
                    execPipe((PipeSequenceType) operation);
                } else if (operation instanceof ExecOnceType) {
                    execOnce((ExecOnceType) operation);
                } else if (operation instanceof ExecEachSegmentType) {
                    execSegment((ExecEachSegmentType) operation);
                } else if (operation instanceof DynamicParameterType) {
                    addDynamicParameter((DynamicParameterType) operation);
                } else {
                    throw new RuntimeException(String.format("Unknown Conversion Operation type: %s", operation.toString()));
                }
            }
        }
    }

    private void execOnce(ExecOnceType execOnce) throws IOException {
        new ExecuteOnceStrategy(contextProvider).execute(getExecOnceOperation(execOnce));
    }

    private void execSegment(ExecEachSegmentType execSegment) throws IOException {
        for (OperationInfo segmentOperation : getExecSegmentOperations(execSegment)) {
            new ExecuteOnceStrategy(contextProvider).execute(segmentOperation);
        }
    }

    private void execPipe(PipeSequenceType pipe) throws IOException {
        // 1. prepare operation to be executed in a pipe
        PipeOperationInfo pipeInfo = new PipeOperationInfo();

        for (ExecOnceType tailOperation : pipe.getExecOnce()) {
            pipeInfo.getTailOperations().add(getExecOnceOperation(tailOperation));
        }
        if (pipe.getCycle() != null) {
            for (Object cycleOperation : pipe.getCycle().getExecEachSegmentOrExecOnce()) {
                if (cycleOperation instanceof ExecOnceType) {
                    pipeInfo.getCycleOperations().add(
                            getExecOnceOperation((ExecOnceType) cycleOperation));
                } else if (cycleOperation instanceof ExecEachSegmentType) {
                    pipeInfo.getCycleOperations().addAll(
                            getExecSegmentOperations((ExecEachSegmentType) cycleOperation));
                }
            }
        }

        // 2. execute in a pipe
        new ExecutePipeStrategy(contextProvider).execute(pipeInfo);
    }

    private void addDynamicParameter(DynamicParameterType dynamicParam) {
        ContextInfo contextInfo = new ContextInfoBuilder()
                .setSequence(seqNum)
                .setSequenceType(seqType)
                .build();
        contextProvider.getDynamicContext().appendParameter(
                dynamicParam.getName(), dynamicParam.getValue(), contextInfo);
    }

    private OperationInfo getExecOnceOperation(ExecOnceType execOnce) {
        ContextInfo contextInfo = new ContextInfoBuilder()
                .setSequence(seqNum)
                .setSequenceType(seqType)
                .build();
        return new OperationInfo(execOnce.getValue(), execOnce.getName(), execOnce.getClass(),
                contextInfo);
    }

    private List<OperationInfo> getExecSegmentOperations(ExecEachSegmentType execSegment) {
        List<OperationInfo> result = new ArrayList<>();

        // 1. get segments number
        int segmentNum = contextProvider.getSegmentContext().getSegmentsNum();

        // 2. process operations for each segment within sequence
        for (int segment = 0; segment < segmentNum; segment++) {
            // 2.1 get resource number for the the given (segment, sequence):
            int resourceNum = contextProvider.getResourceContext().getResourceCount(segment, seqNum, seqType);

            // 2.2 process operations for each resource within segment and sequence
            for (int resource = 0; resource < resourceNum; resource++) {
                // context info
                ContextInfo contextInfo = new ContextInfoBuilder()
                        .setSequence(seqNum)
                        .setSequenceType(seqType)
                        .setSegment(segment)
                        .setResource(resource)
                        .build();

                // executable: operation info
                if (execSegment.getExec() != null) {
                    OperationInfo operationInfo = new OperationInfo(execSegment.getExec().getValue(), execSegment.getName(), execSegment.getClass(),
                            contextInfo);
                    result.add(operationInfo);
                }

                // dynamic parameter
                if (execSegment.getDynamicParameter() != null) {
                    for (DynamicParameterType dynamicParam : execSegment.getDynamicParameter()) {
                        contextProvider.getDynamicContext().appendParameter(
                                dynamicParam.getName(), dynamicParam.getValue(), contextInfo);
                    }
                }
            }
        }

        return result;
    }

}
