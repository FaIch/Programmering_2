package edu.ntnu.idatt2001.wargamesjfx.Battle;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;
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
        try {
            testArmy.addUnit(new CommanderUnit("Bob",180));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        assertEquals(1,testArmy.getNumberOfUnits());
    }

    @Test
    @DisplayName("Add unit to army")
    void addUnit(){
        assertFalse(testArmy.hasUnits());
        try {
            testArmy.addUnit(new InfantryUnit("Per",1));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        assertTrue(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Add list of units to army")
    void addListOfUnits(){
        ArrayList<Unit> testList = new ArrayList<>();
        try {
            testList.add(new CommanderUnit("Bob", 180));
            int i = 0;
            for (i = 0; i < 50; i++) {
                testList.add(new InfantryUnit("Footman", 100));
            }
            for (i = 0; i < 20; i++) {
                testList.add(new RangedUnit("Archer", 100));
            }
            for (i = 0; i < 5; i++) {
                testList.add(new CavalryUnit("Chad", 100));
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        testArmy.addAllUnits(testList);
        assertEquals(76,testArmy.getNumberOfUnits());
    }

    @Test
    @DisplayName("Add empty list to Army, throws IllegalArgumentException")
    void addEmptyListThrowsException(){
        ArrayList<Unit> testList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> testArmy.addAllUnits(testList));
    }

    @Test
    @DisplayName("Add null unit, throws IllegalArgumentException")
    void addNullUnitThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> testArmy.addUnit(null));

    }

    @Test
    @DisplayName("Create army with empty list, throws IllegalArgumentException")
    void createArmyWithEmptyListThrowsException(){
        ArrayList<Unit> testList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () ->{
           Army failArmy = new Army("FailArmy",testList);
        });
    }

    @Test
    @DisplayName("Remove unit in army")
    void removeUnitInList(){
        try {
            testArmy.addUnit(new InfantryUnit("Footman",100));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        assertTrue(testArmy.hasUnits());
        Unit randomUnit = testArmy.getRandomUnit();
        testArmy.removeUnit(randomUnit);
        assertFalse(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Remove unit thats not in army, throws IllegalArgumentException")
    void removeUnitNotInListThrowsException(){
        CommanderUnit commanderUnit = new CommanderUnit("Bob",180);
        assertThrows(IllegalArgumentException.class, () -> testArmy.removeUnit(commanderUnit));
    }

    @Test
    @DisplayName("Has units")
    void hasUnits() {
        assertFalse(testArmy.hasUnits());
        CommanderUnit commanderUnit = new CommanderUnit("Boy",1);
        try {
            testArmy.addUnit(commanderUnit);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        assertTrue(testArmy.hasUnits());
    }

    @Test
    @DisplayName("Get an empty list of InfantryUnits, throws exception")
    void getEmptyUnitList(){
        assertThrows(IllegalArgumentException.class, () -> testArmy.getInfantryUnits());
    }

    @Test
    @DisplayName("Get Infantry units in army")
    void getInfantryUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        try {
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
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        testArmy.addAllUnits(testList);
        assertEquals(50,testArmy.getInfantryUnits().size());
    }

    @Test
    @DisplayName("Get Ranged units in army")
    void getRangedUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        try {
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
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        testArmy.addAllUnits(testList);
        assertEquals(20,testArmy.getRangedUnits().size());
    }

    @Test
    @DisplayName("Get Cavalry units in army")
    void getCavalryUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        try {
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
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        testArmy.addAllUnits(testList);
        assertEquals(5,testArmy.getCavalryUnits().size());
    }

    @Test
    @DisplayName("Get Commander units in army")
    void getCommanderUnitsTest(){
        ArrayList<Unit> testList = new ArrayList<>();
        try {
            testList.add(new CommanderUnit("Bob",180));
            testList.add(new CommanderUnit("Bob",180));
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
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        testArmy.addAllUnits(testList);
        assertEquals(3,testArmy.getCommanderUnits().size());
    }
}