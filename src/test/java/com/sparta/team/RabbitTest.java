package com.sparta.team;

import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RabbitTest {

    private List<Rabbit> rabbits = new ArrayList<>();
    private List<Rabbit> bunnies = new ArrayList<>();

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
        for (Rabbit rabbit : rabbits) {
            if (rabbit instanceof FemaleRabbit) {
                List<Rabbit> newBunnies = ((FemaleRabbit) rabbit).giveBirth();
                for (Rabbit newBunny : newBunnies) {
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
        Rabbit maleRabbit = rabbits.get(0);
        Assertions.assertEquals(true, maleRabbit instanceof MaleRabbit);
    }

    @Test
    @DisplayName("Create Female Rabbit")
    public void createFemaleRabbit() {
        Rabbit femaleRabbit = rabbits.get(2);
        Assertions.assertEquals(true, femaleRabbit instanceof FemaleRabbit);
    }

//    @Test
//    @DisplayName("Increase rabbit age ")
//    public void incrementedRabbitAge() {
//        rabbits.get(0).incrementAge();
//        Assertions.assertEquals(1, rabbits.get(0).getAgeInMonths());
//    }

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

    }

    @Test
    @DisplayName("Is No Longer Pregnant")
    public void isNotPregnant() {
    }
}