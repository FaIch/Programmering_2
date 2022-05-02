package edu.ntnu.idatt2001.wargamesjfx.Units;

public class BannerUnit extends Unit{

    public BannerUnit (String name){super(name, 80, 0, 0);}

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getResistBonus() {
        return 0;
    }
}
