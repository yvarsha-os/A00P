package abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

public class ToDoListApplication {
    private List<Task> tasks;
    private Scanner scanner;

    public ToDoListApplication() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added.");
    }

    public void updateTask() {
        displayTasks();
        System.out.print("Enter the index of the task to update (0 to " + (tasks.size() - 1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine();
            tasks.get(index).setDescription(newDescription);
            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void removeTask() {
        displayTasks();
        System.out.print("Enter the index of the task to remove (0 to " + (tasks.size() - 1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        ToDoListApplication todoApp = new ToDoListApplication();
        int choice;

        do {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Display Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    todoApp.addTask();
                    break;
                case 2:
                    todoApp.updateTask();
                    break;
                case 3:
                    todoApp.removeTask();
                    break;
                case 4:
                    todoApp.displayTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
