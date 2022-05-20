package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.TerrainAttackBonus;

/**
 * The type Ranged unit.
 * Has a variable counter, used to determine what the resist bonus should be, based on how many times the unit has
 * been attacked
 *
 * Implements TerrainAttackBonus to calculate attack bonus based on terrain
 */
public class RangedUnit extends Unit implements TerrainAttackBonus {
    private int counter;

    /**
     * Instantiates a new Ranged unit.
     * Constructor 1
     * Constructor where the user decides all stats for the unit
     *
     * @param health the health of the unit
     * @param name the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public RangedUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Ranged unit.
     * Constructor 2
     * Constructor where the health, attack and armor of the unit is predetermined
     *
     * @param name of the unit
     */
    public RangedUnit(String name){
        super(100, name, 15, 8);
    }

    /**
     * Increases counter for calculating resist bonus, increases when the resistBonus method is called in Unit class
     * ensuring that the number of times the unit has been attacked is taken into account with what the resistBonus
     * the unit has.
     */
    public void increaseCounter(){
        counter++;
    }


    /**
     * The attack bonus of this unit varies with terrain, advantage when attacking from Hill
     * Disadvantage in Forest
     * @return the attack bonus of the unit
     * @param enemyUnit the unit that is being attacked
     * @param terrain the terrain the attack is happening in
     */
    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {
        return 3 + getTerrainAttackBonus(terrain);
    }

    /**
     * Variable resist bonus. Depends on number of times the unit has been attacked.
     * Starts at 6, and decreases by 2 each time the unit is attacked, until the bonus is at 2, where it is locked
     * @return the current resist bonus of the unit.
     * @param terrain the terrain the attack is taking place in
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        int resistBonus;
        if (this.counter == 0){
            resistBonus = 6;
        }
        else if (this.counter == 1){
            resistBonus = 4;
        }
        else{
            resistBonus = 2;
        }
        increaseCounter();
        return resistBonus;
    }

    /**
     * Method for calculating attack bonus based on terrain
     * If the terrain is Forest, the unit loses 2 points of damage, if the terrain is Hill, the unit gains 2 points of damage
     * @param terrain the terrain the attack is taking place in
     * @return the calculated attack bonus
     */
    public int getTerrainAttackBonus(Terrain terrain) {
        return switch (terrain) {
            case Forest -> -2;
            case Hill -> 2;
            default -> 0;
        };
    }
}
