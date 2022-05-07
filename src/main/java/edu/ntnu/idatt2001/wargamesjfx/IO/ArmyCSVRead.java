package edu.ntnu.idatt2001.wargamesjfx.IO;


import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The code is heavily inspired by lecture in file handling in IDATT2001 Example code.
 */
/**
 * The type Army csv read, used for reading and creating an army from a file.
 */
public class ArmyCSVRead {
    private static final String DELIMITER = ",";

    /**
     * Instantiates a new Army csv read.
     */
    public ArmyCSVRead() {
    }

    /**
     * Reads Army from a csv file.
     * First the method ensures that the path is not only a '.csv' file, but also that it exists,
     * and that it is not empty.
     * <p>
     * Afterwards it uses scanner to register one line of the file at a time. Creating a new Army with the first line
     * and then adding units all remaining lines in the file. It splits the lines on comma, and checks that the length
     * of the split array, is 3. If not, the Army is not written correctly in the file, and can therefor not be read.
     * <p>
     * Then it checks what type of unit the added unit should be, before adding that unit. Continues as long as the
     * scanner finds a new line with information in it.
     *
     * @param file the file the army should be read from
     * @return the army that is created upon reading the file
     * @throws IOException the io exceptions that are thrown if any of the operations fail.
     */
    public static Army readArmyFromCSV(File file) throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IOException("Only .csv files are supported");
        }
        if (!file.exists()) {
            throw new IOException("File does not exist");
        }
        Army army;
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNext()) {
                throw new IOException("File is empty");
            }
            army = new Army(scanner.nextLine());
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(DELIMITER);

                if (values.length != 1) {
                    throw new IOException("Invalid format. Ensure lines in .csv file is: 'Type'");
                }

                String type = values[0];
                UnitType unitType;
                try {
                    unitType = UnitType.valueOf(type);
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException(e.getMessage());
                }
                army.addUnit(GetUnitFactory.getUnit(unitType));
            }
        }
        return army;
    }

    public static ArrayList<String> getArmies() {
        ArrayList<String> returnList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/resources/edu/ntnu/idatt2001/" +
                "wargamesjfx/files/allArmies.csv"))) {
            if (!scanner.hasNext()) {
                throw new IOException("File is empty");
            }
            while (scanner.hasNext()){
                returnList.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public static Army getExistingArmy(String armyName) {
        Army returnArmy = null;
        try (Scanner scanner = new Scanner(new File("src/main/resources/edu/ntnu/idatt2001/" +
                "wargamesjfx/files/allArmies.csv"))) {
            if (!scanner.hasNext()) {
                throw new IOException("File is empty");
            }
            while (scanner.hasNext()) {
                if (scanner.nextLine().equals(armyName)) {
                    returnArmy = readArmyFromCSV(new File("src/main/resources/edu/ntnu/idatt2001/" +
                            "wargamesjfx/files/" + armyName + ".csv"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArmy;
    }

    public static boolean isNewArmy(Army army){
        ArrayList<String> existingArmies = getArmies();
        for (String existingArmy : existingArmies){
            if (army.getName().equals(existingArmy)){
                return false;
            }
        }
        return true;
    }
}