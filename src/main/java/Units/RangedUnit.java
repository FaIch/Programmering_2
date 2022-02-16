package Units;

public class RangedUnit extends Unit{
    private int counter;

    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health){
        super(name, health, 15, 8);
    }

    public void increaseCounter(){
        counter++;
    }
    @Override
    public int getAttackBonus() {
        return 3;
    }

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
