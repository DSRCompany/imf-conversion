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
package com.netflix.imfutility.itunes;

import com.netflix.imfutility.AbstractFormatBuilder;
import com.netflix.imfutility.ConversionException;
import com.netflix.imfutility.conversion.ConversionEngine;
import com.netflix.imfutility.conversion.templateParameter.context.DestTemplateParameterContext;
import com.netflix.imfutility.conversion.templateParameter.context.DynamicTemplateParameterContext;
import com.netflix.imfutility.generated.itunes.metadata.ChapterInputType;
import com.netflix.imfutility.generated.mediainfo.FfprobeType;
import com.netflix.imfutility.itunes.asset.ChapterAssetProcessor;
import com.netflix.imfutility.itunes.asset.PosterAssetProcessor;
import com.netflix.imfutility.itunes.asset.TrailerAssetProcessor;
import com.netflix.imfutility.itunes.destcontext.DestContextResolveStrategy;
import com.netflix.imfutility.itunes.destcontext.InputDestContextResolveStrategy;
import com.netflix.imfutility.itunes.destcontext.NameDestContextResolveStrategy;
import com.netflix.imfutility.itunes.inputparameters.ITunesInputParameters;
import com.netflix.imfutility.itunes.inputparameters.ITunesInputParametersValidator;
import com.netflix.imfutility.itunes.mediainfo.SimpleMediaInfoBuilder;
import com.netflix.imfutility.itunes.xmlprovider.ChaptersXmlProvider;
import com.netflix.imfutility.itunes.xmlprovider.MetadataXmlProvider;
import com.netflix.imfutility.mediainfo.MediaInfoException;
import com.netflix.imfutility.util.ConversionHelper;
import com.netflix.imfutility.xml.XmlParsingException;
import com.netflix.imfutility.xsd.conversion.DestContextTypeMap;
import com.netflix.imfutility.xsd.conversion.DestContextsTypeMap;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.math3.fraction.BigFraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.netflix.imfutility.conversion.templateParameter.context.parameters.DestContextParameters.ASPECT_RATIO;
import static com.netflix.imfutility.itunes.ITunesConversionConstants.DYNAMIC_PARAM_OUTPUT_ITMSP;
import static com.netflix.imfutility.itunes.ITunesConversionConstants.DYNAMIC_PARAM_TRAILER_MEDIAINFO_INPUT;
import static com.netflix.imfutility.itunes.ITunesConversionConstants.DYNAMIC_PARAM_TRAILER_MEDIAINFO_OUTPUT;
import static com.netflix.imfutility.itunes.ITunesConversionConstants.DYNAMIC_PARAM_VENDOR_ID;

/**
 * iTunes format builder (see {@link AbstractFormatBuilder}). It's used for conversion to iTunes format ('convert' iTunes mode).
 */
public class ITunesFormatBuilder extends AbstractFormatBuilder {

    private final Logger logger = LoggerFactory.getLogger(ITunesFormatBuilder.class);

    private final ITunesInputParameters iTunesInputParameters;
    private File itmspDir;
    private MetadataXmlProvider metadataXmlProvider;
    private ChaptersXmlProvider chaptersXmlProvider;

    public ITunesFormatBuilder(ITunesInputParameters inputParameters) {
        super(new ITunesFormat(), inputParameters);
        this.iTunesInputParameters = inputParameters;
    }

    @Override
    protected void doValidateCmdLineArguments() {
        ITunesInputParametersValidator.validateCmdLineArguments(iTunesInputParameters);
    }

    @Override
    protected void doBuildDynamicContextPreCpl() {
        DynamicTemplateParameterContext dynamicContext = contextProvider.getDynamicContext();
        // fill vendorId parameter
        String vendorId = iTunesInputParameters.getCmdLineArgs().getVendorId();
        dynamicContext.addParameter(DYNAMIC_PARAM_VENDOR_ID, vendorId);
        // fill output parameter to [vendor-id].itmsp
        String itmspName = vendorId + ".itmsp";
        dynamicContext.addParameter(DYNAMIC_PARAM_OUTPUT_ITMSP, itmspName, false);
        setOSParameters();
    }

    @Override
    protected void doBuildDynamicContextPostCpl() throws IOException, XmlParsingException {
    }

    @Override
    protected void preConvert() throws IOException, XmlParsingException {
        // 1. creating [vendor-id].itmsp output directory
        createItmspDir();

        // 2. load, parse and validate metadata.xml
        loadMetadata();

        // 3. process additional assets (poster, chapters, trailer)
        processAdditionalAssets();
    }

    @Override
    protected void postConvert() throws IOException, XmlParsingException {
    }

