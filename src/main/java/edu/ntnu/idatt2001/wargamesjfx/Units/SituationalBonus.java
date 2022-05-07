package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;

public class SituationalBonus {

    public static int getSituationalBonus(UnitType unitTypeAttack, UnitType unitTypeDefend){
        if (unitTypeAttack.equals(UnitType.CavalryUnit)){
            if (unitTypeDefend.equals(UnitType.InfantryUnit)){
                return 4;
            }
        }
        else if (unitTypeAttack.equals(UnitType.MageUnit)){
            if (unitTypeDefend.equals(UnitType.DragonUnit)){
                return -100;
            }
        }
        return 0;
    }

}
