package com.sparta.team.display;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DisplayManager implements DisplayManagerInterface {

    static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    static Logger log = Logger.getLogger(DisplayManager.class.getName());

    public DisplayManager(){
        initialiseLogging();
        log.debug("Simulation Results");
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
    }

    @Override
    public void displayTimeElapsed(int time) {
//        float numberOfYearsInSimulation = (float) time;
//        String formattedString = String.format("%.01f", (numberOfYearsInSimulation/12f));
        log.debug("The simulation has been running for " + (time/12)+ "years " + (time%12) + " months.");

    }

    @Override
    public void displayMaleRabbitsLived(int rabbits) {

        log.debug("Number of male rabbits were "+ rabbits);

    }

    @Override
    public void displayFemaleRabbitsLived(int rabbits) {

        log.debug("Number of female rabbits were "+ rabbits);

    }

    @Override
    public void displayRabbitsLived(int maleRabbits, int femaleRabbits) {

        displayMaleRabbitsLived(maleRabbits);
        displayFemaleRabbitsLived(femaleRabbits);

    }

    @Override
    public void displayMaleRabbitsAlive(int rabbits) {

        log.debug("Number of male rabbits alive are "+ rabbits);

    }

    @Override
    public void displayFemaleRabbitsAlive(int rabbits) {

        log.debug("Number of female rabbits alive are "+ rabbits);

    }

    @Override
    public void displayRabbitsAlive(int maleRabbits, int femaleRabbits) {

        displayMaleRabbitsAlive(maleRabbits);
        displayFemaleRabbitsAlive(femaleRabbits);
    }

}
