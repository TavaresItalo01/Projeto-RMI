package rmi;

public class ContaBancaria {
    private String numeroConta;
    private double saldo;

    public ContaBancaria(String numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return false;
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente.");
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    public void excluir() {
        this.saldo = 0;
        System.out.println("Conta " + numeroConta + " excluída com sucesso.");
    }
}

