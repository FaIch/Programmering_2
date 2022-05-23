package edu.ntnu.idatt2001.wargamesjfx.factory;

import edu.ntnu.idatt2001.wargamesjfx.units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The factory: Get unit factory, used for creating and adding units to army. Given the little information this class
 * gets from other classes, the objects made are not connected elsewhere. Ensuring low coupling.
 */
public class GetUnitFactory {

    /**
     * Get unit, takes in unitType and name and creates the unit.
     *
     * @param unitType the unit type
     * @param name     the name
     * @return the created unit
     */
    public static Unit getUnit(UnitType unitType, String name){
        if (unitType == null){
            throw new IllegalArgumentException("UnitType can not be null");
        }
        else {
            Unit returnUnit = null;
            switch (unitType) {
                case InfantryUnit -> returnUnit = new InfantryUnit(name);
                case RangedUnit -> returnUnit = new RangedUnit(name);
                case CavalryUnit -> returnUnit = new CavalryUnit(name);
                case CommanderUnit -> returnUnit = new CommanderUnit(name);
                case MageUnit -> returnUnit = new MageUnit(name);
                case BannerUnit -> returnUnit = new BannerUnit(name);
                case DragonUnit -> returnUnit = new DragonUnit(name);
            }
            return returnUnit;
        }
    }

    /**
     * Gets a number of units.
     *
     * @param unitType the unit type to be created
     * @param number   the number of units to be made
     * @param name     the name of the unit
     * @return A given amount of units
     */
    public static List<Unit> getXUnits(UnitType unitType, int number, String name) {
        ArrayList<Unit> returnList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            returnList.add(getUnit(unitType, name));
        }
        return returnList;
    }
}
