package com.sparta.team.display;

public interface DisplayManagerInterface {

    void displayTimeElapsed(int time);
    void displayMaleRabbitsLived(long rabbits);
    void displayFemaleRabbitsLived(long rabbits);
    void displayRabbitsLived(long maleRabbits, long femaleRabbits);

    void displayMaleRabbitsAlive(long rabbits);
    void displayFemaleRabbitsAlive(long rabbits);
    void displayRabbitsAlive(long maleRabbits, long femaleRabbits);


}
