package com.sparta.team;

import com.sparta.team.manager.WorldSimulation;
import com.sparta.team.model.Animal;
import com.sparta.team.model.FemaleFox;
import com.sparta.team.model.MaleFox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WorldSimulationTester {

    private List<Animal> fox = new ArrayList<>();
    private List<Animal> kits = new ArrayList<>();
    WorldSimulation worldSimulation = new WorldSimulation();

    @BeforeEach
    public void setup() {
        fox.add(new MaleFox());
        fox.add(new MaleFox());
        fox.add(new FemaleFox());
        fox.add(new FemaleFox());
    }

    @Test
    @DisplayName("Successful birth of Fox")
    public void runSimulation() {

    }
}
