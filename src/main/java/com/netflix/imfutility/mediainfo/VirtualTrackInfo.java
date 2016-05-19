package com.netflix.imfutility.mediainfo;

import com.netflix.imfutility.conversion.templateParameter.context.parameters.SequenceContextParameters;
import com.netflix.imfutility.xsd.conversion.SequenceType;
import com.netflix.imfutility.xsd.mediainfo.StreamType;

import java.util.HashMap;
import java.util.Map;

/**
 * A helper class to wrap required parameters (media info).
 */
public class VirtualTrackInfo {

    private SequenceType seqType;

    private Map<SequenceContextParameters, String> parameters = new HashMap<>();

    public VirtualTrackInfo(SequenceType seqType, StreamType stream) {
        this.seqType = seqType;

        // audio
        addParameter(SequenceContextParameters.CHANNELS_NUM, stream.getChannels());
        addParameter(SequenceContextParameters.SAMPLE_RATE, stream.getSampleRate());
        addParameter(SequenceContextParameters.BITS_PER_SAMPLE, stream.getBitsPerSample());

        // video
        addParameter(SequenceContextParameters.WIDTH, stream.getWidth());
        addParameter(SequenceContextParameters.HEIGHT, stream.getHeight());
        addParameter(SequenceContextParameters.BIT_DEPTH, stream.getBitsPerRawSample());
        addParameter(SequenceContextParameters.PIXEL_FORMAT, stream.getPixFmt());
        addParameter(SequenceContextParameters.FRAME_RATE, stream.getRFrameRate());
    }

    private void addParameter(SequenceContextParameters paramName, Object paramValue) {
        if (paramValue != null) {
            parameters.put(paramName, paramValue.toString());
        }
    }

    public SequenceType getSeqType() {
        return seqType;
    }

    public Map<SequenceContextParameters, String> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VirtualTrackInfo that = (VirtualTrackInfo) o;

        if (seqType != that.seqType) return false;
        return parameters != null ? parameters.equals(that.parameters) : that.parameters == null;

    }

    @Override
    public int hashCode() {
        int result = seqType != null ? seqType.hashCode() : 0;
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

}