    @Override
    protected String getConversionConfiguration() {
        return conversionProvider.getConvertConfiguration().get(0);
    }

    @Override
    protected DestContextTypeMap getDestContextMap(DestContextsTypeMap destContexts) {
        String format = iTunesInputParameters.getCmdLineArgs().getFormat();

        DestContextResolveStrategy resolveStrategy = format != null
                ? new NameDestContextResolveStrategy(format)
                : new InputDestContextResolveStrategy(contextProvider);
        return resolveStrategy.resolveContext(destContexts);
    }

    private void createItmspDir() {
        DynamicTemplateParameterContext dynamicContext = contextProvider.getDynamicContext();
        String itmspName = dynamicContext.getParameterValueAsString(DYNAMIC_PARAM_OUTPUT_ITMSP);

        logger.info("Creating {} output directory...", itmspName);

        itmspDir = new File(contextProvider.getWorkingDir(), itmspName);
        logger.info("Itmsp output directory: {}", itmspDir);
        if (!itmspDir.mkdir()) {
            throw new ConversionException(String.format(
                    "Couldn't create %s output directory!", itmspName));
        }

        logger.info("Created {} output directory: OK\n", itmspName);
    }

    private void setOSParameters() {
        DynamicTemplateParameterContext dynamicContext = contextProvider.getDynamicContext();
        dynamicContext.addParameter("isOSX", Boolean.toString(SystemUtils.IS_OS_MAC_OSX));
    }

    private void loadMetadata() throws IOException, XmlParsingException {
        File metadataFile = iTunesInputParameters.getMetadataFile();

        metadataXmlProvider = metadataFile == null
                ? new MetadataXmlProvider(inputParameters.getWorkingDirFile(), MetadataXmlProvider.generateSampleMetadata())
                : new MetadataXmlProvider(inputParameters.getWorkingDirFile(), metadataFile);
    }

    private void processAdditionalAssets() throws XmlParsingException, IOException {
        processPoster();
        processChapters();
        processTrailer();
    }

    private void processPoster() throws IOException {
        File poster = iTunesInputParameters.getPosterFile();
        if (poster == null) {
            return;
        }

        new PosterAssetProcessor(metadataXmlProvider, itmspDir)
                .setVendorId(iTunesInputParameters.getCmdLineArgs().getVendorId())
                .process(poster);
    }

    //  TODO: move implementation to provider
    private void processChapters() throws XmlParsingException, IOException {
        File chaptersFile = iTunesInputParameters.getChaptersFile();

        if (chaptersFile == null) {
            return;
        }

        chaptersXmlProvider = new ChaptersXmlProvider(chaptersFile);

        metadataXmlProvider.appendChaptersTimeCode(chaptersXmlProvider.getChapters().getTimecodeFormat());

        ChapterAssetProcessor processor = new ChapterAssetProcessor(metadataXmlProvider, itmspDir)
                .setAspectRatio(getDestAspectRatio());

        int i = 1;
        for (ChapterInputType chapter : chaptersXmlProvider.getChapters().getChapter()) {
            processor.setInputChapter(chapter)
                    .setChapterIndex(i)
                    .process(chaptersXmlProvider.getChapterFile(chapter));
            i++;
        }
    }

    private void processTrailer() throws XmlParsingException, IOException {
        File trailer = iTunesInputParameters.getTrailerFile();
        if (trailer == null) {
            return;
        }

        new TrailerAssetProcessor(metadataXmlProvider, itmspDir)
                .setVendorId(iTunesInputParameters.getCmdLineArgs().getVendorId())
                .setFormat(getTrailerMediaInfo(trailer).getFormat())
                .setLocale(metadataXmlProvider.getDefaultLocale())
                .process(trailer);
    }

    private FfprobeType getTrailerMediaInfo(File trailer) throws XmlParsingException, IOException {
        try {
            return new SimpleMediaInfoBuilder(contextProvider, new ConversionEngine().getExecuteStrategyFactory())
                    .setCommandName("trailer")
                    .setInputDynamicParam(DYNAMIC_PARAM_TRAILER_MEDIAINFO_INPUT)
                    .setOutputDynamycParam(DYNAMIC_PARAM_TRAILER_MEDIAINFO_OUTPUT)
                    .build(trailer);
        } catch (MediaInfoException e) {
            throw new ConversionException("Conversion aborted cause of MediaInfo failures", e);
        }
    }

    private BigFraction getDestAspectRatio() {
        DestTemplateParameterContext destContext = contextProvider.getDestContext();
        return ConversionHelper.parseAspectRatio(destContext.getParameterValue(ASPECT_RATIO));
    }

}
