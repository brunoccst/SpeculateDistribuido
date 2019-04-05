import java.rmi.Naming;

public class SpeculateClient {

	public static void main (String[] args) {
		double n;

		if	(args.length != 2)  {
			System.out.println("Uso: java SpeculateClient <maquina> <nome>");
			System.exit(1);
		}
		try {
			SpeculateInterface speculate = (SpeculateInterface) Naming.lookup ("//"+args[0]+"/Notas");
			speculate.Registra()
			
		} catch (Exception e) {
			System.out.println ("SpeculateClient falhou.");
			e.printStackTrace();
		}
	}

}
