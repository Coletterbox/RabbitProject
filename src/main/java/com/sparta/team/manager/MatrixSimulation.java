package com.sparta.team.manager;

import com.sparta.team.model.FemaleRabbit;
import com.sparta.team.model.MaleRabbit;
import com.sparta.team.model.Rabbit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixSimulation {



    private List<Long> femaleRabbitsByAge = new ArrayList<>();


    private int maleRabbitsLived = 0;
    private int femaleRabbitsLived = 0;

    private boolean fertileMaleAvailable = false;


    public void startSimulation(){

        int numberOfMonths = 20;
        int rabbitLifespan = 10;

        femaleRabbitsByAge = new ArrayList<>(10);

        //flow of time
        for(int i = 0; i < 20; i++){

            //BIG ASSUMPTION FOR NOW !!!! DANGER
            //TODO
            if(i == 3) fertileMaleAvailable = true;

            update();




        }
    }



    public void update(){

        //FOLLOW THE WHITE RABBIT
//        femaleRabbitsByAge.add(0, 9812639812l);
//        femaleRabbitsByAge.remove(rabbitLifespan-1);




    }



    public int getNewFemaleGeneration(){
        int newFemaleGeneration =  0;
        Random random = new Random();
        int numberOfRabbits = random.nextInt(14) + 1;

        for (int i = 0; i < numberOfRabbits; i++) {
            int gender = (int) (Math.random() * 2);
            if (gender == 2) gender = 1;
            if (gender == 0) { // IF GENDER IS 0 THEN FEMALE, GENDER IS 1 THEN MALE
                newFemaleGeneration++;
                femaleRabbitsLived++;
            } else {
                maleRabbitsLived++;
            }
        }

        return newFemaleGeneration;
    }






}
