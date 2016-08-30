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
import com.netflix.imfutility.itunes.asset.bean.ChapterAsset;
import com.netflix.imfutility.itunes.chapters.builder.ChaptersXmlSampleBuilder;
import com.netflix.imfutility.itunes.image.ImageValidationException;
import com.netflix.imfutility.itunes.util.AssetUtils;
import com.netflix.imfutility.itunes.util.FakeMetadataXmlProvider;
import com.netflix.imfutility.itunes.util.TestUtils;
import com.netflix.imfutility.util.TemplateParameterContextCreator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests chapter asset processing.
 * (see {@link ChapterAssetProcessor}).
 */
public class ChapterAssetProcessorTest {

    private FakeMetadataXmlProvider metadataXmlProvider;

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
    public void setup() throws Exception {
        metadataXmlProvider = new FakeMetadataXmlProvider();
    }

    @Test
    public void testCorrectChapter() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider, TemplateParameterContextCreator.getWorkingDir());

        processor.setInputChapterItem(ChaptersXmlSampleBuilder.buildInputChapter())
                .setAspectRatio(new BigFraction(16).divide(9))
                .setChapterIndex(1)
                .process(AssetUtils.getTestCorrectChapterFile());

        File asset = new File(TemplateParameterContextCreator.getWorkingDir(), "chapter01.jpg");
        assertTrue(asset.exists());
        assertTrue(asset.isFile());

        ChapterAsset chapterAsset = metadataXmlProvider.getRootElement().getChapterAssets().get(0);
        assertNull(chapterAsset.getType());
        assertNull(chapterAsset.getRole());
        assertEquals("chapter01.jpg", chapterAsset.getFileName());
    }

    @Test(expected = ImageValidationException.class)
    public void testInvalidChapter() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider,
                TemplateParameterContextCreator.getWorkingDir());

        processor.setInputChapterItem(ChaptersXmlSampleBuilder.buildInputChapter())
                //  aspect ratio of image 16:9
                .setAspectRatio(new BigFraction(4).divide(3))
                .setChapterIndex(1)
                .process(AssetUtils.getTestCorrectChapterFile());
    }

    @Test(expected = AssetValidationException.class)
    public void testInvalidFile() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider,
                TemplateParameterContextCreator.getWorkingDir());

        processor.setInputChapterItem(ChaptersXmlSampleBuilder.buildInputChapter())
                .setAspectRatio(new BigFraction(16).divide(9))
                .setChapterIndex(1)
                .process(TestUtils.getTestFile());
    }

    @Test(expected = ConversionException.class)
    public void testInvalidPath() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider,
                TemplateParameterContextCreator.getWorkingDir());

        processor.setInputChapterItem(ChaptersXmlSampleBuilder.buildInputChapter())
                .setAspectRatio(new BigFraction(16).divide(9))
                .setChapterIndex(1)
                .process(new File("invalid_path"));
    }

    @Test(expected = AssetValidationException.class)
    public void testParametersNotSet() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider,
                TemplateParameterContextCreator.getWorkingDir());

        processor.process(AssetUtils.getTestCorrectChapterFile());
    }

    @Test(expected = AssetValidationException.class)
    public void testChapterIndexOutOfBound() throws Exception {
        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider,
                TemplateParameterContextCreator.getWorkingDir());

        processor.setInputChapterItem(ChaptersXmlSampleBuilder.buildInputChapter())
                .setAspectRatio(new BigFraction(16).divide(9))
                //  chapter index must be from 1 to 99
                .setChapterIndex(100)
                .process(AssetUtils.getTestCorrectChapterFile());
    }
}
