package day1;


import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        assertThat(10, is(5+5) );

    }

}
