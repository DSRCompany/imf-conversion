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
package com.netflix.imfutility.itunes.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Test utility for chapters.xml.
 */
public final class ChaptersUtils {

    private ChaptersUtils() {
    }

    public static File getCorrectChaptersXml() throws URISyntaxException {
        return new File(ClassLoader.getSystemClassLoader().getResource("xml/chapters/test-chapters.xml").toURI());
    }

    public static File getDiffLocalesChaptersXml() throws URISyntaxException {
        return new File(ClassLoader.getSystemClassLoader().getResource("xml/chapters/test-chapters-diff-locales.xml").toURI());
    }

    public static File getIdentFilesChaptersXml() throws URISyntaxException {
        return new File(ClassLoader.getSystemClassLoader().getResource("xml/chapters/test-chapters-ident-files.xml").toURI());
    }

    public static File getInvalidChaptersXml() throws URISyntaxException {
        return new File(ClassLoader.getSystemClassLoader().getResource("xml/chapters/invalid/invalid-test-chapters.xml").toURI());
    }

    public static File getBrokenChaptersXml() throws URISyntaxException {
        return new File(ClassLoader.getSystemClassLoader().getResource("xml/chapters/broken/broken-test-chapters.xml").toURI());
    }

    public static void createChapterFile(int chapterIndex) throws IOException {
        File file = new File(String.format("chapter%02d.jpg", chapterIndex));
        if (!file.createNewFile()) {
            throw new IOException(String.format("Cannot create test chapter file %s", file.getAbsolutePath()));
        }

        try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outStream.write(chapterIndex);
        }
        file.deleteOnExit();
    }
}
