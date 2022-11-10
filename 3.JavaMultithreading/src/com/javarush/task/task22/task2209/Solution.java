package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* 
Составить цепочку слов
Киев Нью-Йорк Афины Прага Вена Амстердам Мельбурн
*/

public class Solution {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
//        String ss = "D:\\IDEA_project\\JavaRushTasks\\3.JavaMultithreading\\src\\com\\javarush\\task\\task22\\task2209\\textFile.txt";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))){
            List<String> list = Files.readAllLines(Paths.get(console.readLine()), Charset.defaultCharset());
            list.forEach(s -> builder.append(s).append(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder result = getLine(builder.toString().split("\\s"));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();
        StringBuilder builder = new StringBuilder();
        LinkedList<Word> linkedList = new LinkedList<>();
        Queue<Word> wordsQueue = getQueue(words);

        linkedList.add(wordsQueue.poll());
        int counter = 0;
        while (!wordsQueue.isEmpty()) {
            counter++;
            boolean flag = false;
            Word word = wordsQueue.poll();

            if (word.equalsWithLastChar(linkedList.getLast())) {
                linkedList.addLast(word);
                flag = true;
            }
            if (word.equalsWithStartChar(linkedList.getFirst())) {
                linkedList.addFirst(word);
                flag = true;
            }

            if (!flag && linkedList.size() > 1) {
                wordsQueue.add(linkedList.removeFirst());
                wordsQueue.add(word);
            } else if (!flag){
                wordsQueue.add(word);
            }
            if (linkedList.contains(word) & wordsQueue.contains(word))
                wordsQueue.remove(word);

            if (counter == 50) {
                counter = 0;
                LinkedList<Word> temp = (LinkedList<Word>) wordsQueue;
                Collections.shuffle(temp);
                wordsQueue = temp;
            }

        }
        linkedList.forEach(w -> builder.append(w.toString()).append(" "));
        return builder;
    }

    private static Queue<Word> getQueue(String[] s) {
        Queue<Word> queue = new LinkedList<>();
        Arrays.stream(s).forEach(w -> queue.add(new Word(w)));
        return queue;
    }

    private static class Word {
        String word;
        char start;
        char end;

        public Word(String word) {
            this.word = word;
            start = word.toLowerCase().charAt(0);
            end = word.toLowerCase().charAt(word.length() - 1);
        }

        public boolean equalsWithLastChar(Word word) {
            return start == word.end;
        }

        public boolean equalsWithStartChar(Word word) {
            return end == word.start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word1 = (Word) o;
            return start == word1.start && end == word1.end && Objects.equals(word, word1.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word, start, end);
        }

        @Override
        public String toString() {
            return word;
        }
    }
}