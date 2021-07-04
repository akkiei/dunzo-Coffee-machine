package mocktail;

import inventory.InventoryManager;
import model.Beverage;
import model.Inventory;
import model.Machines;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrepareDrink {
    private HashMap<String, Integer> receipe;
    private String drinkName;
    private InventoryManager inventoryManager;
    private static PrepareDrink prepareDrink;
    private HashMap<String, HashMap> machine;
    private Inventory inventory;
    private Beverage beverages;
    private Machines machines;
    private HashMap<String, Integer> InventoryJson;
    private static final int MAXQ_REQ = 1000;
    private ThreadPoolExecutor threadPoolExecutor;

    private PrepareDrink(HashMap<String, HashMap> machine) {
        this.machine = machine;
        InventoryJson = machine.get("total_items_quantity");
        inventory = new Inventory(InventoryJson);   // modal

        HashMap<String, HashMap> BeveragesJson = machine.get("beverages"); // {key, map} // modal

        int outletsCount = (int) machine.get("outlets").get("count_n");  // modal
        machines = new Machines(outletsCount, BeveragesJson);

        threadPoolExecutor = new ThreadPoolExecutor(outletsCount, outletsCount, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(MAXQ_REQ));
        threadPoolExecutor.setRejectedExecutionHandler(new TaskRejectHandler());
    }

    public static PrepareDrink getInstance(final HashMap<String, HashMap> machine) {
        if (prepareDrink == null)
            prepareDrink = new PrepareDrink(machine);
        return prepareDrink;
    }

    public void process() {
        this.inventoryManager = InventoryManager.getInstance();
        HashMap<String, Integer> ingredients = inventory.getInventory();
        for (String key : ingredients.keySet()) {
            inventoryManager.addStockIntoInventory(key, ingredients.get(key));
        }

        HashMap<String, HashMap<String, Integer>> beverages = machines.getListOfBeverages();
        for (String key : beverages.keySet()) {
            Beverage beverage = new Beverage(beverages.get(key), key);
            addBeverageRequest(beverage);
        }

////        for
//        if (
//                true
//            // if drink is made successfully
//        ) {
//
//            System.out.println(this.drinkName + " is prepared.");
//
//        } else {
//            String less_ingredient = "";
//            if (
//                    true // add condition for that
//                // drink ingredient is missing
//            ) {
//
//
//                System.out.println(this.drinkName + " cannot be prepared because " + less_ingredient + " is not available");
//            } else {
//                System.out.println(this.drinkName + " cannot be prepared because item " + less_ingredient + " is not sufficient");
//
//            }
//        }

    }

    public void addBeverageRequest(Beverage beverage) {
        DrinkTask task = new DrinkTask(beverage);
        threadPoolExecutor.execute(task);
    }

    public void stopMachine() {
        threadPoolExecutor.shutdown();
    }

    public void reset() {
        System.out.println("Resetting\n");
        this.stopMachine();
        this.inventoryManager.resetInventory();
    }


}
