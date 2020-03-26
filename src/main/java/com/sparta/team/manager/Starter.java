package com.sparta.team.manager;

import com.sparta.team.display.UserInput;

/**
 * Welcome to Team Awesome
 */
public class Starter {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        int type = userInput.getTerminalOutputForResultType();
        WorldSimulation worldSimulation = new WorldSimulation();
        worldSimulation.startSimulation(40, type);

    }
}
