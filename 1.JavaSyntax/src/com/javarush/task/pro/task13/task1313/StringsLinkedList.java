package com.javarush.task.pro.task13.task1313;

import java.util.Iterator;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();

    public void printAll() {
        Node currentElement = first.next;
        while ((currentElement) != null) {
            System.out.println(currentElement.value);
            currentElement = currentElement.next;
        }
    }

    public void add(String value) {
        Node newNode = new Node();
        if (first.next == null) {
            first.next = newNode;
            last.prev = newNode;
            newNode.value = value;
        } else {
            newNode.prev = last.prev;
            newNode.prev.next = newNode;
            last.prev = newNode;
            newNode.value = value;
        }
    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}
