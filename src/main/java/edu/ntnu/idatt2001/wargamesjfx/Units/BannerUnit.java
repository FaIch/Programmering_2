package edu.ntnu.idatt2001.wargamesjfx.Units;

public class BannerUnit extends Unit{

    public BannerUnit(int health, int attack, int armor) {
        super(health, attack, armor);
    }

    public BannerUnit(){super(80, 0, 0);}

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getResistBonus() {
        return 0;
    }
}
