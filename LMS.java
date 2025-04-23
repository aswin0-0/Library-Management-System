import java.util.ArrayList; 
import java.util.Scanner;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void borrow() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public String getAvailability() {
        return isAvailable ? "Yes" : "No";
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nAvailable: " + getAvailability();
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(String id, String title, String author) {
        books.add(new Book(id, title, author));
        System.out.println("Book added: " + title);
    }

    public void removeBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                System.out.println("Book removed: " + books.get(i).getTitle());
                books.remove(i);
                break;
            }
        }
    }

    public Book searchByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return books.get(i);
            }
        }
        return null;
    }

    public void borrowBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id) && books.get(i).getAvailability().equals("Yes")) {
                books.get(i).borrow();
                System.out.println("Borrowed: " + books.get(i).getTitle());
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id) && books.get(i).getAvailability().equals("No")) {
                books.get(i).returnBook();
                System.out.println("Returned: " + books.get(i).getTitle());
                return;
            }
        }
        System.out.println("Book not found or already available.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("Displaying all books in the library:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println("Book " + (i + 1) + ":");
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Available: " + book.getAvailability() + "\n");
        }
    }
}

public class LMS {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Book\n2. Remove Book\n3. Search by Title\n4. Borrow Book\n5. Return Book\n6. Display Books\n7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(id, title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    library.removeBook(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter Title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = library.searchByTitle(searchTitle);
                    if (foundBook != null) {
                        System.out.println(foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to borrow: ");
                    library.borrowBook(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Book ID to return: ");
                    library.returnBook(scanner.nextLine());
                    break;
                case 6:
                    library.displayBooks();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
