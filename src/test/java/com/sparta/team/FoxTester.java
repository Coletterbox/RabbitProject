package com.sparta.team;

import com.sparta.team.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FoxTester {

    private List<Animal> fox = new ArrayList<>();
    private List<Animal> kits = new ArrayList<>();

    @BeforeEach
    public void setup() {
        fox.add(new FemaleFox());
        fox.add(new FemaleFox());
        fox.add(new FemaleFox());
        fox.add(new FemaleFox());
    }

    @Test
    @DisplayName("Successful birth of Fox")
    public void testFemalesGiveBirth() {
        for (Animal fox : fox) {
            if (fox instanceof FemaleFox) {
                List<Fox> newKits = ((FemaleFox) fox).giveBirth();
                for (Animal newKit : newKits) {
                    kits.add(newKit);
                }
            }
        }
        boolean check = false;
        if (kits.size() >= 2 && kits.size() <= 28) check = true;
        Assertions.assertEquals(true, check);
    }

    @Test
    @DisplayName("Create Female Fox")
    public void createFemaleFox() {
        Animal femaleFox = fox.get(2);
        Assertions.assertEquals(true, femaleFox instanceof Fox);
    }

    @Test
    @DisplayName("Increase fox age ")
    public void incrementedFoxAge() {
        fox.get(3).incrementAge();
        Assertions.assertEquals(1, fox.get(3).getAgeInMonths());
    }

    @Test
    @DisplayName("Mate at Matured age")
    public void mateAtMaturedAge() {
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        Assertions.assertEquals(true, fox.get(2).isMature());
    }

    @Test
    @DisplayName("Do not mate at young age")
    public void notMaturedAge() {
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        fox.get(2).incrementAge();
        Assertions.assertEquals(false, fox.get(2).isMature());
    }

    @Test
    @DisplayName("Is Pregnant")
    public void isPregnant() {
        FemaleAnimal femaleAnimal = (FemaleAnimal) fox.get(2);
        femaleAnimal.giveBirth();
        Assertions.assertEquals(true, femaleAnimal.isPregnant());
    }

    @Test
    @DisplayName("Is No Longer Pregnant")
    public void isNotPregnant() {
        FemaleAnimal femaleAnimal = (FemaleAnimal) fox.get(3);
        Assertions.assertEquals(false, femaleAnimal.isPregnant());
    }
}
