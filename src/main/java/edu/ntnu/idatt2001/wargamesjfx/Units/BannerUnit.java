package edu.ntnu.idatt2001.wargamesjfx.Units;

public class BannerUnit extends Unit{

    public BannerUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    public BannerUnit(String name){super(80, name,0, 0);}

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getResistBonus() {
        return 0;
    }
}
