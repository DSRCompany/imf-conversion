package com.netflix.imfutility.conversion.templateParameter;

import com.netflix.imfutility.cpl.uuid.ResourceUUID;
import com.netflix.imfutility.cpl.uuid.SegmentUUID;
import com.netflix.imfutility.cpl.uuid.SequenceUUID;
import com.netflix.imfutility.xsd.conversion.SequenceType;

/**
 * A current state depending on the conversion operation type (sequence, segment, etc.).
 */
public class ContextInfo {

    public static final SegmentUUID DEFAULT_SEGMENT_UUID = null;
    public static final SequenceUUID DEFAULT_SEQUENCE_UUID = null;
    public static final SequenceType DEFAULT_SEQUENCE_TYPE = null;
    public static final ResourceUUID DEFAULT_RESOURCE_UUID = null;

    public static ContextInfo EMPTY = new ContextInfo(
            DEFAULT_SEGMENT_UUID, DEFAULT_SEQUENCE_UUID, DEFAULT_SEQUENCE_TYPE, DEFAULT_RESOURCE_UUID);

    private final SegmentUUID segmentUuid;
    private final SequenceUUID sequenceUuid;
    private final SequenceType sequenceType;
    private final ResourceUUID resourceUuid;

    public ContextInfo(SegmentUUID segmentUuid, SequenceUUID sequenceUuid, SequenceType sequenceType, ResourceUUID resourceUuid) {
        this.segmentUuid = segmentUuid;
        this.sequenceUuid = sequenceUuid;
        this.sequenceType = sequenceType;
        this.resourceUuid = resourceUuid;
    }

    public SegmentUUID getSegmentUuid() {
        return segmentUuid;
    }

    public SequenceUUID getSequenceUuid() {
        return sequenceUuid;
    }

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    public ResourceUUID getResourceUuid() {
        return resourceUuid;
    }
}
