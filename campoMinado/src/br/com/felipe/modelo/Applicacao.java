package br.com.felipe.modelo;

import br.com.felipe.visao.TabuleiroConsole;

public class Applicacao {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
		
		new TabuleiroConsole(tabuleiro);
		
	}

}
