package edu.ntnu.idatt2001.wargamesjfx.IO;

import edu.ntnu.idatt2001.wargamesjfx.Battle.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCSVReadTest {

    @Test
    void readArmyFromCSV() {
        ArmyCSVRead armyCSVRead = new ArmyCSVRead();
        Army army = null;
        try {
            armyCSVRead.readArmyFromCSV(new File("src/main/resources/Army1.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                army = armyCSVRead.readArmyFromCSV(new File("src/main/resources/Army1.csv"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        assert army != null;
        assertEquals(76,army.getNumberOfUnits());
    }

    @Test
    void readNonExistingFile(){
        ArmyCSVRead armyCSVRead = new ArmyCSVRead();
        assertThrows(IOException.class, () -> armyCSVRead.readArmyFromCSV(
                new File("src/main/resources/noFileToSeeHere.csv")));
    }

    @Test
    void readEmptyFile(){
        ArmyCSVRead armyCSVRead = new ArmyCSVRead();
        assertThrows(IOException.class, () -> armyCSVRead.readArmyFromCSV(
                new File("src/main/resources/Empty.csv")));
    }

}