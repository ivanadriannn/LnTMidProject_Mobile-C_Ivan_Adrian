import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        while (true) {
        	clearScreen();
            System.out.println("================================");
            System.out.println("= WELCOME TO IVAN'S AUTOMOTIVE =");
            System.out.println("================================");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: [1 - 3]: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
         
            switch (choice) {
                case 1:
                    Vehicle vehicle = Vehicle.createVehicle(scanner);
                    if (vehicle != null) {
                        vehicles.add(vehicle);
                        System.out.println("Vehicle added successfully!\n");
                    } else {
                        System.out.println("Failed to add vehicle!\n");
                    }
                    clearScreen();
                    break;
                case 2:
                    Vehicle.viewVehicles(vehicles, scanner);
                    System.out.println("Please press ENTER to continue...");
                    scanner.nextLine();
                    clearScreen();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                    clearScreen();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number from 1-3...\n");
            }
        }
    }
    
    public static void clearScreen() {
        for (int i = 0; i < 25; ++i) {
            System.out.println();
        }
    }
}