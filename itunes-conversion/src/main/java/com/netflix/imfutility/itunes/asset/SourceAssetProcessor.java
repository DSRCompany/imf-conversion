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

import com.netflix.imfutility.itunes.asset.bean.AssetRole;
import com.netflix.imfutility.itunes.asset.bean.AssetType;
import com.netflix.imfutility.itunes.asset.bean.VideoAsset;
import com.netflix.imfutility.itunes.asset.builder.VideoAssetBuilder;
import com.netflix.imfutility.itunes.metadata.MetadataXmlProvider;

import java.io.File;
import java.util.Locale;

/**
 * Asset processor specified for main source managing.
 */
public class SourceAssetProcessor extends AssetProcessor<VideoAsset> {

    private Locale locale;

    public SourceAssetProcessor(MetadataXmlProvider<?> metadataXmlProvider, File destDir) {
        super(metadataXmlProvider, destDir);
    }

    public SourceAssetProcessor setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    @Override
    protected boolean checkMandatoryParams() {
        return locale != null;
    }

    @Override
    protected void validate(File assetFile) throws AssetValidationException {
        // already validated
    }

    @Override
    protected VideoAsset buildAsset(File assetFile) {
        return new VideoAssetBuilder(assetFile, getDestFileName(assetFile))
                .setCropToZero(true)
                .setType(AssetType.FULL)
                .setRole(AssetRole.SOURCE)
                .setLocale(locale)
                .build();
    }

    @Override
    protected String getDestFileName(File assetFile) {
        return assetFile.getName();
    }
}
