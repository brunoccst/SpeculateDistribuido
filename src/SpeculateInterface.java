import java.rmi.Remote;
import java.rmi.RemoteException;

import Modelos.Jogador;

public interface SpeculateInterface extends Remote {
	
	/*
	 * registraJogador
	 * Recebe: string com o nome do usuário/jogador.
	 * Retorna: id (valor inteiro) do usuário (que corresponde a um número de identificação único para este
	 * usuário durante uma partida), -1 se este usuário já está cadastrado ou -2 se o número máximo de
	 * jogadores (2 vezes o número máximo de partidas) tiver sido atingido 
	 */
	public int registraJogador(String nomeDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: código de sucesso (0 indica sucesso e -1, erro).
	 * Observação: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador será vencedor por WO (ou
	 * seja, receberá o código 5 quando chamar ehMinhaVez).
	 */
	public int encerraPartida(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: -2 (tempo de espera esgotado), -1 (erro), 0 (ainda não há partida), 1 (sim, há partida e o
	 * jogador inicia jogando) ou 2 (sim, há partida e o jogador é o segundo a jogar).
	 */
	public int temPartida(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: string vazio para erro ou string com o nome do oponente.
	 */
	public String obtemOponente(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: -2 (erro: ainda não há 2 jogadores registrados na partida), -1 (erro: jogador não
	 * encontrado), 0 (não), 1 (sim), 2 (é o vencedor), 3 (é o perdedor), 4 (houve empate), 5 (vencedor por
	 * WO), 6 (perdedor por WO).
	 */
	public int ehMinhaVez(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: número de bolas que o jogador ainda tem em suas mãos, -2 (erro: ainda não há 2 jogadores
	 * registrados na partida), -1 (erro: jogador não encontrado).
	 */
	public int obtemNumBolas(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: número de bolas que o oponente ainda tem em suas mãos, -2 (erro: ainda não há 2
	 * jogadores registrados na partida), -1 (erro: jogador não encontrado).
	 */
	public int obtemNumBolasOponente(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: string vazio em caso de erro ou string com o tabuleiro de jogo.
	 * Observação: essa string é uma representação do tabuleiro que possui 6 caracteres, respectivamente
	 * correspondentes ao estado de cada uma das 6 casas do tabuleiro. Se o caractere corresponder a um
	 * “*”, isto significa que a respectiva casa está ocupada por uma bola. Se a casa estiver desocupada, o
	 * caractere será o próprio valor do dado que deve ser tirado para colocar uma bola nesta casa. A casa
	 * 6, por exemplo, nunca conterá um “*”. Como no início do jogo há bolas nas casas 1, 3 e 5, o string
	 * retornado no início do jogo deverá ser “*2*4*6”.
	 */
	public String obtemTabuleiro(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador), número de lançamentos que o
	 * jogador realizará.
	 * Retorna: 1 (tudo certo), ou -1 (erro), -2 (erro: ainda não há partida), -3 (não é a vez do jogador), -4
	 * (é a vez do jogador, mas não para definir o número de lançamentos), -5 (o número de jogadas é
	 */
	public int defineJogadas(int idDoUsuario, int numeroDeLancamentos) throws RemoteException;

	/*
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: número obtido no dado, ou -1 (erro), -2 (erro: ainda não há partida), -3 (não é a vez do
	 * jogador), -4 (é a vez do jogador, mas não para jogar dados).
	 */
	public int jogaDado(int idDoUsuario) throws RemoteException;
}
