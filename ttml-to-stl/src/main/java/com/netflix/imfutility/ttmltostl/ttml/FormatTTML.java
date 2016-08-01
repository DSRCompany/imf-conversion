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
package com.netflix.imfutility.ttmltostl.ttml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This class represents W3C's TTML DFXP .XML subtitle format
 * <br><br>
 * Copyright (c) 2012 J. David Requejo <br>
 * j[dot]david[dot]requejo[at] Gmail
 * <br><br>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * <br><br>
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <br><br>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * @author J. David Requejo
 */
public class FormatTTML implements TimedTextFileFormat {

    private TimedTextObject tto = new TimedTextObject();
    private Integer parsedFileCount = 0;
    private int startMS = 0;
    private int endMS = 0;
    private int offsetMS = 0;
    private Document doc = null;

    public TimedTextObject parseFile(String fileName, InputStream is, int startMS, int endMS, int offsetMS) throws IOException, FatalParsingException {
        tto.fileName = fileName;
        this.startMS = startMS;
        this.endMS = endMS;
        this.offsetMS = offsetMS;
        this.doc = null;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            //we recover the metadata
            Node node = doc.getElementsByTagName("ttm:title").item(0);
            if (node != null) tto.title = node.getTextContent();
            node = doc.getElementsByTagName("ttm:copyright").item(0);
            if (node != null) tto.copyrigth = node.getTextContent();
            node = doc.getElementsByTagName("ttm:desc").item(0);
            if (node != null) tto.description = node.getTextContent();

            //we recover the styles
            NodeList styleN = doc.getElementsByTagName("style");
            //we recover the timed text elements
            NodeList captionsN = doc.getElementsByTagName("p");
            //regions of the layout could also be recovered this way

            tto.warnings += "Styling attributes are only recognized inside a style definition, to be referenced later in the captions.\n\n";
            //we parse the styles
            for (int i = 0; i < styleN.getLength(); i++) {
                Style style = new Style(Style.defaultID());
                node = styleN.item(i);
                NamedNodeMap attr = node.getAttributes();
                //we get the id
                Node currentAtr = attr.getNamedItem("id");
                if (currentAtr != null)
                    style.iD = currentAtr.getNodeValue();
                currentAtr = attr.getNamedItem("xml:id");
                if (currentAtr != null)
                    style.iD = currentAtr.getNodeValue();

                //Change style.iD to avoid a case when styles from several TTML have the same name.
                style.iD = getMutlyInputStyleId(style.iD);

                //we get the style it may be based upon
                currentAtr = attr.getNamedItem("style");
                if (currentAtr != null) {
                    //FIXME: there can be several style ID in row and we must process all sequencially
                    if (tto.styling.containsKey(getMutlyInputStyleId(currentAtr.getNodeValue())))
                        style = new Style(style.iD, tto.styling.get(currentAtr.getNodeValue()));
                }

                //we check for background color
                currentAtr = attr.getNamedItem("tts:backgroundColor");
                if (currentAtr != null)
                    style.backgroundColor = parseColor(currentAtr.getNodeValue());

                //we check for color
                currentAtr = attr.getNamedItem("tts:color");
                if (currentAtr != null)
                    style.color = parseColor(currentAtr.getNodeValue());

                //we check for font family
                currentAtr = attr.getNamedItem("tts:fontFamily");
                if (currentAtr != null)
                    style.font = currentAtr.getNodeValue();

                //we check for font size
                currentAtr = attr.getNamedItem("tts:fontSize");
                if (currentAtr != null)
                    style.fontSize = currentAtr.getNodeValue();

                //we check for italics
                currentAtr = attr.getNamedItem("tts:fontStyle");
                if (currentAtr != null)
                    if (currentAtr.getNodeValue().equalsIgnoreCase("italic") || currentAtr.getNodeValue().equalsIgnoreCase("oblique"))
                        style.italic = true;
                    else if (currentAtr.getNodeValue().equalsIgnoreCase("normal"))
                        style.italic = false;

                //we check for bold
                currentAtr = attr.getNamedItem("tts:fontWeight");
                if (currentAtr != null)
                    if (currentAtr.getNodeValue().equalsIgnoreCase("bold"))
                        style.bold = true;
                    else if (currentAtr.getNodeValue().equalsIgnoreCase("normal"))
                        style.bold = false;

                //we check opacity (to set the alpha)
                currentAtr = attr.getNamedItem("tts:opacity");
                if (currentAtr != null) {
                    try {
                        //a number between 1.0 and 0
                        float alpha = Float.parseFloat(currentAtr.getNodeValue());
                        if (alpha > 1)
                            alpha = 1;
                        else if (alpha < 0)
                            alpha = 0;

                        String aa = Integer.toHexString((int) (alpha * 255));
                        if (aa.length() < 2)
                            aa = "0" + aa;

                        style.color = style.color.substring(0, 6) + aa;
                        style.backgroundColor = style.backgroundColor.substring(0, 6) + aa;

                    } catch (NumberFormatException e) {
                        //ignore the alpha
                    }
                }

                //we check for text align
                currentAtr = attr.getNamedItem("tts:textAlign");
                if (currentAtr != null)
                    if (currentAtr.getNodeValue().equalsIgnoreCase("left") || currentAtr.getNodeValue().equalsIgnoreCase("start"))
                        style.textAlign = "bottom-left";
                    else if (currentAtr.getNodeValue().equalsIgnoreCase("right") || currentAtr.getNodeValue().equalsIgnoreCase("end"))
                        style.textAlign = "bottom-right";

                //we check for underline
                currentAtr = attr.getNamedItem("tts:textDecoration");
                if (currentAtr != null)
                    if (currentAtr.getNodeValue().equalsIgnoreCase("underline"))
                        style.underline = true;
                    else if (currentAtr.getNodeValue().equalsIgnoreCase("noUnderline"))
                        style.underline = false;

                //we add the style
                tto.styling.put(style.iD, style);
            }

            //we parse the captions
            for (int i = 0; i < captionsN.getLength(); i++) {

                Caption caption = fillCaptionAttributes(captionsN.item(i));

                //and save the caption
                if (caption != null) {
                    caption.content = caption.content.trim().replaceAll("[ \\t]+", " ");
                    int key = caption.start.mseconds;
                    //in case the key is already there, we increase it by a millisecond, since no duplicates are allowed
                    // FIXME: for regions it must be changed (region can intersect in start time)
                    while (tto.captions.containsKey(key)) key++;
                    tto.captions.put(key, caption);
                }
            }
        } catch (SAXException | ParserConfigurationException e) {
            throw new FatalParsingException("Error during parsing: " + e.getMessage());
        }

