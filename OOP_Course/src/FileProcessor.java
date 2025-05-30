import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileProcessor {
    private static FileProcessor instance;
    private FileProcessor() {}
    public static FileProcessor getInstance() {
        if (instance == null) {
            instance = new FileProcessor();
        }
        return instance;
    }
    public ArrayList<Sport> readFile(String fileName) {
        ArrayList<Sport> sports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line == null || !line.equals("N Sport Countries IsOlympic")) {
                System.out.println("Формат інформації у файлі не підходить: " + fileName);
                return sports;
            }
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length != 4) continue;
                try {
                    int number = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int countries = Integer.parseInt(parts[2].trim());
                    boolean isOlympic = Boolean.parseBoolean(parts[3].trim());
                    sports.add(new Sport(number, name, countries, isOlympic));
                } catch (NumberFormatException e) {
                    System.out.println("Рядок невірного формату в " + fileName + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу " + fileName + ": " + e.getMessage());
        }
        return sports;
    }

    public ArrayList<Sport> filterByCountries(List<Sport> sports, String fileName, Predicate<Sport> filter) {
        ArrayList<Sport> filteredList = new ArrayList<>();
        for (Sport sport : sports) {
            if (filter.test(sport)) {
                filteredList.add(sport);
            }
        }
        return filteredList;
    }
}