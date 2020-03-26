package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldSimulation {

    private List<Animal> maleRabbits = new ArrayList();
    private List<Animal> femaleRabbits = new ArrayList();
    private List<Animal> maleFoxes = new ArrayList();
    private List<Animal> femaleFoxes = new ArrayList();

    private int maleRabbitsLived = 0;
    private int femaleRabbitsLived = 0;
    private int maleFoxesLived = 0;
    private int femaleFoxesLived = 0;

    public void startSimulation(int numberOfSeconds, int outputType) {

        //hardcoded initial population
        maleRabbits.add(new MaleRabbit());
        femaleRabbits.add(new FemaleRabbit());
        maleFoxes.add(new MaleFox());
        femaleFoxes.add(new FemaleFox());
        maleRabbitsLived++;
        femaleRabbitsLived++;
        DisplayManager displayManager = new DisplayManager(outputType);

        for (int i = 0; i < numberOfSeconds; i++) {
            update();
            if (outputType == 1) {
                displayManager.displayTimeElapsed(i);
                displayManager.displayRabbitsAlive(maleRabbits.size(), femaleRabbits.size());
                displayManager.displayRabbitsLived(maleRabbitsLived, femaleRabbitsLived);
                displayManager.displayRabbitsAlive(maleFoxes.size(), femaleFoxes.size());
                displayManager.displayRabbitsLived(maleFoxesLived, femaleFoxesLived);
            }
        }

        if (outputType == 2) {
            displayManager.displayTimeElapsed(numberOfSeconds);
            displayManager.displayRabbitsAlive(maleRabbits.size(), femaleRabbits.size());
            displayManager.displayRabbitsLived(maleRabbitsLived, femaleRabbitsLived);
            displayManager.displayRabbitsAlive(maleFoxes.size(), femaleFoxes.size());
            displayManager.displayRabbitsLived(maleFoxesLived, femaleFoxesLived);
        }
        displayManager.writerClose();


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
        giveBirthAllFemales(maleFoxes, femaleFoxes);
    }

    private List<Animal> updateAge(List<Animal> animals) {
        List<Animal> toRemove = new ArrayList<>();

        for (Animal r : animals) {
            r.incrementAge();
            if (!r.isAlive()) {
                toRemove.add(r);
                continue;
            }
        }
        animals.removeAll(toRemove);
        return animals;
    }

    private void giveBirthAllFemales(List<Animal> males, List<Animal> females) {
        List<Animal> toAddAnimals = new ArrayList<>();
        Animal dummyFemale;
        if (females.get(0) instanceof FemaleRabbit) {
            dummyFemale = new FemaleRabbit();
        } else {
            dummyFemale = new FemaleFox();
        }
        int maleIndex = 0;
        int femaleIndex = 0;
        Random random = new Random();

        while (maleIndex < males.size() && femaleIndex < females.size()) {
            if (!(males.get(maleIndex).isMature())) {
                maleIndex++;
            } else if (!(females.get(femaleIndex).isMature())) {
                femaleIndex++;
            } else {
                if (random.nextBoolean()) {
//                    femaleRabbit.setPregnant(true);
                    toAddAnimals.addAll(dummyFemale.giveBirth());
                }
                maleIndex++;
                femaleIndex++;
            }
        }
        addNewRabbits(toAddAnimals);
    }

    private boolean checkFoxPregnant(FemaleFox fox) {
        if (fox.getAgeInMonths() % 12 == 10) {
            return false;
        }
        return true;
    }

//    private void removeDeadRabbit(Rabbit r) {
//        if (r instanceof MaleRabbit) {
//            maleRabbits.remove(r);
//        } else {
//            femaleRabbits.remove(r);
//        }
//    }

    private void addNewRabbits(List<? extends Animal> listOfAnimals) {
        for (Animal animal : listOfAnimals) {
            if (animal instanceof MaleRabbit) {
                maleRabbits.add(animal);
                maleRabbitsLived++;
            } else if (animal instanceof FemaleRabbit) {
                femaleRabbits.add(animal);
                femaleRabbitsLived++;
            } else if (animal instanceof MaleFox) {
                maleFoxes.add(animal);
                maleFoxesLived++;
            } else if (animal instanceof FemaleFox) {
                femaleFoxes.add(animal);
                femaleFoxesLived++;
            }
        }
    }
}
