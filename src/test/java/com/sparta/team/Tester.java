package com.sparta.team;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Made By Team Awesome
 */
public class Tester
{
    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Create Female Rabbit")
    public void createMaleRabbit() {  }
    @Test
    @DisplayName("Create Male Rabbit")
    public void createFemaleRabbit() { }
    @Test
    @DisplayName("Check if dead male rabbit removed from list")
    public void maleDeadRemovedFromList() { }
    @Test
    @DisplayName("Check if dead female rabbit removed from list")
    public void femaleDeadRemovedFromList() { }
    @Test
    @DisplayName("Check male rabbits died")
    public void checkMaleDied() { }
    @Test
    @DisplayName("Check female rabbit died")
    public void checkFemaleDied() { }
    @Test
    @DisplayName("Increase rabbit age ")
    public void incrementedRabbitAge() { }
    @Test
    @DisplayName("Mate at Matured age")
    public void mateAtMaturedAge() { }
    @Test
    @DisplayName("Is Pregnant")
    public void isPregnant() { }
    @Test
    @DisplayName("Is No Longer Pregnant")
    public void isNotPregnant() { }
    @Test
    @DisplayName("Set the duration")
    public void simulationDuration(){}
}
