package edu.ntnu.idatt2001.wargamesjfx.Units;

public class MageUnit extends Unit{

    public MageUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public MageUnit (String name){super(name, 70, 40, 5);}

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getResistBonus() {
        return 0;
    }
}
