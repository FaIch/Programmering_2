package edu.ntnu.idatt2001.wargamesjfx.interfaces;

import edu.ntnu.idatt2001.wargamesjfx.battle.Terrain;

/**
 * The interface Terrain defence bonus, used for calculating resist bonus based on terrain
 */
public interface TerrainDefenceBonus {
    /**
     * Gets terrain defence bonus.
     *
     * @param terrain the terrain the attack is happening in
     * @return the defence bonus the unit gets based on given terrain
     */
    int getTerrainDefenceBonus(Terrain terrain);
}
