package com.sparta.team.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FemaleFox extends Fox {

    private static final int PREGNANCY_LENGTH = 12;
    private boolean isPregnant = false;

    public FemaleFox() {
        super();
    }

    public List<Fox> giveBirth() {
        List<Fox> listOfBabies = new ArrayList<>();
        Random random = new Random();
        int numberOfBunnies = random.nextInt(14) + 1;
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

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }
}
