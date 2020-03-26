package com.sparta.team;

import com.sparta.team.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RabbitTester {

    private List<Animal> rabbits = new ArrayList<>();
    private List<Animal> bunnies = new ArrayList<>();

    @BeforeEach
    public void setup() {
        rabbits.add(new MaleRabbit());
        rabbits.add(new MaleRabbit());
        rabbits.add(new FemaleRabbit());
        rabbits.add(new FemaleRabbit());
    }

    @Test
    @DisplayName("Successful birth")
    public void testFemalesGiveBirth() {
        for (Animal rabbit : rabbits) {
            if (rabbit instanceof FemaleRabbit) {
                List<Rabbit> newBunnies = ((FemaleRabbit) rabbit).giveBirth();
                for (Animal newBunny : newBunnies) {
                    bunnies.add(newBunny);
                }
            }
        }
        boolean check = false;
        if (bunnies.size() >= 2 && bunnies.size() <= 28) check = true;
        Assertions.assertEquals(true, check);
    }

    @Test
    @DisplayName("Create Male Rabbit")
    public void createMaleRabbit() {
        Animal maleRabbit = rabbits.get(0);
        Assertions.assertEquals(true, maleRabbit instanceof Rabbit);
    }

    @Test
    @DisplayName("Create Female Rabbit")
    public void createFemaleRabbit() {
        Animal femaleRabbit = rabbits.get(2);
        Assertions.assertEquals(true, femaleRabbit instanceof Rabbit);
    }

    @Test
    @DisplayName("Increase rabbit age ")
    public void incrementedRabbitAge() {
        rabbits.get(0).incrementAge();
        Assertions.assertEquals(1, rabbits.get(0).getAgeInMonths());
    }

    @Test
    @DisplayName("Mate at Matured age")
    public void mateAtMaturedAge() {
        rabbits.get(0).incrementAge();
        rabbits.get(0).incrementAge();
        rabbits.get(0).incrementAge();
        rabbits.get(0).incrementAge();
        Assertions.assertEquals(true, rabbits.get(0).isMature());
    }

    @Test
    @DisplayName("Do not mate at young age")
    public void notMaturedAge() {
        rabbits.get(0).incrementAge();
        rabbits.get(0).incrementAge();
        Assertions.assertEquals(false, rabbits.get(0).isMature());
    }

    @Test
    @DisplayName("Is Pregnant")
    public void isPregnant() {
        FemaleAnimal femaleAnimal = (FemaleAnimal) rabbits.get(2);
        femaleAnimal.giveBirth();
        Assertions.assertEquals(true, femaleAnimal.isPregnant());
    }

    @Test
    @DisplayName("Is No Longer Pregnant")
    public void isNotPregnant() {
        FemaleAnimal femaleAnimal = (FemaleAnimal) rabbits.get(3);
        Assertions.assertEquals(false, femaleAnimal.isPregnant());
    }
}