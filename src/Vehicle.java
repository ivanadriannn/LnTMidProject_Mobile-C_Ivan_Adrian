import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Vehicle {
	protected String type;
    protected String brand;
    protected String name;
    protected String licenseNumber;
    protected int topSpeed;
    protected int gasCapacity;
    protected int wheel;
    
    public Vehicle(String type, String brand, String name, String licenseNumber, int topSpeed, int gasCapacity,
			int wheel) {
		this.type = type;
		this.brand = brand;
		this.name = name;
		this.licenseNumber = licenseNumber;
		this.topSpeed = topSpeed;
		this.gasCapacity = gasCapacity;
		this.wheel = wheel;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLicenseNumber() {
		return licenseNumber;
	}


	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


	public int getTopSpeed() {
		return topSpeed;
	}


	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}


	public int getGasCapacity() {
		return gasCapacity;
	}


	public void setGasCapacity(int gasCapacity) {
		this.gasCapacity = gasCapacity;
	}


	public int getWheel() {
		return wheel;
	}


	public void setWheel(int wheel) {
		this.wheel = wheel;
	}
	
	public abstract void specificInput(Scanner scanner);

    public abstract void display();

    public static Vehicle createVehicle(Scanner scanner) {
        String type = null;
        do {
            try {
                System.out.println("Input type [Car | Motorcycle]: ");
                type = scanner.nextLine();
                if (!"Car".equals(type) && !"Motorcycle".equals(type)) {
                    throw new Exception("Invalid vehicle type.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!"Car".equals(type) && !"Motorcycle".equals(type));

        String brand = null;
        do {
            try {
                System.out.println("Input brand [>= 5]: ");
                brand = scanner.nextLine();
                if (brand.length() < 5) {
                    throw new Exception("Brand must be at least 5 characters.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (brand == null || brand.length() < 5);

        String name = null;
        do {
            try {
                System.out.println("Input name [>= 5]: ");
                name = scanner.nextLine();
                if (name.length() < 5) {
                    throw new Exception("Name must be at least 5 characters.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (name == null || name.length() < 5);

        String licenseNumber = null;
        do {
            try {
                System.out.println("Input license: ");
                licenseNumber = scanner.nextLine();
                if (!licenseNumber.matches("[A-Z]\\s[0-9]{1,4}\\s[A-Z]{1,3}")) {
                    throw new Exception("Invalid license number format.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (licenseNumber == null || !licenseNumber.matches("[A-Z]\\s[0-9]{1,4}\\s[A-Z]{1,3}"));

        int topSpeed = 0;
        do {
            try {
                System.out.println("Input top speed [100 <= topSpeed <= 250]: ");
                topSpeed = scanner.nextInt();
                scanner.nextLine(); 
                if (topSpeed < 100 || topSpeed > 250) {
                    throw new Exception("Top speed must be between 100 and 250.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (topSpeed < 100 || topSpeed > 250);

        int gasCapacity = 0;
        do {
            try {
                System.out.println("Input gas capacity [30 <= gasCap <= 60]: ");
                gasCapacity = scanner.nextInt();
                scanner.nextLine(); 
                if (gasCapacity < 30 || gasCapacity > 60) {
                    throw new Exception("Gas capacity must be between 30 and 60.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (gasCapacity < 30 || gasCapacity > 60);

        int wheel = 0;
        do {
            try {
            	if ("Car".equals(type)) {
            	    System.out.println("Input wheel count [4 <= wheel <= 6]: ");
            	} else if ("Motorcycle".equals(type)) {
            		System.out.println("Input wheel count [2 <= wheel <= 3]: ");
            	}
                wheel = scanner.nextInt();
                scanner.nextLine(); 
                if (("Car".equals(type) && (wheel < 4 || wheel > 6)) || ("Motorcycle".equals(type) && (wheel < 2 || wheel > 3))) {
                    throw new Exception("Invalid wheel count.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (("Car".equals(type) && (wheel < 4 || wheel > 6)) || ("Motorcycle".equals(type) && (wheel < 2 || wheel > 3)));

        if ("Car".equals(type)) {
            return Car.createCar(scanner, type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel);
        } else if ("Motorcycle".equals(type)) {
            return Motorcycle.createMotorcycle(scanner, type, brand, name, licenseNumber, topSpeed, gasCapacity, wheel);
        } else {
            System.out.println("Invalid vehicle type.");
            return null;
        }
    }

    public static void viewVehicles(ArrayList<Vehicle> vehicles, Scanner scanner) {
        System.out.println("List of vehicles:");
        System.out.println("+----+-----------------------+---------------------------+");
        System.out.println("| No | Type of Vehicle       | Name                      |");
        System.out.println("+----+-----------------------+---------------------------+");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.printf("| %-2d | %-21s | %-25s |%n", i + 1, vehicle.getType(), vehicle.getName());
            System.out.println("+----+-----------------------+---------------------------+");
        }
        
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles to test drive!");
            return;
        }
        else {
            System.out.println("Pick a vehicle number to test drive [Enter '0' to exit]: ");
        }
        
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for vehicle number. Please enter a number.");
            scanner.nextLine(); 
            return;
        }

        if (choice == 0) {
            System.out.println("Exiting test drive mode.");
            return;
        }

        if (choice >= 1 && choice <= vehicles.size()) {
            Vehicle vehicle = vehicles.get(choice - 1);
            System.out.println("Details of vehicle " + choice + ":");
            vehicle.display();
        } else {
            System.out.println("Invalid vehicle number.");
        }
    }

    public void turnOnEntertainmentSystem() {
        System.out.println("Turning on entertainment system...");
    }
}