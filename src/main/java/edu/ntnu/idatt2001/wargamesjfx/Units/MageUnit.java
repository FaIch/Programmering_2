package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.SituationalBonus;

public class MageUnit extends Unit implements SituationalBonus {

    public MageUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    public MageUnit(String name){super(70, name, 35, 5);}

    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        return 3 + getSituationalAttackBonus(enemyUnit);
    }

    @Override
    public int getResistBonus(Terrain terrain) {
        return 1;
    }

    @Override
    public int getSituationalAttackBonus(Unit unit) {
        if (unit.getClass().getSimpleName().equals("DragonUnit")){
            return -50;
        }
        return 0;
    }

}
