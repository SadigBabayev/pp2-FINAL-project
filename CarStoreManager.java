import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarStoreManager {  

  private List<Car> cars;
    private int lastId; 

    public CarStoreManager() {
        this.cars = new ArrayList<>();
        this.lastId = 0;
    }

    
    private int getNextId() {
        return ++lastId;
    }


    public void addCar(String make, String model, int year, double price, int mileage,
                       String color, String transmission, String fuelType, Seller seller) {
        int newId = getNextId();
        Car newCar = new Car(newId, make, model, year, price, mileage, color, transmission, fuelType, seller);
        cars.add(newCar);
    }

   
    public void addCar(Car car) {
       
        if (car.getId() > lastId) {
            lastId = car.getId();
        }
        cars.add(car);
    }
 
    public List<Car> listCars() {
        return cars;
    }

    // Find a car by ID 
    public Car findCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null; 
    }

    // Delete car by ID
    public boolean deleteCarById(int id) {
        Car car = findCarById(id);
        if (car != null) {
            cars.remove(car);
            return true;
        }
        return false;
    }

    public boolean updateCar(int id, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == id) {
                cars.set(i, updatedCar);
                return true;
            }
        }
        return false;
    }

 
    public void sortByPrice() {
        cars.sort(Comparator.comparingDouble(Car::getPrice));
    }

   
    public int getTotalCars() {
        return cars.size();
    }


   
public List<Car> searchByAttribute(String attribute, String value) {
    value = value.toLowerCase(); 
    List<Car> result = new ArrayList<>();

    for (Car car : cars) {
        switch (attribute) {
            case "id":
                if (Integer.toString(car.getId()).equals(value)) result.add(car);
                break;
            case "make":
                if (car.getMake().toLowerCase().contains(value)) result.add(car);
                break;
            case "model":
                if (car.getModel().toLowerCase().contains(value)) result.add(car);
                break;
            case "year":
                if (Integer.toString(car.getYear()).equals(value)) result.add(car);
                break;
            case "price":
                if (Double.toString(car.getPrice()).equals(value)) result.add(car);
                break;
            case "mileage":
                if (Integer.toString(car.getMileage()).equals(value)) result.add(car);
                break;
            case "color":
                if (car.getColor().toLowerCase().contains(value)) result.add(car);
                break;
            case "transmission":
                if (car.getTransmission().toLowerCase().contains(value)) result.add(car);
                break;
            case "fueltype":
                if (car.getFuelType().toLowerCase().contains(value)) result.add(car);
                break;
            case "sellername":
                if (car.getSeller().getName().toLowerCase().contains(value)) result.add(car);
                break;
            case "sellerphone":
                if (car.getSeller().getPhone().toLowerCase().contains(value)) result.add(car);
                break;
            case "selleremail":
                if (car.getSeller().getEmail().toLowerCase().contains(value)) result.add(car);
                break;
        }
    }

    return result;
}

public void sortByAttribute(String attribute) {
    switch(attribute.toLowerCase()) {
        case "id":
            cars.sort(Comparator.comparingInt(Car::getId));
            break;
        case "make":
            cars.sort(Comparator.comparing(Car::getMake, String.CASE_INSENSITIVE_ORDER));
            break;
        case "model":
            cars.sort(Comparator.comparing(Car::getModel, String.CASE_INSENSITIVE_ORDER));
            break;
        case "year":
            cars.sort(Comparator.comparingInt(Car::getYear));
            break;
        case "price":
            cars.sort(Comparator.comparingDouble(Car::getPrice));
            break;
        case "mileage":
            cars.sort(Comparator.comparingInt(Car::getMileage));
            break;
        case "color":
            cars.sort(Comparator.comparing(Car::getColor, String.CASE_INSENSITIVE_ORDER));
            break;
        case "transmission":
            cars.sort(Comparator.comparing(Car::getTransmission, String.CASE_INSENSITIVE_ORDER));
            break;
        case "fueltype":
            cars.sort(Comparator.comparing(Car::getFuelType, String.CASE_INSENSITIVE_ORDER));
            break;
        case "sellername":
            cars.sort(Comparator.comparing(car -> car.getSeller().getName(), String.CASE_INSENSITIVE_ORDER));
            break;
        case "sellerphone":
            cars.sort(Comparator.comparing(car -> car.getSeller().getPhone(), String.CASE_INSENSITIVE_ORDER));
            break;
        case "selleremail":
            cars.sort(Comparator.comparing(car -> car.getSeller().getEmail(), String.CASE_INSENSITIVE_ORDER));
            break;
        default:
            System.out.println("Unknown attribute for sorting: " + attribute);
    }
}
public List<Car> filterCars(List<FilterCondition> conditions) {
    List<Car> results = new ArrayList<>();
    for (Car car : cars) {
        boolean matchesAll = true;
        for (FilterCondition cond : conditions) {
            if (!cond.matches(car)) {
                matchesAll = false;
                break;
            }
        }
        if (matchesAll) {
            results.add(car);
        }
    }
    return results;
}

}

