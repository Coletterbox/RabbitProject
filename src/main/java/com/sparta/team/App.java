package com.sparta.team;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.display.DisplayManagerInterface;
import com.sparta.team.display.UserInput;
import com.sparta.team.manager.WorldSimulation;

public class App {
    public static void main(String[] args) {

        UserInput userInput = new UserInput();
        WorldSimulation worldSimulation = new WorldSimulation();
        String type = userInput.getTerminalOutputMethod();
        worldSimulation.startSimulation(30);
    }
}
