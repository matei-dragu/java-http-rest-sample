package ro.tatacalu.java9;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CollectionsTest {

    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    public void simpleTest() throws JsonProcessingException {
        List<String> stringList = List.of("first", "second", "third", "fourth", "fifth");
        Assertions.assertEquals(stringList.size(), 5);

        stringList.stream().forEach(s -> {
            System.out.println("string: " + s);
        });

        String valueAsString = OM.writeValueAsString(stringList);
        System.out.println(valueAsString);
    }



}
