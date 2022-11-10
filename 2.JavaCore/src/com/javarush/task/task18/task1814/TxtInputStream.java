package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    private String regex = "\\.txt$";
    private Pattern pattern = Pattern.compile(regex);

    public TxtInputStream(String fileName) throws Exception {
        super(fileName);
        Matcher matcher = pattern.matcher(fileName);
        if (!matcher.find()) {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) {
    }
}

