package com.sparta.team.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FemaleRabbit extends Rabbit {

    private static final int PREGNANCY_LENGTH = 1;
    private boolean isPregnant = false;

    public FemaleRabbit() {
        super();
    }

    public List<Rabbit> giveBirth() {
        List<Rabbit> listOfBunnies = new ArrayList<>();
        Random random = new Random();
        int numberOfBunnies = random.nextInt(14) + 1;
        for (int i = 0; i < numberOfBunnies; i++) {
            boolean setGender = random.nextBoolean(); // TRUE IS FEMALE, FALSE IS MALE
            if (setGender) {
                listOfBunnies.add(new FemaleRabbit());
            } else {
                listOfBunnies.add(new MaleRabbit());
            }
        }
        return listOfBunnies;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }
}
