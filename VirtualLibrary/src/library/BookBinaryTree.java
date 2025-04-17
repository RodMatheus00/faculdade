package library;

public class BookBinaryTree {

    // Nó da árvore
    private static class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }

    private Node root;

    // Inserir um livro na árvore
    public void insert(Book book) {
        root = insertRecursive(root, book);
    }

    private Node insertRecursive(Node current, Book book) {
        if (current == null) {
            return new Node(book);
        }

        if (book.getTitle().compareToIgnoreCase(current.book.getTitle()) < 0) {
            current.left = insertRecursive(current.left, book);
        } else if (book.getTitle().compareToIgnoreCase(current.book.getTitle()) > 0) {
            current.right = insertRecursive(current.right, book);
        } else {
            // Títulos iguais não são inseridos
            System.out.println("Livro com título \"" + book.getTitle() + "\" já existe na árvore.");
        }

        return current;
    }

    // Buscar livro por título
    public Book search(String title) {
        return searchRecursive(root, title);
    }

    private Book searchRecursive(Node current, String title) {
        if (current == null) {
            return null;
        }

        if (title.equalsIgnoreCase(current.book.getTitle())) {
            return current.book;
        }

        if (title.compareToIgnoreCase(current.book.getTitle()) < 0) {
            return searchRecursive(current.left, title);
        } else {
            return searchRecursive(current.right, title);
        }
    }

    // Exibir todos os livros em ordem (alfabética por título)
    public void inOrderTraversal() {
        System.out.println("Livros em ordem alfabética:");
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node current) {
        if (current != null) {
            inOrderRecursive(current.left);
            System.out.println(current.book);
            inOrderRecursive(current.right);
        }
    }
}
