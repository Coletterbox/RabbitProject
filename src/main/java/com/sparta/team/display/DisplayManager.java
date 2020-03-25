package com.sparta.team.display;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

public class DisplayManager implements DisplayManagerInterface {

    static final String LOG_PROPERTIES_FILE = "resources/log4jResults.properties";
    static Logger log = Logger.getLogger(DisplayManager.class.getName());

    int aliveM = 0;
    int aliveF = 0;

    public DisplayManager(){
        initialiseLogging();
        log.debug("__________________________________________");
        log.debug("____________Simulation Results____________");
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
    }

    @Override
    public void displayTimeElapsed(int time) {
        log.debug("Simulation running for [" + (time/12)+ " years " + (time%12) + " months]");

    }

    @Override
    public void displayMaleRabbitsLived(int rabbits) {
        log.debug("Male rabbits were ["+ rabbits +"] has died ["+(rabbits-aliveM)+"]");
    }

    @Override
    public void displayFemaleRabbitsLived(int rabbits) {
        log.debug("Female rabbits were ["+ rabbits +"] has died ["+(rabbits-aliveF)+"]");
    }

    @Override
    public void displayRabbitsLived(int maleRabbits, int femaleRabbits) {

        displayMaleRabbitsLived(maleRabbits);
        displayFemaleRabbitsLived(femaleRabbits);
    }

    @Override
    public void displayMaleRabbitsAlive(int rabbits) {
        log.debug("Male rabbits alive ["+ rabbits+"]");
    }

    @Override
    public void displayFemaleRabbitsAlive(int rabbits) {
        log.debug("Female rabbits alive ["+ rabbits+"]");
    }

    @Override
    public void displayRabbitsAlive(int maleRabbits, int femaleRabbits) {
        aliveM = maleRabbits;
        aliveF = femaleRabbits;
        displayMaleRabbitsAlive(maleRabbits);
        displayFemaleRabbitsAlive(femaleRabbits);
    }
}
