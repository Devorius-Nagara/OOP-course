import java.util.Scanner;

class Vehicle {
    double cost;
    double mass;
    double velocity;

    static Vehicle createVehicle(double cost, double mass, double velocity) {
        Vehicle vehicle = new Vehicle();
        vehicle.cost = cost;
        vehicle.mass = mass;
        vehicle.velocity = velocity;
        return vehicle;
    }
}

class MathProcessor {
    static void performOperation(String action, double[] value, double operand) {
        switch (action) {
            case "divide":
                if (operand == 0) {
                    System.out.println("Помилка: ділення на нуль неможливе");
                    break;
                }
                value[0] /= operand;
                break;
            case "logBase10":
                value[0] = Math.log10(value[0] + operand);
                break;
            case "logBaseE":
                value[0] = Math.log(value[0] + operand);
                break;
            case "exponentiate":
                value[0] = Math.pow(value[0], operand);
                break;
            default:
                System.out.println("Невідома операція");
                break;
        }
    }

    static void updateVehicle(String attribute, Vehicle vehicle, double factor) {
        switch (attribute) {
            case "cost":
                vehicle.cost *= factor;
                break;
            case "mass":
                vehicle.mass *= factor;
                break;
            case "velocity":
                vehicle.velocity *= factor;
                break;
            default:
                System.out.println("Неправильний параметр");
        }
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть опцію [1-3]:\n1. Виконати математичні обчислення\n2. Змінити параметри транспортного засобу\n3. Завершити програму");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введіть значення для x:");
                double[] x = {scanner.nextDouble()};
                System.out.println("Введіть значення для y:");
                double y = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Оберіть операцію:\n1. divide - ділення\n2. logBase10 - log10(x + y)\n3. logBaseE - natural log(x + y)\n4. exponentiate - x^y");
                String action = scanner.nextLine();
                MathProcessor.performOperation(action, x, y);
                System.out.println("Результат: " + x[0]);
                break;

            case 2:
                System.out.println("Введіть вартість, масу та швидкість транспортного засобу:");
                double cost = scanner.nextDouble();
                double mass = scanner.nextDouble();
                double velocity = scanner.nextDouble();
                Vehicle vehicle = Vehicle.createVehicle(cost, mass, velocity);
                scanner.nextLine();
                System.out.println("Оберіть атрибут для зміни (cost, mass, velocity):");
                String attribute = scanner.nextLine();
                final double factor = 1.39;
                MathProcessor.updateVehicle(attribute, vehicle, factor);
                System.out.println("Оновлені параметри: cost = " + vehicle.cost + ", mass = " + vehicle.mass + ", velocity = " + vehicle.velocity);
                break;

            case 3:
                System.out.println("Програма завершена");
                System.exit(0);

            default:
                System.out.println("Невірний вибір");
                break;
        }
    }
}
