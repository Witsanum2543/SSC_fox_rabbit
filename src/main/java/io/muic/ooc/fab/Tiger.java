package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Tiger extends Animal{
    // The age at which a Tiger can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a Tiger can live.
    private static final int MAX_AGE = 120;
    // The likelihood of a Tiger breeding.
    private static final double BREEDING_PROBABILITY = 0.06;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    // Random generator
    private static final Random RANDOM = new Random();

    // Individual characteristics (instance fields).

    // The Tiger's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a Tiger. A Tiger can be created as a new born (age zero and not
     * hungry) or with a random age and food level.
     *
     * @param randomAge If true, the Tiger will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Tiger(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        foodLevel = RANDOM.nextInt(FoodValue.RABBIT_FOOD_VALUE.getFoodValue());
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    /**
     * This is what the Tiger does most of the time: it hunts for rabbits. In the
     * process, it might breed, die of hunger, or die of old age.
     *
     * @param newTiger A list to return newly born Tigers.
     */
    public void act(List<Animal> newTiger) {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            giveBirth(newTiger);
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Make this Tiger more hungry. This could result in the Tiger's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits/foxes adjacent to the current location. Only the first live
     * rabbit or fox is eaten.
     *
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood() {
        List<Location> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object animal = getField().getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;

                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = FoodValue.RABBIT_FOOD_VALUE.getFoodValue();
                    return where;
                }
            }
            else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = FoodValue.FOX_FOOD_VALUE.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not this Tiger is to give birth at this step. New births
     * will be made into free adjacent locations.
     *
     * @param newTiger A list to return newly born Tigers.
     */
    private void giveBirth(List<Animal> newTiger) {
        // New Tigers are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Tiger young = new Tiger(false, getField(), loc);
            newTiger.add(young);
        }
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }
}
