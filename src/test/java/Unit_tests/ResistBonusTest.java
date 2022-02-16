package Unit_tests;
import Units.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResistBonusTest {
    @Test
    public void resistBonus_test() {
        RangedUnit unit1 = new RangedUnit("Håkon", 100);
        RangedUnit unit2 = new RangedUnit("Joakim", 100);

        unit1.attack(unit2);
        assertEquals(96, unit2.getHealth());

        unit1.attack(unit2);
        assertEquals(90, unit2.getHealth());

        unit1.attack(unit2);
        assertEquals(82, unit2.getHealth());
    }

}
