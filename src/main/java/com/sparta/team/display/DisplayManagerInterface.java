package com.sparta.team.display;

public interface DisplayManagerInterface {

    void displayTimeElapsed(int time);
    void displayMaleRabbitsLived(int rabbits);
    void displayFemaleRabbitsLived(int rabbits);
    void displayRabbitsLived(int maleRabbits, int femaleRabbits);

    void displayMaleRabbitsAlive(int rabbits);
    void displayFemaleRabbitsAlive(int rabbits);
    void displayRabbitsAlive(int maleRabbits, int femaleRabbits);


}
