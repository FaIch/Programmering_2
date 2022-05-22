package edu.ntnu.idatt2001.wargamesjfx.Battle;
import java.util.*;
import java.util.stream.Collectors;

import edu.ntnu.idatt2001.wargamesjfx.Units.*;

/**
 * The type Army.
 */
public class Army{
    private final String name;
    private final List<Unit> units;


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
     * calls addAllUnits method to add the units
     *
     * @param name     the name of the army
     * @param newUnits A list of units that the army will have
     * @throws IllegalArgumentException if the list is null
     */
    public Army(String name, List<Unit> newUnits) throws IllegalArgumentException{
        if (newUnits == null){
            throw new IllegalArgumentException("List cannot be null");
        }
        else {
            this.name = name;
            this.units = new ArrayList<>();
            addAllUnits(newUnits);
        }
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
     * @param unit A unit of any type that will be added to the army.
     * @throws IllegalArgumentException if the parameter unit equals null.
     */
    public void addUnit(Unit unit) throws IllegalArgumentException{
        if (unit == null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        units.add(unit);
    }

    /**
     * Adds a list of units to the army
     *
     * @param unitsToBeAdded an input list of units that will be added to the army.
     */
    public void addAllUnits(List<Unit> unitsToBeAdded) throws IllegalArgumentException{
        if (unitsToBeAdded == null){
            throw new IllegalArgumentException("List of units cannot be null");
        }
        for (Unit unit : unitsToBeAdded){
            addUnit(unit);
        }
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
        else {
            units.remove(unit);
        }
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
     *
     * @return the array list of units from the army.
     */
    public List<Unit> getAllUnits() {
        return units;
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

    /**
     * Get all infantry units in the Army.
     *
     * Checks if the unit in army is an instance of InfantryUnit
     *
     * @return A list consisting of infantry units in the army.
     */
    public List<Unit> getInfantryUnits(){
        return units.stream()
                .filter(p -> p instanceof InfantryUnit)
                .collect(Collectors.toList());
    }

    /**
     * Get ranged units in the army
     *
     * checks if the unit is an instance of ranged unit
     *
     * @return the list consisting of ranged units in the army
     */
    public List<Unit> getRangedUnits(){
        return units.stream()
                .filter(p -> p instanceof RangedUnit)
                .collect(Collectors.toList());
    }

    /**
     * Get cavalry units in the army.
     *
     * Checks if the unit is an instance of cavalry unit and not an instance of commander
     *
     * @return the list of cavalry units in the army.
     */
    public List<Unit> getCavalryUnits(){
        return units.stream()
                .filter(p -> p instanceof CavalryUnit)
                .filter(p -> !(p instanceof  CommanderUnit))
                .collect(Collectors.toList());
    }

    /**
     * Get list of the commander units in the army.
     *
     * Checks if the unit is an instance of cavalry unit, and an instance of commander unit.
     *
     * @return the list of commander units in the army.
     */
    public List<Unit> getCommanderUnits(){
        return units.stream()
                .filter(p -> p instanceof CommanderUnit)
                .collect(Collectors.toList());
    }


    /**
     * Get list of MageUnits in the army
     *
     * Checks for instance of MageUnit, if so, adds it to the list
     *
     * @return the list of mage units
     */
    public List<Unit> getMageUnits(){
        return units.stream()
                .filter(p -> p instanceof MageUnit)
                .collect(Collectors.toList());
    }

    /**
     * Get list of BannerUnits in the army
     *
     * Checks for instance of BannerUnit, if so, adds it to the list
     *
     * @return the list of banner units
     */
    public List<Unit> getBannerUnits(){
        return units.stream()
                .filter(p -> p instanceof BannerUnit)
                .collect(Collectors.toList());
    }

    /**
     * Get list of DragonUnits in the army
     *
     * Checks for instance of DragonUnit, if so, adds it to the list
     *
     * @return the list of dragon units
     */
    public List<Unit> getDragonUnits(){
        return units.stream()
                .filter(p -> p instanceof DragonUnit)
                .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return "Name of Army: " + this.name +
                "\n Number of units left: " + getNumberOfUnits() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army army)) return false;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
