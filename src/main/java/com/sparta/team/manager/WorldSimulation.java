package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import com.sparta.team.model.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WorldSimulation {

    static final String LOG_PROPERTIES_FILE_WORLD_SIMULATION = "resources/log4j.properties";
    static Logger logSim = Logger.getLogger(WorldSimulation.class.getName());

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
    private int introduceFoxesAt = 7;

    public void startSimulation(int numberOfSeconds, int outputType) {

        initialiseLogging();

//        Properties properties = new Properties();
//        try {
//            properties.load(new FileReader("resources/simulationSettings.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String foxEatingAge = properties.getProperty("foxEatingAge");
//        this.startEatingAt = Integer.parseInt(foxEatingAge);
//        String foxIntroducedAt = properties.getProperty("foxIntroducedAt");
//        this.introduceFoxesAt = Integer.parseInt(foxIntroducedAt);

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
                displayManager.displayAnimalsAlive("rabbits", maleRabbits.size(), femaleRabbits.size());
                //displayManager.displayAnimalsLived("rabbits", maleRabbitsLived, femaleRabbitsLived);
                displayManager.displayAnimalsAlive("foxes", maleFoxes.size(), femaleFoxes.size());
                //displayManager.displayAnimalsLived("foxes", maleFoxesLived, femaleFoxesLived);
                displayManager.displayAnimalsEaten(maleRabbitsLived + femaleRabbitsLived, maleRabbits.size() + femaleRabbits.size(), maleRabbitsEaten + femaleRabbitsEaten);
            }
        }

        if (outputType == 2) {
            displayManager.displayTimeElapsed(numberOfSeconds);
            displayManager.displayAnimalsAlive("rabbits", maleRabbits.size(), femaleRabbits.size());
            //displayManager.displayAnimalsLived("rabbits", maleRabbitsLived, femaleRabbitsLived);
            displayManager.displayAnimalsAlive("foxes", maleFoxes.size(), femaleFoxes.size());
            //displayManager.displayAnimalsLived("foxes", maleFoxesLived, femaleFoxesLived);
            displayManager.displayAnimalsEaten(maleRabbitsLived + femaleRabbitsLived, maleRabbits.size() + femaleRabbits.size(), maleRabbitsEaten + femaleRabbitsEaten);
        }
        logSim.debug("Finished simulation cycle.");
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
        logSim.debug("Updating age for animals");
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
        List<Animal> allFoxes = new ArrayList<>();
        allFoxes.addAll(maleFoxes);
        allFoxes.addAll(femaleFoxes);
        int randomElement;
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
        logSim.debug("Foxes finished eating rabbits this month.");
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE_WORLD_SIMULATION);
    }
}
