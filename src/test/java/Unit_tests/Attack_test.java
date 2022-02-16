package Unit_tests;
import Units.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Attack_test {

    @Test
    public void attackTest() {
        InfantryUnit infantryUnit1 = new InfantryUnit("Grunt", 100,20,10);
        InfantryUnit infantryUnit2 = new InfantryUnit("Footman", 100);

        infantryUnit1.attack(infantryUnit2);
        assertEquals(89, infantryUnit2.getHealth());

        infantryUnit2.attack(infantryUnit1);
        assertEquals(94, infantryUnit1.getHealth());

        System.out.println(infantryUnit1);
        System.out.println(infantryUnit2);
    }
}
