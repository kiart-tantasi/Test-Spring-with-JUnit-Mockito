import DummyPackage.DummyObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

// https://www.baeldung.com/junit-5

public class TesterDemo {

    DoSomethingApp doSomethingApp = new DoSomethingApp();

    // ----------- Before and After ----------- //

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }

    // --------------------------------------- //


    @DisplayName("DisplayName: assumptionTrue")
    @Test
    void assumptionTrue() {
        assumeTrue(5 > 1);
        assertEquals(doSomethingApp.doSomething(), "Hello");
    }

    @DisplayName("DisplayName: assumptionNotTrue")
    @Test
    void assumptionNotTrue() {
        assumeTrue(2 > 5); // skip this testing method because assumption is not true
        assertEquals(doSomethingApp.doSomething(), "Hello");
    }

    @Disabled("disabled test")
    @Test
    void disabledMethod() {
        assertTrue(5 > 1);
    }


    // ---------- Lambda ---------- //

    @Test
    void lambdaExpressions() {
        List numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(i -> (int) i)
                .sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    void lambdaTester() {
        List intArray = new ArrayList<Integer>();
        intArray.add(3);
        intArray.add(2);
        intArray.add(10);

        assertTrue(intArray.stream()
                .mapToInt(i -> (int) i)
                .sum() > 10, () -> "False: The sum is actually equal to or bigger than 10");
    }

    @Test
    void testAssertAll() {
        assertAll(
                "testing assertAll ...",
                () -> assertTrue(5> 1),
                () -> assertTrue(10 == 10),
                () -> assertTrue(10 - 1 == 9)
        );
    }

    // -------------- Exception Testing -------------- //
    @Test
    void testException() {
        Throwable dummyException = assertThrows(IllegalStateException.class, () -> {
            throw new IllegalStateException("Threw!");
        });
        assertEquals(
                "Threw!",
                dummyException.getMessage()
        );
    }

    // ??????
    @Test
    void testExceptionType() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Integer.valueOf(null);
                }
        );
    }

    // ------------------------------------------------------------- //


    // -------------------------- Test Suites ---------------------- //
//    @RunWith(JUnitPlatform.class)
//    @SelectPackages("com.baeldung")
//    public class AllUnitTest {}
//
//    @RunWith(JUnitPlatform.class)
//    @SelectClasses({AssertionTest.class, AssumptionTest.class, ExceptionTest.class})
//    public class AllUnitTest {}
    // ------------------------------------------------------------ //


    // -------------------------- Dynamic Tests ---------------------- //
//    @TestFactory
//    public Stream<DynamicTest> translateDynamicTestsFromStream() {
//        return in.stream()
//                .map(word ->
//                        DynamicTest.dynamicTest("Test translate " + word, () -> {
//                            int id = in.indexOf(word);
//                            assertEquals(out.get(id), translate(word));
//                        })
//                );
//    }
    // ------------------------------------------------------------ //

//    ***************
//    This example is very straightforward and easy to understand. We want to translate words using two ArrayList, named in and out, respectively. The factory method must return a Stream, Collection, Iterable, or Iterator. In our case, we chose a Java 8 Stream.
//
//    Please note that @TestFactory methods must not be private or static. The number of tests is dynamic, and it depends on the ArrayList size.

    @Test
    void testJacoco() {
        DummyObject dummyObject = new DummyObject();
        assertEquals("JACOCO", dummyObject.testJacoco());
    }

}
