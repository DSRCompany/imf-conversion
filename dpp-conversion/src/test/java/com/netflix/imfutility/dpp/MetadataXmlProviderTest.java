package com.netflix.imfutility.dpp;

import com.netflix.imfutility.util.MetadataUtils;
import com.netflix.imfutility.util.TemplateParameterContextCreator;
import com.netflix.imfutility.xml.XmlParsingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * <ul>
 * <li>Tests the metadata.xml can be parsed and mapped to Java model successfully.</li>
 * <li>Tests the XSD validation is applied to the metadata.xml and an exception is thrown is validation doesn't pass.</li>
 * <li>Tests that bmx parameter files are created successfully.</li>
 * </ul>
 */
public class MetadataXmlProviderTest {

    private MetadataXmlProvider metadataProvider;

    @Before
    public void setUp() throws Exception {
        // reset the current metadata provider
        this.metadataProvider = null;
    }

    @After
    public void tearDown() throws Exception {
        // remove all tmp files created during tests
        if (metadataProvider != null) {
            metadataProvider.getBmxDppParameterFiles().forEach(File::delete);
        }
    }

    /**
     * Tests that test-metadata.xml is loaded correctly.
     *
     * @throws Exception
     */
    @Test
    public void parseCorrectMetadataXml() throws Exception {
        // load generated test-metadata.xml
        this.metadataProvider = new MetadataXmlProvider(MetadataUtils.getCorrectMetadataXml(), TemplateParameterContextCreator.getCurrentTmpDir());

        assertNotNull(metadataProvider.getDpp());
        assertNotNull(metadataProvider.getDpp().getEditorial());
        assertNotNull(metadataProvider.getDpp().getTechnical());
        assertNotNull(metadataProvider.getDpp().getTechnical().getAudio());
        assertNotNull(metadataProvider.getDpp().getTechnical().getVideo());
    }

    @Test(expected = XmlParsingException.class)
    public void testParseBrokenXml() throws Exception {
        this.metadataProvider = new MetadataXmlProvider(MetadataUtils.getBrokenXmlMetadataXml(), TemplateParameterContextCreator.getCurrentTmpDir());
    }

    @Test(expected = XmlParsingException.class)
    public void testParseInvalidXsd() throws Exception {
        this.metadataProvider = new MetadataXmlProvider(MetadataUtils.getInvalidXsdMetadataXml(), TemplateParameterContextCreator.getCurrentTmpDir());
    }

    @Test(expected = FileNotFoundException.class)
    public void testParseInvalidFilePath() throws Exception {
        this.metadataProvider = new MetadataXmlProvider(new File("invalid-path"), TemplateParameterContextCreator.getCurrentTmpDir());
    }

    /**
     * Tests test-metadata.xml transformation into a sef of parameter files for BMXLib tool.
     *
     * @throws Exception
     */
    @Test
    public void transformXmlToBmxParameters() throws Exception {
        // load generated test-metadata.xml
        this.metadataProvider = new MetadataXmlProvider(MetadataUtils.getCorrectMetadataXml(), TemplateParameterContextCreator.getCurrentTmpDir());

        // generate parameter files
        metadataProvider.createBmxDppParameterFiles();
        File ukdppFile = metadataProvider.getBmxDppParameterFile(MetadataXmlProvider.DMFramework.UKDPP);
        File as11coreFile = metadataProvider.getBmxDppParameterFile(MetadataXmlProvider.DMFramework.AS11CORE);
        File as11segmFile = metadataProvider.getBmxDppParameterFile(MetadataXmlProvider.DMFramework.AS11Segmentation);

        //check
        assertTrue("UKDPP parameters are not generated", ukdppFile.length() > 0);
        assertTrue("AS11 parameters are not generated", as11coreFile.length() > 0);
        assertTrue("SEG parameters are not generated", as11segmFile.length() > 0);
    }
}
