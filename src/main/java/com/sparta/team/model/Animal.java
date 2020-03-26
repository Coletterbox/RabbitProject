package com.sparta.team.model;

import java.util.Collection;

public abstract class Animal implements AnimalInterface {

    protected int ageInMonths;
    private boolean isAlive;
    protected int lifespan;
    protected int ageOfMaturity;

    public Animal() {
        this.ageInMonths = 0;
        this.isAlive = true;
    }

    @Override
    public boolean isMature() {
        if (this.ageInMonths >= ageOfMaturity) {
            return true;
        }
        return false;
    }

    @Override
    public void incrementAge() {
        ageInMonths++;
        if (ageInMonths > lifespan) {
            animalDies();
        }
    }

    void animalDies() {
        this.isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

}

