package com.javarush.task.task14.task1408;

public class UkrainianHen extends Hen {
    private final String country = Country.UKRAINE;

    @Override
    int getCountOfEggsPerMonth() {
        return 45;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу "
                + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
