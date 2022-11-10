package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    public static Cell[][] area;

    public static void main(String[] args) {

        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };

        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        Date start = new Date();
        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
        Date end = new Date();
        System.out.println("Time: " + (end.getTime() - start.getTime()));
    }

    public static int getRectangleCount(byte[][] a) {
        area = Cell.getArea(a);
        int count = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int y = 0; y < area.length; y++) {
                for (int x = 0; x < area[y].length; x++) {
                    Cell cell = area[y][x];
                    if (!cell.isRectangle & cell.value == 1) {
                        flag = true;
                        searchRectangle(cell);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void searchRectangle(Cell cell) {
        if (!cell.isRectangle & cell.value == 1) {
            cell.isRectangle = true;
            area[cell.y][cell.x] = cell;
            for (Cell neighbor : getNeighbors(cell)) {
                if (!neighbor.isRectangle)
                    searchRectangle(neighbor);
            }
        }
    }

    public static List<Cell> getNeighbors(Cell cell) {
        List<Cell> cells = new ArrayList<>();
        for (int y = cell.y - 1; y <= cell.y + 1; y++) {
            for (int x = cell.x - 1; x <= cell.x + 1; x++) {
                if (y < 0 || y >= area.length)
                    continue;
                if (x < 0 || x >= area[y].length)
                    continue;
                if (area[y][x] == cell)
                    continue;
                cells.add(area[y][x]);
            }
        }
        return cells;
    }

    public static class Cell {
        int x;
        int y;
        int value;
        boolean isRectangle;

        public Cell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public static Cell[][] getArea(byte[][] a) {
            Cell[][] area = new Cell[a.length][];
            for (int y = 0; y < a.length; y++) {
                area[y] = new Cell[a[y].length];
                for (int x = 0; x < a[y].length; x++) {
                    area[y][x] = new Cell(x,y,a[y][x]);
                }
            }
            return area;
        }

        public static void printArea(Cell[][] area) {
            for (int y = 0; y < area.length; y++) {
                for (int x = 0; x < area[y].length; x++) {
                    System.out.print(area[y][x]);
                }
                System.out.println();
            }
        }

        @Override
        public String toString() {
            return "[" + isRectangle + "]";
        }
    }
}
