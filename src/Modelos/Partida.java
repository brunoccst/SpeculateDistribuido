package Modelos;

import java.util.Timer;

public class Partida {
	private int id;
	private Jogador[] jogadores = new Jogador[1];
	private Jogador jogadorEmAcao;
	private Tabuleiro tabuleiro;
	private Dado dado;

	/**
	 * Cria uma nova instancia de partida sendo o jogador 1 o primeiro em ação.
	 * @param id ID da partida.
	 * @param j1 Jogador 1 e primeiro a jogar.
	 * @param j2 Jogador 2 e segundo a jogar.
	 * @param tabuleiro Tabuleiro da partida.
	 */
	public Partida(int id, Jogador j1, Jogador j2, Tabuleiro tabuleiro) {
		this.setId(id);
		
		this.adicionaJogador(j1);
		this.adicionaJogador(j2);
		
		this.setJogadorEmAcao(j1);
		this.dado = new Dado();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jogador[] getJogadores() {
		return jogadores;
	}

	public void setJogadores(Jogador[] jogadores) {
		this.jogadores = jogadores;
	}
	
	public boolean adicionaJogador(Jogador jogador) {
		if (this.jogadores[0] == null) {
			this.jogadores[0] = jogador;
			return true;
		}
		else if (this.jogadores[1] == null) {
			this.jogadores[1] = jogador;
			return true;
		}
		else {
			return false; 
		}
	}
	
	public Jogador getJogador1() {
		return this.jogadores[0];
	}
	
	public Jogador getJogador2() {
		return this.jogadores[1];
	}

	public Jogador getJogadorEmAcao() {
		return jogadorEmAcao;
	}

	public void setJogadorEmAcao(Jogador jogadorEmAcao) {
		this.jogadorEmAcao = jogadorEmAcao;
	}
	
	public void trocaTurno() {
		if (this.jogadorEmAcao == getJogador1()) {
			setJogadorEmAcao(getJogador2());
		}
		else {
			setJogadorEmAcao(getJogador1());
		}
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}
