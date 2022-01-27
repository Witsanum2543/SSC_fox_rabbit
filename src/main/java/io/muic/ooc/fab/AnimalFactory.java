package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static final Map<AnimalTypes, Class<? extends Animal>> ANIMAL_MAPPING = new HashMap<>();

    static {
        ANIMAL_MAPPING.put(AnimalTypes.RABBIT, Rabbit.class);
        ANIMAL_MAPPING.put(AnimalTypes.FOX, Fox.class);
    }

    public static Animal createAnimal(AnimalTypes animalTypes, boolean randomAge, Field field, Location location) {
        // ANIMAL_MAPPING.get(animalTypes).getDeclaredConstructor(boolean.class, Field.class, Location.class).newInstance(randomAge, field, location);
        if (animalTypes.equals(AnimalTypes.RABBIT)) {
            return new Rabbit(randomAge, field, location);
        } else if (animalTypes.equals(AnimalTypes.FOX)) {
            return new Fox(randomAge, field, location);
        } else {
            throw new RuntimeException("Unknown animal type");
        }
    }
}
