package com.sparta.team;

import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
}
