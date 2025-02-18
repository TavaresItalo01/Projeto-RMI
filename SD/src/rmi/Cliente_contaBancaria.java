package rmi;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente_contaBancaria {
    public static void main(String[] args) {
        try {
            // Localizando o serviço remoto
            Servico servico = (Servico) Naming.lookup("rmi://localhost/Servico");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Menu de Gerenciamento de Banco ---");
                System.out.println("1. Cadastrar Conta");
                System.out.println("2. Depositar");
                System.out.println("3. Sacar");
                System.out.println("4. Excluir Conta");
                System.out.println("5. Consultar Saldo");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o número da conta para cadastrar: ");
                        String numeroConta = scanner.next();
                        if (servico.cadastrarConta(numeroConta)) {
                            System.out.println("Conta cadastrada com sucesso!");
                        } else {
                            System.out.println("Erro: Conta já existente.");
                        }
                        break;

                    case 2:
                        System.out.print("Digite o número da conta: ");
                        numeroConta = scanner.next();
                        System.out.print("Digite o valor do depósito: ");
                        double deposito = scanner.nextDouble();
                        if (servico.depositar(numeroConta, deposito)) {
                            System.out.println("Depósito realizado com sucesso!");
                        } else {
                            System.out.println("Erro: Conta não encontrada.");
                        }
                        break;

                    case 3:
                        System.out.print("Digite o número da conta: ");
                        numeroConta = scanner.next();
                        System.out.print("Digite o valor do saque: ");
                        double saque = scanner.nextDouble();
                        if (servico.sacar(numeroConta, saque)) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Erro: Conta não encontrada ou saldo insuficiente.");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o número da conta para excluir: ");
                        numeroConta = scanner.next();
                        if (servico.excluirConta(numeroConta)) {
                            System.out.println("Conta excluída com sucesso!");
                        } else {
                            System.out.println("Erro: Conta não encontrada.");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o número da conta: ");
                        numeroConta = scanner.next();
                        double saldo = servico.consultarSaldo(numeroConta);
                        if (saldo == -1) {
                            System.out.println("Erro: Conta não encontrada.");
                        } else {
                            System.out.println("Saldo da conta " + numeroConta + ": " + saldo);
                        }
                        break;

                    case 6:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
