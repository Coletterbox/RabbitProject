package com.sparta.team.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatrixSimulation {



    private List<Long> femaleRabbitsByAge = new ArrayList<>();
    private List<Long> maleRabbitsByAge = new ArrayList<>();

    private final int RABBITMATURITY = 3;


    private long maleRabbitsLived = 0;
    private long femaleRabbitsLived = 0;

    private long maleRabbitAlive = 0;
    private long femaleRabbitsAlive = 0;



    public void startSimulation(){

        int numberOfMonths = 120;
        int rabbitLifespan = 5;

        femaleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));
        maleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));

        femaleRabbitsByAge.set(0, 1l);
        maleRabbitsByAge.set(0, 1l);
        femaleRabbitsLived++;
        maleRabbitsLived++;




        //flow of time
        for(int i = 0; i < numberOfMonths; i++){


            update();

            System.out.println("Generation " + i);
            System.out.println(femaleRabbitsLived);
            System.out.println(maleRabbitsLived);
        }
    }



    public void update(){


        long numberOfCouples = getRabbitCouples();
        ageRabbits();
        generateNewGeneration(numberOfCouples);
        removeDeadRabbits();



    }


    public long getRabbitCouples(){

        long numberOfMatureFemaleRabbits = 0;
        long numberOfMatureMaleRabbits = 0;

        // add up all the values between index 3 and the last index
        for(int i = RABBITMATURITY; i < femaleRabbitsByAge.size(); i++){
            numberOfMatureFemaleRabbits += femaleRabbitsByAge.get(i);
            numberOfMatureMaleRabbits += maleRabbitsByAge.get(i);
        }

        long numberOfCouples = 0;

        //determine how many times we have to breed
        if(numberOfMatureFemaleRabbits < numberOfMatureMaleRabbits){
            numberOfCouples = numberOfMatureFemaleRabbits;
        }
        else{
            numberOfCouples = numberOfMatureMaleRabbits;
        }


        return numberOfCouples;
    }






    public void ageRabbits(){
        femaleRabbitsByAge.add(0, 0l);
        maleRabbitsByAge.add(0, 0l);
    }






    public void generateNewGeneration(long numberOfCouples)
    {
        long newFemaleGeneration =  0;
        long newMaleGeneration = 0;

        Random random = new Random();
        int numberOfNewRabbits = random.nextInt(14) + 1;

        boolean coupleSuccess;

        //iterate over all the rabbit couples
        for(int i = 0; i < numberOfCouples; i++) {
            //will the couple be successful?
            coupleSuccess = random.nextBoolean();
            if(coupleSuccess) {
                //one couple makes new rabbits
                for (int j = 0; j < numberOfNewRabbits; j++) {

                    boolean isFemale = random.nextBoolean();

                    if (isFemale) {
                        newFemaleGeneration++;
                        femaleRabbitsLived++;
                    } else {
                        newMaleGeneration++;
                        maleRabbitsLived++;
                    }
                }
            }

        }

        femaleRabbitsByAge.set(0,newFemaleGeneration);
        maleRabbitsByAge.set(0,newMaleGeneration);

    }






    public void removeDeadRabbits(){
        femaleRabbitsByAge.remove(femaleRabbitsByAge.size()-1);
        maleRabbitsByAge.remove(maleRabbitsByAge.size()-1);
    }








}
