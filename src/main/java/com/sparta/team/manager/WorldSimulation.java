package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldSimulation {

    private List<Rabbit> maleRabbits = new ArrayList();
    private List<Rabbit> femaleRabbits = new ArrayList();

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
        updateAge(maleRabbits);
        updateAge(femaleRabbits);

        giveBirthAllFemales(maleRabbits, femaleRabbits);
    }

    private List<Rabbit> updateAge(List<Rabbit> rabbits) {
        List<Rabbit> toRemove = new ArrayList<>();

        for (Rabbit r : rabbits) {
            r.incrementAge();
            if (!r.isAlive()) {
                toRemove.add(r);
                continue;
            }
        }
        rabbits.removeAll(toRemove);
        return rabbits;
    }

    private void giveBirthAllFemales(List<Rabbit> maleRabbits, List<Rabbit> femaleRabbits) {
        List<Rabbit> toAddRabbits = new ArrayList<>();
        FemaleRabbit dummyFemale = new FemaleRabbit();

        int maleIndex = 0;
        int femaleIndex = 0;
        Random random = new Random();

        while (maleIndex < maleRabbits.size() && femaleIndex < femaleRabbits.size()) {
            if (!(maleRabbits.get(maleIndex).isMature())) {
                maleIndex++;
            } else if (!(femaleRabbits.get(femaleIndex).isMature())) {
                femaleIndex++;
            } else {
                if (random.nextBoolean()) {
//                    femaleRabbit.setPregnant(true);
                    toAddRabbits.addAll(dummyFemale.giveBirth());
                }
                maleIndex++;
                femaleIndex++;
            }
        }
        addNewRabbits(toAddRabbits);
    }

//    private void removeDeadRabbit(Rabbit r) {
//        if (r instanceof MaleRabbit) {
//            maleRabbits.remove(r);
//        } else {
//            femaleRabbits.remove(r);
//        }
//    }

    private void addNewRabbits(List<Rabbit> rabbitList) {
        for (Rabbit r : rabbitList) {
            if (r instanceof MaleRabbit) {
                maleRabbits.add(r);
                maleRabbitsLived++;
            } else {
                femaleRabbits.add(r);
                femaleRabbitsLived++;
            }
        }
    }
}
