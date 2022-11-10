package com.javarush.task.task18.task1826;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {

    public static void main(String[] args) {
        byte[] key = "njsbLI!@##UWD28e938h-dfs11d322376-=@!39!@##$@#@!#".getBytes();
        try (FileInputStream input = new FileInputStream( args[1]);
             FileOutputStream output = new FileOutputStream(args[2])) {
            byte[] file = new byte[input.available()];
            input.read(file);
            file = cryptXOR(file,key);
            output.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] cryptXOR(byte[] data, byte[] key) {
        byte[] result = null;
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length)){
            for (int i = 0, k = 0; i < data.length; i++, k++) {
                if (k == key.length) k = 0;
                byteStream.write(data[i] ^ key[k]);
            }
            byteStream.flush();
            result = byteStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
