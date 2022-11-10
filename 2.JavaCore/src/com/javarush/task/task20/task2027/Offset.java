package com.javarush.task.task20.task2027;

public enum Offset {
    UL(-1, -1),
    U(0, -1),
    UR(1, -1),
    R(1,0),
    DR(1, 1),
    D(0, 1),
    DL(-1, 1),
    L(-1, 0);

    int x;
    int y;

    Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
