package com.javarush.task.pro.task10.task1010;

import java.util.Objects;

/* 
Два айфона
*/

public class Iphone {
    private String model;
    private String color;
    private int price;

    public Iphone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public boolean equals(Object obj){
        // сравнение ссылок объектов
        if (this == obj)
            return true;

        // проверка не равен ли объект null
        if (obj == null)
            return false;

        // проверка принадлежности классов при помощи оператора instanceof
        if (!(obj instanceof Iphone))
            return false;

        // приведение типа
        Iphone iphone = (Iphone) obj;

        // сравнение полей объектов если один из них null
        if (iphone.model == null)
            return this.model == null && this.price == iphone.price;

        if (iphone.color == null)
            return this.color == null && this.price == iphone.price;

        // сравнение полей объектов
        return (this.model != null && this.model.equals(iphone.model)) && (this.color != null && this.color.equals(iphone.color)) && this.price == iphone.price;
    }

    public static void main(String[] args) {
        Iphone iphone1 = new Iphone("X", "Black", 999);
        Iphone iphone2 = new Iphone("X", "Black", 999);

        System.out.println(iphone1.equals(iphone2));
    }

}
