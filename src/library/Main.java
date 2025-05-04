package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VirtualLibrary library = new VirtualLibrary();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Virtual Library ---");
            System.out.println("1. Add book");
            System.out.println("2. List books");
            System.out.println("3. Remove book");
            System.out.println("4. View book");
            System.out.println("5. Show view history");
            System.out.println("6. Add to waitlist");
            System.out.println("7. Show waitlist");
            System.out.println("8. Serve next in waitlist");
            System.out.println("9. Connect books (graph edge)");
            System.out.println("10. Recommend books (Dijkstra)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    library.addBook(new Book(title, author, year));
                    break;

                case 2:
                    library.listBooks();
                    break;

                case 3:
                    System.out.print("Enter the title to remove: ");
                    String titleToRemove = scanner.nextLine();
                    if (library.removeBookByTitle(titleToRemove)) {
                        System.out.println("Book removed.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the title to view: ");
                    String viewTitle = scanner.nextLine();
                    Book book = library.viewBook(viewTitle);
                    if (book != null) {
                        System.out.println("Viewing: " + book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 5:
                    library.showViewHistory();
                    break;

                case 6:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    library.addToWaitList(name);
                    break;

                case 7:
                    library.showWaitList();
                    break;

                case 8:
                    library.serveNextInLine();
                    break;

                case 9:
                    System.out.print("First book title: ");
                    String t1 = scanner.nextLine();
                    System.out.print("Second book title: ");
                    String t2 = scanner.nextLine();
                    Book b1 = library.findBookByTitle(t1);
                    Book b2 = library.findBookByTitle(t2);
                    if (b1 != null && b2 != null) {
                        library.addRelation(b1, b2);
                        System.out.println("Books connected.");
                    } else {
                        System.out.println("One or both books not found.");
                    }
                    break;

                case 10:
                    System.out.print("Enter the title of the book to get recommendations: ");
                    String originTitle = scanner.nextLine();
                    Book origin = library.findBookByTitle(originTitle);
                    if (origin != null) {
                        library.recommendBooks(origin);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);

        scanner.close();
    }
}
