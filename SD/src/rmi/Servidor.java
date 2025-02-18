package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Servidor extends UnicastRemoteObject implements Servico {

    private static final long serialVersionUID = 1L; // Evita warnings de serialização`
    private Map<String, ContaBancaria> contas;

    protected Servidor() throws RemoteException {
        super();
        contas = new HashMap<>();
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

    @Override
    public double calculaIMC(double peso, double altura) throws RemoteException {
        if (altura <= 0) {
            throw new IllegalArgumentException("A altura deve ser maior que zero.");
        }
        return peso / (altura * altura);
    }

    @Override
    public boolean cadastrarConta(String numeroConta) throws RemoteException {
        if (contas.containsKey(numeroConta)) {
            return false; // Conta já existe
        }
        contas.put(numeroConta, new ContaBancaria(numeroConta));
        return true;
    }

    @Override
    public boolean depositar(String numeroConta, double valor) throws RemoteException {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta == null) {
            return false; // Conta não encontrada
        }
        conta.depositar(valor);
        return true;
    }

    @Override
    public boolean sacar(String numeroConta, double valor) throws RemoteException {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta == null) {
            return false; // Conta não encontrada
        }
        return conta.sacar(valor);
    }

    @Override
    public boolean excluirConta(String numeroConta) throws RemoteException {
        ContaBancaria conta = contas.remove(numeroConta);
        if (conta == null) {
            return false; // Conta não encontrada
        }
        conta.excluir();
        return true;
    }

    @Override
    public double consultarSaldo(String numeroConta) throws RemoteException {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta == null) {
            return -1; // Conta não encontrada
        }
        return conta.getSaldo();
    }

}
