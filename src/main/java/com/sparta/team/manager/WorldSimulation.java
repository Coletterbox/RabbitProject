package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class WorldSimulation {

    private List<Animal> maleRabbits = new ArrayList();
    private List<Animal> femaleRabbits = new ArrayList();
    private List<Animal> maleFoxes = new ArrayList();
    private List<Animal> femaleFoxes = new ArrayList();

    private long maleRabbitsLived = 0;
    private long femaleRabbitsLived = 0;
    private long maleFoxesLived = 0;
    private long femaleFoxesLived = 0;
    private long maleRabbitsEaten = 0;
    private long femaleRabbitsEaten = 0;

    private int startEatingAt = 1;
    private int introduceFoxesAt = 8;

    public void startSimulation(int numberOfSeconds, int outputType) {

        //hardcoded initial population
        maleRabbits.add(new MaleRabbit());
        femaleRabbits.add(new FemaleRabbit());
        maleRabbitsLived++;
        femaleRabbitsLived++;
        DisplayManager displayManager = new DisplayManager(outputType);

        for (int i = 0; i < numberOfSeconds; i++) {
            update();
            if (i == introduceFoxesAt) {
                maleFoxes.add(new MaleFox());
                femaleFoxes.add(new FemaleFox());
                maleFoxesLived++;
                femaleFoxesLived++;
            }
            if (i > introduceFoxesAt) {
                updateFoxes();
            }
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
    }
    private void updateFoxes() {
        updateAge(maleFoxes);
        updateAge(femaleFoxes);
        foxesEatRabbits();
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
        int maleIndex = 0;
        int femaleIndex = 0;
        Random random = new Random();

        while (maleIndex < males.size() && femaleIndex < females.size()) {
            FemaleAnimal femaleAnimal = (FemaleAnimal) females.get(femaleIndex);
            if (!(males.get(maleIndex).isMature())) {
                maleIndex++;
            } else if (!(femaleAnimal.isMature()) && !(femaleAnimal.isPregnant())) {
                femaleIndex++;
            } else {
                if (random.nextBoolean()) {
                    toAddAnimals.addAll((Collection<? extends Animal>) femaleAnimal.giveBirth());
                }
                maleIndex++;
                femaleIndex++;
            }
        }
        addNewAnimals(toAddAnimals);
    }

    private void addNewAnimals(List<? extends Animal> listOfAnimals) {
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

    private void foxesEatRabbits() {
//        List<Rabbit> allRabbits = new ArrayList<>();
//        allRabbits.addAll(maleRabbits);
//        allRabbits.addAll(femaleRabbits);
        List<Animal> allFoxes = new ArrayList<>();
        allFoxes.addAll(maleFoxes);
        allFoxes.addAll(femaleFoxes);
        int randomElement;
//        List<Rabbit> toRemove = new ArrayList<>();

        for (Animal fox : allFoxes) {
            if (fox.getAgeInMonths() >= startEatingAt) {
                Random random = new Random();
                int numberOfRabbitsEaten = random.nextInt(20) + 1;
                for (int i = 0; i < numberOfRabbitsEaten; i++) {
                    if (random.nextBoolean()) {
                        randomElement = (int) ((Math.random() * (femaleRabbits.size() - 1)));
                        if (femaleRabbits.size() > randomElement) {
                            femaleRabbits.remove(randomElement);
                            femaleRabbitsEaten++;
                        }
                    } else {
                        randomElement = (int) ((Math.random() * (femaleRabbits.size() - 1)));
                        if (maleRabbits.size() > randomElement) {
                            maleRabbits.remove(randomElement);
                            maleRabbitsEaten++;
                        }
                    }
                }
            }
        }
    }
}
