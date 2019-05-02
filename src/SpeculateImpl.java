import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Semaphore;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import Modelos.*;

public class SpeculateImpl extends UnicastRemoteObject implements SpeculateInterface {
	
	private static final long serialVersionUID = -513804057617910473L;
	
	private static final int nroMaxPartidas = 500;
	private static Map<Integer, Partida> partidas = new Hashtable<Integer, Partida>(nroMaxPartidas);
	private static GeradorDeIDs geradorDeIdsJogadores = new GeradorDeIDs();
	private static Partida ultimaPartida;
	
	private static final int nroMaxJogadores = nroMaxPartidas * 2;
	private static Map<Integer, Jogador> jogadores = new Hashtable<Integer, Jogador>(nroMaxJogadores);
	private static GeradorDeIDs geradorDeIdsPartidas = new GeradorDeIDs();
	
	private static Semaphore semaforo = new Semaphore(1);
	
	protected SpeculateImpl() throws RemoteException { }
	
	private Jogador getJogadorPorNome(String nome) {
		for (Map.Entry<Integer, Jogador> entry : jogadores.entrySet()) {
			Jogador jogador = entry.getValue();
			if (jogador.getNome().contentEquals(nome))
				return jogador;		
		}
		return null;
	}
	
	private void adicionaJogadorAPartida(Jogador jogador) {
		if (ultimaPartida == null || !ultimaPartida.adicionaJogador(jogador))
        {
        	int proximoId = geradorDeIdsPartidas.getProximoId();
        	Partida novaPartida = new Partida(proximoId);
        	partidas.put(proximoId, novaPartida);
        	novaPartida.adicionaJogador(jogador);
        	ultimaPartida = novaPartida;
        }
	}
	/**
	 * @return id (valor inteiro) do usuario (que corresponde a um numero de identificaco unico para este
	 * usuario durante uma partida), -1 se este usuario ja esta cadastrado ou -2 se o numero maximo de
	 * jogadores (2 vezes o numero maximo de partidas) tiver sido atingido 
	 */
	@Override
	public int registraJogador(String nomeDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			// Se ja chegou ao nro max de jogadores nao pode registrar.
			if (jogadores.size() >= nroMaxJogadores)
				return -2;
			// Se ja existe um jogador com este nome, nao pode registrar.
			else if (getJogadorPorNome(nomeDoUsuario) != null) {
				System.out.println("Ja existe um jogador com este nome");
				return -1;
			}
			else {
				// Cria o jogador, o coloca na lista de jogadores, o coloca numa partida e retorna seu ID.
				int idDoJogador = geradorDeIdsJogadores.getProximoId();
		        Jogador novoJogador = new Jogador(idDoJogador, nomeDoUsuario);
		        jogadores.put(idDoJogador,  novoJogador);
		        
		        System.out.println("Novo jogador: " + novoJogador.toString());
		        adicionaJogadorAPartida(novoJogador);
		        
		        return idDoJogador;
			}			
		} catch(Exception e) {
			System.out.println("Erro em registraJogador");
			e.printStackTrace();
			return -3;
		} finally {
			semaforo.release();
		}
	}
	
	private Partida getPartidaDoJogador(int idDoJogador) {
		Partida partida = null;

		Object[] partidasAux = partidas.values().toArray();
		for (int i = 0; i < partidasAux.length; i++) {
			Partida partidaAux = (Partida)partidasAux[i];
			if (partidaAux.getJogador1().getId() == idDoJogador
				|| partidaAux.getJogador2().getId() == idDoJogador) {
				partida = partidaAux;
				break;
			}
		}
		
		return partida;
	}
	
	/**
	 * @return codigo de sucesso (0 indica sucesso e -1, erro).
	 * Observaco: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador sera vencedor por WO (ou
	 * seja, recebera o codigo 5 quando chamar ehMinhaVez).
	 */
	@Override
	public int encerraPartida(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			
			if (!partida.getPartidaAcabou()) {
				partida.encerraPartidaPorWO(idDoUsuario, true);				
			}
			else {				
				partida.encerraPartida(idDoUsuario);
			}
			
			return 0;
		}
		catch(Exception e) {
			System.out.println("Erro em encerraPartida");
			e.printStackTrace();
			return -1;
		} finally {
			semaforo.release();
		}
	}
	
	/**
	 * @return -2 (tempo de espera esgotado), -1 (erro), 0 (ainda nao ha partida), 1 (sim, ha partida e o
	 * jogador inicia jogando) ou 2 (sim, ha partida e o jogador eh o segundo a jogar).
	 */
	@Override
	public int temPartida(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			if (partida == null || partida.getJogador2() == null) {
				return 0;
			}
			else if (partida.getJogador1().getId() == idDoUsuario) {
				return 1;
			}
			else if (partida.getJogador2().getId() == idDoUsuario){
				return 2;
			}
		} catch(Exception ex) {
			System.out.println("Erro em encerraPartida");
			ex.printStackTrace();
			return -1;
		} finally {
			semaforo.release();
		}
		
		return -2;
	}
	
	/**
	 * @return string vazio para erro ou string com o nome do oponente.
	 */
	@Override
	public String obtemOponente(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			
			if (partida.getJogador1() == null || partida.getJogador2() == null)
				return "";

			if (partida.getJogador1().getId() == idDoUsuario)
				return partida.getJogador2().getNome();
			else
				return partida.getJogador1().getNome();
		}
		catch(Exception e) {
			System.out.println("Erro em obtemOponente");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @return -2 (erro: ainda nao ha 2 jogadores registrados na partida), -1 (erro: jogador nao
	 * encontrado), 0 (nao), 1 (sim), 2 (eh o vencedor), 3 (eh o perdedor), 4 (houve empate), 5 (vencedor por
	 * WO), 6 (perdedor por WO).
	 */
	@Override
	public int ehMinhaVez(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Jogador jogador = jogadores.get(idDoUsuario);
			if (jogador == null) {
				return -1;
			}
			else {
				Partida partida = getPartidaDoJogador(idDoUsuario);
				if (partida == null || partida.getJogador2() == null) {
					return -2;
				}
				else {
					if (partida.getPartidaAcabou()) {
						if (partida.getGanhador() == jogador) {
							return 2;
						}
						else {
							return 3;
						}
					}
					else {
						if (partida.getJogadorEmAcao() == jogador) {
							return 1;
						}
						else {
							return 0;
						}					
					}
					
				}
			}
		}
		catch(Exception e) {
			System.out.println("Erro em ehMinhaVez");
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		return -1;
	}
	
	/**
	 * @return numero de bolas que o jogador ainda tem em suas maos, -2 (erro: ainda nao ha 2 jogadores
	 * registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	@Override
	public int obtemNumBolas(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Jogador jogador = jogadores.get(idDoUsuario);
			return jogador.getBolasEmMao();			
		}
		catch(Exception e) {
			System.out.println("Erro em obtemNumBolas");
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		return -1;
	}
	
	/**
	 * @return numero de bolas que o oponente ainda tem em suas maos, -2 (erro: ainda nao ha 2
	 * jogadores registrados na partida), -1 (erro: jogador nao encontrado).
	 */
	@Override
	public int obtemNumBolasOponente(int idDoUsuario) throws RemoteException {
		try {
			semaforo.acquire();
			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			if (partida == null)
				return -1;
			else if (partida.getJogador2() == null) {
				return -2;
			}
			else if (partida.getJogador1().getId() == idDoUsuario) {
				return partida.getJogador2().getBolasEmMao();
			}
			else {
				return partida.getJogador1().getBolasEmMao();
			}
		}
		catch(Exception e) {
			System.out.println("Erro em obtemNumBolasOponente");
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		return -1;	
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
		try {
			semaforo.acquire();
			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			return partida.getTabuleiro().toString();
		}
		catch(Exception e) {
			System.out.println("Erro em obtemTabuleiro");
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		return "";
	}
	
	
	/**
	 * @return 1 (tudo certo), ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do jogador), -4
	 * (eh a vez do jogador, mas nao para definir o numero de lancamentos), -5 (o número de jogadas eh invalido, por exemplo, maior do que o numero de bolas que o jogador tem em maos).
	 */
	@Override
	public int defineJogadas(int idDoUsuario, int numeroDeLancamentos) throws RemoteException {
		try {
			semaforo.acquire();
			
			Jogador jogador = jogadores.get(idDoUsuario);			
			Partida partida = getPartidaDoJogador(idDoUsuario);
			if (partida == null || partida.getJogador2() == null)
				return -2;
			else if (partida.getJogadorEmAcao().getId() != idDoUsuario) {
				return -3;
			}
			else if (numeroDeLancamentos < 0 || jogador.getBolasEmMao() < numeroDeLancamentos) {
				return -5;
			}
			else {
				jogador.setNumeroDeJogadas(numeroDeLancamentos);
				return 1;
			}
		}
		catch(Exception e) {
			System.out.println("Erro em defineJogadas");
			e.printStackTrace();
			return -1;
		}
		finally {
			semaforo.release();
		}
	}
	
	/**
	 * @return numero obtido no dado, ou -1 (erro), -2 (erro: ainda nao ha partida), -3 (nao eh a vez do
	 * jogador), -4 (eh a vez do jogador, mas nao para jogar dados).
	 */
	@Override
	public int jogaDado(int idDoUsuario) throws RemoteException {
		try {
			boolean podeJogar = ehMinhaVez(idDoUsuario) == 1;
			
			semaforo.acquire();
			
			if (podeJogar) {
				Jogador jogador = jogadores.get(idDoUsuario);
				int nroDeJogadas = jogador.getNumeroDeJogadas();
				if (nroDeJogadas == 0) {
					return -4; // eh a vez do jogador, mas nao para jogar dados
				}
				else {
					Partida partida = getPartidaDoJogador(idDoUsuario);
					if (partida == null || partida.getJogador2() == null) {
						return -2; // erro: ainda nao ha partida
					}
					else {
						// Joga o dado ate o numero de jogadas definidas pelo usuario
						Tabuleiro tabuleiro = partida.getTabuleiro();
						
						Random r = new Random();
						int resultado = r.nextInt(6) + 1;
						//resultado = 6;
						if (resultado == 6) {
							tabuleiro.colocaBolaNaCaneleta();
							jogador.decrementaBolasEmMaos(1);
						}
						else {
							if (tabuleiro.temBolaNaCasa(resultado)) {
								tabuleiro.tiraBolaDaCasa(resultado);
								jogador.incrementaBolasEmMaos(1);
							}
							else {
								tabuleiro.colocaBolaNaCasa(resultado);
								jogador.decrementaBolasEmMaos(1);
							}
						}
						
						if (jogador.getBolasEmMao() == 0) {
							partida.encerraPartida(idDoUsuario);
						}
						else {
							nroDeJogadas -= 1;
							jogador.setNumeroDeJogadas(nroDeJogadas);
							if (nroDeJogadas == 0) {
								partida.trocaTurno();
							}							
						}
						return resultado;
					}
				}
			}
			else {
				return -3; // nao eh a vez do jogador
			}
		}
		catch(Exception e) {
			System.out.println("Erro em jogaDado");
			e.printStackTrace();
			return -1; // erro
		}
		finally {
			semaforo.release();
		}
	}
}
