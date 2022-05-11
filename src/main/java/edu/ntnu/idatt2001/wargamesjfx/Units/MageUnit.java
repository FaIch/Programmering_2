package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

public class MageUnit extends Unit{

    public MageUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    public MageUnit(String name){super(70, name, 40, 5);}

    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        return 0;
    }

    @Override
    public int getResistBonus(Terrain terrain) {
        return 0;
    }
}
