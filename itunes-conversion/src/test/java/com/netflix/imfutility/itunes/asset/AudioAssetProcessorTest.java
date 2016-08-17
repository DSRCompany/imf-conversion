/*
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
package com.netflix.imfutility.itunes.asset;

import com.netflix.imfutility.ConversionException;
import com.netflix.imfutility.generated.itunes.metadata.AssetType;
import com.netflix.imfutility.generated.itunes.metadata.AssetTypeType;
import com.netflix.imfutility.generated.itunes.metadata.DataFileRoleType;
import com.netflix.imfutility.generated.itunes.metadata.DataFileType;
import com.netflix.imfutility.itunes.util.AssetUtils;
import com.netflix.imfutility.itunes.xmlprovider.MetadataXmlProvider;
import com.netflix.imfutility.util.TemplateParameterContextCreator;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Tests additional audio source asset processing.
 * (see {@link AudioAssetProcessor}).
 */
public class AudioAssetProcessorTest {

    private MetadataXmlProvider metadataXmlProvider;
    private File destDir;
    private File inputAsset;

    @BeforeClass
    public static void setupAll() throws IOException {
        // create both working directory and logs folder.
        FileUtils.deleteDirectory(TemplateParameterContextCreator.getWorkingDir());
        File workingDir = TemplateParameterContextCreator.getWorkingDir();
        if (!workingDir.mkdir()) {
            throw new RuntimeException("Could not create a working dir within tmp folder");
        }
    }

    @AfterClass
    public static void teardownAll() throws IOException {
        FileUtils.deleteDirectory(TemplateParameterContextCreator.getWorkingDir());
    }

    @Before
    public void setup() throws IOException {
        destDir = AssetUtils.createDirectory(TemplateParameterContextCreator.getWorkingDir(), "destDir");
        inputAsset = AssetUtils.createFile(TemplateParameterContextCreator.getWorkingDir(), "audio");

        metadataXmlProvider = AssetUtils.createMetadataXmlProvider();
    }

    @After
    public void teardown() throws IOException {
        FileUtils.deleteDirectory(destDir);
        if (inputAsset.exists()) {
            FileUtils.forceDelete(inputAsset);
        }
    }

    @Test
    public void testCorrectAudio() throws Exception {
        AudioAssetProcessor processor = new AudioAssetProcessor(metadataXmlProvider, destDir);

        processor.setLocale(AssetUtils.createLocale("en-US"))
                .process(inputAsset);

        // input asset must be moved to dest dir
        assertFalse(inputAsset.exists());

        File outputAsset = new File(destDir, "audio");
        assertTrue(outputAsset.exists());
        assertTrue(outputAsset.isFile());


        AssetType audioAsset = metadataXmlProvider.getPackageType().getVideo().getAssets().getAsset().get(0);
        assertEquals(AssetTypeType.FULL, audioAsset.getType());

        DataFileType audioDataFile = audioAsset.getDataFile().get(0);
        assertEquals("audio", audioDataFile.getFileName());
        assertEquals(DataFileRoleType.AUDIO, audioDataFile.getRole());
        assertEquals("en-US", audioDataFile.getLocale().getName());
    }

    @Test(expected = ConversionException.class)
    public void testInvalidPath() throws Exception {
        AudioAssetProcessor processor = new AudioAssetProcessor(metadataXmlProvider, destDir);

        processor.setLocale(AssetUtils.createLocale("en-US"))
                .process(new File("invalid_path"));
    }

    @Test(expected = AssetValidationException.class)
    public void testParametersNotSet() throws Exception {
        AudioAssetProcessor processor = new AudioAssetProcessor(metadataXmlProvider, destDir);

        //  locale is required
        processor.process(inputAsset);
    }
}
