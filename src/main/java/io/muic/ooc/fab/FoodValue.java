package io.muic.ooc.fab;

public enum FoodValue {
    // The food value of a single rabbit. In effect, this is the
    // number of steps a Animal can go before it has to eat again.
    RABBIT_FOOD_VALUE(9),
    FOX_FOOD_VALUE(12);

    private int foodValue;

    FoodValue(int foodValue) {this.foodValue = foodValue;}

    public int getFoodValue() { return foodValue; }
}
