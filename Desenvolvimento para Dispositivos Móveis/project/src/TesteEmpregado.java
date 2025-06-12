public class TesteEmpregado {
    public static void main(String[] args) {
        Empregado emp1 = new Empregado("João", "Silva", 9500.00);
        Empregado emp2 = new Empregado("Maria", "Souza", 12000.00);

        System.out.println("Salário anual de " + emp1.getPrimeiroNome() + ": R$" + emp1.getSalarioAnual());
        System.out.println("Salário anual de " + emp2.getPrimeiroNome() + ": R$" + emp2.getSalarioAnual());

        emp1.aplicarAumento(10);
        emp2.aplicarAumento(10);

        System.out.println("\nApós aumento de 10%:");
        System.out.println("Salário anual de " + emp1.getPrimeiroNome() + ": R$" + emp1.getSalarioAnual());
        System.out.println("Salário anual de " + emp2.getPrimeiroNome() + ": R$" + emp2.getSalarioAnual());

        System.out.println("\n" + emp1.getPrimeiroNome() + " ganha bem? " + emp1.ganhaBem());
        System.out.println(emp2.getPrimeiroNome() + " ganha bem? " + emp2.ganhaBem());
    }
}
