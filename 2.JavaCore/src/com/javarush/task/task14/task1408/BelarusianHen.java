package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen {
    private final String country = Country.BELARUS;

    @Override
    int getCountOfEggsPerMonth() {
        return 130;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу "
                + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
