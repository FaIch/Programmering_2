package IO;

import Battle.Army;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

public class ArmyCSVWrite {
    private static final String NEWLINE = "\n";
    private static final String DELIMITER = ",";

    public ArmyCSVWrite(){}

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
