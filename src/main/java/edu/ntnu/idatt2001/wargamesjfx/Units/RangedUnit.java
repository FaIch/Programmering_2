package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Battle;

/**
 * The type Ranged unit.
 * Has a variable counter, used to determine what the resist bonus should be, based on how many times the unit has
 * been attacked
 */
public class RangedUnit extends Unit{
    private int counter;

    /**
     * Instantiates a new Ranged unit.
     * Constructor 1
     * Constructor where the user decides all stats for the unit
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Ranged unit.
     * Constructor 2
     * Constructor there the attack and armor of the unit is predetermined
     *  @param name   the name of the unit set by user
     *
     */
    public RangedUnit(String name){
        super(name, 100, 15, 8);
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
     */
    @Override
    public int getAttackBonus() {
        return switch (Battle.terrain) {
            case "Forest" -> 1;
            case "Hill" -> 5;
            default -> 3;
        };
    }

    /**
     * Variable resist bonus. Depends on number of times the unit has been attacked.
     * Starts at 6, and decreases by 2 each time the unit is attacked, until the bonus is at 2, where it is locked
     * @return the current resist bonus of the unit.
     */
    @Override
    public int getResistBonus() {
        int resistBonus = 0;
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
}
