package com.javarush.task.task20.task2027;

public class Neighbor {
    Cell cell;
    Offset offset;

    public Neighbor(Cell cell, Cell tObj) {
        this.cell = cell;
        Offset[] offsets = Offset.class.getEnumConstants();
        int x = cell.x - tObj.x;
        int y = cell.y - tObj.y;
        for (Offset offset : offsets) {
            if (offset.x == x && offset.y == y)
                this.offset = offset;
        }
    }
}
