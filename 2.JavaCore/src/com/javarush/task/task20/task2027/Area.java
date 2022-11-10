package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Area {
    public Cell[][] area;

    public LinkedList<Cell> offsetSearch(Cell start, char[] chars, Offset offset) {
        LinkedList<Cell> cells = new LinkedList<>();
        int x = start.x;
        int y = start.y;
        for (int i = 0; i < chars.length; i++) {
            if (y < 0 || y >= area.length)
                break;
            if (x < 0 || x >= area[y].length)
                break;
            if (area[y][x].value != chars[i])
                break;
            cells.add(area[y][x]);
            x += offset.x;
            y += offset.y;
        }
        return cells;
    }

    public ArrayList<Cell> getCells(char ch) {
        ArrayList<Cell> result = new ArrayList<>();
        for (int y = 0; y < this.area.length; y++) {
            for (int x = 0; x < this.area[y].length; x++) {
                Cell cell = this.area[y][x];
                if (cell.value == ch)
                    result.add(cell);
            }
        }
        return result;
    }

    public List<Cell> getNeighbors(Cell cell, char target) {
        List<Cell> cells = new ArrayList<>();
        for (int y = cell.y - 1; y <= cell.y + 1; y++) {
            for (int x = cell.x - 1; x <= cell.x + 1; x++) {
                if (y < 0 || y >= area.length)
                    continue;
                if (x < 0 || x >= area[y].length)
                    continue;
                if (area[y][x] == cell)
                    continue;
                if (area[y][x].value == target)
                    cells.add(area[y][x]);
            }
        }
        return cells;
    }
}
