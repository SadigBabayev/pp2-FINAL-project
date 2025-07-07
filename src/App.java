import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class App {

    // ANSI color codes helper
    public static class AnsiColors {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";

        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }

    // Helper input methods with exceptions
    private static int readInt(Scanner scanner, String prompt) throws InvalidNumberInputException {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException("Invalid integer input: '" + input + "'");
        }
    }

    private static double readDouble(Scanner scanner, String prompt) throws InvalidNumberInputException {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException("Invalid double input: '" + input + "'");
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) throws EmptyFieldException {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new EmptyFieldException("Input cannot be empty.");
        }
        return input;
    }

    private static void editCar(Scanner scanner, CarStoreManager manager, int carId) {
        Car car = manager.findCarById(carId);
        if (car == null) {
            System.out.println(AnsiColors.RED + "Car not found." + AnsiColors.RESET);
            return;
        }

        try {
            System.out.println(AnsiColors.BOLD + "Editing car (leave blank to keep current value):" + AnsiColors.RESET);

            System.out.print("Make (" + car.getMake() + "): ");
            String make = scanner.nextLine();
            if (!make.isEmpty()) car.setMake(make);

            System.out.print("Model (" + car.getModel() + "): ");
            String model = scanner.nextLine();
            if (!model.isEmpty()) car.setModel(model);

            System.out.print("Year (" + car.getYear() + "): ");
            String yearStr = scanner.nextLine();
            if (!yearStr.isEmpty()) car.setYear(Integer.parseInt(yearStr));

            System.out.print("Price (" + car.getPrice() + "): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) car.setPrice(Double.parseDouble(priceStr));

            System.out.print("Mileage (" + car.getMileage() + "): ");
            String mileageStr = scanner.nextLine();
            if (!mileageStr.isEmpty()) car.setMileage(Integer.parseInt(mileageStr));

            System.out.print("Color (" + car.getColor() + "): ");
            String color = scanner.nextLine();
            if (!color.isEmpty()) car.setColor(color);

            System.out.print("Transmission (" + car.getTransmission() + "): ");
            String transmission = scanner.nextLine();
            if (!transmission.isEmpty()) car.setTransmission(transmission);

            System.out.print("Fuel Type (" + car.getFuelType() + "): ");
            String fuelType = scanner.nextLine();
            if (!fuelType.isEmpty()) car.setFuelType(fuelType);

            Seller seller = car.getSeller();

            System.out.print("Seller Name (" + seller.getName() + "): ");
            String sellerName = scanner.nextLine();
            if (!sellerName.isEmpty()) seller.setName(sellerName);

            System.out.print("Seller Phone (" + seller.getPhone() + "): ");
            String sellerPhone = scanner.nextLine();
            if (!sellerPhone.isEmpty()) seller.setPhone(sellerPhone);

            System.out.print("Seller Email (" + seller.getEmail() + "): ");
            String sellerEmail = scanner.nextLine();
            if (!sellerEmail.isEmpty()) seller.setEmail(sellerEmail);

            System.out.println(AnsiColors.GREEN + "Car updated successfully." + AnsiColors.RESET);

        } catch (NumberFormatException e) {
            System.out.println(AnsiColors.RED + "Invalid number input. Update aborted." + AnsiColors.RESET);
        }
    }

    private static void printCarDetails(Car car) {
                System.out.println(AnsiColors.BOLD + AnsiColors.PURPLE + "Id: " + AnsiColors.RESET + car.getId());
        System.out.println(AnsiColors.BOLD + AnsiColors.PURPLE + "Make: " + AnsiColors.RESET + car.getMake());
        System.out.println(AnsiColors.BOLD + AnsiColors.PURPLE + "Model: " + AnsiColors.RESET + car.getModel());
        System.out.println(AnsiColors.BOLD + AnsiColors.BLUE + "Year: " + AnsiColors.RESET + car.getYear());
        System.out.println(AnsiColors.BOLD + AnsiColors.GREEN + "Price: $" + AnsiColors.RESET + car.getPrice());
        System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Mileage: " + AnsiColors.RESET + car.getMileage() + " km");
        System.out.println(AnsiColors.BOLD + AnsiColors.YELLOW + "Color: " + AnsiColors.RESET + car.getColor());
        System.out.println(AnsiColors.BOLD + AnsiColors.PURPLE + "Transmission: " + AnsiColors.RESET + car.getTransmission());
        System.out.println(AnsiColors.BOLD + AnsiColors.PURPLE + "Fuel Type: " + AnsiColors.RESET + car.getFuelType());

        Seller seller = car.getSeller();
        System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Seller: " + AnsiColors.RESET + seller.getName());
        System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Phone: " + AnsiColors.RESET + seller.getPhone());
        System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Email: " + AnsiColors.RESET + seller.getEmail());

        System.out.println(AnsiColors.BLUE + "-----------------------------------" + AnsiColors.RESET);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarStoreManager manager = new CarStoreManager();

        // Sample Sellers
        Seller seller1 = new Seller("John Doe", "0501234567", "john@example.com");
        Seller seller2 = new Seller("Alice Smith", "0517654321", "alice@example.com");

        // Sample Cars
        manager.addCar(new Car(1, "Toyota", "Corolla", 2018, 12000, 75000, "White", "Automatic", "Gasoline", seller1));
        manager.addCar(new Car(2, "Honda", "Civic", 2017, 11000, 82000, "Black", "Manual", "Gasoline", seller2));
        manager.addCar(new Car(3, "Ford", "Focus", 2019, 13000, 60000, "Blue", "Automatic", "Diesel", seller1));

        boolean running = true;
        while (running) {
            System.out.println("\n" + AnsiColors.BOLD + AnsiColors.CYAN + "=== Car Store Management ===" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "1. List all cars" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "2. Search car by ID" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "3. Add new car" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "4. Sort cars by properties" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "5. Search cars by properties" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "6. Filter" + AnsiColors.RESET);
            System.out.println(AnsiColors.RED + "7. Exit" + AnsiColors.RESET);
            System.out.print(AnsiColors.GREEN + "Choose an option: " + AnsiColors.RESET);

            int choice = -1;
            try {
                choice = readInt(scanner, "");
            } catch (InvalidNumberInputException e) {
                System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(AnsiColors.BOLD + "Total cars: " + manager.getTotalCars() + AnsiColors.RESET);
                    for (Car car : manager.listCars()) {
                        printCarDetails(car);
                    }
                    break;

                case 2:
                    try {
                        int searchId = readInt(scanner, AnsiColors.GREEN + "Enter car ID to search: " + AnsiColors.RESET);
                        Car foundCar = manager.findCarById(searchId);

                        if (foundCar != null) {
                            System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Found car:" + AnsiColors.RESET);
                            printCarDetails(foundCar);

                            System.out.print(AnsiColors.YELLOW + "Do you want to edit or delete this car? (edit/delete/no): " + AnsiColors.RESET);
                            String action = scanner.nextLine().trim().toLowerCase();

                            switch (action) {
                                case "edit":
                                    editCar(scanner, manager, searchId);
                                    break;
                                case "delete":
                                    boolean deleted = manager.deleteCarById(searchId);
                                    if (deleted) {
                                        System.out.println(AnsiColors.GREEN + "Car deleted successfully." + AnsiColors.RESET);
                                    } else {
                                        System.out.println(AnsiColors.RED + "Failed to delete the car." + AnsiColors.RESET);
                                    }
                                    break;
                                case "no":
                                    System.out.println("No changes made.");
                                    break;
                                default:
                                    System.out.println(AnsiColors.RED + "Invalid option. No changes made." + AnsiColors.RESET);
                            }
                        } else {
                            System.out.println(AnsiColors.RED + "Car with ID " + searchId + " not found." + AnsiColors.RESET);
                        }
                    } catch (InvalidNumberInputException e) {
                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                    }
                    break;

                case 3:
                    try {
                        String make = readNonEmptyString(scanner, AnsiColors.GREEN + "Make: " + AnsiColors.RESET);
                        String model = readNonEmptyString(scanner, AnsiColors.GREEN + "Model: " + AnsiColors.RESET);
                        int year = readInt(scanner, AnsiColors.GREEN + "Year: " + AnsiColors.RESET);
                        double price = readDouble(scanner, AnsiColors.GREEN + "Price: " + AnsiColors.RESET);
                        int mileage = readInt(scanner, AnsiColors.GREEN + "Mileage: " + AnsiColors.RESET);
                        String color = readNonEmptyString(scanner, AnsiColors.GREEN + "Color: " + AnsiColors.RESET);
                        String transmission = readNonEmptyString(scanner, AnsiColors.GREEN + "Transmission: " + AnsiColors.RESET);
                        String fuelType = readNonEmptyString(scanner, AnsiColors.GREEN + "Fuel Type: " + AnsiColors.RESET);
                        String sellerName = readNonEmptyString(scanner, AnsiColors.GREEN + "Seller Name: " + AnsiColors.RESET);
                        String sellerPhone = readNonEmptyString(scanner, AnsiColors.GREEN + "Seller Phone: " + AnsiColors.RESET);
                        String sellerEmail = readNonEmptyString(scanner, AnsiColors.GREEN + "Seller Email: " + AnsiColors.RESET);

                        Seller seller = new Seller(sellerName, sellerPhone, sellerEmail);
                        manager.addCar(make, model, year, price, mileage, color, transmission, fuelType, seller);

                        System.out.println(AnsiColors.GREEN + "Car added successfully!" + AnsiColors.RESET);
                    } catch (InvalidNumberInputException | EmptyFieldException e) {
                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                    }
                    break;

                case 4:
                    System.out.println(AnsiColors.CYAN + "Sort cars by:" + AnsiColors.RESET);
                    System.out.println("1. ID");
                    System.out.println("2. Make");
                    System.out.println("3. Model");
                    System.out.println("4. Year");
                    System.out.println("5. Price");
                    System.out.println("6. Mileage");
                    System.out.println("7. Color");
                    System.out.println("8. Transmission");
                    System.out.println("9. Fuel Type");
                    System.out.println("10. Seller Name");
                    System.out.println("11. Seller Phone");
                    System.out.println("12. Seller Email");
                    System.out.print(AnsiColors.GREEN + "Choose attribute number to sort by: " + AnsiColors.RESET);

                    try {
                        int sortChoice = readInt(scanner, "");
                        String sortAttribute = "";

                        switch (sortChoice) {
                            case 1:
                                sortAttribute = "id";
                                break;
                            case 2:
                                sortAttribute = "make";
                                break;
                            case 3:
                                sortAttribute = "model";
                                break;
                            case 4:
                                sortAttribute = "year";
                                break;
                            case 5:
                                sortAttribute = "price";
                                break;
                            case 6:
                                sortAttribute = "mileage";
                                break;
                            case 7:
                                sortAttribute = "color";
                                break;
                            case 8:
                                sortAttribute = "transmission";
                                break;
                            case 9:
                                sortAttribute = "fueltype";
                                break;
                            case 10:
                                sortAttribute = "sellername";
                                break;
                            case 11:
                                sortAttribute = "sellerphone";
                                break;
                            case 12:
                                sortAttribute = "selleremail";
                                break;
                            default:
                                System.out.println(AnsiColors.RED + "Invalid choice." + AnsiColors.RESET);
                                break;
                        }

                        if (!sortAttribute.isEmpty()) {
                            manager.sortByAttribute(sortAttribute);
                            System.out.println(AnsiColors.GREEN + "Cars sorted by " + sortAttribute + "." + AnsiColors.RESET);
                        }
                    } catch (InvalidNumberInputException e) {
                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                    }
                    break;

                case 5:
                    System.out.println(AnsiColors.CYAN + "Search by:" + AnsiColors.RESET);
                    System.out.println("1. ID");
                    System.out.println("2. Make");
                    System.out.println("3. Model");
                    System.out.println("4. Year");
                    System.out.println("5. Price");
                    System.out.println("6. Mileage");
                    System.out.println("7. Color");
                    System.out.println("8. Transmission");
                    System.out.println("9. Fuel Type");
                    System.out.println("10. Seller Name");
                    System.out.println("11. Seller Phone");
                    System.out.println("12. Seller Email");
                    System.out.print(AnsiColors.GREEN + "Choose attribute number: " + AnsiColors.RESET);

                    try {
                        int attrChoice = readInt(scanner, "");
                        String attribute = "";

                        switch (attrChoice) {
                            case 1:
                                attribute = "id";
                                break;
                            case 2:
                                attribute = "make";
                                break;
                            case 3:
                                attribute = "model";
                                break;
                            case 4:
                                attribute = "year";
                                break;
                            case 5:
                                attribute = "price";
                                break;
                            case 6:
                                attribute = "mileage";
                                break;
                            case 7:
                                attribute = "color";
                                break;
                            case 8:
                                attribute = "transmission";
                                break;
                            case 9:
                                attribute = "fueltype";
                                break;
                            case 10:
                                attribute = "sellername";
                                break;
                            case 11:
                                attribute = "sellerphone";
                                break;
                            case 12:
                                attribute = "selleremail";
                                break;
                            default:
                                System.out.println(AnsiColors.RED + "Invalid choice." + AnsiColors.RESET);
                                attribute = "";
                        }

                        if (attribute.isEmpty()) break;

                        System.out.print(AnsiColors.GREEN + "Enter value to search for: " + AnsiColors.RESET);
                        String searchValue = scanner.nextLine().trim().toLowerCase();

                        List<Car> foundCars = manager.searchByAttribute(attribute, searchValue);

                        if (foundCars.isEmpty()) {
                            System.out.println(AnsiColors.RED + "No cars found matching " + attribute + " = " + searchValue + AnsiColors.RESET);
                        } else {
                            System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Found " + foundCars.size() + " car(s):" + AnsiColors.RESET);

                            for (Car car : foundCars) {
                                printCarDetails(car);
                            }

                            System.out.print(AnsiColors.YELLOW + "Do you want to edit or delete any of these cars? (edit/delete/no): " + AnsiColors.RESET);
                            String action = scanner.nextLine().trim().toLowerCase();

                            switch (action) {
                                case "edit":
                                    try {
                                        int editId = readInt(scanner, AnsiColors.GREEN + "Enter the ID of the car to edit: " + AnsiColors.RESET);
                                        editCar(scanner, manager, editId);
                                    } catch (InvalidNumberInputException e) {
                                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                                    }
                                    break;
                                case "delete":
                                    try {
                                        int deleteId = readInt(scanner, AnsiColors.GREEN + "Enter the ID of the car to delete: " + AnsiColors.RESET);
                                        boolean deleted = manager.deleteCarById(deleteId);
                                        if (deleted) {
                                            System.out.println(AnsiColors.GREEN + "Car deleted successfully." + AnsiColors.RESET);
                                        } else {
                                            System.out.println(AnsiColors.RED + "Car with ID " + deleteId + " not found." + AnsiColors.RESET);
                                        }
                                    } catch (InvalidNumberInputException e) {
                                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                                    }
                                    break;
                                case "no":
                                    System.out.println("No changes made.");
                                    break;
                                default:
                                    System.out.println(AnsiColors.RED + "Invalid option. No changes made." + AnsiColors.RESET);
                            }
                        }

                    } catch (InvalidNumberInputException e) {
                        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
                    }
                    break;

          case 6:
    try {
        System.out.print(AnsiColors.GREEN + "How many attributes do you want to filter by? " + AnsiColors.RESET);
        int n = readInt(scanner, "");

        List<FilterCondition> filters = new ArrayList<>();

        List<String> stringAttributes = Arrays.asList(
            "make", "model", "color", "transmission", "fueltype",
            "sellername", "sellerphone", "selleremail"
        );
        List<String> numericAttributes = Arrays.asList(
            "id", "year", "price", "mileage"
        );

        for (int i = 0; i < n; i++) {
            String attribute = "";
            while (true) {
                System.out.println(AnsiColors.CYAN + "\nAttribute #" + (i + 1) + ":" + AnsiColors.RESET);
                System.out.println("1. ID\n2. Make\n3. Model\n4. Year\n5. Price\n6. Mileage\n7. Color\n8. Transmission\n9. Fuel Type\n10. Seller Name\n11. Seller Phone\n12. Seller Email");
                System.out.print(AnsiColors.GREEN + "Choose attribute number to filter by: " + AnsiColors.RESET);

                int attrChoice2;
                try {
                    attrChoice2 = readInt(scanner, "");
                } catch (InvalidNumberInputException e) {
                    System.out.println(AnsiColors.RED + "Invalid number. Please enter a valid attribute number." + AnsiColors.RESET);
                    continue;
                }

                switch (attrChoice2) {
                    case 1: attribute = "id"; break;
                    case 2: attribute = "make"; break;
                    case 3: attribute = "model"; break;
                    case 4: attribute = "year"; break;
                    case 5: attribute = "price"; break;
                    case 6: attribute = "mileage"; break;
                    case 7: attribute = "color"; break;
                    case 8: attribute = "transmission"; break;
                    case 9: attribute = "fueltype"; break;
                    case 10: attribute = "sellername"; break;
                    case 11: attribute = "sellerphone"; break;
                    case 12: attribute = "selleremail"; break;
                    default:
                        System.out.println(AnsiColors.RED + "Invalid attribute selected. Please try again." + AnsiColors.RESET);
                        continue;
                }
                break; 
            }

            List<String> validOps;
            if (numericAttributes.contains(attribute)) {
                validOps = Arrays.asList("equals", "greater", "less");
            } else {
                validOps = Arrays.asList("equals", "contains");
            }

            String operator = "";
            while (true) {
                System.out.println("Select operator:");
                for (int opIndex = 0; opIndex < validOps.size(); opIndex++) {
                    System.out.println((opIndex + 1) + ". " + validOps.get(opIndex));
                }
                System.out.print("Enter operator number: ");
                String opInput = scanner.nextLine().trim();

                int opNum;
                try {
                    opNum = Integer.parseInt(opInput);
                } catch (NumberFormatException e) {
                    System.out.println(AnsiColors.RED + "Invalid number. Please enter a valid operator number." + AnsiColors.RESET);
                    continue;
                }

                if (opNum < 1 || opNum > validOps.size()) {
                    System.out.println(AnsiColors.RED + "Invalid operator number. Please try again." + AnsiColors.RESET);
                    continue;
                }

                operator = validOps.get(opNum - 1);
                break;
            }

            String value = "";
            while (true) {
                System.out.print("Enter value to compare: ");
                value = scanner.nextLine().trim();
                if (value.isEmpty()) {
                    System.out.println(AnsiColors.RED + "Value cannot be empty. Please enter a valid value." + AnsiColors.RESET);
                    continue;
                }
                if (numericAttributes.contains(attribute)) {
                    try {
                        if (attribute.equals("price")) {
                            Double.parseDouble(value);
                        } else {
                            Integer.parseInt(value);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(AnsiColors.RED + "Invalid number format for attribute '" + attribute + "'. Please enter a valid number." + AnsiColors.RESET);
                        continue;
                    }
                }
                break;
            }

            filters.add(new FilterCondition(attribute, operator, value));
        }

        List<Car> filteredCars = manager.filterCars(filters);

        if (filteredCars.isEmpty()) {
            System.out.println(AnsiColors.RED + "No cars matched the filter criteria." + AnsiColors.RESET);
        } else {
            System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "Filtered cars:" + AnsiColors.RESET);
            for (Car c : filteredCars) {
                printCarDetails(c);
            }
        }
    } catch (InvalidNumberInputException e) {
        System.out.println(AnsiColors.RED + e.getMessage() + AnsiColors.RESET);
    }
    break;
    

                case 7:
                    running = false;
                    System.out.println(AnsiColors.GREEN + "Exiting application. Goodbye!" + AnsiColors.RESET);
                    break;

                default:
                    System.out.println(AnsiColors.RED + "Invalid choice. Try again." + AnsiColors.RESET);
            }
        }

        scanner.close();
    }
}
