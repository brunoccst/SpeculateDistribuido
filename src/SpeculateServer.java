import java.rmi.Naming;
import java.rmi.RemoteException;

public class SpeculateServer {

	public static void main (String[] args) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("Registro RMI criado e pronto.");			
		} catch (RemoteException e) {
			System.out.println("Registro RMI j� est� rodando.");			
		}
		try {
			Naming.rebind ("Speculate", new SpeculateImpl ());
			System.out.println ("SpeculateServer est� pronto.");
		} catch (Exception e) {
			System.out.println ("SpeculateServer falhou:");
			e.printStackTrace();
		}
	}
	
	/*
	 * registraJogador
	 * Recebe: string com o nome do usu�rio/jogador.
	 * Retorna: id (valor inteiro) do usu�rio (que corresponde a um n�mero de identifica��o �nico para este
	 * usu�rio durante uma partida), -1 se este usu�rio j� est� cadastrado ou -2 se o n�mero m�ximo de
	 * jogadores (2 vezes o n�mero m�ximo de partidas) tiver sido atingido.
	 */
	public int registraJogador(String nomeDoUsuario) {
		return -1;
	}

	/*
	 * encerraPartida
	 * Recebe: id do usu�rio (obtido atrav�s da chamada registraJogador).
	 * Retorna: c�digo de sucesso (0 indica sucesso e -1, erro).
	 * Observa��o: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	 * para a partida ou de se determinar que houve empate, o outro jogador ser� vencedor por WO (ou seja,
	 * receber� o c�digo 5 quando chamar ehMinhaVez).
	 */
	public int encerraPartida(int idDoUsuario) {
		return -1;
	}
}
