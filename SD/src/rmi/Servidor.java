package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Servidor extends UnicastRemoteObject implements Servico {

    private static final long serialVersionUID = 1L; // Evita warnings de serialização

    protected Servidor() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            // Criando e iniciando o registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry iniciado na porta 1099.");

            // Criando a instância do serviço e registrando no RMI
            Servidor servidor = new Servidor();
            String localizacao = "rmi://localhost/Servico";
            Naming.rebind(localizacao, servidor);

            System.out.println("Servidor RMI pronto e rodando...");
        } catch (RemoteException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Erro de URL mal formulada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getDataHora() throws RemoteException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(Calendar.getInstance().getTime());
    }

    @Override
    public String inverteString(String string) throws RemoteException {
        String retorno = "";
        StringBuffer strb = new StringBuffer(string);
        retorno = strb.reverse().toString();
        return retorno;
    }
}
