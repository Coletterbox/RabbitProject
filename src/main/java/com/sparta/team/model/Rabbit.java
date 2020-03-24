package com.sparta.team.model;

public abstract class Rabbit implements RabbitInterface {

    //private String rabbitID;
    private int ageInMonths;
    private boolean isAlive;

    private static final int AGE_OF_MATURITY = 3;

    public Rabbit() {
        //this.rabbitID = rabbitID;
        this.ageInMonths = 0;
        this.isAlive = true;
    }

    @Override
    public boolean isMature() {
        if (ageInMonths >= AGE_OF_MATURITY) {
            return true;
        }
        return false;
    }

    @Override
    public void incrementAge() {
        ageInMonths++;
    }
}

