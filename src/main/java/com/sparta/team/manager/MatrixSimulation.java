package com.sparta.team.manager;


import com.sparta.team.display.DisplayManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MatrixSimulation {

    static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    static Logger log = Logger.getLogger(MatrixSimulation.class.getName());


    private int rabbitMaturity = 3;
    private int rabbitLifespan = 60;

    private int foxMaturity = 10;
    private int foxLifespan = 60;

    private List<Long> femaleRabbitsByAge;
    private List<Long> maleRabbitsByAge;

    private List<Long> femaleFoxesByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));
    private List<Long> maleFoxesByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));

    private long maleRabbitsLived = 0;
    private long femaleRabbitsLived = 0;

    private long maleRabbitsAlive = 0;
    private long femaleRabbitsAlive = 0;

    private long rabbitsEaten = 0;

    private long maleFoxesLived = 0;
    private long femaleFoxesLived = 0;

    private long maleFoxesAlive = 0;
    private long femaleFoxesAlive = 0;

    private int monthsElapsed = 0;
    private int numberOfMonths = 0;

    private int maxNumberOfRabbitsFoxEats = 20;

    private int foxBreedingFrequency = 12;
    private int foxIntroductionMonth = 0;

    private int foxLitterSize = 10;
    private int rabbitLitterSize= 14;





    Random random = new Random();


    public void startSimulation(int numberOfMonthsTemp, int displayOutputType) {


        initialiseLogging();
        importProperties();
        populateInitialGeneration();
        DisplayManager displayManager = new DisplayManager(displayOutputType);





        //flow of time
        //if there are no more rabbits - game over
        while ((getRabbitsAlive() > 0) && monthsElapsed < numberOfMonths) {
            log.trace("------------------------------");
            log.trace("Generation " + monthsElapsed + " start");
            log.trace("---------------");

            update();

            log.trace("---------------");
            log.trace("Generation " + monthsElapsed + " end");
            log.trace("------------------------------");


            if (monthsElapsed == foxIntroductionMonth) {
                releaseTheHounds();
            }


            if (displayOutputType == 1) {
                displayManager.displayTimeElapsed(monthsElapsed);
                displayManager.displayAnimalsAlive("Rabbits", maleRabbitsAlive, femaleRabbitsAlive);
                displayManager.displayAnimalsAlive("Foxes", maleFoxesAlive, femaleFoxesAlive);
                long numberOfRabbitsDied = (maleRabbitsLived + femaleRabbitsLived) - (getRabbitsAlive());
                displayManager.displayAnimalsDied(rabbitsEaten, numberOfRabbitsDied);

            }


        }


        if (displayOutputType == 2) {
            displayManager.displayTimeElapsed(monthsElapsed);
            displayManager.displayAnimalsAlive("Rabbits", getMaleRabbitsAlive(), femaleRabbitsAlive);
            displayManager.displayAnimalsAlive("Foxes", maleFoxesAlive, femaleFoxesAlive);
            long numberOfRabbitsDied = (maleRabbitsLived + femaleRabbitsLived) - (getRabbitsAlive());
            displayManager.displayAnimalsDied(rabbitsEaten, numberOfRabbitsDied);
        }
        displayManager.writerClose();
    }




