package io.muic.ooc.fab;

public abstract class Animal {

    // Individual characteristics (instance fields).
    // Whether the Animal is alive or not.
    private boolean alive = true;
    // The Animal's age.
    private int age = 0;

    protected abstract int getMaxAge();

    protected abstract void setDead();

    protected abstract int getBreedingAge();

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
        if (age > getMaxAge) {
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
}
