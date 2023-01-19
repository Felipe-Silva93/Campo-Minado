package br.com.felipe.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.felipe.execao.ExplosaoException;

public class CampoTeste {
	//private Campo campo = new Campo(3,3); chamando o @BeforeEach ele estancia esse objeto para cada methodo
	
	Campo campo;
	@BeforeEach
	void iniciarCampo() {
		campo =  new Campo(3,3);
	
	} 
	
	@Test
	void testeVizinhoAEsquerda(){
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoADireita(){
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoEmCima(){
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoEmBaixo(){
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoNaDiagonal(){
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeNaoVizinho(){
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	@Test
	void testeAlternarMarcacao() {
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAlterMarcacaoEsperandotrueComoResposta() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
	}
	@Test
	void testeAlterMarcacaoEsperandoFalseComoResposta() {
		campo.alterarMarcacao();//ele marca
		campo.alterarMarcacao();//desmarca e retorna false

		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirCampoNaoMinado() {
		
		
		assertTrue(campo.abrir());
	}
	@Test
	void testeAbrirCampoNaoMinadoMarcado() {
		campo.alterarMarcacao();//ele marca

		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirCampoMinadoEMarcado() {
		campo.alterarMarcacao();//ele marca
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirCampoMinadoENaoMarcado() {
		
		campo.minar();
		assertThrows(ExplosaoException.class, ()->{campo.abrir();});//testa se a exploção deu certo como esperado
	}
	
	@Test
	void testeAbrirCampoComVisinho() {
		
		Campo vizinho1 = new Campo(2,2);
		Campo vizinhoDoVizinho1 = new Campo(1,1);
		
		vizinho1.adicionarVizinho(vizinhoDoVizinho1);
		
		campo.adicionarVizinho(vizinho1);
		
		
		
		
		campo.abrir();
		assertTrue(vizinho1.isAberto() && vizinhoDoVizinho1.isAberto());
	}
	
	@Test
	void testeAbrirCampoComVisinho2() {
		
		Campo vizinho1 = new Campo(1,1);
		Campo vizinhoDoVizinho1 = new Campo(1,1);
		vizinho1.minar();
		
		Campo campo22 = new Campo(2,2);
		campo22.adicionarVizinho(vizinho1);
		campo22.adicionarVizinho(vizinhoDoVizinho1);

		campo.adicionarVizinho(campo22);
		
		
		
		
		campo.abrir();
		assertTrue(campo22.isAberto() && vizinhoDoVizinho1.isFechado());
	}

}
