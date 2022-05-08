package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Battle;
import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

/**
 * The type Infantry unit.
 */
public class InfantryUnit extends Unit{

    /**
     * Instantiates a new Infantry unit.
     * Constructor 1
     *
     * @param health the health of the unit
     * @param name the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public InfantryUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Infantry unit.
     * Constructor 2
     * Name set by user, health, attack and armor predetermined
     *
     * @param name of the unit
     */
    public InfantryUnit(String name){
        super(100,name,15,10);

    }

    /**
     * bonus based on terrain, if terrain is Forest, increased bonus
     * @return The attack bonus of the unit
     */
    @Override
    public int getAttackBonus() {
        if (Battle.terrain.equals(Terrain.Forest)){
            return 4;
        }
        return 2;
    }



    /**
     * Resist bonus based on terrain, if terrain is Forest, increased bonus
     * @return the resist bonus of the unit.
     */
    @Override
    public int getResistBonus() {
        if (Battle.terrain.equals(Terrain.Forest)){
            return 3;
        }
        return 1;
    }

}
