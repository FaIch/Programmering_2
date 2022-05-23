package edu.ntnu.idatt2001.wargamesjfx.io;

import edu.ntnu.idatt2001.wargamesjfx.battle.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArmyCSVReadTest {

    @Test
    void readArmyFromCSV() {
        Army army = null;
        try {
            ArmyCSVRead.readArmyFromCSV(new File("src/main/resources/edu/ntnu/idatt2001/" +
                    "wargamesjfx/files/Human.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                army = ArmyCSVRead.readArmyFromCSV(new File("src/main/resources/edu/ntnu/idatt2001/" +
                        "wargamesjfx/files/Human.csv"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        assert army != null;
        assertEquals(76,army.getNumberOfUnits());
    }

    @Test
    void readNonExistingFile(){
        assertThrows(IOException.class, () -> ArmyCSVRead.readArmyFromCSV(
                new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/noFileToSeeHere.csv")));
    }

    @Test
    void readEmptyFile(){
        assertThrows(IOException.class, () -> ArmyCSVRead.readArmyFromCSV(
                new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/Empty.csv")));
    }

}