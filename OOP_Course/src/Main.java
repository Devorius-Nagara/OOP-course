import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileProcessor fileProcessor = FileProcessor.getInstance();
        List<String> inputFiles = new ArrayList<>();
        System.out.println("Введіть імена файлів (без .txt), які потрібно зчитати (введіть 0 для завершення):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("0")) break;
            if (!input.endsWith(".txt")) input += ".txt";
            inputFiles.add(input);
        }
        if (inputFiles.isEmpty()) {
            System.out.println("Файли не введені. Завершення програми.");
            return;
        }
        ArrayList<Sport> allSports = new ArrayList<>();
        for (String file : inputFiles) {
            ArrayList<Sport> sportsFromFile = fileProcessor.readFile(file);
            allSports.addAll(sportsFromFile);
        }
        if (allSports.isEmpty()) {
            System.out.println("Не вдалося зчитати жодного виду спорту.");
            return;
        }
        System.out.println("Оберіть тип сортування:\n1 - За назвою спорту\n2 - За кількістю країн, потім за назвою" +
                "\n3 - За олімпійськістю, потім за кількістю країн, потім за назвою");
        int sortType = 0;
        while (sortType < 1 || sortType > 3) {
            System.out.print("Ваш вибір (1/2/3): ");
            if (scanner.hasNextInt()) {
                sortType = scanner.nextInt();
            } else {
                scanner.next();
            }
        }
        ArrayList<Sport> group1 = new ArrayList<>();
        ArrayList<Sport> group2 = new ArrayList<>();
        ArrayList<Sport> group3 = new ArrayList<>();
        for (Sport sport : allSports) {
            if (sport.countries <= 50) {
                group1.add(sport);
            } else if (sport.countries <= 119) {
                group2.add(sport);
            } else {
                group3.add(sport);
            }
        }
        Sorter.sortAndSave(group1, "less_than_50.txt", sortType);
        Sorter.sortAndSave(group2, "between_51_and_119.txt", sortType);
        Sorter.sortAndSave(group3, "more_than_120.txt", sortType);
        System.out.println("Обробку завершено. Дані збережено у вихідні файли.");
    }
}
