package com.netflix.imfutility;

/**
 * All constants to be used in the application.
 */
public final class Constants {

/* 1. logging */

    public static final String LOGS_DIR = "logs";
    public static final String LOG_TEMPLATE = "%d-%s-%s-%s.log";

/* 2. IMF */

    public static final String ASSETMAP_FILE = "ASSETMAP.xml";

/* 3. XSD */

    // 3.1: assetmap.xml
    public static final String XSD_ASSETMAP_XSD = "xsd/imf/assetmap/asset-map.xsd";
    public static final String ASSETMAP_PACKAGE = "com.netflix.imfutility.imf.assetmap";

    // 3.2: config.xml
    public static final String CONFIG_XSD = "xsd/config.xsd";
    public static final String CONFIG_PACKAGE = "com.netflix.imfutility.config";

    // 3.3: conversion.xml
    public static final String CONVERSION_XSD = "xsd/conversion.xsd";
    public static final String CONVERSION_PACKAGE = "com.netflix.imfutility.conversion";

    // 3.4 media-info.xml
    public static final String MEDIAINFO_XSD = "xsd/media-info.xsd";
    public static final String MEDIAINFO_PACKAGE = "com.netflix.imfutility.mediainfo";

    // 3.5 IMF 2013 cpl.xml
    public static final String XSD_CPL_2013_XSD = "xsd/imf/2013/imf-cpl.xsd";
    public static final String CPL_2013_PACKAGE = "com.netflix.imfutility.imf._2013";

/* 4. Command line args*/

    public static final boolean DEFAULT_DELETE_TMP_FILES_ON_EXIT = true;
    public static final boolean DEFAULT_DELETE_TMP_FILES_ON_FAIL = false;
    public static final boolean DEFAULT_CLEAN_WORKING_DIR = true;

/* 5. Default values */

    public static final String DEFAULT_OUTPUT_VALIDATION_FILE = "errors.xml";
    public static final String VALIDATION_OUTPUT_XML_ERROR_TAG = "error";
    public static final String DEFAULT_CONVERSION_XML = "xml/conversion.xml";
    public static final String MEDIA_INFO_SUFFIX = "mediaInfo";


    private Constants() {

    }
}