        //parsedFileCount++;

        tto.built = true;
        return tto;
    }

    private String traverseAttributeUp(Node node, String attrName) {
        NamedNodeMap attr = node.getAttributes();
        if (attr != null) {
            Node currentAtr = attr.getNamedItem(attrName);
            if (currentAtr != null) {
                String attrValue = currentAtr.getNodeValue().trim();
                if (attrValue.length() > 0) {
                    return attrValue;
                }
            }
        }

        if (node.getParentNode() != null) {
            return traverseAttributeUp(node.getParentNode(), attrName);
        }

        return null;
    }

    private Time traverseTimecode(Node node, String timecodeName) {
        String timecodeValue = traverseAttributeUp(node, timecodeName);

        if (timecodeValue != null) {
            Time nodeTime = new Time("", "");
            nodeTime.mseconds = parseTimeExpression(timecodeValue);
            return nodeTime;
        }

        return null;
    }


    private Caption fillCaptionAttributes(Node node) {

        //+define parentStartTime
        //+define parentEndTime
        //define parentStyles : it must be inherited from container also
        //define parentRegion : even body can have it, can be single only
        //origin and extent (x and y of view/region area) - omit for now.

        Caption caption = new Caption();
        caption.content = "";

        //get region
        caption.region = traverseAttributeUp(node, "region");

        caption.start = traverseTimecode(node, "begin");
        if (caption.start == null) {
            caption.start = new Time("", "");
        }

        caption.end = traverseTimecode(node, "end");
        if (caption.end == null) {
            caption.end = new Time("", "");
            Time durTime = traverseTimecode(node, "dur");
            if (durTime != null) {
                caption.end.mseconds = caption.start.mseconds + durTime.mseconds;
            } else {
                caption.end.mseconds = Integer.MAX_VALUE;
            }
        }

        //Lets filter captions and fit timecodes in accordance with startTC/endTC
        if (!fitAndCheckCaptionTimecodeRange(caption)) {
            //Caption must be filtered out.
            return null;
        }

        NamedNodeMap attr = node.getAttributes();
        if (attr != null) {
            //we get the style
            Node currentAtr = attr.getNamedItem("style");
            if (currentAtr != null) {
                String stylesStr[] = currentAtr.getNodeValue().split("\\s+");
                caption.styles = new Style[stylesStr.length];

                for (int i = 0; i < stylesStr.length; i++) {
                    Style style = tto.styling.get(getMutlyInputStyleId(stylesStr[i]));
                    if (style != null) {
                        // FIXME: AL: We should override style items for style later
                        caption.style = style; //set last style for container, later it should not be used. use styles.
                        caption.styles[i] = style;
                    } else {
                        //unrecognized style
                        tto.warnings += "unrecoginzed style referenced: " + currentAtr.getNodeValue() + "\n\n";
                    }
                }
            }
        }

        //we save the text
        NodeList textN = node.getChildNodes();
        caption.nodes = new Caption[textN.getLength()];
        if (textN.getLength() == 0) {
            if (node.getNodeName().equals("#text")) {
                //don't trim anything here. trim and remove multiple spaces at final string build.
                caption.content = node.getTextContent();
                //replace new lines to spaces.
                caption.content = caption.content.replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
            } else if (node.getNodeName().equals("br")) {
                caption.content = "\n";
            }
        } else {
            for (int j = 0; j < textN.getLength(); j++) {
                Node childNode = textN.item(j);
                caption.nodes[j] = fillCaptionAttributes(childNode);
            }
        }

        //Build final string for whole container.
        buildCaptionString(caption);
        return caption;
    }


    private void buildCaptionString(Caption caption) {
        if (caption.nodes.length > 0) {
            caption.content = "";
            for (Caption subCaption : caption.nodes) {
                buildCaptionString(subCaption);

                caption.content += subCaption.content;
            }
        }
    }

    private Boolean fitAndCheckCaptionTimecodeRange(Caption caption) {
        int startTCMilliseconds = startMS > 0 ? startMS : 0;
        int endTCMilliseconds = endMS > 0 ? endMS : Integer.MAX_VALUE;
        int offsetTCMilliseconds = offsetMS > 0 ? offsetMS : 0;

        if (startTCMilliseconds > caption.end.mseconds
                || endTCMilliseconds < caption.start.mseconds) {
            //caption time range is not in the required time range.
            return false;
        }

        caption.start.mseconds = Math.max(caption.start.mseconds, startTCMilliseconds);
        caption.end.mseconds = Math.min(caption.end.mseconds, endTCMilliseconds);

        caption.start.mseconds = offsetTCMilliseconds + caption.start.mseconds - startTCMilliseconds;
        caption.end.mseconds = offsetTCMilliseconds + caption.end.mseconds - startTCMilliseconds;

        return true;
    }

    private String getMutlyInputStyleId(String iD) {
        return parsedFileCount > 0 ? iD + "-#mlt-ttml-sc-" + parsedFileCount.toString() : iD;
    }

    public String[] toFile(TimedTextObject tto) {

        //first we check if the TimedTextObject had been built, otherwise...
        if (!tto.built)
            return null;

        //we will write the lines in an ArrayList
        int index = 0;
        //the minimum size of the file is the number of captions and styles + lines for sections and formats and the metadata, so we'll take some extra space.
        ArrayList<String> file = new ArrayList<>(30 + tto.styling.size() + tto.captions.size());

        //identification line is placed
        file.add(index++, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        //root element is placed
        file.add(index++, "<tt xml:lang=\"" + tto.language + "\" xmlns=\"http://www.w3.org/ns/ttml\" xmlns:tts=\"http://www.w3.org/ns/ttml#styling\">");
        //head
        file.add(index++, "\t<head>");
        //metadata
        file.add(index++, "\t\t<metadata xmlns:ttm=\"http://www.w3.org/ns/ttml#metadata\">");
        //title
        String title;
        if (tto.title == null || tto.title.isEmpty())
            title = tto.fileName;
        else title = tto.title;
        file.add(index++, "\t\t\t<ttm:title>" + title + "</ttm:title>");
        //Copyright
        if (tto.copyrigth != null && !tto.copyrigth.isEmpty())
            file.add(index++, "\t\t\t<ttm:copyright>" + tto.copyrigth + "</ttm:copyright>");
        //additional info
        String desc = "Converted by the Online Subtitle Converter developed by J. David Requejo\n";
        if (tto.author != null && !tto.author.isEmpty())
            desc += "\n Original file by: " + tto.author + "\n";
        if (tto.description != null && !tto.description.isEmpty())
            desc += tto.description + "\n";
        file.add(index++, "\t\t\t<ttm:desc>" + desc + "\t\t\t</ttm:desc>");

        //metadata closes
        file.add(index++, "\t\t</metadata>");
        //styling opens
        file.add(index++, "\t\t<styling>");

        String line;
        //Next we iterate over the styles
        Iterator<Style> itrS = tto.styling.values().iterator();
        while (itrS.hasNext()) {
            Style style = itrS.next();
            //we add the attributes
            line = "\t\t\t<style xml:id=\"" + style.iD + "\"";
            if (style.color != null)
                line += " tts:color=\"#" + style.color + "\"";
            if (style.backgroundColor != null)
                line += " tts:backgroundColor=\"#" + style.backgroundColor + "\"";
            if (style.font != null)
                line += " tts:fontFamily=\"" + style.font + "\"";
            if (style.fontSize != null)
                line += " tts:fontSize=\"" + style.fontSize + "\"";
            if (style.italic)
                line += " tts:fontStyle=\"italic\"";
            if (style.bold)
                line += " tts:fontWeight=\"bold\"";
            line += " tts:textAlign=\"";
            if (style.textAlign.contains("left"))
                line += "left\"";
            else if (style.textAlign.contains("right"))
                line += "rigth\"";
            else line += "center\"";
            if (style.underline)
                line += " tts:textDecoration=\"underline\"";
            //style is ready, we close it.
            line += " />";
            //we insert it
            file.add(index++, line);
        }

        //styling closes
        file.add(index++, "\t\t</styling>");

        //head closes
        file.add(index++, "\t</head>");
        //body opens
        file.add(index++, "\t<body>");
        //unique div opens
        file.add(index++, "\t\t<div>");

        //Next we iterate over the captions
        Iterator<Caption> itrC = tto.captions.values().iterator();
        while (itrC.hasNext()) {
            Caption caption = itrC.next();
            String content = caption.content.replace("\n", "<br/>");
            //we open the subtitle line
            line = "\t\t\t<p begin=\"" + caption.start.getTime("hh:mm:ss,ms").replace(',', '.') + "\"";
            line += " end=\"" + caption.end.getTime("hh:mm:ss,ms").replace(',', '.') + "\"";
            if (caption.style != null)
                line += " style=\"" + caption.style.iD + "\"";
            //attributes are done being inserted, if region was implemented it should be added before this.
            line += " >" + content + "</p>\n";
            //we write the line
            file.add(index++, line);
        }

        //unique div closes
        file.add(index++, "\t\t</div>");
        //body closes
        file.add(index++, "\t</body>");
        //root closes
        file.add(index++, "</tt>");

        //an empty line is added
        file.add(index++, "");

        String[] toReturn = new String[file.size()];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = file.get(i);
        }
        return toReturn;
    }


	/* PRIVATE METHODS */

    /**
     * Identifies the color expression and obtains the RGBA equivalent value.
     *
     * @param color
     * @return
     */
    private String parseColor(String color) {
        String value = "";
        String[] values;
        if (color.startsWith("#")) {
            if (color.length() == 7) {
                value = color.substring(1) + "ff";
            } else if (color.length() == 9) {
                value = color.substring(1);
            } else {
                //unrecognized format
                value = "ffffffff";
                tto.warnings += "Unrecoginzed format: " + color + "\n\n";
            }

        } else if (color.startsWith("rgb")) {
            boolean alpha = false;
            if (color.startsWith("rgba"))
                alpha = true;
            try {
                values = color.split("[(]")[1].split("[,]");
                int r, g, b, a = 255;
                r = Integer.parseInt(values[0]);
                g = Integer.parseInt(values[1]);
                b = Integer.parseInt(values[2].substring(0, 2));
                if (alpha) a = Integer.parseInt(values[3].substring(0, 2));

                values[0] = Integer.toHexString(r);
                values[1] = Integer.toHexString(g);
                values[2] = Integer.toHexString(b);
                if (alpha) values[2] = Integer.toHexString(a);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < values.length; i++) {
                    if (values[i].length() < 2)
                        values[i] = "0" + values[i];
                    sb.append(values[i]);
                }

                if (!alpha) {
                    sb.append("ff");
                }
                value = sb.toString();

            } catch (Exception e) {
                value = "ffffffff";
                tto.warnings += "Unrecoginzed color: " + color + "\n\n";
            }

        } else {
            //it should be a named color so...
            value = Style.getRGBValue("name", color);
            //if not recognized named color
            if (value == null || value.isEmpty()) {
                value = "ffffffff";
                tto.warnings += "Unrecoginzed color: " + color + "\n\n";
            }
        }

        return value;
    }


    /**
     * returns the number of milliseconds equivalent to this time expression
     *
     * @param timeExpression
     * @return
     */
    private int parseTimeExpression(String timeExpression) {
        int mSeconds = 0;
        if (timeExpression.contains(":")) {
            //it is a clock time
            String[] parts = timeExpression.split(":");
            if (parts.length == 3) {
                //we have h:m:s.fraction
                int h, m;
                float s;
                h = Integer.parseInt(parts[0]);
                m = Integer.parseInt(parts[1]);
                s = Float.parseFloat(parts[2]);
                mSeconds = h * 3600000 + m * 60000 + (int) (s * 1000);
            } else if (parts.length == 4) {
                //we have h:m:s:f.fraction
                int h, m, s;
                float f;
                int frameRate = 25;
                //we recover the frame rate
                Node n = doc.getDocumentElement().getAttributes().getNamedItem("ttp:frameRate");
                if (n == null) {
                    n = doc.getElementsByTagName("ttp:frameRate").item(0);
                }
                if (n != null) {
                    //used as auxiliary string
                    String aux = n.getNodeValue();
                    try {
                        frameRate = Integer.parseInt(aux);
                    } catch (NumberFormatException e) {
                        //should not happen, but if it does, use default value...
                    }
                }
                h = Integer.parseInt(parts[0]);
                m = Integer.parseInt(parts[1]);
                s = Integer.parseInt(parts[2]);
                f = Float.parseFloat(parts[3]);
                mSeconds = h * 3600000 + m * 60000 + s * 1000 + (int) (f * 1000 / frameRate);
            } else {
                //unrecognized  clock time format
            }

        } else {
            //it is an offset - time, this is composed of a number and a metric
            String metric = timeExpression.substring(timeExpression.length() - 1);
            timeExpression = timeExpression.substring(0, timeExpression.length() - 1).replace(',', '.').trim();
            double time;
            try {
                time = Double.parseDouble(timeExpression);
                if (metric.equalsIgnoreCase("h"))
                    mSeconds = (int) (time * 3600000);

                else if (metric.equalsIgnoreCase("m"))
                    mSeconds = (int) (time * 60000);

                else if (metric.equalsIgnoreCase("s"))
                    mSeconds = (int) (time * 1000);

                else if (metric.equalsIgnoreCase("ms"))
                    mSeconds = (int) time;

                else if (metric.equalsIgnoreCase("f")) {
                    int frameRate;
                    //we recover the frame rate
                    Node n = doc.getElementsByTagName("ttp:frameRate").item(0);
                    if (n != null) {
                        //used as auxiliary string
                        String s = n.getNodeValue();
                        frameRate = Integer.parseInt(s);
                        mSeconds = (int) (time * 1000 / frameRate);
                    }

                } else if (metric.equalsIgnoreCase("t")) {
                    int tickRate;
                    //we recover the tick rate
                    Node n = doc.getElementsByTagName("ttp:tickRate").item(0);
                    if (n != null) {
                        //used as auxiliary string
                        String s = n.getNodeValue();
                        tickRate = Integer.parseInt(s);
                        mSeconds = (int) (time * 1000 / tickRate);
                    }


                } else {
                    //invalid metric

                }
            } catch (NumberFormatException e) {
                //incorrect format for offset time
            }
        }

        return mSeconds;
    }

}
