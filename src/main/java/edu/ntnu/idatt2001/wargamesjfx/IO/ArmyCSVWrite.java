package edu.ntnu.idatt2001.wargamesjfx.IO;


import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Units.Unit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.List;

/**
 * Class for writing an Army to a csv file.
 * A fair amount of the code is copied/inspired by code examples in IDATT 2001 Filhåndtering
 */
public class ArmyCSVWrite {
    private static final String NEWLINE = "\n";
    private static final String DELIMITER = ",";

    /**
     * Instantiates a new Army csv write.
     * Empty constructor since the class should be able to write multiple files without creating a new instance.
     */
    public ArmyCSVWrite(){}

    /**
     * The actual method for writing the army to a file.
     * Checks that the path of the file is correct, that it is in the correct format. i.e '.csv'
     * Uses FileWriter to write strings to files. First line being the army name, and the lines after being
     * a single unit.
     *
     * @param army the army that will be written to a file
     * @param file the file, the army should be written to. Could be an existing file or a new one.
     * @throws IOException throws IOException if any of the IO operations fail.
     */

    //TODO fix it so number shows after unit
    public static void writeFile(Army army, File file, boolean bool) throws IOException{
        if (!file.getPath().startsWith(FileSystems.getDefault()
                .getPath("src","main","resources").toString())){
            throw new IOException("Error, files must be written to src/main/resources/");
        }
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Only .csv files are supported");
        }
        if (army == null){
            throw new IOException("Army cannot be null");
        }
        String line = army.getName();
        Map<Unit, Integer> unitsMapped = getMappedUnits(army.getAllUnits());
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(line + NEWLINE);
            unitsMapped.forEach((unit, number) -> {
                try{
                    fileWriter.write(unit.getClass().getSimpleName() + DELIMITER + unit.getName() + DELIMITER +
                            number + NEWLINE);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            throw new IOException("Cannot write army to file:" + e.getMessage());
        }
        if (bool) {
            try (FileWriter fileWriter = new FileWriter("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/allArmies.csv", true)) {
                fileWriter.write(line + NEWLINE);
            } catch (IOException e) {
                throw new IOException("Cannot write army name to file:" + e.getMessage());
            }
        }
    }


    private static Map<Unit, Integer> getMappedUnits(List<Unit> list) throws IllegalArgumentException{
        if (list == null || list.size() == 0){
            throw new IllegalArgumentException("List cannot be empty/null");
        }
        Map<Unit, Integer> unitTypeAndNumberMapped = new HashMap<>();
        for (Unit unit : list){
            if (!unitTypeAndNumberMapped.containsKey(unit)){
                unitTypeAndNumberMapped.put(unit, 1);
            }
            else {
                unitTypeAndNumberMapped.put(unit, unitTypeAndNumberMapped.get(unit) + 1);
            }
        }
        return unitTypeAndNumberMapped;
    }
}
