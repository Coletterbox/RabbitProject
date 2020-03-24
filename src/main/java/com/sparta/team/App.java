package com.sparta.team;

import com.sparta.team.manager.WorldSimulation;

public class App {
    public static void main(String[] args) {

        WorldSimulation worldSimulation = new WorldSimulation();
        worldSimulation.startSimulation(9);
    }
}
