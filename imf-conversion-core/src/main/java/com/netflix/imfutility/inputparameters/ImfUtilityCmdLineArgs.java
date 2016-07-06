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
package com.netflix.imfutility.inputparameters;

import com.lexicalscope.jewel.cli.Option;

/**
 * Defines all command line parameters common for all formats. Some of the parameters are optional.
 */
public interface ImfUtilityCmdLineArgs {

    @Option(helpRequest = true, description = "display help", shortName = {"h"}, longName = {"help"})
    boolean getHelp();

    @Option(description = "a full path to the IMP folder", longName = {"imp"}, defaultToNull = true)
    String getImp();

    @Option(description = "a name of the CPL within the IMP folder", longName = {"cpl"}, defaultToNull = true)
    String getCpl();

    @Option(description = "a full path to a config.xml", shortName = {"c"}, longName = {"config"}, defaultToNull = true)
    String getConfig();

    @Option(description = "a working directory where conversion is performed and output flatten file(s) are placed",
            shortName = {"w"}, longName = {"working-dir"}, defaultToNull = true)
    String getWorkingDirectory();

}
