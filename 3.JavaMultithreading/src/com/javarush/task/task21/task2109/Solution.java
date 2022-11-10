package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/

import java.util.Objects;

public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return i == a.i && j == a.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            B b = (B) o;
            return Objects.equals(name, b.name);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), name);
        }
    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public int getI() {
            return super.getI();
        }

        @Override
        public int getJ() {
            return super.getJ();
        }

        @Override
        public String getName() {
            return super.getName();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new C(getI(), getJ(), getName());
        }
    }

    public static void main(String[] args) {

    }
}
