package abc;

  

	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.Scanner;

	class Ticket {
	    private String description;

	    public Ticket(String description) {
	        this.description = description;
	    }

	    public String getDescription() {
	        return description;
	    }

	    @Override
	    public String toString() {
	        return description;
	    }
	}

	public class CustomerSupportTicketSystem {
	    private Queue<Ticket> ticketQueue;
	    private Scanner scanner;

	    public CustomerSupportTicketSystem() {
	        ticketQueue = new LinkedList<>();
	        scanner = new Scanner(System.in);
	    }

	    public void addTicket() {
	        System.out.print("Enter ticket description: ");
	        String description = scanner.nextLine();
	        ticketQueue.add(new Ticket(description));
	        System.out.println("Ticket added.");
	    }

	    public void processTicket() {
	        if (ticketQueue.isEmpty()) {
	            System.out.println("No tickets to process.");
	            return;
	        }
	        Ticket ticket = ticketQueue.poll(); // Remove and return the head of the queue
	        System.out.println("Processed ticket: " + ticket);
	    }

	    public void displayTickets() {
	        if (ticketQueue.isEmpty()) {
	            System.out.println("No pending tickets.");
	            return;
	        }
	        System.out.println("Pending Tickets:");
	        for (Ticket ticket : ticketQueue) {
	            System.out.println("- " + ticket);
	        }
	    }

	    public static void main(String[] args) {
	        CustomerSupportTicketSystem ticketSystem = new CustomerSupportTicketSystem();
	        int choice;

	        do {
	            System.out.println("\nCustomer Support Ticket System");
	            System.out.println("1. Add Ticket");
	            System.out.println("2. Process Ticket");
	            System.out.println("3. Display Pending Tickets");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            Scanner scanner = new Scanner(System.in);
	            choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    ticketSystem.addTicket();
	                    break;
	                case 2:
	                    ticketSystem.processTicket();
	                    break;
	                case 3:
	                    ticketSystem.displayTickets();
	                    break;
	                case 4:
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 4);
	    }
	}
