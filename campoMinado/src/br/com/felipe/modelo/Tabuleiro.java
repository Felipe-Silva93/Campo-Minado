package br.com.felipe.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.felipe.execao.ExplosaoException;

public class Tabuleiro {//testar toda a class

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo>campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		gerarCampos();
		associarOsVizinhos();
		sortearAsMinas();
	}
	
	public void abrir(int linhas, int colunas) {
		try{
			
		 campos.parallelStream()		
			.filter(c->c.getLinha() == linhas && c.getColuna()==colunas)
			.findFirst()//função terminadora retornando um optional
			.ifPresent(c->c.abrir());//s tiver presente faça tal coisa
		}catch(ExplosaoException e) {
			campos.forEach(c-> c.setAberto(true));
			throw e;
		}
	}
	public void alterarMarcacao(int linhas, int colunas) {
		campos.parallelStream()
		.filter(c->c.getLinha() == linhas && c.getColuna()==colunas)
		.findFirst()//função terminadora retornando um optional
		.ifPresent(c->c.alterarMarcacao());//s tiver presente faça tal coisa
	}
	
	//esse metodo vai definir quem pode ser vizinho de acordo com a proximidade
	private void associarOsVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2:campos) {
				c1.adicionarVizinho(c2);
				
			}
		}
		
	}

	private void gerarCampos() {
		for(int l = 0; l<linhas;l++) {
			for(int c = 0;c<colunas; c++) {
				campos.add(new Campo(l,c));
			}
		}
		
	}
	private void sortearAsMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c ->c.isMinado();
		
		do {
			
			int aleatorio = (int)(Math.random()*campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		}while(minasArmadas<minas);
	}
	
	public boolean objetivoAlcancado() {//testar
		
		
		return campos.stream().allMatch(c -> c.objetivoAlcancado());//se ao percorrer campos ver que para cada atributo deu Match o objetivo vou alcançado por que vai retornar true
	}
	
	public void reiniciar() {
		campos.stream().forEach(c->c.reiniciar());
		sortearAsMinas();
	}
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for(int colu= 0; colu<colunas; colu++) {
			sb.append(" ");
			sb.append(colu);
			sb.append(" ");
			
		}
		sb.append("\n");
		
		int i= 0;

		for(int l = 0; l < linhas; l++ ) {
			sb.append(l);
			sb.append(" ");
			for(int c = 0; c<colunas; c++){
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");		
			}
		
		return sb.toString();		
	}
	
}
