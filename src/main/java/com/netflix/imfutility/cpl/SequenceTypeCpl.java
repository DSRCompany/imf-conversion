package com.netflix.imfutility.cpl;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Alexander on 5/15/2016.
 */
public enum SequenceTypeCpl {

    AUDIO("MainAudioSequence"),

    IMAGE("MainImageSequence"),

    SUBTITLE("SubtitlesSequence");

    private final String name;

    SequenceTypeCpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SequenceTypeCpl fromName(String name) {
        for (SequenceTypeCpl e : values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static String getSupportedTypes() {
        return Arrays.stream(SequenceTypeCpl.values())
                .map(SequenceTypeCpl::getName)
                .collect(Collectors.joining(" ", "[", "]"));
    }
}
