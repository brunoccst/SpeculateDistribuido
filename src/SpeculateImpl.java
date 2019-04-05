import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Modelos.Bola;
import Modelos.Dado;
import Modelos.Jogador;
import Modelos.Tabuleiro;

public class SpeculateImpl extends UnicastRemoteObject implements SpeculateInterface {

	private static final long serialVersionUID = -513804057617910473L;
	
	private ArrayList<Bola> bolas;
	private Dado dado;
	private Tabuleiro tabuleiro;
	private Jogador jogador1;
	private Jogador jogador2;
	
	private Jogador jogadorEmTurno;
	
	public SpeculateImpl() throws RemoteException {
		
		// Cria as 33 bolas.
		bolas = new ArrayList<Bola>();
		for (int i = 0; i < 33; i++) {
			bolas.add(new Bola());
		}
		
		// Cria o dado.
		dado = new Dado();
		
		// Cria o tabuleiro.
		tabuleiro = new Tabuleiro();
		
	}
	
	private void iniciaBolasNasCasas() throws RemoteException  {
		Bola bola;
		
		bola = bolas.remove(0);
		tabuleiro.ColocaBolaNaCasa1(bola);
		
		bola = bolas.remove(0);
		tabuleiro.ColocaBolaNaCasa3(bola);
		
		bola = bolas.remove(0);
		tabuleiro.ColocaBolaNaCasa5(bola);
	}
	
	private void daBolasIniciaisAoJogador(Jogador j) throws RemoteException  {
		for (int i = 0; i < 15; i++) {
			Bola b = bolas.get(i);
			j.recebeBola(b);
		}
	}
	
	public void Inicia(Jogador j1, Jogador j2) throws RemoteException {
		jogador1 = j1;
		jogador2 = j2;
		
		iniciaBolasNasCasas();
		
		daBolasIniciaisAoJogador(j1);
		daBolasIniciaisAoJogador(j2);
		
		jogadorEmTurno = j1;
	}
	
	public void TrocaTurno() throws RemoteException  {
		if (jogadorEmTurno == jogador1) {
			jogadorEmTurno = jogador2;
		}
		else {
			jogadorEmTurno = jogador1;
		}
	}

}
