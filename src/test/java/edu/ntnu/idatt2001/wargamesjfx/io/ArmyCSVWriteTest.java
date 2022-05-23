package edu.ntnu.idatt2001.wargamesjfx.io;

import edu.ntnu.idatt2001.wargamesjfx.battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.units.*;
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
        testList.add(new CommanderUnit(""));
        int i;
        for (i = 0; i < 50; i++) {
            testList.add(new InfantryUnit(""));
        }
        for (i = 0; i < 20; i++) {
            testList.add(new RangedUnit(""));
        }
        for (i = 0; i < 5; i++) {
            testList.add(new CavalryUnit(""));
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
        army.addUnit(new InfantryUnit(""));
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