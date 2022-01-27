package io.muic.ooc.fab;

import java.util.Random;

public abstract class Animal {
    // Characteristics shared by all rabbits (class variables).
    // A shared random number generator to control breeding.
    private static final Random RANDOM = new Random();

    // Individual characteristics (instance fields).
    // Whether the Animal is alive or not.
    private boolean alive = true;
    // The Animal's age.
    private int age = 0;
    // The fox's position.
    private Location location;
    // The field occupied.
    private Field field;

    protected abstract int getMaxAge();

    protected abstract int getBreedingAge();

    public Location getLocation() {
        return location;
    }

    public Field getField() {
        return field;
    }

    public Animal(boolean randomAge, Field field, Location location) {
        this.field = field;
        setLocation(location);
        if (randomAge) {
            setAge(RANDOM.nextInt(getMaxAge()));
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Increase the age. This could result in the Animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * An Animal can breed if it has reached the breeding age.
     *
     * @return true if the Animal can breed, false otherwise.
     */
    protected boolean canBreed() {
        return getAge() >= getBreedingAge();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Check whether the Animal is alive or not.
     *
     * @return true if the Animal is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Place the Animal at the new location in the given field.
     *
     * @param newLocation The Animal's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Indicate that the Animal is no longer alive. It is removed from the
     * field.
     */
    protected void setDead() {
        setAlive(false);
        if (getLocation() != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    protected abstract int getMaxLitterSize();

    protected abstract double getBreedingProbability();

}
