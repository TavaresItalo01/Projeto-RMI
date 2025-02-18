package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servico extends Remote {
	//metodos do cliente 
	public String getDataHora() throws RemoteException;
	
	public String inverteString(String string) throws RemoteException;
	
	//metodo do cliente Calculadora IMC
	double calculaIMC(double peso, double altura) throws RemoteException;

	 // metodos de gerenciamento de contas bancarias
	 boolean cadastrarConta(String numeroConta) throws RemoteException;
	 boolean depositar(String numeroConta, double valor) throws RemoteException;
	 boolean sacar(String numeroConta, double valor) throws RemoteException;
	 boolean excluirConta(String numeroConta) throws RemoteException;
	 double consultarSaldo(String numeroConta) throws RemoteException;
}
