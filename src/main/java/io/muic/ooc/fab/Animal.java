package io.muic.ooc.fab;

public abstract class Animal {

    // The rabbit and fox's age.
    private int age = 0;

    protected abstract int getMaxAge();
    protected abstract void setDead();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Increase the age. This could result in the rabbit's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge) {
            setDead();
        }
    }
}
