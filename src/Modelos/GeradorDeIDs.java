package Modelos;

import java.util.concurrent.Semaphore;

public class GeradorDeIDs {
	
	private int idAtual = 0;
	private Semaphore semaforo;
	private String nomeDoGerador;
	
	public GeradorDeIDs() {
		semaforo = new Semaphore(1);
	}
	
	public GeradorDeIDs(String nomeDoGerador) {
		semaforo = new Semaphore(1);
		this.nomeDoGerador = nomeDoGerador;
	}
	
	public int getProximoId() {
		try {
			semaforo.acquire();
			return idAtual++;
		}
		catch(Exception e) {
			String msg = "Falha ao gerar um novo ID";
			if (nomeDoGerador != "") {
				msg += " (" + nomeDoGerador + ")";
			}
			System.out.println(msg);
			e.printStackTrace();
			return -1;
		}
		finally {
			semaforo.release();
		}
	}
}
