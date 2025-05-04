package library;

import java.util.*;

public class VirtualLibrary {
    private LinkedList<Book> books;
    private Queue<String> waitList;
    private Stack<Book> viewHistory;
    private HashMap<Book, Set<Book>> graph;

    public VirtualLibrary() {
        books = new LinkedList<>();
        waitList = new LinkedList<>();
        viewHistory = new Stack<>();
        graph = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        graph.putIfAbsent(book, new HashSet<>());
    }

    public void addRelation(Book b1, Book b2) {
        graph.putIfAbsent(b1, new HashSet<>());
        graph.putIfAbsent(b2, new HashSet<>());
        graph.get(b1).add(b2);
        graph.get(b2).add(b1); // undirected
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
            return;
        }

        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void recommendBooks(Book origin) {
        Map<Book, Integer> distances = dijkstraSimple(graph, origin);

        System.out.println("Recommended books based on \"" + origin.getTitle() + "\":");
        distances.entrySet().stream()
            .filter(e -> !e.getKey().equals(origin))
            .sorted(Map.Entry.comparingByValue())
            .forEach(e -> System.out.println(e.getKey() + " (Distance: " + e.getValue() + ")"));
    }

    public static Map<Book, Integer> dijkstraSimple(HashMap<Book, Set<Book>> graph, Book origin) {
        Map<Book, Integer> distances = new HashMap<>();
        Queue<Book> queue = new LinkedList<>();

        distances.put(origin, 0);
        queue.add(origin);

        while (!queue.isEmpty()) {
            Book current = queue.poll();
            int currentDistance = distances.get(current);

            for (Book neighbor : graph.getOrDefault(current, new HashSet<>())) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    // Other methods: removeBookByTitle, viewBook, showViewHistory, etc.
    public boolean removeBookByTitle(String title) {
        Book toRemove = findBookByTitle(title);
        if (toRemove != null) {
            books.remove(toRemove);
            graph.remove(toRemove);
            for (Set<Book> neighbors : graph.values()) {
                neighbors.remove(toRemove);
            }
            return true;
        }
        return false;
    }

    public Book viewBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            viewHistory.push(book);
            return book;
        }
        return null;
    }

    public void showViewHistory() {
        if (viewHistory.isEmpty()) {
            System.out.println("No books viewed yet.");
            return;
        }

        System.out.println("Viewing history:");
        for (int i = viewHistory.size() - 1; i >= 0; i--) {
            System.out.println(viewHistory.get(i));
        }
    }

    public void addToWaitList(String userName) {
        waitList.offer(userName);
        System.out.println(userName + " added to the waitlist.");
    }

    public void showWaitList() {
        if (waitList.isEmpty()) {
            System.out.println("The waitlist is empty.");
            return;
        }

        System.out.println("Current waitlist:");
        for (String user : waitList) {
            System.out.println(user);
        }
    }

    public void serveNextInLine() {
        if (!waitList.isEmpty()) {
            String nextUser = waitList.poll();
            System.out.println("Serving: " + nextUser);
        } else {
            System.out.println("No users in the waitlist.");
        }
    }
}
