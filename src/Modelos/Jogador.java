package Modelos;
import java.util.ArrayList;

public class Jogador {

	private int id;
	private String nome;
	private int bolasEmMao;

	/**
	 * Inicia um novo jogador com 15 bolas em mãos.
	 * @param id
	 * @param nome
	 */
	public Jogador(int id, String nome) {
		this.setId(id);
		this.setNome(nome);
		this.setBolasEmMao(15);
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

	public int getBolasEmMao() {
		return bolasEmMao;
	}

	public void setBolasEmMao(int bolasEmMao) {
		this.bolasEmMao = bolasEmMao;
	}
	
	public void incrementaBolasEmMaos(int numeroDeBolasAIncrementar) {
		int bolasEmMaosAtualmente = this.getBolasEmMao();
		int novoNumero = bolasEmMaosAtualmente + numeroDeBolasAIncrementar;
		this.setBolasEmMao(novoNumero);
	}
	
	public void decrementaBolasEmMaos(int numeroDeBolasADecrementar) {
		int bolasEmMaosAtualmente = this.getBolasEmMao();
		int novoNumero = bolasEmMaosAtualmente - numeroDeBolasADecrementar;
		if (novoNumero < 0)
			throw new IllegalArgumentException("Nao eh possivel remover tantas bolas pois o jogador nao possui tantas.");
		else
			this.setBolasEmMao(novoNumero);
	}
}
