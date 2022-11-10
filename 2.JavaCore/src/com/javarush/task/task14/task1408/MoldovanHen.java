package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen {
    private final String country = Country.MOLDOVA;

    @Override
    int getCountOfEggsPerMonth() {
        return 69;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу "
                + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
