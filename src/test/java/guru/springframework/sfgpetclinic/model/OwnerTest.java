package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        // When you get a complex object and we are doing a lot of assertions against it,
        // We can nest the assertions together and do multiple assertAll invocations
        // We can go multiple levels down on the hierarchy and validate values at each level
        // Here we are going 2 levels down: Person, Owner
        assertAll("Properties Test",
                () -> {
                    assertAll("Person Properties",
                            () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                            () -> assertEquals("Buck", owner.getLastName(), "Last Name Did not Match"));
                },
                () -> {
                    assertAll("Owner Properties",
                            () -> assertEquals("Key West", owner.getCity(), "City Did not Match"),
                            () -> assertEquals("1231231234", owner.getTelephone(), "Telephone Did not Match"));
                }
        );

        assertThat(owner.getCity(), is("Key West"));
    }
}