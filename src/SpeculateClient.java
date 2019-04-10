import java.rmi.Naming;
import java.rmi.RemoteException;

public class SpeculateClient {

	private static int meuId;
	private static SpeculateInterface speculate;
	
	public static void main (String[] args) {
		String maquina = null;
		String nomeDoUsuario = null;

		meuId = -1;
		
		if	(args.length != 2)  {
			System.out.println("Uso: java SpeculateClient <maquina> <nome>");
			System.exit(1);
		}
		else {
			maquina = args[0];
			nomeDoUsuario = args[1];
		}
		
		try {
			speculate = (SpeculateInterface) Naming.lookup ("//"+maquina+"/Speculate");
			meuId = speculate.registraJogador(nomeDoUsuario);
			
			int minhaPosicao = esperaPartida();
			System.out.println("Seu oponente é: " + speculate.obtemOponente(meuId));
			
			if (minhaPosicao == 1) {
				
			}
			else {
				while (speculate.ehMinhaVez(meuId) != 1) {
					
				}
			}
			
		} catch (Exception e) {
			System.out.println ("SpeculateClient falhou.");
			e.printStackTrace();
		}
	}
	
	private static int esperaPartida() throws RemoteException {
		int statusDaPartida = -2;
		while (true)
		{
			statusDaPartida = speculate.temPartida(meuId);
			switch (statusDaPartida) {
				case -2:
					System.out.println("Tempo de espera por partida esgotado.");
					break;
				case -1:
					System.out.println("Erro na espera por partida.");
					break;
				case 0:
					System.out.println("Não há partida ainda.");
					break;
				case 1:
				case 2:
					System.out.println("Partida pronta e você é o "+statusDaPartida+"º jogador");
					return statusDaPartida;
			}
		}
	}

}
