package rmi;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Localizando o serviço remoto
        	Servico servico = (Servico) Naming.lookup("rmi://localhost/Servico");

            // Chamando os métodos remotos
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite uma palavra: ");
            String palavra = scanner.nextLine();

            System.out.println("Data e Hora do Servidor: " + servico.getDataHora());
            System.out.println("String invertida: " + servico.inverteString(palavra));

            System.out.println("\n--- Calculadora de IMC ---");
            System.out.print("Digite seu peso (em kg): ");
            double peso = scanner.nextDouble();
            System.out.print("Digite sua altura (em metros): ");
            double altura = scanner.nextDouble();
            
            System.out.printf("Seu IMC é: %.2f\n", + servico.calculaIMC(peso, altura));
            
            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro no Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }	

}
