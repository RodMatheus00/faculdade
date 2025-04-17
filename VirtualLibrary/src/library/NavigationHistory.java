package library;

import java.util.Stack;

public class NavigationHistory {
    private Stack<Book> history;

    public NavigationHistory() {
        history = new Stack<>();
    }

    public void viewBook(Book book) {
        history.push(book);
        System.out.println("Visualizando livro: " + book.getTitle());
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("Nenhum livro visualizado ainda.");
        } else {
            System.out.println("Histórico de visualizações (mais recente primeiro):");
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println("- " + history.get(i).getTitle());
            }
        }
    }

    public Book back() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}
