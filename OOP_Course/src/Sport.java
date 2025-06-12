public class Sport {
    int number;
    String name;
    int countries;
    boolean isOlympic;

    public Sport(int number, String name, int countries, boolean isOlympic) {
        this.number = number;
        this.name = name;
        this.countries = countries;
        this.isOlympic = isOlympic;
    }

    @Override
    public String toString() {
        return number + "," + name + "," + countries + "," + isOlympic;
    }
}

