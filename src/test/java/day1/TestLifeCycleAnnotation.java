package day1;


import org.junit.jupiter.api.*;

@DisplayName("Learning Test Lifecycle Annotation")
public class TestLifeCycleAnnotation {


    @BeforeAll
    public static void init(){
        System.out.println("Before All is running");
    }
    @AfterAll
    public static void close(){
        System.out.println("After all is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("Before each is running");
    }

    @AfterEach
    public void tearDownEach(){
        System.out.println("After each is running");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @Test
    public void test2(){
        System.out.println("Test 2 is running");

    }


}
