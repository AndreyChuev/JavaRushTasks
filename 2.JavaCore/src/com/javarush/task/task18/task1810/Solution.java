package com.javarush.task.task18.task1810;

import java.io.*;
import java.util.Scanner;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        Scanner scanner = null;
        InputStream input = null;
        try {
          scanner = new Scanner(System.in);
          while ((input = new FileInputStream(scanner.nextLine())).available() >= 1000);
          throw new DownloadException();
        } finally {
            if (scanner != null)
                scanner.close();
            if (input != null)
                input.close();
        }
    }

    public static class DownloadException extends Exception {

    }
}
