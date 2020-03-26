package com.sparta.team.display;

public interface DisplayManagerInterface {

    void displayTimeElapsed(int time);
    void displayRabbitsLived(String animalType,long maleRabbits, long femaleRabbits);
    void displayRabbitsAlive(String animalType,long maleRabbits, long femaleRabbits);
    void writerClose();
}
