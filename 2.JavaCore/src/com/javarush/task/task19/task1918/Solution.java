package com.javarush.task.task19.task1918;

/*
Знакомство с тегами
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private static String fileString;
    private static Pattern openTag;
    private static Pattern closeTag;

    public static void main(String[] args) {
        patternsInitialize(args[0]);
//        readFileStrings("D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1918\\test.html");
        readFileStrings(getFileFromConsole());

        ArrayList<TagPosition> positions = getTagPositionsList();
//        positions.forEach(System.out::println);

        ArrayList<Couple> couples = new ArrayList<>();

        TagPosition open = null;
        TagPosition close = null;

        while (!positions.isEmpty()) {
            for (int i = 0; i < positions.size(); i++) {
                TagPosition temp = positions.get(i);
                if (temp.getType() == TagType.OPEN) {
                    open = temp;
                } else if (temp.getType() == TagType.CLOSE) {
                    close = temp;
                    couples.add(new Couple(open,close));
                    positions.remove(open);
                    positions.remove(close);
                    break;
                }
            }
        }
        couples.sort(Couple::compareTo);

        for (Couple couple : couples) {
            System.out.println(fileString.substring(couple.getOpen(), couple.getClose()).trim());
        }
    }

    private static ArrayList<TagPosition> getTagPositionsList() {
        ArrayList<TagPosition> tagPositions = new ArrayList<>();
        Matcher open = openTag.matcher(fileString);
        Matcher close = closeTag.matcher(fileString);
        while (open.find())
            tagPositions.add(new TagPosition(TagType.OPEN, open.start(), open.end()));
        while (close.find())
            tagPositions.add(new TagPosition(TagType.CLOSE, close.start(), close.end()));
        tagPositions.sort(TagPosition::compareTo);
        return tagPositions;
    }

    private static void patternsInitialize(String tag) {
        openTag = Pattern.compile("<" + tag);
        closeTag = Pattern.compile("</" + tag + ">");
    }

    private static String getFileFromConsole() {
        String file = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            file = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void readFileStrings(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready())
                builder.append(reader.readLine());
            fileString = builder.toString().replaceAll("[\\n\\r]+", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Couple implements Comparable<Couple>{
        private final int open;
        private final int close;
        private final TagPosition tagOpen;
        private final TagPosition tagClose;

        public Couple(TagPosition open, TagPosition close) {
            if (open.getType() == TagType.OPEN & close.getType() == TagType.CLOSE) {
                this.open = open.getStart();
                this.close = close.getEnd();
                tagOpen = open;
                tagClose = close;
            } else {
                throw new IllegalArgumentException("Не правильный тип тега: open=" + open + " close=" + close);
            }
        }

        public int getOpen() {
            return open;
        }

        public int getClose() {
            return close;
        }

        @Override
        public int compareTo(Couple o) {
            int weightThis = tagOpen.getPositionCode() + tagClose.getPositionCode();
            int weightO = o.tagOpen.getPositionCode() + o.tagClose.getPositionCode();
            return weightThis - weightO;
        }
    }
}
