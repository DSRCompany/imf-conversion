package stl;

import ttml.Caption;
import ttml.Style;
import ttml.TimedTextObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 6/24/2016.
 */
public class DefaultTtiStrategy implements ITtiStrategy {

    private List<Caption> captions;

    @Override
    public byte[] build(TimedTextObject tto) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        this.captions = new ArrayList<>(tto.captions.values());

        int sn = 0;
        for (int captionNum = 0; captionNum < captions.size(); captionNum++) {
            Caption caption = captions.get(captionNum);

            // 1. split to lines
            String[] lines = splitAndCleanText(caption);

            // 2. apply styles
            byte[] text = applyStyles(caption, lines);

            // 3. split to extension blocks
            byte[][] extensionBlocks = splitToExtensionBlocks(text);

            // 4. create a subtitle object
            StlSubtitle stlSubtitle = new StlSubtitle(captions, caption, captionNum, lines.length, extensionBlocks);

            // 5. create a TTI block for each EB
            for (int ebn = 0; ebn < extensionBlocks.length; ebn++) {
                byte[] ttiBlock = doBuildTtiBlock(stlSubtitle, sn, ebn);
                result.write(ttiBlock);
                sn++;
            }
        }

        return result.toByteArray();
    }

    private String[] splitAndCleanText(Caption caption) throws IOException {
        ByteArrayOutputStream allText = new ByteArrayOutputStream();

        String[] lines = caption.content.split("<br />");
        //we clean XML, span would be implemented here
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("\\<.*?\\>", "");
        }

        return lines;
    }

    private byte[] applyStyles(Caption caption, String[] lines) throws IOException {
        ByteArrayOutputStream allText = new ByteArrayOutputStream();

        if (caption.style != null) {
            Style style = caption.style;
            if (style.italic) {
                allText.write((byte) 0x80);
            } else {
                allText.write((byte) 0x81);
            }
            if (style.underline) {
                allText.write((byte) 0x82);
            } else {
                allText.write((byte) 0x83);
            }

            //colors
            String color = style.color.substring(0, 6);
            if (color.equalsIgnoreCase("000000")) {
                allText.write((byte) 0x00);
            } else if (color.equalsIgnoreCase("0000ff")) {
                allText.write((byte) 0x04);
            } else if (color.equalsIgnoreCase("00ffff")) {
                allText.write((byte) 0x06);
            } else if (color.equalsIgnoreCase("00ff00")) {
                allText.write((byte) 0x02);
            } else if (color.equalsIgnoreCase("ff0000")) {
                allText.write((byte) 0x01);
            } else if (color.equalsIgnoreCase("ffff00")) {
                allText.write((byte) 0x03);
            } else if (color.equalsIgnoreCase("ff00ff")) {
                allText.write((byte) 0x05);
            } else {
                allText.write((byte) 0x07);
            }
        }

        for (int i = 0; i < lines.length; i++) {
            for (char ch : lines[i].toCharArray()) {
                //check it is a supported char, else it is ignored
                if ((ch >= 0x20) && (ch <= 0x7f)) {
                    allText.write((byte) ch);
                }
            }
            if (i < lines.length - 1) {
                allText.write((byte) 0x8A); // end of lines
            }
        }

        return allText.toByteArray();
    }

    private byte[][] splitToExtensionBlocks(byte[] text) throws IOException {
        // how many eb we need
        int textPerBlock = TTI_TEXT_SIZE - 1; // 0x8F terminate
        int ebnBlockCount = 1 + (text.length / (textPerBlock + 1));

        // split bytes to ebs
        byte[][] result = new byte[ebnBlockCount][TTI_TEXT_SIZE];
        int curr = 0;
        int ebn = 0;
        while (curr < text.length) {
            int size = Math.min(TTI_TEXT_SIZE - 1, text.length - curr);
            System.arraycopy(text, curr, result[ebn], 0, size);
            for (int i = size; i < TTI_TEXT_SIZE; i++) {
                result[ebn][i] = (byte) 0x8F; // terminate
            }
            ebn++;
            curr += size;
        }

        return result;
    }

    protected byte[] doBuildTtiBlock(StlSubtitle stlSubtitle, int sn, int ebn) {
        byte[] ttiBlock = new byte[TTI_BLOCK_SIZE];

        //SGN
        ttiBlock[0] = 0;

        //SN
        byte[] snValue = getSn(stlSubtitle, sn);
        ttiBlock[1] = snValue[0];
        ttiBlock[2] = snValue[1];

        //EBN
        ttiBlock[3] = getEbn(stlSubtitle, ebn);

        //CS
        ttiBlock[4] = getCs(stlSubtitle);

        //TCI
        byte[] tciValue = getTci(stlSubtitle);
        ttiBlock[5] = tciValue[0];
        ttiBlock[6] = tciValue[1];
        ttiBlock[7] = tciValue[2];
        ttiBlock[8] = tciValue[3];

        //TCO
        byte[] tcoValue = getTco(stlSubtitle);
        ttiBlock[9] = tcoValue[0];
        ttiBlock[10] = tcoValue[1];
        ttiBlock[11] = tcoValue[2];
        ttiBlock[12] = tcoValue[3];

        //VP
        ttiBlock[13] = getVP(stlSubtitle);

        //JC
        ttiBlock[14] = getJc(stlSubtitle);

        //CF
        ttiBlock[15] = 0;

        // TF
        byte[] text = stlSubtitle.getExtensionBlocks()[ebn];
        System.arraycopy(text, 0, ttiBlock, 16, text.length);

        return ttiBlock;
    }

    protected byte[] getSn(StlSubtitle stlSubtitle, int sn) {
        byte[] result = new byte[2];
        result[0] = (byte) (sn % 256);
        result[1] = (byte) (sn / 256);
        return result;
    }

    protected byte getEbn(StlSubtitle stlSubtitle, int ebn) {
        return (ebn == stlSubtitle.getExtensionBlocks().length - 1) ? (byte) 0xFF : (byte) ebn;
    }

    protected byte[] getTci(StlSubtitle stlSubtitle) {
        byte[] result = new byte[4];
        String[] timeCode = stlSubtitle.getCaption().start.getTime("h:m:s:f/25").split(":");
        result[0] = Byte.parseByte(timeCode[0]);
        result[1] = Byte.parseByte(timeCode[1]);
        result[2] = Byte.parseByte(timeCode[2]);
        result[3] = Byte.parseByte(timeCode[3]);
        return result;
    }

    protected byte[] getTco(StlSubtitle stlSubtitle) {
        byte[] result = new byte[4];
        String[] timeCode = stlSubtitle.getCaption().end.getTime("h:m:s:f/25").split(":");
        result[0] = Byte.parseByte(timeCode[0]);
        result[1] = Byte.parseByte(timeCode[1]);
        result[2] = Byte.parseByte(timeCode[2]);
        result[3] = Byte.parseByte(timeCode[3]);
        return result;
    }

    protected byte getCs(StlSubtitle stlSubtitle) {
        Integer prevEndTimeMs = null;
        if (stlSubtitle.getCaptionNum() - 1 >= 0) {
            Caption prev = stlSubtitle.getCaptions().get(stlSubtitle.getCaptionNum() - 1);
            prevEndTimeMs = prev.end.getMseconds();
        }

        Integer nextEndTimeMs = null;
        if (stlSubtitle.getCaptionNum() + 1 < stlSubtitle.getCaptions().size()) {
            Caption next = stlSubtitle.getCaptions().get(stlSubtitle.getCaptionNum() + 1);
            nextEndTimeMs = next.end.getMseconds();
        }

        Integer currentEndTimeMs = stlSubtitle.getCaption().end.getMseconds();

        if ((prevEndTimeMs != null) && (nextEndTimeMs != null)
                && prevEndTimeMs.equals(currentEndTimeMs) && nextEndTimeMs.equals(currentEndTimeMs)) {
            return (byte) 0x02; // intermediate
        } else if ((nextEndTimeMs != null) && nextEndTimeMs.equals(currentEndTimeMs)) {
            return (byte) 0x01; // first
        } else if ((prevEndTimeMs != null) && prevEndTimeMs.equals(currentEndTimeMs)) {
            return (byte) 0x03; // last
        }

        return (byte) 0x00; // no cumulative
    }

    protected byte getVP(StlSubtitle stlSubtitle) {
        int mnr = GsiAttribute.MNR.getIntValue();
        int vp = mnr / 2;
        if (stlSubtitle.getLinesCount() > (mnr - vp)) {
            vp -= stlSubtitle.getLinesCount() - (mnr - vp );
            vp = Math.max(0, vp);
        }
        return (byte) vp;
    }

    protected byte getJc(StlSubtitle stlSubtitle) {
        if (stlSubtitle.getCaption().style == null) {
            return (byte) 0x02; // center
        }

        if (stlSubtitle.getCaption().style.textAlign.contains("left")) {
            return (byte) 0x01;
        }

        if (stlSubtitle.getCaption().style.textAlign.contains("right")) {
            return (byte) 0x03;
        }

        return (byte) 0x02; // center
    }

    protected byte getCf(StlSubtitle stlSubtitle) {
        return (byte) 0x00;
    }


}
