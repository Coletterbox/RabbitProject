package com.sparta.team.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FemaleFox extends Fox implements FemaleAnimal {

    private static final int PREGNANCY_LENGTH = 12;
    private boolean isPregnant = false;
    private int pregnancyDuration;

    public FemaleFox() {
        super();
        pregnancyDuration = 0;
    }

    @Override
    public void incrementAge() {
        ageInMonths++;
        if (ageInMonths > lifespan) {
            animalDies();
        }
        if (isPregnant) {
            pregnancyDuration++;
        }
        if (pregnancyDuration == PREGNANCY_LENGTH) {
            this.setPregnant(false);
        }
    }

    @Override
    public List<Fox> giveBirth() {
        this.setPregnant(true);
        List<Fox> listOfBabies = new ArrayList<>();
        Random random = new Random();
        int numberOfBunnies = random.nextInt(10) + 1;
        for (int i = 0; i < numberOfBunnies; i++) {
            boolean setGender = random.nextBoolean(); // TRUE IS FEMALE, FALSE IS MALE
            if (setGender) {
                listOfBabies.add(new FemaleFox());
            } else {
                listOfBabies.add(new MaleFox());
            }
        }
        return listOfBabies;
    }

    @Override
    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }
}
