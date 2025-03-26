package library;

import java.util.LinkedList;

public class VirtualLibrary {
    private LinkedList<Book> books;

    public VirtualLibrary() {
        books = new LinkedList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is currently empty.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {
        VirtualLibrary library = new VirtualLibrary();

        // Adding books
        Book book1 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        Book book2 = new Book("1984", "George Orwell", 1949);

        library.addBook(book1);
        library.addBook(book2);

        // Listing books
        System.out.println("\nBooks in the library:");
        library.listBooks();
    }
}
