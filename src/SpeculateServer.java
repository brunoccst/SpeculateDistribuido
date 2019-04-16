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
}
