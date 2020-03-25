package com.sparta.team.display;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;

public class DisplayManager implements DisplayManagerInterface {

    //static final String LOG_PROPERTIES_FILE_RESULTS = "resources/log4jResults.properties";
    //static Logger log = Logger.getLogger(DisplayManager.class.getName());

    private long aliveM = 0;
    private long aliveF = 0;
    private int type;
    private Writer writer = null;

    public DisplayManager(int type) {
        this.type = type;
        // initialiseLogging();
        initialWriter();
    }

//    public static void initialiseLogging() {
//        PropertyConfigurator.configure(LOG_PROPERTIES_FILE_RESULTS);
//    }

    @Override
    public void displayTimeElapsed(int time) {
        if (type == 1) {
            writeToFile("_                                                _");
            writeToFile("____________Month " + time + " Results____________");
        } else {
            writeToFile("_                                                _");
            writeToFile("______________Simulation End Results______________");
        }
        writeToFile("Simulation running for [" + (time / 12) + " years " + (time % 12) + " months]");
    }

    @Override
    public void displayMaleRabbitsAlive(long rabbits) {
        writeToFile("Male rabbits alive [" + rabbits + "]");
    }

    @Override
    public void displayFemaleRabbitsAlive(long rabbits) {
        writeToFile("Female rabbits alive [" + rabbits + "]");
    }

    @Override
    public void displayMaleRabbitsLived(long rabbits) {
        writeToFile("Male rabbits lived [" + rabbits + "] has died [" + (rabbits - aliveM) + "]");
    }

    @Override
    public void displayFemaleRabbitsLived(long rabbits) {
        writeToFile("Female rabbits lived [" + rabbits + "] has died [" + (rabbits - aliveF) + "]");
    }

    @Override
    public void displayRabbitsLived(long maleRabbits, long femaleRabbits) {
        displayMaleRabbitsLived(maleRabbits);
        displayFemaleRabbitsLived(femaleRabbits);
    }

    @Override
    public void displayRabbitsAlive(long maleRabbits, long femaleRabbits) {
        aliveM = maleRabbits;
        aliveF = femaleRabbits;
        displayMaleRabbitsAlive(maleRabbits);
        displayFemaleRabbitsAlive(femaleRabbits);
    }

    private void initialWriter() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("SimulationResults.txt"), "utf-8"));
        } catch (IOException ex) {
            // Report log please
        }
    }

    private void writeToFile(String line) {
        try {
            writer.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace(); // Report log please
        }
    }

    public void writerClose() {
        System.out.println("Please check results file for results.");
        writeToFile("______________Simulation Finished______________");
        writeToFile("_                                             _");
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(); // Report log please
        }
    }
}
