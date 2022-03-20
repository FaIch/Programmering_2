package IO;

import Battle.Army;
import Units.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCSVWriteTest {

    @Test
    void writeArmyToFile() {
        Army army = new Army("Army1");
        ArrayList<Unit> testList = new ArrayList<>();
        try {
            testList.add(new CommanderUnit("Bob", 180));
            int i = 0;
            for (i = 0; i < 50; i++) {
                testList.add(new InfantryUnit("Footman", 100));
            }
            for (i = 0; i < 20; i++) {
                testList.add(new RangedUnit("Archer", 100));
            }
            for (i = 0; i < 5; i++) {
                testList.add(new CavalryUnit("Chad", 100));
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        army.addAllUnits(testList);

        try {
            ArmyCSVWrite.writeFile(army,new File("src/main/resources/Army1.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void writeWrongFileType() {
        Army army = new Army("WrongFileType");
        army.addUnit(new InfantryUnit("Roger",1));
        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/resources/wrongFileType.txt")));
    }

    @Test
    void writeNullArmy() {
        Army army = null;
        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/resources/nullArmy.csv")));
    }

    @Test
    void writeWrongFilePath() {
        Army army = new Army("");

        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/java/Battle/wrongFilePath.csv")));
    }
}