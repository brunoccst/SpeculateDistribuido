import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeculateInterface extends Remote {
	
	/*
	 * registraJogador
	 * Recebe: string com o nome do usuario/jogador.
	 * Retorna: id (valor inteiro) do usuario (que corresponde a um numero de identificacao unico para este
	 * usuario durante uma partida), -1 se este usuario ja esta cadastrado ou -2 se o numero maximo de
	 * jogadores (2 vezes o numero maximo de partidas) tiver sido atingido 
	 */
	public int registraJogador(String nomeDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: codigo de sucesso (0 indica sucesso e -1, erro).
	 * Observacao: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador sera vencedor por WO (ou
	 * seja, recebera o codigo 5 quando chamar ehMinhaVez).
	 */
	public int encerraPartida(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: -2 (tempo de espera esgotado), -1 (erro), 0 (ainda nao ha partida), 1 (sim, ha partida e o
	 * jogador inicia jogando) ou 2 (sim, ha partida e o jogador eh o segundo a jogar).
	 */
	public int temPartida(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: string vazio para erro ou string com o nome do oponente.
	 */
	public String obtemOponente(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: -2 (erro: ainda nao ha 2 jogadores registrados na partida), -1 (erro: jogador nao
	 * encontrado), 0 (nao), 1 (sim), 2 (eh o vencedor), 3 (eh o perdedor), 4 (houve empate), 5 (vencedor por
	 * WO), 6 (perdedor por WO).
	 */
	public int ehMinhaVez(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: numero de bolas que o jogador ainda tem em suas maos, -2 (erro: ainda nao ha 2 jogadores
	 * registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	public int obtemNumBolas(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: numero de bolas que o oponente ainda tem em suas maos, -2 (erro: ainda nao ha 2
	 * jogadores registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	public int obtemNumBolasOponente(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: string vazio em caso de erro ou string com o tabuleiro de jogo.
	 * Observacao: essa string eh uma representacao do tabuleiro que possui 6 caracteres, respectivamente
	 * correspondentes ao estado de cada uma das 6 casas do tabuleiro. Se o caractere corresponder a um
	 * "*", isto significa que a respectiva casa esta ocupada por uma bola. Se a casa estiver desocupada, o
	 * caractere sera o proprio valor do dado que deve ser tirado para colocar uma bola nesta casa. A casa
	 * 6, por exemplo, nunca contera um "*". Como no inicio do jogo ha bolas nas casas 1, 3 e 5, o string
	 * retornado no inicio do jogo devera ser "*2*4*6".
	 */
	public String obtemTabuleiro(int idDoUsuario) throws RemoteException;
	
	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador), numero de lancamentos que o
	 * jogador realizara.
	 * Retorna: 1 (tudo certo), ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do jogador), -4
	 * (eh a vez do jogador, mas nao para definir o numero de lancamentos), -5 (o numero de jogadas eh
	 */
	public int defineJogadas(int idDoUsuario, int numeroDeLancamentos) throws RemoteException;

	/*
	 * Recebe: id do usuario (obtido atravehs da chamada registraJogador).
	 * Retorna: numero obtido no dado, ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do
	 * jogador), -4 (eh a vez do jogador, mas nao para jogar dados).
	 */
	public int jogaDado(int idDoUsuario) throws RemoteException;

}
