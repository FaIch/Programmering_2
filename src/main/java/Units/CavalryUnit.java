package Units;

public class CavalryUnit extends Unit{
    private int counter;

    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health){
        super(name, health, 20,12);
    }

    public void increaseCounter(){
        counter++;
    }

    @Override
    public int getAttackBonus() {
        int attackBonus = 0;
        if (counter == 0){
            attackBonus = 6;
        }
        else{
            attackBonus = 2;
        }
        increaseCounter();
        return  attackBonus;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
