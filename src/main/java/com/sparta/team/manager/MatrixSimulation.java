package com.sparta.team.manager;

import com.sparta.team.display.DisplayManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatrixSimulation {

    static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    static Logger log = Logger.getLogger(MatrixSimulation.class.getName());


    private final int RABBITMATURITY = 3;
    private final int RABBITLIFESPAN = 60;

    private final int FOXMATURITY = 10;
    private final int FOXLIFESPAN = 60;

    private List<Long> femaleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(RABBITLIFESPAN, 0l));
    private List<Long> maleRabbitsByAge = new ArrayList<Long>(Collections.nCopies(RABBITLIFESPAN, 0l));

    private List<Long> femaleFoxesByAge = new ArrayList<Long>(Collections.nCopies(RABBITLIFESPAN, 0l));
    private List<Long> maleFoxesByAge = new ArrayList<Long>(Collections.nCopies(RABBITLIFESPAN, 0l));

    private long maleRabbitsLived = 0;
    private long femaleRabbitsLived = 0;

    private long maleRabbitsAlive = 0;
    private long femaleRabbitsAlive = 0;

    private long maleFoxesLived = 0;
    private long femaleFoxesLived = 0;

    private long maleFoxesAlive = 0;
    private long femaleFoxesAlive = 0;

    private int monthsElapsed = 0;

    private int maxNumberOfRabbitsFoxEats = 20;

    Random random = new Random();



    public void startSimulation(int numberOfMonths, int displayOutputType){

        initialiseLogging();
        populateInitialGeneration();
        DisplayManager displayManager = new DisplayManager(displayOutputType);

        //flow of time
        //if there are no more rabbits - game over
        while((maleRabbitsAlive+femaleFoxesAlive > 0 ) && monthsElapsed < numberOfMonths){
            log.trace("------------------------------");
            log.trace("Generation " + monthsElapsed + " start");
            log.trace("---------------");

            update();

            log.trace("---------------");
            log.trace("Generation " + monthsElapsed + " end");
            log.trace("------------------------------");


            if(displayOutputType == 1){
                displayManager.displayTimeElapsed(monthsElapsed);
                displayManager.displayRabbitsAlive(maleRabbitsAlive, femaleRabbitsAlive);
                displayManager.displayRabbitsLived(maleRabbitsLived, femaleRabbitsLived);
            }

            monthsElapsed++;
        }



        if(displayOutputType == 2){
            displayManager.displayTimeElapsed(numberOfMonths);
            displayManager.displayRabbitsAlive(maleRabbitsAlive, femaleRabbitsAlive);
            displayManager.displayRabbitsLived(maleRabbitsLived, femaleRabbitsLived);
        }
        displayManager.writerClose();
    }



    public void populateInitialGeneration(){

        log.trace("Adam and Eve added");
        femaleRabbitsByAge.set(0, 1l);
        maleRabbitsByAge.set(0, 1l);

        log.trace("Lucifer and Philip");
        femaleFoxesByAge.set(0, 1l);
        maleFoxesByAge.set(0, 1l);

        femaleRabbitsLived++;
        maleRabbitsLived++;

        femaleFoxesLived++;
        maleFoxesLived++;
    }



    public void update(){

        log.trace("Female rabbit population at start: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population at start: " + maleRabbitsByAge.toString());





        //getting the number of couples for Rabbits
        long numberOfRabbitCouples = getAnimalCouples(RABBITMATURITY, femaleRabbitsByAge, maleRabbitsByAge);
        log.trace("Number of couples available: " + numberOfRabbitCouples);

        //getting the number of couples for Foxes
        long numberOfFoxCouples = 0;
        if(monthsElapsed % 12 == 0){
            numberOfFoxCouples = getAnimalCouples(FOXMATURITY, femaleFoxesByAge, maleFoxesByAge);
        }





        ageAnimals();
        log.trace("Female rabbit population after aging: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after aging: " + maleRabbitsByAge.toString());





        //breeding
        generateNewGeneration(numberOfRabbitCouples, 14, "Rabbit");
        log.trace("Female rabbit population after new population born: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after new population born: " + maleRabbitsByAge.toString());

        if(monthsElapsed % 12 == 0){
            //breed foxes method
            generateNewGeneration(numberOfFoxCouples, 10, "Fox");

        }


        removeDeadAnimals();
        log.trace("Female rabbit population after funerals: " + femaleRabbitsByAge.toString());
        log.trace("Male rabbit population after funerals: " + maleRabbitsByAge.toString());
        updateAliveAnimals();



        foxesEatRabbits();



    }


    public long getAnimalCouples(int animalMaturity, List<Long> femaleAnimalsByAge, List<Long> maleAnimalsByAge){

        long numberOfMatureFemaleAnimals = 0;
        long numberOfMatureMaleAnimals = 0;

        // add up all the values between index 3 and the last index
        for(int i = animalMaturity; i < femaleAnimalsByAge.size(); i++){
            numberOfMatureFemaleAnimals += femaleAnimalsByAge.get(i);
            numberOfMatureMaleAnimals += maleAnimalsByAge.get(i);
        }

        long numberOfCouples = 0;

        //determine how many times we have to breed
        if(numberOfMatureFemaleAnimals < numberOfMatureMaleAnimals){
            numberOfCouples = numberOfMatureFemaleAnimals;
        }
        else{
            numberOfCouples = numberOfMatureMaleAnimals;
        }


        return numberOfCouples;
    }


    public void foxesEatRabbits(){
        //exit this method if no more bunnies

        long totalNumberOfFoxes = maleFoxesAlive + femaleFoxesAlive;
        long numberOfRabbitsToEat = (long) random.nextDouble() * totalNumberOfFoxes*maxNumberOfRabbitsFoxEats;


        boolean victimIsFemale = random.nextBoolean();

        /*
        select the rabbit to be eaten from an index
           - can't be 0!



        */
        for(int i = 0; i < numberOfRabbitsToEat; i++){

            int randomAgeIndex = random.nextInt(femaleRabbitsByAge.size());

            if(victimIsFemale){


                //find the oldest rabbit in a list
                //finding the last nonzero value in a list




            }
            else{

            }



        }




    }



    public void generateNewGeneration(long numberOfCouples, int litterSize, String animalType)
    {
        long newFemaleGeneration =  0;
        long newMaleGeneration = 0;

        int numberOfNewAnimals;

        boolean coupleSuccess;

        //iterate over all the rabbit couples
        log.trace("---------");
        for(int i = 0; i < numberOfCouples; i++) {
            //will the couple be successful?

            log.trace("Couple " + i + " trying for kids");
            coupleSuccess = random.nextBoolean();
            log.trace("was successful: " + coupleSuccess);
            if(coupleSuccess) {
                //one couple makes new rabbits

                //picking number of rabbits
                numberOfNewAnimals = random.nextInt(litterSize) + 1;
                log.trace("they had " + numberOfNewAnimals + " kids");
                for (int j = 0; j < numberOfNewAnimals; j++) {

                    boolean isFemale = random.nextBoolean();


                    if (isFemale) {
                        newFemaleGeneration++;
                        if(animalType.equals("Rabbit")) femaleRabbitsLived++;
                        else femaleFoxesLived++;

                    } else {
                        newMaleGeneration++;
                        if(animalType.equals("Rabbit")) maleRabbitsLived++;
                        else maleFoxesLived++;

                    }



                }
            }
            log.trace("---");
        }
        log.trace("---------");

        if(animalType.equals("Rabbit")) {
            femaleRabbitsByAge.set(0, newFemaleGeneration);
            maleRabbitsByAge.set(0, newMaleGeneration);
        }
        else{
            femaleFoxesByAge.set(0, newFemaleGeneration);
            maleFoxesByAge.set(0, newMaleGeneration);
        }

    }


    public void ageAnimals(){
        femaleRabbitsByAge.add(0, 0l);
        maleRabbitsByAge.add(0, 0l);
        femaleFoxesByAge.add(0, 0l);
        maleFoxesByAge.add(0, 0l);
    }


    public void removeDeadAnimals(){
        femaleRabbitsByAge.remove(femaleRabbitsByAge.size()-1);
        maleRabbitsByAge.remove(maleRabbitsByAge.size()-1);

        femaleFoxesByAge.remove(femaleRabbitsByAge.size()-1);
        maleFoxesByAge.remove(maleRabbitsByAge.size()-1);
    }


    public void updateAliveAnimals(){
        long newFemaleRabbitsAlive = 0;
        long newMaleRabbitsAlive = 0;

        long newFemaleFoxesAlive = 0;
        long newMaleFoxesAlive = 0;

        for(long l : femaleRabbitsByAge){
            newFemaleRabbitsAlive += l;
        }

        for(long l : maleRabbitsByAge){
            newMaleRabbitsAlive += l;
        }

        for(long l : maleFoxesByAge){
            newMaleFoxesAlive += 1;
        }

        for(long l : femaleFoxesByAge){
            newMaleFoxesAlive += 1;
        }

        femaleRabbitsAlive = newFemaleRabbitsAlive;
        maleRabbitsAlive = newMaleRabbitsAlive;
        femaleFoxesAlive = newFemaleFoxesAlive;
        maleRabbitsAlive = newMaleFoxesAlive;
    }



    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
    }




    public int getRABBITMATURITY() {
        return RABBITMATURITY;
    }

    public int getRABBITLIFESPAN() {
        return RABBITLIFESPAN;
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
}
