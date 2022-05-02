package edu.ntnu.idatt2001.wargamesjfx.Factory;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.util.ArrayList;

public class GetUnitFactory {

    public static Unit getUnit(UnitType unitType, String name, int health){
        Unit returnUnit = null;
        switch (unitType){
            case InfantryUnit -> returnUnit = new InfantryUnit(name);
            case RangedUnit -> returnUnit = new RangedUnit(name);
            case CavalryUnit -> returnUnit = new CavalryUnit(name);
            case CommanderUnit -> returnUnit = new CommanderUnit(name);
        }
        return returnUnit;
    }

    public static ArrayList<Unit> getXUnits(UnitType unitType, String name, int health, int number) {
        ArrayList<Unit> returnList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            returnList.add(getUnit(unitType, name, health));
        }
        return returnList;
    }
}
