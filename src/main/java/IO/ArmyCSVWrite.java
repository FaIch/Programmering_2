package IO;

import Battle.Army;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * The code is heavily inspired by lecture in file handling in IDATT2001 Example code.
 */

/**
 * The class for writing an Army to a csv file.
 */
public class ArmyCSVWrite {
    private static final String NEWLINE = "\n";
    private static final String DELIMITER = ",";

    /**
     * Instantiates a new Army csv write.
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
    public static void writeFile(Army army, File file) throws IOException{
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
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(line + NEWLINE);
            army.getAllUnits().forEach(unit -> {
                try {
                    fileWriter.write(unit.getClass().getSimpleName() + DELIMITER + unit.getName() +
                            DELIMITER + unit.getHealth() + NEWLINE);
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            throw new IOException("Cannot write army to file:" + e.getMessage());
        }
    }
}
