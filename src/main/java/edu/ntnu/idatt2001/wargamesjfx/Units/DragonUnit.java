package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Interface.TerrainBonus;

public class DragonUnit extends Unit implements TerrainBonus {


    /**
     * Instantiates a new Dragon Unit.
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public DragonUnit(int health, String name, int attack, int armor) {super(health, name, attack, armor);}

    public DragonUnit(String name){
        super(200, name, 30, 20);

    }

    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        return 3;
    }

    @Override
    public int getResistBonus(Terrain terrain) {
        return 3;
    }

    @Override
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Forest)){
            return -10;
        }
        return 0;
    }

    @Override
    public int getTerrainArmorBonus(Terrain terrain) {
        return 0;
    }
}
