package library;

import java.util.LinkedList;
import java.util.Queue;

public class WaitlistManager {
    private Queue<String> waitlist;

    public WaitlistManager() {
        waitlist = new LinkedList<>();
    }

    public void addToWaitlist(String userName) {
        waitlist.add(userName);
        System.out.println(userName + " foi adicionado à lista de espera.");
    }

    public void notifyNextUser() {
        if (waitlist.isEmpty()) {
            System.out.println("A lista de espera está vazia.");
        } else {
            String nextUser = waitlist.poll();
            System.out.println("Notificando " + nextUser + ": o livro agora está disponível!");
        }
    }

    public void printWaitlist() {
        if (waitlist.isEmpty()) {
            System.out.println("Nenhum usuário na lista de espera.");
        } else {
            System.out.println("Lista de espera:");
            for (String user : waitlist) {
                System.out.println("- " + user);
            }
        }
    }
}
