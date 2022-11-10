package com.javarush.task.task24.task25.task2503;

import java.util.*;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();

        class Pos {
            final Column column;
            final int pos;

            public Pos(int ordinal, int pos) {
                column = Column.values()[ordinal];
                this.pos = pos;
            }
            public int compareTo(Pos o) {
                return Integer.compare(this.pos, o.pos);
            }
        }

        List<Pos> posList = new LinkedList<>();
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] != -1)
                posList.add(new Pos(i, realOrder[i]));
        }
        posList.sort(Pos::compareTo);
        posList.forEach(pos -> {
            result.add(pos.column);
        });

        return result;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder != null && realOrder[this.ordinal()] != -1;
    }

    @Override
    public void hide() {
        for (Column value : Column.values()) {
            if (this == value && value.isShown()) {
                realOrder[value.ordinal()] = -1;
            }
        }
        List<Column> columns = getVisibleColumns();
        Column[] newOrder = new Column[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            newOrder[i] = columns.get(i);
        }
        configureColumns(newOrder);
    }


//    public static void main(String[] args) {
//        configureColumns(Column.values());
//        Column[] columns = Column.values();
//        for (Column column : columns) {
//            System.out.println(column + " " + column.columnName + " " + column.isShown());
//        }
//        System.out.println();
//        Column column = columns[2];
//        column.hide();
//
//        for (Column column1 : columns) {
//            System.out.println(column1 + " " + column1.columnName + " " + column1.isShown());
//        }
//    }
}
