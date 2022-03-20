package IO;

import Battle.Army;
import Units.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ArmyCSVRead {
    private static final String DELIMITER = ",";

    public ArmyCSVRead() {}

    public Army readArmyFromCSV(File file) throws IOException {
        if (!file.getName().endsWith(".csv")){
            throw new IOException("Only .csv files are supported");
        }
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        Army army;
        try (Scanner scanner = new Scanner(file)){
            if (!scanner.hasNext()){
                throw new IOException("File is empty");
            }
            army = new Army(scanner.nextLine());
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] tokens = line.split(DELIMITER);

                if (tokens.length != 3){
                    throw new IOException("Invalid format. Ensure lines in .csv file is: Type, name, health");
                }

                int health;
                try {
                    health = Integer.parseInt(tokens[2]);
                }catch (NumberFormatException e){
                    throw new IOException("Health must be integer");
                }
                String type = tokens[0];
                String name = tokens[1];

                boolean existingType = false;
                Unit unit = null;

                switch (type) {
                    case "InfantryUnit" -> {
                        unit = new InfantryUnit(name, health);
                        existingType = true;
                    }
                    case "RangedUnit" -> {
                        unit = new RangedUnit(name, health);
                        existingType = true;
                    }
                    case "CavalryUnit" -> {
                        unit = new CavalryUnit(name, health);
                        existingType = true;
                    }
                    case "CommanderUnit" -> {
                        unit = new CommanderUnit(name, health);
                        existingType = true;
                    }
                }
                if (!existingType){
                    throw new IOException("Invalid unit type.");
                }
                army.addUnit(unit);
            }
        }
        return army;
    }
}
