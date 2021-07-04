package inventory;

import model.Beverage;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private static InventoryManager inventoryManager;
    private HashMap<String, Integer> ingredients = new HashMap<>();
    ;


    private InventoryManager() {


    }

    public void resetInventory() {
        ingredients.clear();
    }

//    //Using Holder pattern ensures thread safe initialisation of the object,
//    private static class InventoryManagerHolder {
//        public static final InventoryManager instance = new InventoryManager();
//    }
//
//    public static InventoryManager getInstance() {
//        return InventoryManagerHolder.instance;
//    }
//

    public void addStockIntoInventory(String name, int quantity) {

        int currentQuantity = ingredients.getOrDefault(name, 0);
        ingredients.put(name, quantity + currentQuantity);
    }

    public static InventoryManager getInstance() { // make this thread safe

        if (inventoryManager == null)
            inventoryManager = new InventoryManager();
        return inventoryManager;
    }

    //Making this thread safe by synchronizing
    public synchronized boolean checkAndUpdateInventory(Beverage beverage) {
        Map<String, Integer> requiredIngredientMap = beverage.getBeverageIngred();
        boolean isPossible = true;

        for (String ingredient : requiredIngredientMap.keySet()) {
            int ingredientInventoryCount = ingredients.getOrDefault(ingredient, -1);
            if (ingredientInventoryCount == -1 || ingredientInventoryCount == 0) {
                System.out.println(beverage.getDrinkName() + " cannot be prepared because " + ingredient + " is not available");
                isPossible = false;
                break;
            } else if (requiredIngredientMap.get(ingredient) > ingredientInventoryCount) {
                System.out.println(beverage.getDrinkName() + " cannot be prepared because " + ingredient + " is not sufficient");
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            for (String ingredient : requiredIngredientMap.keySet()) {
                int existingInventory = ingredients.getOrDefault(ingredient, 0);
                ingredients.put(ingredient, existingInventory - requiredIngredientMap.get(ingredient));
            }
        }

        return isPossible;
    }


}
