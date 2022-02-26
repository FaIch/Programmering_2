package Units;

/**
 * The type Ranged unit.
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
     *
     * @param name   the name of the unit set by user
     * @param health the health of the unit set by user
     */
    public RangedUnit(String name, int health){
        super(name, health, 15, 8);
    }

    /**
     * Increases counter for calculating resist bonus
     */
    public void increaseCounter(){
        counter++;
    }

    /**
     * This unit has a constant attack bonus of 3.
     * @return the attack bonus of the unit
     */
    @Override
    public int getAttackBonus() {
        return 3;
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
