package Modelos;

import java.util.ArrayList;

public class Tabuleiro {

	private int bolasNoCentro = 0;
	private boolean[] bolasNasCasas = new boolean[6];

	/**
	 * Inicia um novo tabuleiro com as casas 1, 3 e 5 preenchidas.
	 */
	public Tabuleiro() {
		colocaBolaNaCasa(1);
		colocaBolaNaCasa(3);
		colocaBolaNaCasa(5);
	}
	
	public void colocaBolaNaCaneleta() {
		bolasNoCentro++;
	}
	
	public boolean temBolaNaCasa(int casa) {
		if (casa < 1 || casa > 6)
			throw new IllegalArgumentException("Numero da casa deve ser de 1 a 5.");
		return bolasNasCasas[casa - 1];
	}
	
	public void colocaBolaNaCasa(int casa) {
		if (casa < 1 || casa > 6)
			throw new IllegalArgumentException("Numero da casa deve ser de 1 a 5.");
		if (temBolaNaCasa(casa)) {
			throw new IllegalArgumentException("Ja tem uma bola na casa.");
		}
		else {
			bolasNasCasas[casa - 1] = true;	
		}
	}
	
	public void tiraBolaDaCasa(int casa) {
		if (casa < 1 || casa > 6)
			throw new IllegalArgumentException("Numero da casa deve ser de 1 a 5.");
		if (!temBolaNaCasa(casa)) {
			throw new IllegalArgumentException("Nao ha bola nesta casa.");
		}
		else {
			bolasNasCasas[casa - 1] = false;	
		}
	}
	
	@Override
	public String toString() {
		// Monta a representacao das casas.
		String casas = "";
		for (int i = 1; i < bolasNasCasas.length; i++) {
			if (temBolaNaCasa(i)) {
				casas = casas + "*";
			}
			else {
				casas = casas + i;
			}
		}
		casas = casas + "6";
		return casas;
	}
	
}
