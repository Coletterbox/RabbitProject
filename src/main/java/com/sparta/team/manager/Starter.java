package com.sparta.team.manager;

import com.sparta.team.display.UserInput;

/**
 * Welcome to Team Awesome
 */
public class Starter {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        int numberOfMonths = userInput.getTerminalNumberOfMonths();
        int type = userInput.getTerminalOutputForResultType();
        int numberOfSeconds = userInput.getTerminalNumberOfMonths();
        WorldSimulation worldSimulation = new WorldSimulation();
        worldSimulation.startSimulation(numberOfSeconds, type);
//        matrixSimulation.startSimulation(10, type);
    }
}
