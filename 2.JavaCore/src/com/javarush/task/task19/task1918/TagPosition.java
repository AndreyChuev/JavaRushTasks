package com.javarush.task.task19.task1918;

public class TagPosition implements Comparable<TagPosition> {
    private final TagType type;
    private final int start;
    private final int end;
    private final int positionCode;

    public TagPosition(TagType type, int start, int end) {
        this.type = type;
        this.start = start;
        this.end = end;
        positionCode = start + end;
    }

    public int getStart() {
        return start;
    }

    public TagType getType() {
        return type;
    }

    public int getEnd() {
        return end;
    }

    public int getPositionCode() {
        return positionCode;
    }

    @Override
    public int compareTo(TagPosition o) {
        return positionCode - o.positionCode;
    }

    @Override
    public String toString() {
        return String.format("Type=[%-5s] S=[%-5s] E=[%-5s] CODE=[%-5s]", type, start, end, positionCode);
    }
}
