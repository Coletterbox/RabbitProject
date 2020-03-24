package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorldSimulation {

    private List<MaleRabbit> maleRabbits = new ArrayList();
    private List<FemaleRabbit> femaleRabbits = new ArrayList();

    private int maleRabbitsLived = 0;
    private int femaleRabbitsLived = 0;


    public void startSimulation(int numberOfSeconds) {

        //hardcoded initial population
        maleRabbits.add(new MaleRabbit());
        femaleRabbits.add(new FemaleRabbit());
        maleRabbitsLived++;
        femaleRabbitsLived++;

        for (int i = 0; i < numberOfSeconds; i++) {
            update();
        }

        DisplayManager displayManager = new DisplayManager();
        displayManager.displayTimeElapsed(numberOfSeconds);
        displayManager.displayRabbitsAlive(maleRabbits.size(), femaleRabbits.size());
        displayManager.displayRabbitsLived(maleRabbitsLived, femaleRabbitsLived);
    }

    private void update() {
        /*
            1. age rabbits one month
            2. remove dead rabbits
            3. if there is a male rabbit that is mature and a female rabbit that is mature, get the female rabbit pregnant
            4. if there is a female rabbit that is ready to give birth, add those new rabbits
         */
        boolean maleRabbitMature = false;

        List<MaleRabbit> toRemoveMales = new ArrayList<>();

        for (MaleRabbit r : maleRabbits) {
            r.incrementAge();
            if (!r.isAlive()) {
//                removeDeadRabbit(r);
                toRemoveMales.add(r);
                continue;
            }
            if (r.isMature()) {
                maleRabbitMature = true;
            }
        }
        maleRabbits.removeAll(toRemoveMales);

        List<FemaleRabbit> toRemoveFemales = new ArrayList<>();
        List<Rabbit> toAddRabbits = new ArrayList<>();

        for (FemaleRabbit r : femaleRabbits) {
            r.incrementAge();
            if (!r.isAlive()) {
//                removeDeadRabbit(r);
                toRemoveFemales.add(r);
                continue;
            }
            //this implementation only works for 1 month long pregnancies
            if (r.isPregnant()) {
//                addNewRabbits(r.giveBirth());
                toAddRabbits.addAll(r.giveBirth());
            } else if (maleRabbitMature && r.isMature()) {
                r.setPregnant(true);
            }
        }
        femaleRabbits.removeAll(toRemoveFemales);
        addNewRabbits(toAddRabbits);
    }

    private void removeDeadRabbit(Rabbit r) {
        if (r instanceof MaleRabbit) {
            maleRabbits.remove(r);
        } else {
            femaleRabbits.remove(r);
        }
    }

    private void addNewRabbits(List<Rabbit> rabbitList) {
        for (Rabbit r : rabbitList) {
            if (r instanceof MaleRabbit) {
                maleRabbits.add((MaleRabbit) r);
                maleRabbitsLived++;
            } else {
                femaleRabbits.add((FemaleRabbit) r);
                femaleRabbitsLived++;
            }
        }
    }
}
