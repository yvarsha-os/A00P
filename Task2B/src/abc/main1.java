package abc;

class Vehicle { 
    private String brand; 
    private int year; 
 
    public Vehicle(String brand, int year) { 
        this.brand = brand; 
        this.year = year; 
    } 
 
    public void startEngine() { 
        System.out.println("Engine started for " + brand); 
    } 
 
    public void stopEngine() { 
        System.out.println("Engine stopped for " + brand); 
    } 
 
    
    public String getBrand() { 
        return brand; 
    } 
 
    public int getYear() { 
        return year; 
    } 
} 
 
class Car extends Vehicle { 
     
    private String model; 
 
    public Car(String brand, int year, String model) { 
        super(brand, year); 
        this.model = model; 
    } 
 
    public void honk() { 
        System.out.println("Honk! Honk! (Car model: " + model + ")"); 
    } 
 
    public String getModel() { 
        return model; 
    } 
} 
 
public class main1  { 
    public static void main(String[] args) { 
        Vehicle vehicle = new Vehicle("Generic", 2020); 
        System.out.println("Vehicle Brand: " + vehicle.getBrand()); 
        System.out.println("Vehicle Year: " + vehicle.getYear()); 
        vehicle.startEngine(); 
        vehicle.stopEngine(); 
        System.out.println(); 
 
         
        Car car = new Car("Toyota", 2022, "Camry"); 
        System.out.println("Car brand: " + car.getBrand()); 
        System.out.println("Car year: " + car.getYear()); 
        System.out.println("Car model: " + car.getModel()); 
        car.startEngine(); 
        car.honk(); 
        car.stopEngine(); 
    } 
}  