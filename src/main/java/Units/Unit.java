package Units;

public class Unit {
    private final String name;
    private int health;
    protected int attack;
    protected int armor;

    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public void attack(Unit opponent){
        opponent.health = opponent.health - (this.attack + getAttackBonus()) + (opponent.armor + getResistBonus());
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "/n" + "Health: " + this.health + "/n" + "Attack: " + this.attack + "/n" +
                "Armor:" + this.armor;
    }

    public int getAttackBonus(){
        return 0;
    }

    public int getResistBonus(){
        return 0;
    }
}
