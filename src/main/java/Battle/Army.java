package Battle;
import Units.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Army {
    private final String name;
    private final ArrayList<Unit> units;


    public Army(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    public Army(String name, ArrayList<Unit> newUnits) throws IllegalArgumentException{
        if (newUnits == null || newUnits.isEmpty()){
            throw new IllegalArgumentException("List cannot be empty/null");
        }
        this.name = name;
        this.units = new ArrayList<>();
        units.addAll(newUnits);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfUnits(){
        return units.size();
    }

    public void addUnit(Unit unit) throws IllegalArgumentException{
        if (unit == null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        units.add(unit);
    }

    public void addAllUnits(ArrayList<Unit> units1) throws IllegalArgumentException{
        if (units1 == null || units1.isEmpty()){
            throw new IllegalArgumentException("List cannot be null/empty");
        }
        units.addAll(units1);
    }

    public void removeUnit(Unit unit) throws IllegalArgumentException{
        if (!units.contains(unit)){
            throw new IllegalArgumentException("Unit not in list");
        }
        units.remove(unit);
    }

    public boolean hasUnits(){
        return getNumberOfUnits() > 0;
    }

    public ArrayList<Unit> getAllUnits(){
        ArrayList<Unit> returnList = new ArrayList<>();
        for (Unit unit : units){
            returnList.add(unit);
        }
        return returnList;
    }

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
