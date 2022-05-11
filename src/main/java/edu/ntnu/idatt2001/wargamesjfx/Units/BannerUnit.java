package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

public class BannerUnit extends Unit{

    public BannerUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    public BannerUnit(String name){super(80, name,0, 0);}

    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        return 0;
    }

    @Override
    public int getResistBonus(Terrain terrain) {
        return 0;
    }
}
