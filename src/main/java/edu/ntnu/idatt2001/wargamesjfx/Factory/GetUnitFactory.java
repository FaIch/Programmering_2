package edu.ntnu.idatt2001.wargamesjfx.Factory;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.util.ArrayList;

public class GetUnitFactory {

    public static Unit getUnit(UnitType unitType, String name){
        Unit returnUnit = null;
        switch (unitType){
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

    public static ArrayList<Unit> getXUnits(UnitType unitType, String name, int number) {
        ArrayList<Unit> returnList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            returnList.add(getUnit(unitType, name));
        }
        return returnList;
    }
}
