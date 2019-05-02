package Modelos;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	private class Opcao {
		public int Numero;
		public String Texto;
		
		public Opcao(int numero, String texto) {
			Numero = numero;
			Texto = texto;
		}
		
		@Override
		public String toString() {
			return Numero + " - " + Texto;
		}
	}
	
	private static ArrayList<Opcao> opcoes;
	private Scanner entrada;
	
	public Menu(Scanner entrada) {
		this.entrada = entrada;
		
		opcoes = new ArrayList<Opcao>();
		opcoes.add(new Opcao(2, "encerraPartida"));
		opcoes.add(new Opcao(3, "temPartida"));
		opcoes.add(new Opcao(4, "obtemOponente"));
		opcoes.add(new Opcao(5, "ehMinhaVez"));
		opcoes.add(new Opcao(6, "obtemNumBolas"));
		opcoes.add(new Opcao(7, "obtemNumBolasOponente"));
		opcoes.add(new Opcao(8, "obtemTabuleiro"));
		opcoes.add(new Opcao(9, "defineJogadas"));
		opcoes.add(new Opcao(10, "jogaDado"));
	}
	
	public int apresenta() {
		System.out.println(this.toString());
		return aguardaAcao();
	}
	
	public int aguardaAcao() {
		System.out.println("Selecione a sua proxima acao. Digite o numero desejado e pressione 'enter'.");
		int acao = -1;
		do {
			acao = entrada.nextInt();
		}
		while (acao < 2 || acao > 10);
		
		return acao;
	}
	
	@Override
	public String toString() {
		String gui = "Menu de opcoes:\n";
		
		for (int i = 0; i < opcoes.size(); i++) {
			gui += opcoes.get(i).toString() + "\n";
		}
		
		return gui;
	}
}
