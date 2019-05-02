import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import Modelos.Menu;

public class SpeculateClient {
	
	private static Scanner entrada;
	private static SpeculateInterface speculate;
	private static Menu menu;
	
	private static String maquina;
	private static String nomeDoUsuario;
	private static int idDoUsuario;
	
	public static void main (String[] args) {
		entrada = new Scanner(System.in);
		menu = new Menu(entrada);
		
		try
		{
			pegaDadosDoUsuario(args);
			speculate = (SpeculateInterface) Naming.lookup ("//"+maquina+"/Speculate");
			
			if (!tentaRegistrarJogador())
				System.exit(1);
			
			System.out.println("Seu código id é: " + idDoUsuario);
			
			executaLoopDoJogo();
				        
		} catch (Exception e) {
			System.out.println ("SpeculateClient falhou.");
			e.printStackTrace();
		}
	}
	
	private static void pegaDadosDoUsuario(String[] args) {
		if	(args.length != 2)
		{
			System.out.println("Digite o nome da maquina: ");
			maquina = entrada.next();
			
			System.out.println("Digite o nome do jogador: ");
			nomeDoUsuario = entrada.next();
		}
		else
		{
			maquina = args[0];
			nomeDoUsuario = args[1];
		}
	}
	
	private static boolean tentaRegistrarJogador() throws RemoteException {
		idDoUsuario = speculate.registraJogador(nomeDoUsuario);
		switch (idDoUsuario)
		{
			case -1:
				System.out.println("Ja existe um jogador com este nome.");
				return false;
			case -2:
				System.out.println("Servidor está cheio. Tente novamente mais tarde.");
				return false;
			case -3:
				System.out.println("Ocorreu um erro no servidor.");
				return false;
		}
		return true;
	}
	
	private static void executaLoopDoJogo() throws RemoteException {
		boolean clienteEncerrouAPartida = false;
		while (!clienteEncerrouAPartida) {
			int acao = menu.apresenta();
			switch (acao) {
				case 2: // encerraPartida
					if (encerraPartida())
						clienteEncerrouAPartida = true;
					break;
				case 3: // temPartida
					temPartida();
					break;
				case 4: // obtemOponente
					obtemOponente();
					break;
				case 5: // ehMinhaVez
					ehMinhaVez();
					break;
				case 6: // obtemNumBolas
					obtemNumBolas();
					break;
				case 7: // obtemNumBolasOponente
					obtemNumBolasOponente();
					break;
				case 8: // obtemTabuleiro
					obtemTabuleiro();
					break;
				case 9: // defineJogadas
					defineJogadas();
					break;
				case 10: // jogaDado
					jogaDado();
					break;
			}
		}
	}
	
	private static boolean encerraPartida() throws RemoteException {
		int resposta = speculate.encerraPartida(idDoUsuario);
		switch (resposta) {
			case -1:
				System.out.println("Ocorreu um erro ao tentar encerrar a partida.");
				return false;
			default:
				System.out.println("Partida encerrada.");
				return true;
		}
	}
	
	private static void temPartida() throws RemoteException {
		int resposta = speculate.temPartida(idDoUsuario);
		switch (resposta) {
			case -2:
				System.out.println("Tempo de espera esgotado");
				break;
			case -1:
				System.out.println("Ocorreu um erro ao verificar se tem partida.");
				break;
			case 0:
				System.out.println("Ainda nao ha partida.");
				break;
			case 1:
				System.out.println("Ha partida e voce inicia jogando.");
				break;
			case 2:
				System.out.println("Ha partida e voce eh o segundo a jogar.");
				break;
		}
	}
	
	private static void obtemOponente() throws RemoteException {
		String resultado = speculate.obtemOponente(idDoUsuario);
		if (resultado == "") {
			System.out.println("Ocorreu um erro ao tentar obter o nome do oponente");
		}
		else {
			System.out.println("O nome do oponente eh: " + resultado);
		}
	}
	
	private static void ehMinhaVez() throws RemoteException {
		int resultado = speculate.ehMinhaVez(idDoUsuario);
		switch (resultado) {
			case -2:
				System.out.println("Ainda nao ha 2 jogadores registrados na partida.");
				break;
			case -1:
				System.out.println("Jogador nao encontrado.");
				break;
			case 0:
				System.out.println("Nao eh sua vez.");
				break;
			case 1:
				System.out.println("Eh sua vez.");
				break;
			case 2:
				System.out.println("Voce eh o vencedor.");
				break;
			case 3:
				System.out.println("Voce eh o perdedor.");
				break;
			case 4:
				// TODO: Implementar o empate para o trabalho 2.
				break;
			case 5:
				System.out.println("Voce eh o vencedor por WO.");
				break;
			case 6:
				System.out.println("Voce eh o perdedor por WO.");
				break;
		}
	}
	
	private static void obtemNumBolas() throws RemoteException {
		int resultado = speculate.obtemNumBolas(idDoUsuario);
		switch (resultado) {
			case -2:
				System.out.println("Ainda nao ha 2 jogadores registrados na partida.");
				break;
			case -1:
				System.out.println("Jogador nao encontrado.");
				break;
			default:
				System.out.println("O numero de bolas na sua mao eh: " + resultado);
				break;
		}
	}
	
	private static void obtemNumBolasOponente() throws RemoteException {
		int resultado = speculate.obtemNumBolasOponente(idDoUsuario);
		switch (resultado) {
			case -2:
				System.out.println("Ainda nao ha 2 jogadores registrados na partida.");
				break;
			case -1:
				System.out.println("Jogador nao encontrado.");
				break;
			default:
				System.out.println("O numero de bolas na mao do oponente eh: " + resultado);
				break;
		}
	}
	
	private static void obtemTabuleiro() throws RemoteException {
		String resultado = speculate.obtemTabuleiro(idDoUsuario);
		if (resultado == "") {
			System.out.println("Ocorreu um erro ao tentar obter o tabuleiro.");
		}
		else {
			System.out.println(resultado);
		}
	}
	
	private static boolean defineJogadas() throws RemoteException {
		System.out.println("Digite o numero de jogadas desejadas e pressione 'enter'.");
		int numeroDeJogadas = entrada.nextInt();
		int resultado = speculate.defineJogadas(idDoUsuario, numeroDeJogadas);
		switch (resultado) {
			case 1:
				System.out.println("Tudo certo");
				return true;
			case -1:
				System.out.println("Ocorreu um erro ao tentar definir as jogadas.");
				break;
			case -2:
				System.out.println("Ainda nao ha partida.");
				break;
			case -3:
				System.out.println("Nao eh a sua vez.");
				break;
			case -4:
				System.out.println("Eh a sua vez mas nao para definir o numero de lancamentos.");
				break;
			case -5:
				System.out.println("O numero de jogadas eh invalido, por exemplo, maior que o numero de bolas que tem em maos.");
				break;
		}
		
		return false;
	}
	
	private static void jogaDado() throws RemoteException {
		int resultado = speculate.jogaDado(idDoUsuario);
		switch (resultado) {
			case -1:
				System.out.println("Ocorreu um erro ao tentar jogar o dado.");
				break;
			case -2:
				System.out.println("Ainda nao ha partida.");
				break;
			case -3:
				System.out.println("Nao eh a sua vez.");
				break;
			case -4:
				System.out.println("Eh a sua vez mas nao para jogar dados");
				break;
			default:
				System.out.println("O resultado do dado foi: " + resultado);
				break;
		}
	}
}
