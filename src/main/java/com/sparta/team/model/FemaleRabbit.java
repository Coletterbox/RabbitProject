package com.sparta.team.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FemaleRabbit extends Rabbit {

    private static final int PREGNANCY_LENGTH = 1;

    public FemaleRabbit() {
        super();
    }

    public List<Rabbit> giveBirth(){
        List<Rabbit> listOfBunnies = new ArrayList<>();
        Random random = new Random();
        int numberOfBunnies = random.nextInt(14) + 1;
        for (int i = 0; i < numberOfBunnies; i++) {
            int gender = (int) (Math.random() * 2);
            if (gender == 2) gender = 1;
            if (gender == 0) { // IF GENDER IS 0 THEN FEMALE, GENDER IS 1 THEN MALE
                listOfBunnies.add(new FemaleRabbit());
            } else {
                listOfBunnies.add(new MaleRabbit());
            }
        }
        return listOfBunnies;
    }
}
