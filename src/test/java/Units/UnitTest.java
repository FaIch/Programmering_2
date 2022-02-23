package Units;

import Units.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    @DisplayName("Invalid value test, throws IllegalArgumentException")
    public void invalidValueTestThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new InfantryUnit("Bob",-100));
        assertThrows(IllegalArgumentException.class, () -> new InfantryUnit("Per",0));
    }

    @Test
    @DisplayName("Attack test")
    public void attackTest() {
        InfantryUnit infantryUnit1 = new InfantryUnit("Grunt", 100,20,10);
        InfantryUnit infantryUnit2 = new InfantryUnit("Footman", 100);

        infantryUnit1.attack(infantryUnit2);
        assertEquals(89, infantryUnit2.getHealth());

        infantryUnit2.attack(infantryUnit1);
        assertEquals(94, infantryUnit1.getHealth());

    }
    @Test
    @DisplayName("Attack bonus test")
    public void attackBonusTest() {
        CommanderUnit unit1 = new CommanderUnit("1", 100);
        CommanderUnit unit2 = new CommanderUnit("2", 100);
        CommanderUnit unit3 = new CommanderUnit("3", 100);
        CommanderUnit unit4 = new CommanderUnit("4", 100);

        unit1.attack(unit2);
        assertEquals(85, unit2.getHealth());

        unit1.attack(unit3);
        assertEquals(89, unit3.getHealth());

        unit1.attack(unit4);
        assertEquals(89, unit4.getHealth());
    }

    @Test
    @DisplayName("Resist bonus test")
    public void resistBonusTest() {
        RangedUnit unit1 = new RangedUnit("Håkon", 100);
        RangedUnit unit2 = new RangedUnit("Joakim", 100);

        unit1.attack(unit2);
        assertEquals(96, unit2.getHealth());

        unit1.attack(unit2);
        assertEquals(90, unit2.getHealth());

        unit1.attack(unit2);
        assertEquals(82, unit2.getHealth());
    }

    @Test
    @DisplayName("toString test")
    public void toStringTest(){
        InfantryUnit infantryUnit1 = new InfantryUnit("Grunt", 100);
        assertEquals(infantryUnit1.toString(),"Name: " + infantryUnit1.getName() + "\nHealth: "
                + infantryUnit1.getHealth()
                + "\nAttack: " + infantryUnit1.getAttack() +
                "\nArmor:" + infantryUnit1.getArmor() + "\n");

    }
}
