package day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Junit5_intro {

    @Test
    public void test(){
        System.out.println("Learning Junit5");

        assertEquals(1,2);
        assertEquals(1,2, "Something is wrong");

    }

    @Test
    public void test1(){
        String name = "Nurahmet";
        assertEquals('A',name.charAt(0));
        //assertTrue(name.startsWith("A"));

    }

}
