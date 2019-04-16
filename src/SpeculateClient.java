import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import Modelos.Jogador;

public class SpeculateClient {
	
	private static Scanner entrada;
	private static Jogador jogador;
	
	public static void main (String[] args) {
		
		entrada = new Scanner(System.in);
		jogador = new Jogador();
		
		// Pega os parametros iniciais.
		String maquina  = "";
		String nomeDoUsuario = "";
		
		if	(args.length != 2)
		{
			System.out.println("Uso: java SpeculateClient <maquina> <nome>");
			System.exit(1);
		}
		else
		{
			maquina = args[0];
			nomeDoUsuario = args[1];
		}
		
		try
		{
			// Tenta registrar o jogador.
			SpeculateInterface speculate = (SpeculateInterface) Naming.lookup ("//"+maquina+"/Speculate");
			
			jogador.setId(speculate.registraJogador(nomeDoUsuario));
			switch (jogador.getId())
			{
				case -1:
					System.out.println("Ja existe um jogador com este nome.");
					System.exit(1);
					break;
				case -2:
					System.out.println("Servidor est� cheio. Tente novamente mais tarde.");
					System.exit(1);
					break;
			}
			
			System.out.println("Seu c�digo id �: " + jogador.getId());
	        System.out.println("Aguardando outro jogador iniciar a partida...");
	        
	        // Espera uma partida valida.
	        int temPartida;
	        do
	        {
	        	temPartida = speculate.temPartida(jogador.getId());
	        	switch (temPartida)
	        	{
		        	case 1:
		        	case 2:
		        		System.out.println("Partida iniciar�. Voc� jogar� em " + temPartida + "� lugar.");
		        		break;
		        		
		        	case -1:
		        		System.out.println("Tempo de espera esgotado. Partida ser� finalizada.");
		        		speculate.encerraPartida(jogador.getId());
		        		break;
		        		
		        	case -2:
		        		System.out.println("Ocoreu algum problema. Partida ser� finalizada.");
		        		speculate.encerraPartida(jogador.getId());
		        		break;
	        	}
	        }
	        while (temPartida == 0);
	        
	        loopDoJogo(speculate);
	        
		} catch (Exception e) {
			System.out.println ("SpeculateClient falhou.");
			e.printStackTrace();
		}
	}
	
	private static void loopDoJogo(SpeculateInterface speculate) throws RemoteException
	{
		while (true)
		{
			int resultado = speculate.ehMinhaVez(jogador.getId());
			switch (resultado)
			{
				case 0:
					break;
				case 1:
					System.out.println("Tabuleiro atual:");
		            System.out.println(speculate.obtemTabuleiro(jogador.getId()));
		            
		            System.out.println("Sua jogada.");
		            System.out.println("Quantas vezes deseja jogar o dado? Digite um valor maior ou igual a 1 e at� no m�ximo " + jogador.getTotalDeBolas() + ".");
		            int vezes = 0;
		            while (vezes < 1 || vezes > jogador.getTotalDeBolas())
		            	vezes = entrada.nextInt();
		            
		            int nroDado;
		            for (int i = 0; i < vezes; i++)
		            	nroDado = speculate.jogaDado(jogador.getId());
		            
		            // TODO: Finalizar.
		            
					break;
					
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					String msgResultado = "";
					if (resultado == 2)
						msgResultado = "Voc� ganhou.";
					else if (resultado == 3)
						msgResultado = "Voc� perdeu.";
					else if (resultado == 4)
						msgResultado = "Houve empate.";
					else if (resultado == 5)
						msgResultado = "Voc� venceu por WO.";
					else if (resultado == 6)
						msgResultado = "Voc� perdeu por WO.";
					
					System.out.println("Partida encerrada! " + msgResultado);
		            System.out.println("Tabuleiro atual:");
		            System.out.println(speculate.obtemTabuleiro(jogador.getId()));
		            break;
				
				case -1:
					break;
					
				case -2:
					break;
			}
		}
	}
	
}
