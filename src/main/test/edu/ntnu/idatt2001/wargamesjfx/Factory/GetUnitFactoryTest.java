package edu.ntnu.idatt2001.wargamesjfx.Factory;

import edu.ntnu.idatt2001.wargamesjfx.Units.Unit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType.*;
import static org.junit.jupiter.api.Assertions.*;

class GetUnitFactoryTest {

    @Test
    void getUnit() {
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(GetUnitFactory.getUnit(InfantryUnit));
        assertEquals(1,testList.size());
    }

    @Test
    void getXUnits() {
        ArrayList<Unit> testList = new ArrayList<>();
        testList.addAll(GetUnitFactory.getXUnits(RangedUnit, 50));
        assertEquals(50,testList.size());
    }
}