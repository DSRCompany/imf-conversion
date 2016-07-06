/**
 * Copyright (C) 2016 Netflix, Inc.
 *
 *     This file is part of IMF Conversion Utility.
 *
 *     IMF Conversion Utility is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     IMF Conversion Utility is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with IMF Conversion Utility.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.netflix.imfutility.conversion.executor.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Information about all conversion operations to be executed in a pipe.
 * It contains a list of operations to be piped (tail operations), and a list of operations executed sequentially one by one (cycle operations).
 * Each of the cycle operations is piped to the tail operations.
 * <p>
 * Example:
 * <ul>
 * <li>cycleOperations: {{cycle11, cycle12, cycle13}, {cycle21, cycle22}},</li>
 * <li>pipeOperations: {pipe1, pipe2, pipe3}.</li>
 * <li>Execution order:
 * <ol>
 * <li>start pipe1, pipe2, pipe3;</li>
 * <li>start cycle11, cycle12, cycle13</li>
 * <li>create a pipeline:  cycle11 -> cycle12 -> cycle13 -> pipe1 -> pipe2 -> pipe3</li>
 * <li>wait until the first operation (cycle11) is finished</li>
 * <li>finish cycle 12 and cycle 13 (pipe1, pipe2 and pipe3 are still running)</li>
 * <li>start cycle21, cycle22</li>
 * <li>create a pipeline:  cycle21 -> cycle22 -> pipe1 -> pipe2 -> pipe3</li>
 * <li>wait until the first operation (cycle21) is finished</li>
 * <li>finish cycle 22</li>
 * <li>finish pipe1, pipe2 and pipe3</li>
 * </ol>
 * </li>
 * </ul>
 * </p>
 */
public class PipeOperationInfo {

    private final List<List<OperationInfo>> cycleOperations = new ArrayList<>();
    private final List<OperationInfo> tailOperations = new ArrayList<>();

    public List<List<OperationInfo>> getCycleOperations() {
        return cycleOperations;
    }

    public List<OperationInfo> getTailOperations() {
        return tailOperations;
    }

    public void addCycleOperation(List<OperationInfo> cycleHeadOperations) {
        cycleOperations.add(cycleHeadOperations);
    }

    public void addCycleOperation(OperationInfo cycleHeadOperation) {
        List<OperationInfo> cycleHeadOperations = new ArrayList<>();
        cycleHeadOperations.add(cycleHeadOperation);
        cycleOperations.add(cycleHeadOperations);
    }

    public void addTailOperation(OperationInfo tailOperation) {
        tailOperations.add(tailOperation);
    }

    public void addTailOperations(Collection<OperationInfo> operations) {
        this.tailOperations.addAll(operations);
    }


}
