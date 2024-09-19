package abc;

   

	import java.util.*;
	import java.util.stream.Collectors;

	class Employee implements Cloneable, Comparable<Employee> {
	    private String name;
	    private int id;
	    private double salary;

	    public Employee(int id, String name, double salary) {
	        this.name = name;
	        this.id = id;
	        this.salary = salary;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getId() {
	        return id;
	    }

	    public double getSalary() {
	        return salary;
	    }

	    // Clone method
	    @Override
	    protected Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }

	    // Comparable method to sort by ID
	    @Override
	    public int compareTo(Employee other) {
	        return Integer.compare(this.id, other.id);
	    }

	    @Override
	    public String toString() {
	        return "Employee{" +
	                "name='" + name + '\'' +
	                ", id=" + id +
	                ", salary=" + salary +
	                '}';
	    }

	    // Comparator to sort by Salary
	    public static Comparator<Employee> salaryComparator = new Comparator<Employee>() {
	        @Override
	        public int compare(Employee e1, Employee e2) {
	            return Double.compare(e1.salary, e2.salary);
	        }
	    };

	    // Comparator to sort by Name
	    public static Comparator<Employee> nameComparator = new Comparator<Employee>() {
	        @Override
	        public int compare(Employee e1, Employee e2) {
	            return e1.name.compareTo(e2.name);
	        }
	    };
	}

	public class EmployeeManagement {
	    public static void main(String[] args) {
	        // Employee Sorting Example
	        List<Employee> employees = new ArrayList<>();
	        employees.add(new Employee(3, "Alice", 70000));
	        employees.add(new Employee(1, "Bob", 50000));
	        employees.add(new Employee(2, "Charlie", 60000));

	        // Sorting by ID using Comparable
	        Collections.sort(employees);
	        System.out.println("Sorted by ID:");
	        for (Employee employee : employees) {
	            System.out.println(employee);
	        }

	        // Sorting by Salary using Comparator
	        Collections.sort(employees, Employee.salaryComparator);
	        System.out.println("\nSorted by Salary:");
	        for (Employee employee : employees) {
	            System.out.println(employee);
	        }

	        // Sorting by Name using Comparator
	        Collections.sort(employees, Employee.nameComparator);
	        System.out.println("\nSorted by Name:");
	        for (Employee employee : employees) {
	            System.out.println(employee);
	        }

	        // Demonstrating cloneability
	        try {
	            Employee clonedEmployee = (Employee) employees.get(0).clone();
	            System.out.println("\nCloned Employee:");
	            System.out.println(clonedEmployee);
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }

	        // Stream Example
	        System.out.println("\n--- Stream Example ---");
	        List<Employee> moreEmployees = Arrays.asList(
	            new Employee(1, "Alice", 70000),
	            new Employee(2, "Bob", 50000),
	            new Employee(3, "Charlie", 60000),
	            new Employee(4, "David", 120000),
	            new Employee(5, "Eve", 80000),
	            new Employee(6, "Frank", 95000),
	            new Employee(7, "Grace", 45000),
	            new Employee(8, "Hannah", 110000),
	            new Employee(9, "Ivy", 75000),
	            new Employee(10, "Jack", 85000)
	        );

	        // 1. Filtering employees with salary greater than 75000
	        System.out.println("Employees with salary > 75000:");
	        List<Employee> highEarners = moreEmployees.stream()
	            .filter(e -> e.getSalary() > 75000)
	            .collect(Collectors.toList());
	        highEarners.forEach(System.out::println);

	        // 2. Sorting employees by salary
	        System.out.println("\nEmployees sorted by salary:");
	        List<Employee> sortedBySalary = moreEmployees.stream()
	            .sorted(Comparator.comparingDouble(Employee::getSalary))
	            .collect(Collectors.toList());
	        sortedBySalary.forEach(System.out::println);

	        // 3. Finding the employee with the highest salary
	        System.out.println("\nEmployee with the highest salary:");
	        moreEmployees.stream()
	            .max(Comparator.comparingDouble(Employee::getSalary))
	            .ifPresent(System.out::println);

	        // Average salary of employees
	        System.out.println("\nAverage salary of employees:");
	        double averageSalary = moreEmployees.stream()
	            .mapToDouble(Employee::getSalary)
	            .average()
	            .orElse(0.0);
	        System.out.println("Average Salary: " + averageSalary);
	    }
	}
