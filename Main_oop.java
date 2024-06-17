//
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import database.CustomerWriter;

//<------------CAR RENTAL SYSTEM--------->;
public class Main_oop{
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Lux_Car car1 = new Lux_Car("lux001", "BMW", " BMW_3", 1500.0);
        Lux_Car car2 = new Lux_Car("lux002", "AUDI", "AUDI_Q5", 2000.0);
        Lux_Car car3 = new Lux_Car("lux003", "FERRARI", "ROMA", 2500.0);

        Eco_Car car4 = new Eco_Car("eco001", "Toyota", "Camry", 100.0);
        Eco_Car car5 = new Eco_Car("eco002", "Honda", "Accord", 400.0);
        Eco_Car car6 = new Eco_Car("eco003", "Mahindra", "Thar", 550.0);

        rentalSystem.lux_addCar(car1);
        rentalSystem.lux_addCar(car2);
        rentalSystem.lux_addCar(car3);

        rentalSystem.eco_addCar(car4);
        rentalSystem.eco_addCar(car5);
        rentalSystem.eco_addCar(car6);

        rentalSystem.menu();
    }
}
abstract class Car {
    public String carId;
    public String brand;
    public String model;
    public double basePricePerDay;
    public boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }



    abstract public String getCarId();

    abstract public String getBrand();

    abstract public String getModel();

    abstract public  double calculatePrice(int rentalDays);

    abstract  public boolean isAvailable();

    abstract public void rent();

    abstract public void returnCar();
}

// <-----------Lux_cars------------->//
class Lux_Car extends Car{

    public Lux_Car(String carId, String brand, String model, double basePricePerDay){
        super( carId,  brand,  model,  basePricePerDay);
    }
    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public  double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

// <-------------eco_cars-------------->
class Eco_Car extends Car{

    public Eco_Car(String carId, String brand, String model, double basePricePerDay){
        super( carId,  brand,  model,  basePricePerDay);
    }
    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public  double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    public String customerId;
    public String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}
//<----------------------class for the writing in the file------------->//
/*import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerWriter {

    public static void writeCustomerToFile(String customerName, String customerID) throws IOException {
        FileWriter fileWriter = new FileWriter("customers.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String customerDetails = customerName + "," + customerID + "," + customerPhone;
        bufferedWriter.write(customerDetails + "\n");
        bufferedWriter.close();
    }
}
}*/

class CarRentalSystem {
    private List<Lux_Car> lux_cars;
    private List<Eco_Car> eco_cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        lux_cars = new ArrayList<>();
        eco_cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void lux_addCar(Lux_Car car) {
        lux_cars.add(car);
    }
    public void eco_addCar(Eco_Car car) {
        eco_cars.add(car);
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void menu()  {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars:");
                System.out.println("<-------Luxury Cars------->:");
                for (Lux_Car car : lux_cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.println("<-------Economy Cars------->:");
                for (Eco_Car car : eco_cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }


                System.out.print("\nEnter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Lux_Car car : lux_cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }
                for (Eco_Car car : eco_cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Lux_Car car : lux_cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                for (Eco_Car car : eco_cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            }

            else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        //<---------------calling for the packages--------->//

        System.out.println("Do you want subscription to get 40% off!!!");
        System.out.println("If yes enter 1 else 0");
        int num = scanner.nextInt();

        if(num == 1){
            System.out.println("Great!!!");
            System.out.println("Enter your name.");
            String name = scanner.next();

//            CustomerWriter newcustomer = new CustomerWriter(name,"SUB-"+name);
            CustomerWriter newcustomer = null;

            try {
                newcustomer = new CustomerWriter(name, "CUS"+name);
            } catch (IOException e) {
                System.out.println("There was some error in logging your name!");

            }
            System.out.println("Great, you are now our member of subscription.");
        }
        System.out.println("\nThank you for using the Car Rental System!");
 }

};
