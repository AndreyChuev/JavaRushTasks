package com.javarush.task.pro.task13.task1314;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();

    public void add(String value) {
        if (first.next == null) {
            Node node = new Node();
            node.value = value;
            first.next = node;
        }
        if (last.prev == null) {
            last.prev = first.next;
            return;
        }

        Node node = new Node();
        node.value = value;

        Node lastNode = last.prev;
        lastNode.next = node;
        node.prev = lastNode;
        last.prev = node;
    }

    public String get(int index) {
        String result = null;
        if (index < 0) return result;
        Node temp = first.next;
        if (first.next == null)
            return result;
        for (int i = 0; i < index; i++) {
            if (temp.next != null) {
                temp = temp.next;
            } else {
                return result;
            }
        }
        result = temp.value;
        return result;
    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}
