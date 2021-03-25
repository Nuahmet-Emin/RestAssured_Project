package day1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        //hamcrest way of assertion
        //assertThat(actual, some matcher that describe what you trying to do
        assertThat(10, is(5+5) );
        assertThat(10,equalTo(10));


        assertThat(5+5, is(equalTo(10)));

        assertThat(5+5, not(11));

        assertThat(5+5, is(not(11)));

        assertThat(5+5, is(  not(equalTo(11) )  )   );

        // number comparison
        //greaterThan
        assertThat(5+5, is (greaterThan(9) ) );


    }

    @DisplayName("Matchers related to Strings")
    @Test
    public void stringMatchers(){

        String msg = "B21 is learning hamcrest";

        assertThat(msg, is("B21 is learning hamcrest"));
        assertThat(msg,equalTo("B21 is learning hamcrest"));
        assertThat(msg,is(equalTo("B21 is learning hamcrest")));

        //check if this msg start with B21
        assertThat(msg, startsWith("B21"));


        //check if this msg start with using ignore case sensitive
        assertThat(msg,startsWithIgnoringCase("b21"));

        assertThat(msg,endsWith("rest"));

        assertThat(msg, containsString("learning"));

        assertThat(msg, containsStringIgnoringCase("LEARNING"));

        String str = "       ";
        // check if above str is blank
        assertThat(str, blankString());

        assertThat(str.trim(), emptyString());

    }

    @DisplayName("Hamcrest Support for Collection")
    @Test
    public void testCollection(){

        List<Integer> list = Arrays.asList(1,4,7,3,7,44,88,99,44);

        // checking the size of this list
        assertThat(list, hasSize(9)  );

        // check if this list hasItem 7
        assertThat(list, hasItem(7));

        assertThat(list,hasItems(7, 99, 88));

        assertThat(list,everyItem(greaterThan(0)));



    }

}
