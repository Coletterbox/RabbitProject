package com.sparta.team;

import com.sparta.team.manager.MatrixSimulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixSimulationTester {

    MatrixSimulation matrixSimulation = new MatrixSimulation();

    @BeforeEach
    public void setup(){
        matrixSimulation.startSimulation(10, 2);

    }

    @Test
    void update() {
//        assertEquals()
    }

    @Test
    void getRabbitCouples() {
        MatrixSimulation matrixSimulation2 = new MatrixSimulation();
        matrixSimulation2.startSimulation(3, 2);
        //assertEquals(matrixSimulation2.getAnimalCouples(), 1);
    }

    @Test
    void ageRabbits() {
        assertEquals(matrixSimulation.getFemaleRabbitsByAge().get(10), 1);
    }

    @Test
    void generateNewGeneration() {
    }

    @Test
    void removeDeadRabbits() {
    }

    @Test
    void updateAliveRabbits() {
    }
}