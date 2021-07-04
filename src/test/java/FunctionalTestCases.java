import com.fasterxml.jackson.databind.ObjectMapper;
import mocktail.PrepareDrink;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

public class FunctionalTestCases {

    PrepareDrink prepareDrink;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        prepareDrink.reset();
        prepareDrink.stopMachine();
    }

    @Test
    public void test1() throws Exception {
        final String filePath = "src/main/resources/test1.json";
        ObjectMapper objecMapper = new ObjectMapper();
        File file = new File(filePath);
        HashMap<String, HashMap> map = objecMapper.readValue(file, HashMap.class);
        HashMap<String, HashMap> machine = map.get("machine");
        prepareDrink = PrepareDrink.getInstance(machine);
        prepareDrink.process();
    }

    @Test
    public void test2() throws Exception {
        final String filePath = "src/main/resources/test2.json";
        ObjectMapper objecMapper = new ObjectMapper();
        File file = new File(filePath);
        HashMap<String, HashMap> map = objecMapper.readValue(file, HashMap.class);
        HashMap<String, HashMap> machine = map.get("machine");
        prepareDrink = PrepareDrink.getInstance(machine);
        prepareDrink.process();
    }


}