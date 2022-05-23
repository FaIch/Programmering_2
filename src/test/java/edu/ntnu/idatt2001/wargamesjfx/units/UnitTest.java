package edu.ntnu.idatt2001.wargamesjfx.units;

import edu.ntnu.idatt2001.wargamesjfx.battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.factory.UnitType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    @DisplayName("Attack test")
    void attack() {
        InfantryUnit infantryUnit1 = new InfantryUnit("");
        InfantryUnit infantryUnit2 = new InfantryUnit("");
        infantryUnit1.attack(infantryUnit2, Terrain.Forest, false);
        assertEquals(94,infantryUnit2.getHealth());
    }

    @Test
    @DisplayName("Set health test")
    void setHealth() {
        InfantryUnit infantryUnit1 = new InfantryUnit("");
        assertEquals(100,infantryUnit1.getHealth());
        infantryUnit1.setHealth(50);
        assertEquals(50,infantryUnit1.getHealth());
    }

    @Test
    @DisplayName("Set invalid health test")
    void setInvalidHealthThrowsException(){
        InfantryUnit infantryUnit = new InfantryUnit("");
        assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(-50));
        assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(0));
    }

    @Test
    @DisplayName("Attack bonus test")
    void getAttackBonus() {
        CommanderUnit commanderUnit = new CommanderUnit("");
        Unit dummyUnit = new InfantryUnit("");
        Terrain terrain = Terrain.Forest;
        assertEquals(8,commanderUnit.getAttackBonus(UnitType.InfantryUnit, terrain));
        assertEquals(4,commanderUnit.getAttackBonus(UnitType.InfantryUnit, terrain));
        assertEquals(4,commanderUnit.getAttackBonus(UnitType.InfantryUnit, terrain));
    }

    @Test
    @DisplayName("Resist bonus test")
    void getResistBonus() {
        Terrain terrain = Terrain.Forest;
        RangedUnit rangedUnit = new RangedUnit("");
        assertEquals(6,rangedUnit.getResistBonus(terrain));
        assertEquals(4,rangedUnit.getResistBonus(terrain));
        assertEquals(2,rangedUnit.getResistBonus(terrain));
        assertEquals(2,rangedUnit.getResistBonus(terrain));
    }
}