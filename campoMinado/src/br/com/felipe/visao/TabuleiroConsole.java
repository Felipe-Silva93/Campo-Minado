package br.com.felipe.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.felipe.execao.ExplosaoException;
import br.com.felipe.execao.SairException;
import br.com.felipe.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro=tabuleiro;
		executarJogo();
	}
	
	private void executarJogo(){
		try {
			boolean continuar = true;
			while(continuar) {
				
				cicloDoJogo();
				System.out.println("outra partida? (S/n)");

				String resposta = entrada.nextLine();

				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			
			}
			
		}catch(SairException e) {
			System.out.println("vai lá, tchau");
		}finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {//enquanto for diferente de objetivo não alcançado continue
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite x e y :");
				Iterator<Integer>xy = Arrays.stream(digitado.split(","))
						.map(e->Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 para abrir ou 2 para desmarcar ou marcar: ");
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você venceu");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
			if("sair".equalsIgnoreCase(digitado)) {
				throw new SairException();
			}
			return digitado;

		}
	
}
