package abc;
class Animal { 
 public void makeSound() { 
  System.out.println("animal Makes the Sound"); 
 } 
} 
class Dog extends Animal{ 
    public void makeSound() { 
     System.out.println("Bow!Bow!"); 
    } 
} 
 
class Cat extends Animal{ 
 public void makeSound() { 
  System.out.println("meow!meow!"); 
 } 
  
} 
public class main2 { 
  public static void main(String args[]) { 
 Animal animal=new Animal(); 
 Dog dog=new Dog(); 
 Cat cat=new Cat(); 
  
 animal.makeSound(); 
 dog.makeSound(); 
 cat.makeSound(); 
    } 
}