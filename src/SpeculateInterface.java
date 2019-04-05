import java.rmi.Remote;
import java.rmi.RemoteException;

import Modelos.Jogador;

public interface SpeculateInterface extends Remote {
	public void Inicia(Jogador j1, Jogador j2) throws RemoteException;
	public void TrocaTurno() throws RemoteException;
}
