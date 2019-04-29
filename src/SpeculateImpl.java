import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Modelos.Bola;
import Modelos.Jogador;
import Modelos.Tabuleiro;

public class SpeculateImpl extends UnicastRemoteObject implements SpeculateInterface {
	
	private static final long serialVersionUID = -513804057617910473L;
	
	private Tabuleiro tabuleiro;
	private Jogador[] jogadores;
	
	protected SpeculateImpl() throws RemoteException {
		super();
		bolas = new ArrayList<Bola>();
		tabuleiro = new Tabuleiro();
		jogadores = new Jogador[2];
	}
		
	/**
	 * @return id (valor inteiro) do usuÃ¡rio (que corresponde a um nÃºmero de identificaÃ§Ã£o Ãºnico para este
	 * usuÃ¡rio durante uma partida), -1 se este usuÃ¡rio jÃ¡ estÃ¡ cadastrado ou -2 se o nÃºmero mÃ¡ximo de
	 * jogadores (2 vezes o nÃºmero mÃ¡ximo de partidas) tiver sido atingido 
	 */
	@Override
	public int registraJogador(String nomeDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return cÃ³digo de sucesso (0 indica sucesso e -1, erro).
	 * ObservaÃ§Ã£o: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador serÃ¡ vencedor por WO (ou
	 * seja, receberÃ¡ o cÃ³digo 5 quando chamar ehMinhaVez).
	 */
	@Override
	public int encerraPartida(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return -2 (tempo de espera esgotado), -1 (erro), 0 (ainda nÃ£o hÃ¡ partida), 1 (sim, hÃ¡ partida e o
	 * jogador inicia jogando) ou 2 (sim, hÃ¡ partida e o jogador Ã© o segundo a jogar).
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
	 * @return -2 (erro: ainda nÃ£o hÃ¡ 2 jogadores registrados na partida), -1 (erro: jogador nÃ£o
	 * encontrado), 0 (nÃ£o), 1 (sim), 2 (Ã© o vencedor), 3 (Ã© o perdedor), 4 (houve empate), 5 (vencedor por
	 * WO), 6 (perdedor por WO).
	 */
	@Override
	public int ehMinhaVez(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return nÃºmero de bolas que o jogador ainda tem em suas mÃ£os, -2 (erro: ainda nÃ£o hÃ¡ 2 jogadores
	 * registrados na partida), -1 (erro: jogador nÃ£o encontrado).
	 */
	@Override
	public int obtemNumBolas(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return nÃºmero de bolas que o oponente ainda tem em suas mÃ£os, -2 (erro: ainda nÃ£o hÃ¡ 2
	 * jogadores registrados na partida), -1 (erro: jogador nÃ£o encontrado).
	 */
	@Override
	public int obtemNumBolasOponente(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return string vazio em caso de erro ou string com o tabuleiro de jogo.
	 * ObservaÃ§Ã£o: essa string Ã© uma representaÃ§Ã£o do tabuleiro que possui 6 caracteres, respectivamente
	 * correspondentes ao estado de cada uma das 6 casas do tabuleiro. Se o caractere corresponder a um
	 * â€œ*â€�, isto significa que a respectiva casa estÃ¡ ocupada por uma bola. Se a casa estiver desocupada, o
	 * caractere serÃ¡ o prÃ³prio valor do dado que deve ser tirado para colocar uma bola nesta casa. A casa
	 * 6, por exemplo, nunca conterÃ¡ um â€œ*â€�. Como no inÃ­cio do jogo hÃ¡ bolas nas casas 1, 3 e 5, o string
	 * retornado no inÃ­cio do jogo deverÃ¡ ser â€œ*2*4*6â€�.
	 */
	@Override
	public String obtemTabuleiro(int idDoUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * @return 1 (tudo certo), ou -1 (erro), -2 (erro: ainda nÃ£o hÃ¡ partida), -3 (nÃ£o Ã© a vez do jogador), -4
	 * (Ã© a vez do jogador, mas nÃ£o para definir o nÃºmero de lanÃ§amentos), -5 (o nÃºmero de jogadas Ã©
	 */
	@Override
	public int defineJogadas(int idDoUsuario, int numeroDeLancamentos) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return nÃºmero obtido no dado, ou -1 (erro), -2 (erro: ainda nÃ£o hÃ¡ partida), -3 (nÃ£o Ã© a vez do
	 * jogador), -4 (Ã© a vez do jogador, mas nÃ£o para jogar dados).
	 */
	@Override
	public int jogaDado(int idDoUsuario) throws RemoteException {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
}
