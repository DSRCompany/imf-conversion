package com.netflix.imfutility;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.HelpRequestedException;
import com.netflix.imfutility.dpp.DppFormatProcessor;

/**
 * The main class.
 * <ul>
 * <li>Invokes command line parsing logic</li>
 * <li>Invokes an appropriate processor depending on the input format and mode</li>
 * </ul>
 */
public class ImfUtility {

    public static void main(String... args) {
        try {
            ImfUtilityAllCmdLineArgs imfUtilityInputParameters = CliFactory.parseArguments(ImfUtilityAllCmdLineArgs.class, args);

            switch (imfUtilityInputParameters.getFormat()) {
                case dpp:
                    int exitCode = new DppFormatProcessor().process(args);
                    System.exit(exitCode);
                    break;
                default:
                    throw new ConversionException("Unsupported format " + imfUtilityInputParameters.getFormat());
            }

        } catch (HelpRequestedException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

}
