package Modelos;

import java.util.ArrayList;

public class Tabuleiro {

	private ArrayList<Bola> centro;
	private Bola[] casas;
		
	public Tabuleiro() {
		centro = new ArrayList<Bola>();
		casas = new Bola[5];
	}
	
	/*
	 * Calcula o index do array de casas para o número da casa requisitado.
	 */
	private int getIdxCasa(int nroDaCasa) {
		return nroDaCasa - 1;
	}
	
	//================================================================================
    // Métodos referentes a pegar a bola de uma casa.
    //================================================================================
	
	/*
	 * Retira a bola da casa especificada e a retorna.
	 */
	private Bola pegaBolaDaCasa(int nroDaCasa) {
		int idxCasa = getIdxCasa(nroDaCasa);
		Bola bola = casas[idxCasa];
		casas[idxCasa] = null;
		return bola;
	}
	
	public Bola PegaBolaDaCasa1() {
		return pegaBolaDaCasa(1);
	}
	
	public Bola PegaBolaDaCasa2() {
		return pegaBolaDaCasa(2);
	}
	
	public Bola PegaBolaDaCasa3() {
		return pegaBolaDaCasa(3);
	}
	
	public Bola PegaBolaDaCasa4() {
		return pegaBolaDaCasa(4);
	}
	
	public Bola PegaBolaDaCasa5() {
		return pegaBolaDaCasa(5);
	}
	
	
	//================================================================================
    // Métodos referentes a colocar uma bola em uma casa.
    //================================================================================
	
	/*
	 * Coloca a bola na casa especificada.
	 */
	private void colocaBolaNaCasa(Bola bola, int nroDaCasa) {
		int idxCasa = getIdxCasa(nroDaCasa);
		casas[idxCasa] = bola;
	}
	
	public void ColocaBolaNaCasa1(Bola bola) {
		colocaBolaNaCasa(bola, 1);
	}
	
	public void ColocaBolaNaCasa2(Bola bola) {
		colocaBolaNaCasa(bola, 2);
	}
	
	public void ColocaBolaNaCasa3(Bola bola) {
		colocaBolaNaCasa(bola, 3);
	}
	
	public void ColocaBolaNaCasa4(Bola bola) {
		colocaBolaNaCasa(bola, 4);
	}
	
	public void ColocaBolaNaCasa5(Bola bola) {
		colocaBolaNaCasa(bola, 5);
	}
	
	
	//================================================================================
    // Métodos referentes a colocar a bola na canaleta.
    //================================================================================
	
	/*
	 * Coloca a bola específicada na canaleta.
	 */
	public void ColocaNaCanaleta(Bola bola) {
		centro.add(bola);
	}
	
}
