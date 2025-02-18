package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servico extends Remote {
	
	public String getDataHora() throws RemoteException;
	
	public String inverteString(String string) throws RemoteException;
}
