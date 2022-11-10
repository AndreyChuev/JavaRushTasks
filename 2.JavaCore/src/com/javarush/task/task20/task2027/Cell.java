package com.javarush.task.task20.task2027;

import java.util.ArrayList;

public class Cell {
    int x;
    int y;
    char value;
    ArrayList<Neighbor> neighbors = new ArrayList<>();

    public Cell(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void setNeighbors(ArrayList<Cell> neighbors) {
        ArrayList<Neighbor> result = new ArrayList<>();
        for (Cell neighbor : neighbors) {
            result.add(new Neighbor(neighbor,this));
        }
    }

    public static Cell[][] getArea(int[][] a) {
        Cell[][] area = new Cell[a.length][];
        for (int y = 0; y < a.length; y++) {
            area[y] = new Cell[a[y].length];
            for (int x = 0; x < a[y].length; x++) {
                area[y][x] = new Cell(x,y,(char) a[y][x]);
            }
        }
        return area;
    }
}
