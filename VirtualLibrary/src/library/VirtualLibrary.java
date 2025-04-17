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
        WaitlistManager waitlist = new WaitlistManager();
        NavigationHistory history = new NavigationHistory();
        BookBinaryTree tree = new BookBinaryTree();

        // Criando livros
        Book book1 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        Book book2 = new Book("1984", "George Orwell", 1949);
        Book book3 = new Book("Dom Casmurro", "Machado de Assis", 1899);
        Book book4 = new Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);
        Book book5 = new Book("A Revolução dos Bichos", "George Orwell", 1945);
        Book book6 = new Book("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);
        Book book7 = new Book("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
        Book book8 = new Book("Cem Anos de Solidão", "Gabriel García Márquez", 1967);
        Book book9 = new Book("O Hobbit", "J.R.R. Tolkien", 1937);
        Book book10 = new Book("A Menina que Roubava Livros", "Markus Zusak", 2005);

        // Adicionando livros à biblioteca
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Fila de espera
        waitlist.addToWaitlist("Alice");
        waitlist.addToWaitlist("Bob");
        waitlist.addToWaitlist("Carlos");

        System.out.println("\n--- Fila atual ---");
        waitlist.printWaitlist();

        System.out.println("\n--- Livro disponível! ---");
        waitlist.notifyNextUser();

        System.out.println("\n--- Fila atualizada ---");
        waitlist.printWaitlist();

        // Histórico de visualizações
        history.viewBook(book2);
        history.viewBook(book3);
        history.viewBook(book1);

        System.out.println("\n--- Histórico de navegação ---");
        history.showHistory();

        // Inserindo os 10 livros na árvore binária
        tree.insert(book1);
        tree.insert(book2);
        tree.insert(book3);
        tree.insert(book4);
        tree.insert(book5);
        tree.insert(book6);
        tree.insert(book7);
        tree.insert(book8);
        tree.insert(book9);
        tree.insert(book10);

        System.out.println("\n--- Árvore binária de livros (ordenados por título) ---");
        tree.inOrderTraversal();

        // Buscando livros na árvore
        String busca1 = "O Hobbit";
        String busca2 = "Harry Potter";

        Book resultado1 = tree.search(busca1);
        Book resultado2 = tree.search(busca2);

        System.out.println("\nBusca por título: " + busca1);
        System.out.println(resultado1 != null ? "Livro encontrado: " + resultado1 : "Livro não encontrado.");

        System.out.println("\nBusca por título: " + busca2);
        System.out.println(resultado2 != null ? "Livro encontrado: " + resultado2 : "Livro não encontrado.");

        // Geração de nomes aleatórios para 10.000 livros
        System.out.println("\n--- Gerando 10.000 nomes aleatórios de livros ---");
        String[] nomesGerados = GeradorDeNomesDeLivros.gerarNomes(10000);

        // Ordenação com Bubble Sort
        System.out.println("\n--- Ordenando com Bubble Sort ---");
        long inicioBubble = System.currentTimeMillis();
        String[] ordenadoBubble = BubbleSort.ordenar(nomesGerados);
        long fimBubble = System.currentTimeMillis();
        System.out.println("Tempo (Bubble Sort): " + (fimBubble - inicioBubble) + " ms");
        System.out.println("Comparações (Bubble Sort): " + BubbleSort.comparacoes);

        // Ordenação com Merge Sort
        System.out.println("\n--- Ordenando com Merge Sort ---");
        long inicioMerge = System.currentTimeMillis();
        String[] ordenadoMerge = MergeSort.ordenar(nomesGerados);
        long fimMerge = System.currentTimeMillis();
        System.out.println("Tempo (Merge Sort): " + (fimMerge - inicioMerge) + " ms");
        System.out.println("Comparações (Merge Sort): " + MergeSort.comparacoes);

        // Exibir os 10 primeiros nomes ordenados (Bubble Sort)
        System.out.println("\nPrimeiros 10 nomes ordenados (Bubble Sort):");
        int mostradosBubble = 0;
        for (String nome : ordenadoBubble) {
            if (nome != null && !nome.trim().isEmpty()) {
                System.out.println((mostradosBubble + 1) + ": " + nome);
                mostradosBubble++;
                if (mostradosBubble == 10) break;
            }
        }

        // Exibir os 10 primeiros nomes ordenados (Merge Sort)
        System.out.println("\nPrimeiros 10 nomes ordenados (Merge Sort):");
        int mostradosMerge = 0;
        for (String nome : ordenadoMerge) {
            if (nome != null && !nome.trim().isEmpty()) {
                System.out.println((mostradosMerge + 1) + ": " + nome);
                mostradosMerge++;
                if (mostradosMerge == 10) break;
            }
        }
    }
}
