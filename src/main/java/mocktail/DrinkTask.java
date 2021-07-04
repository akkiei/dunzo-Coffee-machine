package mocktail;

import inventory.InventoryManager;
import model.Beverage;

public class DrinkTask implements Runnable {
    private Beverage beverage;

    public DrinkTask(Beverage beverage) {

        this.beverage = beverage;

    }

    @Override
    public void run() {
        if (InventoryManager.getInstance().checkAndUpdateInventory(this.beverage)) {
            System.out.println(beverage.getDrinkName() + " is prepared");
        }
    }
}
