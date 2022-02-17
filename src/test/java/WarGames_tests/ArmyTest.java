package WarGames_tests;
import Units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {
    Army testArmy = new Army("Test");
    @Test
    void getName() {
        assertEquals("Test",testArmy.getName());
    }

    @Test
    void unitMods() {
        CommanderUnit commanderUnit = new CommanderUnit("Bob",180);
        InfantryUnit infantryUnit = new InfantryUnit("Footman",100);
        RangedUnit rangedUnit = new RangedUnit("Archer",100);
        CavalryUnit cavalryUnit = new CavalryUnit("Chad",100);

        assertEquals(0,testArmy.getNumberOfUnits());

        testArmy.addUnit(commanderUnit);
        assertEquals(1,testArmy.getNumberOfUnits());

        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(commanderUnit);
        int i = 0;
        for (i = 0;i < 50;i++){
            testList.add(infantryUnit);
        }
        for (i = 0; i < 20; i++){
            testList.add(rangedUnit);
        }
        for (i = 0; i < 5; i++){
            testList.add(cavalryUnit);
        }

        testArmy.addAllUnits(testList);
        assertEquals(77,testArmy.getNumberOfUnits());

        Unit randomUnit = testArmy.getRandomUnit();
        testArmy.removeUnit(randomUnit);
        assertEquals(76,testArmy.getNumberOfUnits());

        ArrayList<Unit> printList = testArmy.getAllUnits();
        assertEquals(76,printList.size());
        for (Unit unit : printList){
            System.out.println(unit.toString());
        }


    }

    @Test
    void constructorTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        Army testArmy = new Army("Test",testList);
    }

    @Test
    void removeUnit(){
        CommanderUnit commanderUnit = new CommanderUnit("Bob",180);
        assertFalse(testArmy.removeUnit(commanderUnit));
        testArmy.addUnit(commanderUnit);
        Unit unit = testArmy.getRandomUnit();
        assertTrue(testArmy.removeUnit(unit));
    }

    @Test
    void hasUnits() {
        assertFalse(testArmy.hasUnits());
        CommanderUnit commanderUnit = new CommanderUnit("Boy",1);
        testArmy.addUnit(commanderUnit);
        assertTrue(testArmy.hasUnits());
    }

}