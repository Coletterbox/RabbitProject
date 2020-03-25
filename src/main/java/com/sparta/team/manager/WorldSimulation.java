package com.sparta.team.manager;

import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;

import java.util.ArrayList;
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
    }


    private void update() {
        /*
            1. age rabbits one month
            2. remove dead rabbits
            3. if there is a male rabbit that is mature and a female rabbit that is mature, get the female rabbit pregnant
            4. if there is a female rabbit that is ready to give birth, add those new rabbits
         */
        boolean maleRabbitMature = false;

        for (MaleRabbit r : maleRabbits) {
            r.incrementAge();
            if (!r.isAlive()) {
                removeDeadRabbit(r);
                continue;
            }
            if (r.isMature()) {
                maleRabbitMature = true;
            }
        }


        for (FemaleRabbit r : femaleRabbits) {
            r.incrementAge();
            if (!r.isAlive()) {
                removeDeadRabbit(r);
                continue;
            }
            //this implementation only works for 1 month long pregnancies
            if (r.isPregnant()) {
                addNewRabbits(r.giveBirth());
            } else if (maleRabbitMature && r.isMature()) {
                r.setPregnant(true);
            }
        }
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
