import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Modelos.*;

public class SpeculateImpl extends UnicastRemoteObject implements SpeculateInterface {
	
	private static final long serialVersionUID = -513804057617910473L;
	
	private ArrayList<Partida> partidas;
	
	protected SpeculateImpl() throws RemoteException {
		
	}
		
	/**
	 * @return id (valor inteiro) do usuario (que corresponde a um numero de identificaco unico para este
	 * usuario durante uma partida), -1 se este usuario ja esta cadastrado ou -2 se o numero maximo de
	 * jogadores (2 vezes o numero maximo de partidas) tiver sido atingido 
	 */
	@Override
	public int registraJogador(String nomeDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return codigo de sucesso (0 indica sucesso e -1, erro).
	 * Observaco: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador sera vencedor por WO (ou
	 * seja, recebera o codigo 5 quando chamar ehMinhaVez).
	 */
	@Override
	public int encerraPartida(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return -2 (tempo de espera esgotado), -1 (erro), 0 (ainda nao ha partida), 1 (sim, ha partida e o
	 * jogador inicia jogando) ou 2 (sim, ha partida e o jogador eh o segundo a jogar).
	 */
	@Override
	public int temPartida(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return string vazio para erro ou string com o nome do oponente.
	 */
	@Override
	public String obtemOponente(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return -2 (erro: ainda nao ha 2 jogadores registrados na partida), -1 (erro: jogador nao
	 * encontrado), 0 (nao), 1 (sim), 2 (eh o vencedor), 3 (eh o perdedor), 4 (houve empate), 5 (vencedor por
	 * WO), 6 (perdedor por WO).
	 */
	@Override
	public int ehMinhaVez(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return numero de bolas que o jogador ainda tem em suas maos, -2 (erro: ainda nao ha 2 jogadores
	 * registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	@Override
	public int obtemNumBolas(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return numero de bolas que o oponente ainda tem em suas maos, -2 (erro: ainda nao ha 2
	 * jogadores registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	@Override
	public int obtemNumBolasOponente(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return string vazio em caso de erro ou string com o tabuleiro de jogo.
	 * Observacao: essa string eh uma representacao do tabuleiro que possui 6 caracteres, respectivamente
	 * correspondentes ao estado de cada uma das 6 casas do tabuleiro. Se o caractere corresponder a um
	 * "*", isto significa que a respectiva casa esta ocupada por uma bola. Se a casa estiver desocupada, o
	 * caractere sera o proprio valor do dado que deve ser tirado para colocar uma bola nesta casa. A casa
	 * 6, por exemplo, nunca contera um "*". Como no inicio do jogo ha bolas nas casas 1, 3 e 5, o string
	 * retornado no inicio do jogo devera ser "*2*4*6".
	 */
	@Override
	public String obtemTabuleiro(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * @return 1 (tudo certo), ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do jogador), -4
	 * (eh a vez do jogador, mas nao para definir o numero de lancamentos), -5 (o numero de jogadas eh
	 */
	@Override
	public int defineJogadas(int idDoUsuario, int numeroDeLancamentos) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return numero obtido no dado, ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do
	 * jogador), -4 (eh a vez do jogador, mas nao para jogar dados).
	 */
	@Override
	public int jogaDado(int idDoUsuario) throws RemoteException {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
}
