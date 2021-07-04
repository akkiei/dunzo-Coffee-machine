package model;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> inventory;

    public Inventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }


}
