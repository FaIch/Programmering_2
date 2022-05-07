package edu.ntnu.idatt2001.wargamesjfx.Units;

public class DragonUnit extends Unit{


    /**
     * Instantiates a new Dragon Unit.
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public DragonUnit(int health, int attack, int armor) {super(health, attack, armor);}

    public DragonUnit(){
        super(200, 30, 20);

    }

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getResistBonus() {
        return 0;
    }
}
