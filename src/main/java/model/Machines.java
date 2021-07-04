package model;

import java.util.HashMap;

public class Machines {
    private int no_of_outlets = 0;
    private HashMap<String, HashMap<String, Integer>> listOfBeverages;

    public HashMap<String, HashMap<String, Integer>> getListOfBeverages() {
        return listOfBeverages;
    }

    public void setListOfBeverages(HashMap<String, HashMap<String, Integer>> listOfBeverages) {
        this.listOfBeverages = listOfBeverages;
    }


    public Machines(int no_of_outlets, HashMap listOfBeverages) {
        this.no_of_outlets = no_of_outlets;
        this.listOfBeverages = listOfBeverages;
    }

    public int getNo_of_outlets() {
        return no_of_outlets;
    }

}
