package edu.ntnu.idatt2001.wargamesjfx.Interface;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

public interface TerrainBonus {
    public int getTerrainAttackBonus(Terrain terrain);
    public int getTerrainArmorBonus(Terrain terrain);
}
