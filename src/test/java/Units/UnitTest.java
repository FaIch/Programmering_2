package Units;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    @DisplayName("Attack test")
    void attack() {
        InfantryUnit infantryUnit1 = new InfantryUnit("",100);
        InfantryUnit infantryUnit2 = new InfantryUnit("",100);

        infantryUnit1.attack(infantryUnit2);
        assertEquals(94,infantryUnit2.getHealth());
    }

    @Test
    @DisplayName("Set health test")
    void setHealth() {
        InfantryUnit infantryUnit1 = new InfantryUnit("",10);
        assertEquals(10,infantryUnit1.getHealth());
        infantryUnit1.setHealth(50);
        assertEquals(50,infantryUnit1.getHealth());
    }

    @Test
    @DisplayName("Set invalid health test")
    void setInvalidHealthThrowsException(){
        InfantryUnit infantryUnit = new InfantryUnit("",10);
        assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(-50));
        assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(0));

    }

    @Test
    @DisplayName("Attack bonus test")
    void getAttackBonus() {
        CommanderUnit commanderUnit = new CommanderUnit("",10);
        assertEquals(6,commanderUnit.getAttackBonus());
        assertEquals(2,commanderUnit.getAttackBonus());
        assertEquals(2,commanderUnit.getAttackBonus());
    }

    @Test
    @DisplayName("Resist bonus test")
    void getResistBonus() {
        RangedUnit rangedUnit = new RangedUnit("",100);
        assertEquals(6,rangedUnit.getResistBonus());
        assertEquals(4,rangedUnit.getResistBonus());
        assertEquals(2,rangedUnit.getResistBonus());
        assertEquals(2,rangedUnit.getResistBonus());
    }
}