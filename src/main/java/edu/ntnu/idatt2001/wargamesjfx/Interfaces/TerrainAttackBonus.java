package edu.ntnu.idatt2001.wargamesjfx.Interfaces;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

/**
 * The interface Terrain attack bonus, used for calculating attack bonus based on Terrain
 */
public interface TerrainAttackBonus {
    /**
     * Gets terrain attack bonus.
     *
     * @param terrain the terrain the attack is happening in
     * @return the attack bonus of the unit based on terrain
     */
    int getTerrainAttackBonus(Terrain terrain);
}
