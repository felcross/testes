package br.com.domain;

import org.junit.Test;

import br.com.fel.domain.Curso;

public class CursoTest {

	@Test
	public void cadastras() {
		Curso curso = new Curso();
		curso.setCodigo("A1");
		curso.setDescricao("curso teste");
		curso.setNome("curso java ");
		
		
	}
	
	
}
