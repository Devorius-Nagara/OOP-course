import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Sorter {

    public static void sortAndSave(ArrayList<Sport> list, String filename, int sortType) {
        Comparator<Sport> comparator = switch (sortType) {
            case 1 -> Comparator.comparing(s -> s.name);
            case 2 -> Comparator.comparingInt((Sport s) -> s.countries).thenComparing(s -> s.name);
            case 3 -> Comparator.comparing((Sport s) -> s.isOlympic)
                    .thenComparingInt(s -> s.countries)
                    .thenComparing(s -> s.name);
            default -> throw new IllegalArgumentException("Невідомий тип сортування: " + sortType);
        };

        list.sort(comparator);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("N Sport Countries IsOlympic\n");
            int count = 1;
            for (Sport sport : list) {
                writer.write(String.format("%d %s %d %b\n", count++, sport.name, sport.countries, sport.isOlympic));
            }
            System.out.println("Дані успішно записано у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка під час запису у файл: " + filename);
            e.printStackTrace();
        }
    }
}