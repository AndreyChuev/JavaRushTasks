package com.javarush.task.task14.task1408;

public class RussianHen extends Hen {
    private final String country = Country.RUSSIA;

    @Override
    int getCountOfEggsPerMonth() {
        return 35;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу "
                + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
