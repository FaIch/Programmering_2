package WarGames_tests;
import Units.*;
import Battle.*;
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

        assertEquals(0,testArmy.getNumberOfUnits());

        testArmy.addUnit(new CommanderUnit("Bob",180));
        assertEquals(1,testArmy.getNumberOfUnits());

        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit("Bob",180));
        int i = 0;
        for (i = 0;i < 50;i++){
            testList.add(new InfantryUnit("Footman",100));
        }
        for (i = 0; i < 20; i++){
            testList.add(new RangedUnit("Archer",100));
        }
        for (i = 0; i < 5; i++){
            testList.add(new CavalryUnit("Chad",100));
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