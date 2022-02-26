package Units;

/**
 * The type Infantry unit.
 */
public class InfantryUnit extends Unit{

    /**
     * Instantiates a new Infantry unit.
     * Constructor 1
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Infantry unit.
     * Constructor 2
     * Name and health set by user, attack and armor predetermined
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     */
    public InfantryUnit(String name, int health){
        super(name, health, 15,10);

    }

    /**
     * Static attack bonus of 2.
     * @return The attack bonus of the unit
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     * Static resist bonus of 1.
     * @return the resist bonus of the unit.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

}
