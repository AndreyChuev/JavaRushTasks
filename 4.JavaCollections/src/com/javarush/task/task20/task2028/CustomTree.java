package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry root;
    private int size;

    public CustomTree() {
        super();
        root = new Entry<>("myTree");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(String s) {
        boolean check = false;
        Queue<Entry> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Entry node = nodes.poll();

            if (!node.availableToAddLeftChildren && node.leftChild != null) {
                nodes.add(node.leftChild);
            } else if (node.availableToAddLeftChildren) {
                node.leftChild = new Entry<>(s);
                node.leftChild.parent = node;
                node.availableToAddLeftChildren = false;
                size++;
                check = true;
                break;
            }

            if (!node.availableToAddRightChildren && node.rightChild != null) {
                nodes.add(node.rightChild);
            } else if (node.availableToAddRightChildren) {
                node.rightChild = new Entry<>(s);
                node.rightChild.parent = node;
                node.availableToAddRightChildren = false;
                size++;
                check = true;
                break;
            }
        }
        if (!check) {
            openBranches();
            check = add(s);
        }
        return check;
    }

    public String getParent(String value) {
        Stack<Entry> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Entry<String> node = stack.pop();
            if (node == null) continue;
//            System.out.printf(" Node[%5s] Parent[%5s] \n", node.elementName, node.parent);
            if (node.parent != null && node.elementName.equals(value))
                return node.parent.toString();

            if (!node.availableToAddLeftChildren) {
                stack.add(node.leftChild);
            }

            if (!node.availableToAddRightChildren) {
                stack.add(node.rightChild);
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (o.getClass() != String.class) throw new UnsupportedOperationException();
        String value = (String) o;
        boolean check = false;

        Entry parent = null;
        Stack<Entry> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Entry node = stack.pop();
            if (node == null) continue;
            if (node.elementName.equals(value)) {
                parent = node.parent;
                if (parent.leftChild != null && parent.leftChild == node) {
                    parent.leftChild = null;
                    check = true;
                } else if (parent.rightChild != null && parent.rightChild == node) {
                    parent.rightChild = null;
                    check = true;
                }
            } else {
                if (!node.availableToAddLeftChildren && node.leftChild != null) {
                    stack.add(node.leftChild);
                }
                if (!node.availableToAddRightChildren && node.rightChild != null) {
                    stack.add(node.rightChild);
                }
            }
        }
        size = countSize();
        return check;
    }

    private int countSize() {
        int count = -1;
        Queue<Entry> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Entry node = nodes.poll();
            if (node != null) count++;
            else continue;
            if (!node.availableToAddLeftChildren && node.leftChild != null) {
                nodes.add(node.leftChild);
            }
            if (!node.availableToAddRightChildren && node.rightChild != null) {
                nodes.add(node.rightChild);
            }
        }
        return count;
    }

    private void openBranches() {
        Queue<Entry> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Entry node = nodes.poll();

            if (!node.availableToAddLeftChildren && node.leftChild != null) {
                nodes.add(node.leftChild);
            } else if (!node.availableToAddLeftChildren) {
                node.availableToAddLeftChildren = true;
            }

            if (!node.availableToAddRightChildren && node.rightChild != null) {
                nodes.add(node.rightChild);
            } else if (!node.availableToAddRightChildren) {
                node.availableToAddRightChildren = true;
            }
        }
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }

        @Override
        public String toString() {
            return elementName;
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
