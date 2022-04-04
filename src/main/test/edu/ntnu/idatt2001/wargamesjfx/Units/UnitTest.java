package edu.ntnu.idatt2001.wargamesjfx.Units;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    @DisplayName("Attack test")
    void attack() {
        try {
            InfantryUnit infantryUnit1 = new InfantryUnit("",100);
            InfantryUnit infantryUnit2 = new InfantryUnit("",100);
            infantryUnit1.attack(infantryUnit2);
            assertEquals(94,infantryUnit2.getHealth());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Set health test")
    void setHealth() {
        try {
            InfantryUnit infantryUnit1 = new InfantryUnit("",10);
            assertEquals(10,infantryUnit1.getHealth());
            infantryUnit1.setHealth(50);
            assertEquals(50,infantryUnit1.getHealth());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Set invalid health test")
    void setInvalidHealthThrowsException(){
        try {
            InfantryUnit infantryUnit = new InfantryUnit("",10);
            assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(-50));
            assertThrows(IllegalArgumentException.class, () -> infantryUnit.setHealth(0));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Attack bonus test")
    void getAttackBonus() {
        try {
            CommanderUnit commanderUnit = new CommanderUnit("",10);
            assertEquals(6,commanderUnit.getAttackBonus());
            assertEquals(2,commanderUnit.getAttackBonus());
            assertEquals(2,commanderUnit.getAttackBonus());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Resist bonus test")
    void getResistBonus() {
        try {
            RangedUnit rangedUnit = new RangedUnit("",100);
            assertEquals(6,rangedUnit.getResistBonus());
            assertEquals(4,rangedUnit.getResistBonus());
            assertEquals(2,rangedUnit.getResistBonus());
            assertEquals(2,rangedUnit.getResistBonus());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}