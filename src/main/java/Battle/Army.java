package Battle;
import Units.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * The type Army.
 */
public class Army {
    private final String name;
    private final ArrayList<Unit> units;


    /**
     * Instantiates a new Army by name, since there is no list of units when the Army is instantiated
     * an empty list is generated.
     *
     * @param name the name of the army.
     */
    public Army(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Instantiates a new Army by name and a list of units.
     * calls addAllUnits method to deep copy the units before adding.
     *
     * @param name     the name of the army
     * @param newUnits A list of units that the army will have
     * @throws IllegalArgumentException if the list is empty or null.
     */
    public Army(String name, ArrayList<Unit> newUnits) throws IllegalArgumentException{
        if (newUnits == null || newUnits.isEmpty()){
            throw new IllegalArgumentException("List cannot be empty/null");
        }
        this.name = name;
        this.units = new ArrayList<>();
        addAllUnits(newUnits);
    }

    /**
     * Gets name of the army
     *
     * @return the name of the army
     */
    public String getName() {
        return name;
    }

    /**
     * Get number of units in the army.
     *
     * @return the number of units in the army.
     */
    public int getNumberOfUnits(){
        return units.size();
    }

    /**
     * Add unit to army
     *
     * Creates a list of the one unit to use the addAllUnits method to add a deep copy version of the input unit.
     *
     * @param unit A unit of any type that will be added to the army.
     * @throws IllegalArgumentException if the parameter unit equals null.
     */
    public void addUnit(Unit unit) throws IllegalArgumentException{
        if (unit == null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        ArrayList<Unit> pushUnit = new ArrayList<>();
        pushUnit.add(unit);
        addAllUnits(pushUnit);
    }

    /**
     * Adds a list of units
     *
     * calls the deepCopyUnits method to get deep copies of each unit before it is added to the army.
     *
     * @param units1 an input list of units that will be added to the army.
     */
    public void addAllUnits(ArrayList<Unit> units1) throws IllegalArgumentException{
        if (units1 == null || units1.isEmpty()){
            throw new IllegalArgumentException("List cannot be empty/null");
        }
        units.addAll(deepCopyUnits(units1));
    }

    /**
     * Remove unit from army
     *
     * @param unit the unit that will be removed
     * @throws IllegalArgumentException if the unit is not found in the army.
     */
    public void removeUnit(Unit unit) throws IllegalArgumentException{
        if (!units.contains(unit)){
            throw new IllegalArgumentException("Unit not in list");
        }
        units.remove(unit);
    }

    /**
     * Checks if the army has any units
     *
     * @return true if the army has units, false if the army is empty.
     */
    public boolean hasUnits(){
        return getNumberOfUnits() > 0;
    }

    /**
     * Get a list of all units in the army
     * Deep copies every unit in the army, and adds it to a list
     *
     * @return the array list of copied units from the army.
     */
    public ArrayList<Unit> getAllUnits() {
        return this.deepCopyUnits(units);
    }

    /**
     * Method for deep coping units.
     * Checks what type of unit the unit is. then instantiates a new unit of that type.
     * Creates lower coupling between classes
     *
     * @param unitsIn the list of units that needs to be deep copied
     * @return The input list deep copied.
     */

    public ArrayList<Unit> deepCopyUnits(ArrayList<Unit> unitsIn){
        ArrayList<Unit> returnList = new ArrayList<>();
        for (Unit unit : unitsIn){
            if (unit instanceof InfantryUnit){
                returnList.add(new InfantryUnit(unit.getName(),unit.getHealth()));
            }
            else if (unit instanceof RangedUnit){
                returnList.add(new RangedUnit(unit.getName(),unit.getHealth()));
            }
            else if (unit instanceof CavalryUnit){
                if (unit instanceof CommanderUnit){
                    returnList.add(new CommanderUnit(unit.getName(),unit.getHealth()));
                }
                else {
                    returnList.add(new CavalryUnit(unit.getName(),unit.getHealth()));
                }
            }
        }
        return returnList;
    }

    /**
     * Get a random unit from the army.
     *
     * @return a random unit from the list of units.
     */
    public Unit getRandomUnit(){
        Random number = new Random();
        return this.units.get(number.nextInt(getNumberOfUnits()));
    }

    @Override
    public String toString() {
        return "Name of Army: " + this.name +
                "\n Number of units left: " + getNumberOfUnits() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
