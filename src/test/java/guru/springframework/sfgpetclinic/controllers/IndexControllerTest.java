package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");
        // Wherever you need heavy computations like string concatenation, use lambda expression.
        //      Reason: They are executed at run time only if the condition is false.
        assertEquals("index", controller.index(), () -> "Another Expensive Message " +
                "Make me only if you have to");
        // assertJ
        assertThat(controller.index()).isEqualTo("index");
    }

    @DisplayName("Test exception")
    @Test
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Disabled("Demo of preemptive timeout")
    @Test
    void testTimeOutPreempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("I got here 1231234423423");
        });
    }

    /**
     * Use assumptions to do conditional running e.g. based on environment, you may want to run the test
     * If the assumption is evaluated to false, the test is considered as disabled. It won't fail your test suite.
     */
    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }


    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOS() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11() {

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Vijay")
    @Test
    void testIfUserVijay() {

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "Fred")
    @Test
    void testIfUserFred() {

    }

}