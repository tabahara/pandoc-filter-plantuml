package com.quinmantha.helper;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

public class PlantUML implements IPlantUML {
    /**
     * Make Image from given String.
     *
     * @param srcString
     * @return output filename
     */
    public String process(String srcString, String filename){
        String desc = null;
        SourceStringReader reader = new SourceStringReader(split(srcString));
        final ByteArrayOutputStream os;
        try {
            File outFile = new File(filename);
            File parentFile = outFile.getParentFile();
            if( parentFile != null ){
                parentFile.mkdirs();
            }
            OutputStream outputStream;
            outputStream = new FileOutputStream(outFile);
            desc = reader.generateImage(outputStream, new FileFormatOption(FileFormat.SVG));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desc;
    }

    private String split(String inputString){
        StringBuffer sb = new StringBuffer();
        int push = -1;
        StringReader sr = new StringReader(inputString);
        try {
            while(true) {
                int c;
                if (push != -1) {
                    c = push;
                    push = -1;
                } else {
                    c = sr.read();
                    if (c == -1) {
                        break;
                    }
                }

                if (c == '\\') {
                    int c2 = sr.read();
                    if (c2 == -1){
                        break;
                    }

                    if (c2 == 'n') {
                        sb.append('\n');
                    } else {
                        push = c2;
                    }
                } else {
                    sb.append((char)c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String random(){
	return String.valueOf(System.currentTimeMillis() & 0xFFFFFF);
    }

    static public void main(String[] args){
        if( args.length > 0 ) {
            PlantUML o = new PlantUML();
            String desc = o.process(args[0], "dest.svg");
            System.out.println("desc:" + desc);
        } else {
            System.err.println("cmd 'string'");
        }
    }
}


