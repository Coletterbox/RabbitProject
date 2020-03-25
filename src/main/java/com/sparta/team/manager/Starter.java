package com.sparta.team.manager;

import com.sparta.team.display.UserInput;

/**
 * Hello world!
 *
 */
public class Starter
{
    public static void main( String[] args )
    {


        UserInput userInput = new UserInput();
        MatrixSimulation matrixSimulation = new MatrixSimulation();
        int type = userInput.getTerminalOutputForResultType();
        matrixSimulation.startSimulation(20, type);




    }
}
