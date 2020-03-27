
package com.sparta.team.display;

public interface DisplayManagerInterface {

    void displayTimeElapsed(int time);
    void displayAnimalsLived(String animalType, long maleRabbits, long femaleRabbits);
    void displayAnimalsAlive(String animalType, long maleRabbits, long femaleRabbits);
    void displayAnimalsDied(long eaten, long totalDied);
    void displayMessageInReport(String message);
    void writerClose();
}