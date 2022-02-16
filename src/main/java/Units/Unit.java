package Units;

public abstract class Unit {
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
            opponent.health = opponent.health - (this.attack + this.getAttackBonus()) + (opponent.armor + opponent.getResistBonus());
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
        return "Name: " + this.name + "\nHealth: " + this.health + "\nAttack: " + this.attack +
                "\nArmor:" + this.armor + "\n";
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();
}
