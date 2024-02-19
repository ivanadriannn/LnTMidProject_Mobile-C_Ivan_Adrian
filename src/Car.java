import java.util.InputMismatchException;
import java.util.Scanner;

public class Car extends Vehicle {
    private String carType;
    private int entertainmentSystem;
    Scanner scanner = new Scanner(System.in);

    public Car(String type, String brand, String name, String licenseNumber, int topSpeed, int gasCapacity, int wheel, String carType, int entertainmentSystem) {
        super(type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel);
        this.carType = carType;
        this.entertainmentSystem = entertainmentSystem;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getEntertainmentSystem() {
        return entertainmentSystem;
    }

    public void setEntertainmentSystem(int entertainmentSystem) {
        this.entertainmentSystem = entertainmentSystem;
    }

    @Override
    public void specificInput(Scanner scanner) {
        String carType = null;
        do {
            try {
                System.out.println("Input car type [SUV | Supercar | Minivan]: ");
                carType = scanner.nextLine();
                if (!"SUV".equals(carType) && !"Supercar".equals(carType) && !"Minivan".equals(carType)) {
                    throw new Exception("Invalid car type.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!"SUV".equals(carType) && !"Supercar".equals(carType) && !"Minivan".equals(carType));

        this.carType = carType;

        int entertainmentSystem = 0;
        do {
            try {
                System.out.println("Input entertainment system amount [>= 1]: ");
                entertainmentSystem = scanner.nextInt();
                scanner.nextLine();
                if (entertainmentSystem < 1) {
                    throw new Exception("Entertainment system count must be at least 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (entertainmentSystem < 1);

        this.entertainmentSystem = entertainmentSystem;
        System.out.println("ENTER to return");
        scanner.nextLine(); 
    }

    @Override
    public void display() {
        System.out.println("Car Details:");
        System.out.println("Brand: " + getBrand());
        System.out.println("Name: " + getName());
        System.out.println("License Plate: " + getLicenseNumber());
        System.out.println("Type: " + getCarType());
        System.out.println("Gas Capacity: " + getGasCapacity());
        System.out.println("Top Speed: " + getTopSpeed());
        System.out.println("Wheel(s): " + getWheel());
        System.out.println("Entertainment System: " + getEntertainmentSystem());
        turnOnEntertainmentSystem();
        scanner.nextLine();
    }

    public static Car createCar(Scanner scanner, String type, String brand, String name, String licenseNumber, int topSpeed, int gasCapacity, int wheel) {
        Car car = null;
        String carType = null;
        int entertainmentSystem = 0;

        System.out.println("Creating a new Car...");

        car = new Car(type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel, carType, entertainmentSystem);
        car.specificInput(scanner);

        return car;
    }

    @Override
    public void turnOnEntertainmentSystem() {
        super.turnOnEntertainmentSystem();
        if ("Supercar".equals(carType)) {
            System.out.println("Boosting!");
        }
    }
}