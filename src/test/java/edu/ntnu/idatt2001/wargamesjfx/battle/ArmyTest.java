package edu.ntnu.idatt2001.wargamesjfx.battle;

import edu.ntnu.idatt2001.wargamesjfx.factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {
    Army testArmy = new Army("Test");

    @Test
    @DisplayName("Get name of Army")
    void getName() {
        assertEquals("Test",testArmy.getName());
    }


    @Test
    @DisplayName("Get number of units in Army")
    void getNumberOfUnits(){
        assertEquals(0,testArmy.getNumberOfUnits());
            testArmy.addUnit(new CommanderUnit(""));
        assertEquals(1,testArmy.getNumberOfUnits());
    }

    @Test
    @DisplayName("Add unit to army")
    void addUnit(){
        assertFalse(testArmy.hasUnits());
        testArmy.addUnit(new InfantryUnit(""));
        assertTrue(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Add list of units to army")
    void addListOfUnits(){
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(GetUnitFactory.getUnit(UnitType.CommanderUnit, ""));
        testList.addAll(GetUnitFactory.getXUnits(UnitType.InfantryUnit, 50, ""));
        testList.addAll(GetUnitFactory.getXUnits(UnitType.RangedUnit, 20, ""));
        testList.addAll(GetUnitFactory.getXUnits(UnitType.CavalryUnit, 5, ""));
        testArmy.addAllUnits(testList);
        assertEquals(76,testArmy.getNumberOfUnits());
    }

    @Test
    @DisplayName("Add empty list to Army, throws IllegalArgumentException")
    void addEmptyListThrowsException(){

        assertThrows(IllegalArgumentException.class, () -> testArmy.addAllUnits(null));
    }

    @Test
    @DisplayName("Add null unit, throws IllegalArgumentException")
    void addNullUnitThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> testArmy.addUnit(null));
    }

    @Test
    @DisplayName("Create army with empty list, throws IllegalArgumentException")
    void createArmyWithNullListThrowsException(){
        assertThrows(IllegalArgumentException.class, () ->{
           Army failArmy = new Army("FailArmy", null);
        });
    }

    @Test
    @DisplayName("Remove unit in army")
    void removeUnitInList(){
        testArmy.addUnit(new InfantryUnit(""));
        assertTrue(testArmy.hasUnits());
        Unit randomUnit = testArmy.getRandomUnit();
        testArmy.removeUnit(randomUnit);
        assertFalse(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Remove unit thats not in army, throws IllegalArgumentException")
    void removeUnitNotInListThrowsException(){
        CommanderUnit commanderUnit = new CommanderUnit("");
        assertThrows(IllegalArgumentException.class, () -> testArmy.removeUnit(commanderUnit));
    }

    @Test
    @DisplayName("Has units")
    void hasUnits() {
        assertFalse(testArmy.hasUnits());
        CommanderUnit commanderUnit = new CommanderUnit("");
        testArmy.addUnit(commanderUnit);
        assertTrue(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Get an empty list of InfantryUnits, throws exception")
    void getEmptyUnitList(){
        assertEquals(0, testArmy.getInfantryUnits().size());
    }

    @Test
    @DisplayName("Get Infantry units in army")
    void getInfantryUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit(""));
        int i;
        for (i = 0;i < 50;i++){
            testList.add(new InfantryUnit(""));
        }
        for (i = 0; i < 20; i++){
            testList.add(new RangedUnit(""));
        }
        for (i = 0; i < 5; i++){
            testList.add(new CavalryUnit(""));
        }
        testArmy.addAllUnits(testList);
        assertEquals(50,testArmy.getInfantryUnits().size());
    }

    @Test
    @DisplayName("Get Ranged units in army")
    void getRangedUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit(""));
        int i;
        for (i = 0;i < 50;i++){
            testList.add(new InfantryUnit(""));
        }
        for (i = 0; i < 20; i++){
            testList.add(new RangedUnit(""));
        }
        for (i = 0; i < 5; i++){
            testList.add(new CavalryUnit(""));
        }
        testArmy.addAllUnits(testList);
        assertEquals(20,testArmy.getRangedUnits().size());
    }

    @Test
    @DisplayName("Get Cavalry units in army")
    void getCavalryUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit(""));
        int i;
        for (i = 0;i < 50;i++){
            testList.add(new InfantryUnit(""));
        }
        for (i = 0; i < 20; i++){
            testList.add(new RangedUnit(""));
        }
        for (i = 0; i < 5; i++){
            testList.add(new CavalryUnit(""));
        }
        testArmy.addAllUnits(testList);
        assertEquals(5,testArmy.getCavalryUnits().size());
    }

    @Test
    @DisplayName("Get Commander units in army")
    void getCommanderUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit(""));
        testList.add(new CommanderUnit(""));
        testList.add(new CommanderUnit(""));
        int i;
        for (i = 0;i < 50;i++){
            testList.add(new InfantryUnit(""));
        }
        for (i = 0; i < 20; i++){
            testList.add(new RangedUnit(""));
        }
        for (i = 0; i < 5; i++){
            testList.add(new CavalryUnit(""));
        }
        testArmy.addAllUnits(testList);
        assertEquals(3,testArmy.getCommanderUnits().size());
    }
}