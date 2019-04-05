import java.rmi.Naming;
import java.rmi.RemoteException;

public class SpeculateServer {

	public static void main (String[] args) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("Registro RMI criado e pronto.");			
		} catch (RemoteException e) {
			System.out.println("Registro RMI já está rodando.");			
		}
		try {
			Naming.rebind ("Speculate", new SpeculateImpl ());
			System.out.println ("SpeculateServer está pronto.");
		} catch (Exception e) {
			System.out.println ("SpeculateServer falhou:");
			e.printStackTrace();
		}
	}
	
	/*
	 * registraJogador
	 * Recebe: string com o nome do usuário/jogador.
	 * Retorna: id (valor inteiro) do usuário (que corresponde a um número de identificação único para este
	 * usuário durante uma partida), -1 se este usuário já está cadastrado ou -2 se o número máximo de
	 * jogadores (2 vezes o número máximo de partidas) tiver sido atingido.
	 */
	public int registraJogador(String nomeDoUsuario) {
		return -1;
	}

	/*
	 * encerraPartida
	 * Recebe: id do usuário (obtido através da chamada registraJogador).
	 * Retorna: código de sucesso (0 indica sucesso e -1, erro).
	 * Observação: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador será vencedor por WO (ou seja,
	 * receberá o código 5 quando chamar ehMinhaVez).
	 */
	public int encerraPartida(int idDoUsuario) {
		return -1;
	}
}
