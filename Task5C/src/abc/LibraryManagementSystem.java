package abc;

import java.util.ArrayList;
import java.util.List;

// Interface for borrowable items
interface Borrowable {
    boolean borrow();
    boolean returnItem();
}

// Interface for library members
interface Member {
    void borrowItem(Borrowable item);
    void returnItem(Borrowable item);
}

// Book class implementing Borrowable
class Book implements Borrowable {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean borrow() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnItem() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }
}

// LibraryMember class implementing Member
class LibraryMember implements Member {
    private String name;
    private List<Borrowable> borrowedItems;

    public LibraryMember(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void borrowItem(Borrowable item) {
        if (item.borrow()) {
            borrowedItems.add(item);
            System.out.println(name + " borrowed " + ((Book) item).getTitle());
        } else {
            System.out.println("Item is not available for borrowing.");
        }
    }

    @Override
    public void returnItem(Borrowable item) {
        if (item.returnItem()) {
            borrowedItems.remove(item);
            System.out.println(name + " returned " + ((Book) item).getTitle());
        } else {
            System.out.println("Item was not borrowed or already returned.");
        }
    }
}

// Library class to manage books and members
class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public void addMember(LibraryMember member) {
        members.add(member);
        System.out.println("Added member: " + member.getName());
    }

    public void borrowBook(String title, LibraryMember member) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                member.borrowItem(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title, LibraryMember member) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                member.returnItem(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

// Main class to run the application
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create library
        Library library = new Library();

        // Create books
        Book book1 = new Book("1984", "George Orwell");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);

        // Create library members
        LibraryMember member1 = new LibraryMember("Alice");
        LibraryMember member2 = new LibraryMember("Bob");

        // Add members to the library
        library.addMember(member1);
        library.addMember(member2);

        // Borrow and return books
        library.borrowBook("1984", member1);
        library.returnBook("1984", member1);

        library.borrowBook("To Kill a Mockingbird", member2);
        library.returnBook("To Kill a Mockingbird", member2);
    }
}
