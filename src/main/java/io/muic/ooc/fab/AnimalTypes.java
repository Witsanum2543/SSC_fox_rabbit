package io.muic.ooc.fab;

import java.awt.*;

public enum AnimalTypes {

    RABBIT(Rabbit.class, Color.ORANGE, 0.08),
    FOX(Fox.class, Color.BLUE, 0.02);

    private Class<? extends Animal> animalClass;

    private Color color;

    private double probability;

    AnimalTypes(Class<? extends Animal> animalClass, Color color, double probability) {
        this.animalClass = animalClass;
        this.color = color;
        this.probability = probability;
    }

    public Class<? extends Animal> getAnimalClass() {
        return animalClass;
    }

    public Color getColor() {
        return color;
    }

    public double getProbability() {
        return probability;
    }
}
