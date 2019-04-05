package Modelos;
import java.util.ArrayList;

public class Jogador {

	private int id;
	private String nome;
	private ArrayList<Bola> bolas;
	
	public Jogador(int id, String nome) {
		setId(id);
		setNome(nome);
		this.bolas = new ArrayList<Bola>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void recebeBola(Bola bola) {
		bolas.add(bola);
	}
	
	public Bola usaBola() {
		return bolas.remove(0);
	}

}
