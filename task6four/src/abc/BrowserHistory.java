package abc;


	import java.util.ArrayDeque;
	import java.util.Deque;
	import java.util.Scanner;

	public class BrowserHistory {
	    private Deque<String> history;      // To keep track of the browsing history
	    private Deque<String> forwardStack; // To keep track of pages for forward navigation
	    private String currentPage;         // To track the current page

	    public BrowserHistory() {
	        history = new ArrayDeque<>();
	        forwardStack = new ArrayDeque<>();
	        currentPage = null;
	    }

	    // Method to add a new page to the history
	    public void visitPage(String pageUrl) {
	        if (currentPage != null) {
	            history.addLast(currentPage); // Save the current page in history
	            forwardStack.clear();         // Clear forward history on a new visit
	        }
	        currentPage = pageUrl;
	        System.out.println("Visited page: " + currentPage);
	    }

	    // Method to go back to the previous page
	    public void goBack() {
	        if (history.isEmpty()) {
	            System.out.println("No previous page to go back to.");
	        } else {
	            forwardStack.addFirst(currentPage); // Save the current page in forward stack
	            currentPage = history.removeLast(); // Go back to the last page
	            System.out.println("Went back to page: " + currentPage);
	        }
	    }

	    // Method to go forward to the next page
	    public void goForward() {
	        if (forwardStack.isEmpty()) {
	            System.out.println("No next page to go forward to.");
	        } else {
	            history.addLast(currentPage); // Save the current page in history
	            currentPage = forwardStack.removeFirst(); // Go forward to the next page
	            System.out.println("Went forward to page: " + currentPage);
	        }
	    }

	    // Method to display the current page
	    public void displayCurrentPage() {
	        if (currentPage == null) {
	            System.out.println("No page is currently open.");
	        } else {
	            System.out.println("Current page: " + currentPage);
	        }
	    }

	    // Main method to run the browser history system
	    public static void main(String[] args) {
	        BrowserHistory browserHistory = new BrowserHistory();
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\nBrowser History System:");
	            System.out.println("1. Visit a new page");
	            System.out.println("2. Go back to the previous page");
	            System.out.println("3. Go forward to the next page");
	            System.out.println("4. Display the current page");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine(); // consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter the URL of the page to visit: ");
	                    String pageUrl = scanner.nextLine();
	                    browserHistory.visitPage(pageUrl);
	                    break;
	                case 2:
	                    browserHistory.goBack();
	                    break;
	                case 3:
	                    browserHistory.goForward();
	                    break;
	                case 4:
	                    browserHistory.displayCurrentPage();
	                    break;
	                case 5:
	                    System.out.println("Exiting the system.");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 5);

	        scanner.close();
	    }
	}
