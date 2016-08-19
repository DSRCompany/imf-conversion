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
package com.netflix.imfutility.dpp;

/**
 * CoreConstants related to DPP format.
 */
public final class DppConversionConstants {

/* 1. Conversion.xml */

    // 1.1. conversion.xml location
    public static final String CONVERSION_XML = "xml/dpp-conversion.xml";

    // 1.2 dynamic parameters:
    public static final String DYNAMIC_PARAM_PAN = "panParameter";
    public static final String DYNAMIC_PARAM_EBU_AUDIO_TRACKS = "ebuAudioTracks";
    public static final String DYNAMIC_PARAM_UK_DPP_FILE = "ukDppFramework";
    public static final String DYNAMIC_PARAM_AS11_CORE_FILE = "as11CoreFramework";
    public static final String DYNAMIC_PARAM_AS11_SEGM_FILE = "as11SegmentationFramework";
    public static final String DYNAMIC_AUDIO_MAP_XML = "audioMapXml";
    public static final String DYNAMIC_PARAM_OUTPUT_MXF = "output";
    public static final String DYNAMIC_PARAM_TTML_TO_STL = "ttml-to-stl";
    public static final String DYNAMIC_PARAM_METADATA_XML = "metadataXml";
    public static final String DYNAMIC_PARAM_EBU_LINEUP = "EBULineup";
    public static final String DYNAMIC_PARAM_SLATE = "slate";
    public static final String DYNAMIC_PARAM_LAST_ESSENCE = "last_essence";
    public static final String DYNAMIC_PARAM_LAST_FRAME_TC = "last_frame_tc";

    // 1.3 dynamic parameters default values:
    public static final String DYNAMIC_PARAM_VALUE_OUTPUT_MXF = "output";

/* 2. default values */

    public static final String DEFAULT_AUDIO_MAP = "audiomap.xml";

/* 3. XSLT */

    public static final String BMX_PARAMETERS_TRANSFORMATION = "xslt/bmx-parameters.xsl";
    public static final String XSLT2_TRANSFORMER_IMPLEMENTATION = "net.sf.saxon.TransformerFactoryImpl";
    public static final String BMX_FRAMEWORK_PARAM = "framework";


/* 4. config.xml */

    public static final String TTML_TO_STL_TOOL = "ttml-to-stl";

/* 5. layout */

    public static final String RESOURCE_EBU_LINEUP = "sample/Stereo_Programmes_1kHz_EBU_Lineup.wav";
    public static final String UNPACKED_EBU_LINEUP = "Stereo_Programmes_1kHz_EBU_Lineup.wav";
    public static final String RESOURCE_SLATE = "sample/slate.png";
    public static final String UNPACKED_SLATE = "slate.png";

    private DppConversionConstants() {

    }

}
