package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Battle;

/**
 * The type Infantry unit.
 */
public class InfantryUnit extends Unit{

    /**
     * Instantiates a new Infantry unit.
     * Constructor 1
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public InfantryUnit(int health, int attack, int armor) {
        super(health, attack, armor);
    }

    /**
     * Instantiates a new Infantry unit.
     * Constructor 2
     * Name and health set by user, attack and armor predetermined
     *
     */
    public InfantryUnit(){
        super(100,15,10);

    }

    /**
     * bonus based on terrain, if terrain is Forest, increased bonus
     * @return The attack bonus of the unit
     */
    @Override
    public int getAttackBonus() {
        if (Battle.terrain.equals("Forest")){
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
        if (Battle.terrain.equals("Forest")){
            return 3;
        }
        return 1;
    }

}
