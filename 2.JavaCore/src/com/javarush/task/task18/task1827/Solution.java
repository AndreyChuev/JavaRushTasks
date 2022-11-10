package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) return;
        FileBase fileBase = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            fileBase = new FileBase(reader.readLine());
            String[] info = extractInfo(args);
            switch (info[0]) {
                case "-c":
                    fileBase.create(info[1],info[2],info[3]);
                    break;
            }
        } finally {
            if (fileBase != null)
                fileBase.closes();
        }
    }

    private static String[] extractInfo(String[] args) {
        String productName = "";
        int l = args.length;
        for (int i = 1; i < l - 2; i++) {
            productName += args[i] + " ";
        }
        return new String[]{args[0], productName, args[l - 2], args[l - 1]};
    }

    private static class FileBase {
        private BufferedReader reader;
        private FileWriter writer;
        private int maxId = 0;

        public FileBase(String path) {
            try {
                reader = new BufferedReader(new FileReader(path));
                writer = new FileWriter(path, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void create(String productName, String price, String quantity) {
            updateMaxId();
            String data = "\n" + formatter(String.valueOf(++maxId),8) +
                    formatter(productName,30) +
                    formatter(price,8) +
                    formatter(quantity,4);
            try {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void updateMaxId() {
            try {
                while (reader.ready()) {
                    maxId = Math.max(maxId,Integer.parseInt(reader.readLine().substring(0,8).trim()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String formatter(String str, int maxLength) {
            String result = "";
            String pattern = "%-" + maxLength + "s";
            if (str.length() <= maxLength)
                result = String.format(pattern,str);
            else
                result = str.substring(0,maxLength);
            return result;
        }

        public void closes() {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
