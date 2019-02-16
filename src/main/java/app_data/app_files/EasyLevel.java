package app_data.app_files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EasyLevel implements ReadDictionary{
    public static List<String> words = new ArrayList<String>();

    private static String filePath() {
        String filePath = "C:\\Users\\rafal\\workspace\\ProjektNaProgramowanie\\src\\main\\java\\app_data\\app_files\\dictionary.csv";
        return filePath;
    }


    public void readDictionary() {
            try {
                words = Files.readAllLines(Paths.get(filePath()));
            } catch (IOException ex) {
                System.out.println("Brak pliku!");
            }
        }
    }



