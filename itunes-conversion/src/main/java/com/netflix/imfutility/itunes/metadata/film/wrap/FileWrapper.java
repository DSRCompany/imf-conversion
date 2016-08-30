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
package com.netflix.imfutility.itunes.metadata.film.wrap;

import com.netflix.imfutility.generated.itunes.metadata.Checksum;
import com.netflix.imfutility.generated.itunes.metadata.File;
import com.netflix.imfutility.itunes.metadata.ElemWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * Wrapper for {@link File}.
 */
public class FileWrapper extends ElemWrapper<File> {

    private List<JAXBElement<?>> mixed;

    public FileWrapper(JAXBContext context, File inner) {
        super(context, inner);
        this.mixed = inner.getSizeOrFileNameOrChecksum();
    }

    public FileWrapper(JAXBContext context) {
        this(context, new File());
    }

    public void setFileName(String fileName) {
        mixed.add(objectFactory.createFileName(fileName));
    }

    public String getFileName() {
        return getElemValueByName(mixed, "file_name", String.class);
    }

    public void setSize(String size) {
        mixed.add(objectFactory.createSize(size));
    }

    public String getSize() {
        return getElemValueByName(mixed, "size", String.class);
    }

    public void setChecksum(Checksum checksum) {
        mixed.add(objectFactory.createChecksum(checksum));
    }

    public Checksum getChecksum() {
        return getElemValueByName(mixed, "checksum", Checksum.class);
    }
}
