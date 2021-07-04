import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;

import mocktail.PrepareDrink;
import model.*;

public class Main {

    public static void main(String argv[]) throws IOException {
        //Path of given input json file
        String path = "src/main/resources/input.json";

        // JSON input files to test other cases
        // UNCOMMENT anyone to test with other JSON file

        //  path = "src/main/resources/test1.json";
        //  path = "src/main/resources/test1.json";

        // To read JSON file
        ObjectMapper objecMapper = new ObjectMapper();
        File file = new File(path);
        HashMap<String, HashMap> map = objecMapper.readValue(file, HashMap.class);
        HashMap<String, HashMap> machine = map.get("machine");

//        HashMap<String, Integer> InventoryJson = machine.get("total_items_quantity");
//        Inventory inventory = new Inventory(InventoryJson);   // modal
//
//        HashMap<String, HashMap> BeveragesJson = machine.get("beverages"); // {key, map} // modal
////        Beverages beverages = new Beverages(BeveragesJson);
//
//        int outletsCount = (int) machine.get("outlets").get("count_n");  // modal
//        Machines machines = new Machines(outletsCount,BeveragesJson);


        PrepareDrink prepareDrink = PrepareDrink.getInstance(machine);
        prepareDrink.process();
        prepareDrink.reset();
        prepareDrink.stopMachine();
    }

}
