public class Empregado {
    private String primeiroNome;
    private String sobrenome;
    private double salarioMensal;

    public Empregado(String primeiroNome, String sobrenome, double salarioMensal) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        setSalarioMensal(salarioMensal);
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setSalarioMensal(double salarioMensal) {
        if (salarioMensal > 0) {
            this.salarioMensal = salarioMensal;
        } else {
            this.salarioMensal = 0.0;
        }
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public double getSalarioAnual() {
        return salarioMensal * 12;
    }

    public boolean ganhaBem() {
        return salarioMensal > 10000;
    }

    public void aplicarAumento(double percentual) {
        salarioMensal += salarioMensal * (percentual / 100);
    }
}
