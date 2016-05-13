package com.netflix.imfutility.conversion.templateParameter.context;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * All supported segment template parameter names.
 */
public enum SegmentContextParameters {

    NUM("num");

    private final String name;

    SegmentContextParameters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SegmentContextParameters fromName(String name) {
        for (SegmentContextParameters e : values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static String getSupportedContextParameters() {
        return Arrays.stream(SegmentContextParameters.values())
                .map(SegmentContextParameters::getName)
                .collect(Collectors.joining(" ", "[", "]"));
    }

}
