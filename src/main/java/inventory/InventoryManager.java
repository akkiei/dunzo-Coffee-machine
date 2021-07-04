package inventory;

import model.Beverage;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private static InventoryManager inventoryManager;
    private HashMap<String, Integer> ingredients = new HashMap<>();


    private InventoryManager() {

    }

    public void resetInventory() {
        ingredients.clear();
    }


    public void addStockIntoInventory(String name, int quantity) {

        int currentQuantity = ingredients.getOrDefault(name, 0);
        ingredients.put(name, quantity + currentQuantity);
    }

    public static InventoryManager getInstance() { // make this thread safe

        if (inventoryManager == null)
            inventoryManager = new InventoryManager();
        return inventoryManager;
    }

    // this will make
    // it thread safe
    public synchronized boolean checkAndUpdateInventory(Beverage beverage) {
        Map<String, Integer> requiredIngredientMap = beverage.getBeverageIngred();
        boolean canBePrepared = true;

        for (String ingredient : requiredIngredientMap.keySet()) {
            int ingredInventCount = ingredients.getOrDefault(ingredient, -1);
            if (ingredInventCount == -1 || ingredInventCount == 0) {
                System.out.println(beverage.getDrinkName() + " cannot be prepared because " + ingredient + " is not available");
                canBePrepared = false;
                break;
            } else if (requiredIngredientMap.get(ingredient) > ingredInventCount) {
                System.out.println(beverage.getDrinkName() + " cannot be prepared because " + ingredient + " is not sufficient");
                canBePrepared = false;
                break;
            }
        }

        if (canBePrepared) {
            for (String ingredient : requiredIngredientMap.keySet()) {
                int existsInInventory = ingredients.getOrDefault(ingredient, 0);
                ingredients.put(ingredient, existsInInventory - requiredIngredientMap.get(ingredient));
            }
        }

        return canBePrepared;
    }


}
