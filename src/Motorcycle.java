import java.util.InputMismatchException;
import java.util.Scanner;

public class Motorcycle extends Vehicle {
    private String motorcycleType;
    private int helmetCount;
    private double helmetPrice;
    Scanner scanner = new Scanner(System.in);

    public Motorcycle(String type, String brand, String name, String licenseNumber, int topSpeed, int gasCapacity, int wheel, String motorcycleType, int helmetCount) {
        super(type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel);
        this.motorcycleType = motorcycleType;
        this.helmetCount = helmetCount;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public int getHelmetCount() {
        return helmetCount;
    }

    public void setHelmetCount(int helmetCount) {
        this.helmetCount = helmetCount;
    }

    public double getHelmetPrice() {
        return helmetPrice;
    }

    public void setHelmetPrice(double helmetPrice) {
        this.helmetPrice = helmetPrice;
    }

    @Override
    public void specificInput(Scanner scanner) {
        String motorcycleType = null;
        do {
            try {
                System.out.println("Input motorcycle type [Automatic | Manual]: ");
                motorcycleType = scanner.nextLine();
                if (!"Automatic".equals(motorcycleType) && !"Manual".equals(motorcycleType)) {
                    throw new Exception("Invalid motorcycle type.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!"Automatic".equals(motorcycleType) && !"Manual".equals(motorcycleType));

        this.motorcycleType = motorcycleType;

        int helmetCount = 0;
        do {
            try {
                System.out.println("Input helmet count [>= 1]: ");
                helmetCount = scanner.nextInt();
                scanner.nextLine(); 
                if (helmetCount < 1) {
                    throw new Exception("Helmet count must be at least 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (helmetCount < 1);

        this.helmetCount = helmetCount;
        
        System.out.println("Enter the price of each helmet:");
        this.helmetPrice = scanner.nextDouble();
        
        System.out.println("ENTER to return");
        scanner.nextLine(); 
        scanner.nextLine();
    }

    @Override
    public void display() {
        System.out.println("Motorcycle Details:");
        System.out.println("Brand: " + getBrand());
        System.out.println("Name: " + getName());
        System.out.println("License Plate: " + getLicenseNumber());
        System.out.println("Type: " + getMotorcycleType());
        System.out.println("Gas Capacity: " + getGasCapacity());
        System.out.println("Top Speed: " + getTopSpeed());
        System.out.println("Wheel(s): " + getWheel());
        System.out.println("Helmet Count: " + getHelmetCount());
        System.out.println("Helmet Price: " + getHelmetPrice());
        stand();
        scanner.nextLine();
    }

    public static Motorcycle createMotorcycle(Scanner scanner, String type, String brand, String name, String licenseNumber, int topSpeed, int gasCapacity, int wheel) {
        Motorcycle motorcycle = null;
        String motorcycleType = null;
        int helmetCount = 0;

        System.out.println("Creating a new Motorcycle...");

        motorcycle = new Motorcycle(type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel, motorcycleType, helmetCount);
        motorcycle.specificInput(scanner);

        return motorcycle;
    }

    public void stand() {
        System.out.println(name + " is standing!");
    }
}