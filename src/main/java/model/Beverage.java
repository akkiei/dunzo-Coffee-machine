package model;

import java.util.HashMap;

public class Beverage {
    private HashMap<String, Integer> beverageIngred;
    private String drinkName;



    public Beverage(HashMap<String, Integer> beverageIngred, String drinkName) {

        this.beverageIngred = beverageIngred;
        this.drinkName = drinkName;
    }

    public HashMap<String, Integer> getBeverageIngred() {
        return beverageIngred;
    }
    public String getDrinkName() {
        return drinkName;
    }
}
