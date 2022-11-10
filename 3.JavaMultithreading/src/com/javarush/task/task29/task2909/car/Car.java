package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    protected static final int MAX_TRUCK_SPEED = 80;
    protected static final int MAX_SEDAN_SPEED = 120;
    protected static final int MAX_CABRIOLET_SPEED = 90;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers) {
        if (type == TRUCK) {
            return new Truck(numberOfPassengers);
        } else if (type == SEDAN) {
            return new Sedan(numberOfPassengers);
        } else if (type == CABRIOLET) {
            return new Cabriolet(numberOfPassengers);
        } else {
            throw new IllegalArgumentException(String.format("Unknown type '%d'", type));
        }
    }

    private boolean canPassengersBeTransferred() {
        return isDriverAvailable() && fuel > 0;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception("number of liters cannot be less then 0");
        fuel += numberOfLiters;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd) {
        return date.after(summerStart) && date.before(summerEnd);
    }

    public double getWinterConsumption(int length) {
        checkLength(length);
        return length == 0 ? 0d : length * winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length) {
        checkLength(length);
        return length == 0 ? 0d : length * summerFuelConsumption;
    }

    private void checkLength(int length) {
        if (length < 0)
            throw new IllegalArgumentException("Length cannon be less then 0");
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        return isSummer(date, SummerStart, SummerEnd) ? getSummerConsumption(length) : getWinterConsumption(length);
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred()) {
            return numberOfPassengers;
        }
        return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}