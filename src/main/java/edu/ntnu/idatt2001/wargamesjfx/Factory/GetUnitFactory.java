package edu.ntnu.idatt2001.wargamesjfx.Factory;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.util.ArrayList;

public class GetUnitFactory {

    public static Unit getUnit(UnitType unitType){
        if (unitType == null){
            throw new IllegalArgumentException("UnitType can not be null");
        }
        else {
            Unit returnUnit = null;
            switch (unitType) {
                case InfantryUnit -> returnUnit = new InfantryUnit();
                case RangedUnit -> returnUnit = new RangedUnit();
                case CavalryUnit -> returnUnit = new CavalryUnit();
                case CommanderUnit -> returnUnit = new CommanderUnit();
                case MageUnit -> returnUnit = new MageUnit();
                case BannerUnit -> returnUnit = new BannerUnit();
                case DragonUnit -> returnUnit = new DragonUnit();
            }
            return returnUnit;
        }
    }

    public static ArrayList<Unit> getXUnits(UnitType unitType, int number) {
        ArrayList<Unit> returnList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            returnList.add(getUnit(unitType));
        }
        return returnList;
    }
}
