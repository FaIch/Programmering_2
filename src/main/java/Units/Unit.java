package Units;

public abstract class Unit {
    private final String name;
    private int health;
    private final int attack;
    private final int armor;

    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        if (health <= 0 || attack <= 0 || armor <= 0){
            throw new IllegalArgumentException("Unit stats must be above zero.");
        }
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
        return "Name: " + this.name +
                "\nHealth: " + this.health +
                "\nAttack: " + this.attack +
                "\nArmor:" + this.armor + "\n";
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();

}
