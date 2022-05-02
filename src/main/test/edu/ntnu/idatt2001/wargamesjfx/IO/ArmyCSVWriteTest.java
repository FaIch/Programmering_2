package edu.ntnu.idatt2001.wargamesjfx.IO;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCSVWriteTest {

    @Test
    void writeArmyToFile() {
        Army army = new Army("Human");
        ArrayList<Unit> testList = new ArrayList<>();
        testList.add(new CommanderUnit("Bob"));
        int i = 0;
        for (i = 0; i < 50; i++) {
            testList.add(new InfantryUnit("Footman"));
        }
        for (i = 0; i < 20; i++) {
            testList.add(new RangedUnit("Archer"));
        }
        for (i = 0; i < 5; i++) {
            testList.add(new CavalryUnit("Chad"));
        }
        army.addAllUnits(testList);
        try {
            ArmyCSVWrite.writeFile(army,new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/Human.csv"),false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void writeWrongFileType() {
        Army army = new Army("WrongFileType");
        army.addUnit(new InfantryUnit("Roger"));
        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/resources/wrongFileType.txt"),false));
    }

    @Test
    void writeNullArmy() {
        Army army = null;
        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/resources/nullArmy.csv"),false));
    }

    @Test
    void writeWrongFilePath() {
        Army army = new Army("");
        assertThrows(IOException.class, () -> ArmyCSVWrite.writeFile(army,
                new File("src/main/java/Battle/wrongFilePath.csv"),false));
    }
}