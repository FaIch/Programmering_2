package edu.ntnu.idatt2001.wargamesjfx.Interface;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

public interface TerrainBonus {
    int getTerrainAttackBonus(Terrain terrain);
    int getTerrainArmorBonus(Terrain terrain);
}
