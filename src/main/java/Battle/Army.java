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

    public Army(String name, ArrayList<Unit> units){
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfUnits(){
        return units.size();
    }

    public void addUnit(Unit unit){
        units.add(unit.copy());
    }

    public void addAllUnits(ArrayList<Unit> units1){
        for (Unit unit : units1){
            units.add(unit.copy());
        }
    }

    public boolean removeUnit(Unit unit){
        return units.remove(unit);
    }

    public boolean hasUnits(){
        return getNumberOfUnits() > 0;
    }

    public ArrayList<Unit> getAllUnits(){
        ArrayList<Unit> returnList = new ArrayList<>();
        for (Unit unit : units){
            returnList.add(unit.copy());
        }
        return returnList;
    }

    public Unit getRandomUnit(){
        Random number = new Random();
        return units.get(number.nextInt(getNumberOfUnits()));
    }

    @Override
    public String toString() {
        return "Name of Army: " + this.name + "\n Army Size: " + getNumberOfUnits() + "\n";
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
