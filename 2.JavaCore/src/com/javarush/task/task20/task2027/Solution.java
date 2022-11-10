package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {

    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'o', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same").forEach(System.out::println);
        System.out.println();
        int[][] crossword2 = new int[][]{
                {'w', 'e', 'd', 'f', 'd', 'd'},
                {'w', 'o', 'r', 'd', 'r', 'e'},
                {'a', 'd', 'o', 'o', 'r', 'p'},
                {'r', 'o', 'w', 'o', 'r', 'd'},
                {'k', 'e', 'o', 'f', 'd', 'k'},
                {'e', 'p', 'r', 'v', 'd', 'g'},
                {'r', 'k', 'd', 'v', 'b', 'j'}
        };
        detectAllWords(crossword2, "word").forEach(System.out::println);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        Area cellArea = new Area();
        cellArea.area = Cell.getArea(crossword);
        for (String word : words) {
            char[] chars = word.toCharArray();
            ArrayList<Cell> firstChars = cellArea.getCells(chars[0]);
            for (Cell firstChar : firstChars) {
                for (Cell neighbors : cellArea.getNeighbors(firstChar, chars[1])) {
                    firstChar.neighbors.add(new Neighbor(neighbors,firstChar));
                }
            }
            for (Cell firstChar : firstChars) {
                for (Neighbor neighbor : firstChar.neighbors) {
                    LinkedList<Cell> charsList = cellArea.offsetSearch(firstChar,chars,neighbor.offset);
                    if (charsList.size() == chars.length) {
                        int matches = 0;
                        for (int i = 0; i < chars.length; i++) {
                            if (chars[i] == charsList.get(i).value) {
                                matches++;
                            } else {
                                matches = 0;
                                break;
                            }
                        }
                        if (matches == charsList.size()) {
                            Word w = new Word(word);
                            Cell start = charsList.getFirst();
                            Cell end = charsList.getLast();
                            w.setStartPoint(start.x, start.y);
                            w.setEndPoint(end.x, end.y);
                            result.add(w);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
