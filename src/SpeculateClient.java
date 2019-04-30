import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import Modelos.Jogador;

public class SpeculateClient {
	
	private static Scanner entrada;
	private static int meuId;
	
	public static void main (String[] args) {
		
		entrada = new Scanner(System.in);
		
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
			
			meuId = speculate.registraJogador(nomeDoUsuario);
			switch (meuId)
			{
				case -1:
					System.out.println("Ja existe um jogador com este nome.");
					System.exit(1);
					break;
				case -2:
					System.out.println("Servidor está cheio. Tente novamente mais tarde.");
					System.exit(1);
					break;
			}
			
			System.out.println("Seu código id é: " + meuId);
	        System.out.println("Aguardando outro jogador iniciar a partida...");
	        
	        // Espera uma partida valida.
	        int temPartida;
	        do
	        {
	        	temPartida = speculate.temPartida(meuId);
	        	switch (temPartida)
	        	{
		        	case 1:
		        	case 2:
		        		System.out.println("Partida iniciará. Você jogará em " + temPartida + "º lugar.");
		        		break;
		        		
		        	case -1:
		        		System.out.println("Tempo de espera esgotado. Partida será finalizada.");
		        		speculate.encerraPartida(meuId);
		        		break;
		        		
		        	case -2:
		        		System.out.println("Ocoreu algum problema. Partida será finalizada.");
		        		speculate.encerraPartida(meuId);
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
			int resultado = speculate.ehMinhaVez(meuId);
			switch (resultado)
			{
				case 0:
					break;
				case 1:
					System.out.println("Tabuleiro atual:");
		            System.out.println(speculate.obtemTabuleiro(meuId));
		            
		            int nroDeBolasAtuais = speculate.obtemNumBolas(meuId);
		            System.out.println("Sua jogada.");
		            System.out.println("Quantas vezes deseja jogar o dado? Digite um valor maior ou igual a 1 e até no máximo " + nroDeBolasAtuais + ".");
		            int vezes = 0;
		            while (vezes < 1 || vezes > nroDeBolasAtuais)
		            	vezes = entrada.nextInt();
		            
		            int nroDado;
		            for (int i = 0; i < vezes; i++)
		            	nroDado = speculate.jogaDado(meuId);
		            
		            // TODO: Finalizar.
		            
					break;
					
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					String msgResultado = "";
					if (resultado == 2)
						msgResultado = "Você ganhou.";
					else if (resultado == 3)
						msgResultado = "Você perdeu.";
					else if (resultado == 4)
						msgResultado = "Houve empate.";
					else if (resultado == 5)
						msgResultado = "Você venceu por WO.";
					else if (resultado == 6)
						msgResultado = "Você perdeu por WO.";
					
					System.out.println("Partida encerrada! " + msgResultado);
		            System.out.println("Tabuleiro atual:");
		            System.out.println(speculate.obtemTabuleiro(meuId));
		            break;
				
				case -1:
					break;
					
				case -2:
					break;
			}
		}
	}
	
}
