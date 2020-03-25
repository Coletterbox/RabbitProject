package com.sparta.team.display;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;

public class DisplayManager implements DisplayManagerInterface {

    static final String LOG_PROPERTIES_FILE_RESULTS = "resources/log4jResults.properties";
    static Logger log = Logger.getLogger(DisplayManager.class.getName());

    long aliveM = 0;
    long aliveF = 0;
    int type;

    public DisplayManager(int type){
        this.type = type;
        initialiseLogging();
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE_RESULTS);
    }

    @Override
    public void displayTimeElapsed(int time) {
        if (type == 1){
            log.debug("_                                                _");
            log.debug("____________Month " + time + " Results____________");
        }else{
            log.debug("_                                                _");
            log.debug("______________Simulation End Results______________");
        }
        log.debug("Simulation running for [" + (time/12)+ " years " + (time%12) + " months]");
    }

    @Override
    public void displayMaleRabbitsAlive(long rabbits) {
        log.debug("Male rabbits alive ["+ rabbits+"]");
    }

    @Override
    public void displayFemaleRabbitsAlive(long rabbits) {
        log.debug("Female rabbits alive ["+ rabbits+"]");
    }

    @Override
    public void displayMaleRabbitsLived(long rabbits) {
        log.debug("Male rabbits lived ["+ rabbits +"] has died ["+(rabbits-aliveM)+"]");
    }

    @Override
    public void displayFemaleRabbitsLived(long rabbits) {
        log.debug("Female rabbits lived ["+ rabbits +"] has died ["+(rabbits-aliveF)+"]");
    }

    @Override
    public void displayRabbitsLived(long maleRabbits, long femaleRabbits) {
        displayMaleRabbitsLived(maleRabbits);
        displayFemaleRabbitsLived(femaleRabbits);
        if( type==2) {
            System.out.println("Please check results file for results.");
            log.debug("______________Simulation Finished______________");
            log.debug("_                                             _");
        }
    }

    @Override
    public void displayRabbitsAlive(long maleRabbits, long femaleRabbits) {
        aliveM = maleRabbits;
        aliveF = femaleRabbits;
        displayMaleRabbitsAlive(maleRabbits);
        displayFemaleRabbitsAlive(femaleRabbits);
    }
    private void writeResultToFile(){//Not completed
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("SimulationResults.txt"), "utf-8"));
            writer.write("Something");
        } catch (IOException ex) {
            // Report log please
        } finally {
            try {writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
