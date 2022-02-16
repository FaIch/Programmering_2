package Unit_tests;
import Units.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackBonusTest {

    @Test
    public void attackBonus_test(){
        CommanderUnit unit1 = new CommanderUnit("1",100);
        CommanderUnit unit2 = new CommanderUnit("2",100);
        CommanderUnit unit3 = new CommanderUnit("3",100);
        CommanderUnit unit4 = new CommanderUnit("4",100);

        unit1.attack(unit2);
        assertEquals(85, unit2.getHealth());

        unit1.attack(unit3);
        assertEquals(89, unit3.getHealth());

        unit1.attack(unit4);
        assertEquals(89, unit4.getHealth());

    }
}