//    public static long getMaleRabbitsAlive(Long[] array) {
//        return Arrays.stream(array)
//                .mapToLong(Long::longValue)
//                .sum();
//    }




    public void importProperties() {

        Properties properties = new Properties();

        try {
            properties.load(new FileReader("resources/simulation.properties"));

            String numberOfMonths = properties.getProperty("numberOfMonths");
            this.numberOfMonths = Integer.parseInt(numberOfMonths);

            String rabbitMaturity = properties.getProperty("rabbitMaturity");
            this.rabbitMaturity = Integer.parseInt(rabbitMaturity);

            String rabbitLifespan = properties.getProperty("rabbitLifespan");
            this.rabbitLifespan = Integer.parseInt(rabbitLifespan);

            String foxMaturity = properties.getProperty("foxMaturity");
            this.foxMaturity = Integer.parseInt(foxMaturity);

            String foxLifespan = properties.getProperty("foxLifespan");
            this.foxLifespan = Integer.parseInt(foxLifespan);

            String foxIntroductionMonth = properties.getProperty("foxIntroductionMonth");
            this.foxIntroductionMonth = Integer.parseInt(foxIntroductionMonth);

            String foxBreedingFrequency = properties.getProperty("foxBreedingFrequency");
            this.foxBreedingFrequency = Integer.parseInt(foxBreedingFrequency);

            String rabbitMaxLitterSize = properties.getProperty("rabbitMaxLitterSize");
            rabbitLitterSize = Integer.parseInt(rabbitMaxLitterSize);

            String foxMaxLitterSize = properties.getProperty("foxMaxLitterSize");
            foxLitterSize = Integer.parseInt(foxMaxLitterSize);




        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void populateInitialGeneration() {
        femaleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));
        maleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(rabbitLifespan, 0l));


        log.trace("Adam and Eve added");
        femaleRabbitsByAge.set(0, 1l);
        maleRabbitsByAge.set(0, 1l);


        femaleRabbitsLived++;
        maleRabbitsLived++;

        femaleRabbitsAlive++;
        maleRabbitsAlive++;


    }


    public void releaseTheHounds() {

        log.trace("Lucifer and Philip");
        femaleFoxesByAge.set(0, 1l);
        maleFoxesByAge.set(0, 1l);


        femaleFoxesLived++;
        maleFoxesLived++;

        femaleFoxesAlive++;
        maleFoxesAlive++;


    }


    public void update() {

        log.trace("Female rabbit population at start: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population at start: " + maleRabbitsByAge.toString());
        log.trace("Female fox population at start: " + femaleFoxesByAge.toString());
        log.trace("Male fox population at start: " + maleFoxesByAge.toString());


        //getting the number of couples for Rabbits
        long numberOfRabbitCouples = getAnimalCouples(rabbitMaturity, femaleRabbitsByAge, maleRabbitsByAge);
        log.trace("Number of rabbit couples available: " + numberOfRabbitCouples);

        //getting the number of couples for Foxes
        long numberOfFoxCouples = 0;

        //if ((monthsElapsed - foxIntroductionMonth) % foxBreedingFrequency == 0) {
            numberOfFoxCouples = getAnimalCouples(foxMaturity, femaleFoxesByAge, maleFoxesByAge);
            log.trace("Number of fox couples available: " + numberOfFoxCouples);

        //}


        ageAnimals();
        log.trace("Female rabbit population after aging: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after aging: " + maleRabbitsByAge.toString());

        log.trace("Female fox population after aging: " + femaleFoxesByAge.toString());
        log.trace("Male fox population after aging: " + maleFoxesByAge.toString());


        //breeding
        generateNewGenerationExperimental(numberOfRabbitCouples, rabbitLitterSize, "Rabbit");
        log.trace("Female rabbit population after new population born: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after new population born: " + maleRabbitsByAge.toString());


        if ((monthsElapsed - foxIntroductionMonth) % foxBreedingFrequency == 0) {
            //breed foxes method
            generateNewGeneration(numberOfFoxCouples, foxLitterSize, "Fox");
            log.trace("Female fox population after new population born: " + femaleFoxesByAge.toString());
            log.trace("Male fox population after new population born: " + maleFoxesByAge.toString());

        }


        removeDeadAnimals();
        log.trace("Female rabbit population after funerals: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after funerals: " + maleRabbitsByAge.toString());
        log.trace("Female fox population after funerals: " + femaleFoxesByAge.toString());
        log.trace("Male fox population after funerals: " + maleFoxesByAge.toString());
        updateAliveAnimals();


        foxesEatRabbits();
        log.trace("Female rabbit population after feast: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after feast: " + maleRabbitsByAge.toString());

        monthsElapsed++;


    }






    public long getAnimalCouples(int animalMaturity, List<Long> femaleAnimalsByAge, List<Long> maleAnimalsByAge) {

        long numberOfMatureFemaleAnimals = 0;
        long numberOfMatureMaleAnimals = 0;

        // add up all the values between index 3 and the last index
        for (int i = animalMaturity; i < femaleAnimalsByAge.size(); i++) {
            numberOfMatureFemaleAnimals += femaleAnimalsByAge.get(i);
            numberOfMatureMaleAnimals += maleAnimalsByAge.get(i);
        }

        long numberOfCouples = 0;

        //determine how many times we have to breed
        if (numberOfMatureFemaleAnimals < numberOfMatureMaleAnimals) {
            numberOfCouples = numberOfMatureFemaleAnimals;
        } else {
            numberOfCouples = numberOfMatureMaleAnimals;
        }


        return numberOfCouples;
    }


    public void foxesEatRabbits() {
        //exit this method if no more bunnies

        long totalNumberOfFoxes = maleFoxesAlive + femaleFoxesAlive;
        long numberOfRabbitsToEat = (long) (random.nextDouble() * (totalNumberOfFoxes * maxNumberOfRabbitsFoxEats));


        for (int i = 0; i < numberOfRabbitsToEat; i++) {

            if (getRabbitsAlive() == 0) break;
            long randomRabbitNumber = (long) (random.nextDouble() * (maleRabbitsAlive + femaleRabbitsAlive));
            killRandomRabbit(randomRabbitNumber);

        }
    }


    public void killRandomRabbit(long randomRabbitNumber) {


        long currentRabbitNumber = 0;


        //if the random rabbit is female
        if (randomRabbitNumber < femaleRabbitsAlive) {
            for (int i = 0; i < femaleRabbitsByAge.size(); i++) {
                currentRabbitNumber += femaleRabbitsByAge.get(i);
                if (randomRabbitNumber < currentRabbitNumber) {
                    //kill a rabbit in this age group
                    femaleRabbitsByAge.set(i, (femaleRabbitsByAge.get(i) - 1));
                    femaleRabbitsAlive--;
                    rabbitsEaten++;
                    break;
                }
                //want to kill 4th rabbit
                // [1, 0, 2, 3, 4]
            }
        }
        //if not then it's male
        else {
            currentRabbitNumber = femaleRabbitsAlive;
            for (int i = 0; i < maleRabbitsByAge.size(); i++) {
                currentRabbitNumber += maleRabbitsByAge.get(i);
                if (randomRabbitNumber < currentRabbitNumber) {
                    //kill a rabbit in this age group
                    maleRabbitsByAge.set(i, (maleRabbitsByAge.get(i) - 1));
                    maleRabbitsAlive--;
                    rabbitsEaten++;
                    break;
                }

            }
        }
    }


    public void generateNewGeneration(long numberOfCouples, int litterSize, String animalType) {
        long newFemaleGeneration = 0;
        long newMaleGeneration = 0;

        int numberOfNewAnimals;

        boolean coupleSuccess;

        //iterate over all the rabbit couples
        //log.trace("---------");
        for (int i = 0; i < numberOfCouples; i++) {
            //will the couple be successful?

            //log.trace(animalType + " Couple " + i + " trying for kids");
            coupleSuccess = random.nextBoolean();
            //log.trace("was successful: " + coupleSuccess);
            if (coupleSuccess) {
                //one couple makes new rabbits

                //picking number of rabbits
                numberOfNewAnimals = random.nextInt(litterSize) + 1;
                //log.trace("they had " + numberOfNewAnimals + " kids");
                for (int j = 0; j < numberOfNewAnimals; j++) {

                    boolean isFemale = random.nextBoolean();


                    if (isFemale) {
                        newFemaleGeneration++;
                        if (animalType.equals("Rabbit")) {
                            femaleRabbitsLived++;
                        } else {
                            femaleFoxesLived++;
                        }

                    } else {
                        newMaleGeneration++;
                        if (animalType.equals("Rabbit")) {
                            maleRabbitsLived++;
                        } else {
                            maleFoxesLived++;
                        }

                    }


                }
            }
            //log.trace("---");
        }
        //log.trace("---------");

        if (animalType.equals("Rabbit")) {
            femaleRabbitsByAge.set(0, newFemaleGeneration);
            maleRabbitsByAge.set(0, newMaleGeneration);
        } else {
            femaleFoxesByAge.set(0, newFemaleGeneration);
            maleFoxesByAge.set(0, newMaleGeneration);
        }

    }



    public void generateNewGenerationExperimental(long numberOfAttemptingCouples, int litterSize, String animalType) {
        long newFemaleGeneration = 0;
        long newMaleGeneration = 0;


        boolean coupleSuccess;

        long numberOfCouples = 0;
        for(int i = 0; i < numberOfAttemptingCouples; i++){
            coupleSuccess = random.nextBoolean();
            if(!coupleSuccess) numberOfCouples++;
        }

        long LOWER_RANGE = numberOfCouples; //assign lower range value
        long UPPER_RANGE = numberOfCouples*litterSize; //assign upper range value
        long numberOfNewAnimals = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));

        long splitNumber = (long) (random.nextDouble()*numberOfNewAnimals);

        newFemaleGeneration = splitNumber;
        newMaleGeneration = numberOfNewAnimals - splitNumber;


        newFemaleGeneration++;
        if (animalType.equals("Rabbit")) {
            femaleRabbitsLived+= newFemaleGeneration;
        } else {
            femaleFoxesLived+= newFemaleGeneration;
        }


        newMaleGeneration++;
        if (animalType.equals("Rabbit")) {
            maleRabbitsLived+=newMaleGeneration;
        } else {
            maleFoxesLived+=newMaleGeneration;
        }




        if (animalType.equals("Rabbit")) {
            femaleRabbitsByAge.set(0, newFemaleGeneration);
            maleRabbitsByAge.set(0, newMaleGeneration);
        } else {
            femaleFoxesByAge.set(0, newFemaleGeneration);
            maleFoxesByAge.set(0, newMaleGeneration);
        }

    }





    public void ageAnimals() {
        femaleRabbitsByAge.add(0, 0l);
        maleRabbitsByAge.add(0, 0l);
        femaleFoxesByAge.add(0, 0l);
        maleFoxesByAge.add(0, 0l);
    }


    public void removeDeadAnimals() {
        femaleRabbitsByAge.remove(femaleRabbitsByAge.size() - 1);
        maleRabbitsByAge.remove(maleRabbitsByAge.size() - 1);

        femaleFoxesByAge.remove(femaleRabbitsByAge.size() - 1);
        maleFoxesByAge.remove(maleRabbitsByAge.size() - 1);
    }


    public void updateAliveAnimals() {
        long newFemaleRabbitsAlive = 0;
        long newMaleRabbitsAlive = 0;

        long newFemaleFoxesAlive = 0;
        long newMaleFoxesAlive = 0;

        for (long lo : femaleRabbitsByAge) {
            newFemaleRabbitsAlive += lo;
        }

        for (long lo : maleRabbitsByAge) {
            newMaleRabbitsAlive += lo;
        }

        for (long lo : maleFoxesByAge) {
            newMaleFoxesAlive += lo;
        }

        for (long lo : femaleFoxesByAge) {
            newFemaleFoxesAlive += lo;
        }

        femaleRabbitsAlive = newFemaleRabbitsAlive;
        maleRabbitsAlive = newMaleRabbitsAlive;
        femaleFoxesAlive = newFemaleFoxesAlive;
        maleFoxesAlive = newMaleFoxesAlive;
    }


    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
    }


    public int getRabbitMaturity() {
        return rabbitMaturity;
    }

    public int getRabbitLifespan() {
        return rabbitLifespan;
    }

    public List<Long> getFemaleRabbitsByAge() {
        return femaleRabbitsByAge;
    }

    public List<Long> getMaleRabbitsByAge() {
        return maleRabbitsByAge;
    }

    public long getMaleRabbitsLived() {
        return maleRabbitsLived;
    }

    public long getFemaleRabbitsLived() {
        return femaleRabbitsLived;
    }

    public long getMaleRabbitsAlive() {
        return maleRabbitsAlive;
    }

    public long getFemaleRabbitsAlive() {
        return femaleRabbitsAlive;
    }

    public long getRabbitsAlive() {
        return getFemaleRabbitsAlive() + getMaleRabbitsAlive();
    }
}
