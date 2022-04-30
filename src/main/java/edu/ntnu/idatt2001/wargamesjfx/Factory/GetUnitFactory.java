package edu.ntnu.idatt2001.wargamesjfx.Factory;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;

public class GetUnitFactory {

    public Unit getUnit(UnitType unitType, String name, int health){
        Unit unit = null;

        if (unitType == UnitType.INFANTRYUNIT || unitType == UnitType.INFANTRY){
            unit = new InfantryUnit(name, health);
        }
        else if (unitType == UnitType.RANGEDUNIT || unitType == UnitType.RANGED){
            unit = new RangedUnit(name,health);
        }
        else if (unitType == UnitType.CAVALRYUNIT || unitType == UnitType.CAVALRY){
            unit = new CavalryUnit(name,health);
        }
        else if (unitType == UnitType.COMMANDERUNIT || unitType == UnitType.COMMANDER){
            unit = new CommanderUnit(name,health);
        }
        return unit;
    }
}
