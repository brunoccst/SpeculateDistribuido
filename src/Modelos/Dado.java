package Modelos;

import java.util.Random;

public class Dado {
	
	private int resultadoAtual;
	
	public Dado() {
		this(1);
	}
	
	public Dado(int resultadoAtual) {
		this.setResultadoAtual(resultadoAtual);
	}
	
	public int getResultadoAtual() {
		return resultadoAtual;
	}

	public void setResultadoAtual(int novoResultado) {
		if (novoResultado < 1 || novoResultado > 6)
			throw new IllegalArgumentException("O valor do dado deve estar entre 1 e 6.");
		
		this.resultadoAtual = novoResultado;
	}
	
	public int jogaDado() {
		Random r = new Random();
		resultadoAtual = r.nextInt(5) + 1;
		return resultadoAtual;
	}

	
}
