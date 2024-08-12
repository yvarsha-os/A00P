package abc;
//SRP: Single Responsibility Principle
class Employee {
 private String name;
 private String role;

 public Employee(String name, String role) {
     this.name = name;
     this.role = role;
 }

 public String getName() {
     return name;
 }

 public String getRole() {
     return role;
 }
}

class SalaryCalculator {
 public double calculateSalary(Employee employee) {
     switch (employee.getRole()) {
         case "Developer":
             return 60000;
         case "Manager":
             return 80000;
         default:
             return 50000;
     }
 }
}

//OCP: Open/Closed Principle
abstract class Shape {
 abstract double calculateArea();
}

class Rectangle extends Shape {
 private double length;
 private double width;

 public Rectangle(double length, double width) {
     this.length = length;
     this.width = width;
 }

 @Override
 double calculateArea() {
     return length * width;
 }
}

class Circle extends Shape {
 private double radius;

 public Circle(double radius) {
     this.radius = radius;
 }

 @Override
 double calculateArea() {
     return Math.PI * radius * radius;
 }
}

//LSP: Liskov Substitution Principle
class Bird {
 public void fly() {
     System.out.println("Flying...");
 }
}

class Ostrich extends Bird {
 @Override
 public void fly() {
     throw new UnsupportedOperationException("Ostriches can't fly!");
 }
}

//ISP: Interface Segregation Principle
interface Worker {
 void work();
}

interface Eater {
 void eat();
}

class Robot implements Worker {
 @Override
 public void work() {
     System.out.println("Robot is working...");
 }
}

class Human implements Worker, Eater {
 @Override
 public void work() {
     System.out.println("Human is working...");
 }

 @Override
 public void eat() {
     System.out.println("Human is eating...");
 }
}

//DIP: Dependency Inversion Principle
interface MessageService {
 void sendMessage(String message, String recipient);
}

class EmailService implements MessageService {
 @Override
 public void sendMessage(String message, String recipient) {
     System.out.println("Email sent to " + recipient + ": " + message);
 }
}

class SMSService implements MessageService {
 @Override
 public void sendMessage(String message, String recipient) {
     System.out.println("SMS sent to " + recipient + ": " + message);
 }
}

class MyApplication {
 private MessageService messageService;

 public MyApplication(MessageService messageService) {
     this.messageService = messageService;
 }

 public void processMessages(String message, String recipient) {
     messageService.sendMessage(message, recipient);
 }
}

//Client demonstrating all SOLID principles
public class SOLIDPrinciplesDemo {
 public static void main(String[] args) {
     // SRP: Single Responsibility Principle
     Employee emp1 = new Employee("John Doe", "Developer");
     Employee emp2 = new Employee("Jane Smith", "Manager");

     SalaryCalculator salaryCalculator = new SalaryCalculator();
     System.out.println(emp1.getName() + "'s Salary: " + salaryCalculator.calculateSalary(emp1));
     System.out.println(emp2.getName() + "'s Salary: " + salaryCalculator.calculateSalary(emp2));

     // OCP: Open/Closed Principle
     Shape rectangle = new Rectangle(5, 10);
     Shape circle = new Circle(7);
     System.out.println("Rectangle Area: " + rectangle.calculateArea());
     System.out.println("Circle Area: " + circle.calculateArea());

     // LSP: Liskov Substitution Principle
     Bird bird = new Bird();
     bird.fly();  // Works fine

     Bird ostrich = new Ostrich();
     try {
         ostrich.fly();  // Throws an exception, violating LSP
     } catch (UnsupportedOperationException e) {
         System.out.println(e.getMessage());
     }

     // ISP: Interface Segregation Principle
     Worker robot = new Robot();
     robot.work();

     Human human = new Human();
     human.work();
     human.eat();

     // DIP: Dependency Inversion Principle
     MessageService emailService = new EmailService();
     MyApplication app = new MyApplication(emailService);
     app.processMessages("Hello via Email", "john@example.com");

     MessageService smsService = new SMSService();
     app = new MyApplication(smsService);
     app.processMessages("Hello via SMS", "123-456-7890");
 }
}
