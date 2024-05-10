package br.com.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.dao.ClienteDAO;
import br.com.dao.IProdutoDAO;
import br.com.dao.ProdutoDAO;
import br.com.domain.Cliente;
import br.com.domain.Produto;

public class ProdutoTest {
	
	private ProdutoDAO produtoDao;
	
	@Test
	public void cadastrarTest() throws Exception {
		produtoDao = new ProdutoDAO();   
		
		Produto produto = new Produto();
		produto.setCodigoDeBarra("10");
		produto.setProduto("clienteTeste");
		System.out.println(produto.getCodigoDeBarra()+"primeiro teste");
		Integer countCad = produtoDao.cadastrar(produto);		
		assertTrue(countCad == 1);
		
	
		Produto produtoBD = produtoDao.buscar("10");
		System.out.println(produtoBD.getCodigoDeBarra());
	 	assertNotNull(produtoBD);
		assertEquals(produto.getCodigoDeBarra(),produtoBD.getCodigoDeBarra());
		assertEquals(produto.getProduto(),produtoBD.getProduto()); 
		
		Integer countDel = produtoDao.excluir(produto); 
		assertTrue(countDel == 1);
		System.out.println();
		}
	

	@Test
	public void buscarTodosTest() throws Exception {
		produtoDao = new ProdutoDAO();
		
       Produto produto = new Produto();
       produto.setCodigoDeBarra("10");
       produto.setProduto("Teste01");
		System.out.println(produto.getCodigoDeBarra()+"primeiro teste");
		Integer countCad = produtoDao.cadastrar(produto);		
		assertTrue(countCad == 1);
		
		
		
		Produto produto2 = new Produto();
		produto2.setCodigoDeBarra("10");
		produto2.setProduto("Teste01");
		System.out.println(produto2.getCodigoDeBarra()+"segundo teste");
		Integer countCad2 = produtoDao.cadastrar(produto2);		
		assertTrue(countCad2 == 1);		
		
		List<Produto> produtos = produtoDao.buscarTodos();
		assertNotNull(produtos);
		assertEquals(2,produtos.size());
		int countdel= 0;
		for (Produto produto1 : produtos ) {
			produtoDao.excluir(produto1);
			countdel++;
		}
		assertEquals(produtos.size(),countdel);
		produtos = produtoDao.buscarTodos();
		assertEquals(produtos.size(),0);
		
	}	
	
	
	@Test
	public void atualizarTest() throws Exception {
       produtoDao = new ProdutoDAO();
		
       Produto produto = new Produto();
       produto.setCodigoDeBarra("10");
       produto.setProduto("Teste01");
		System.out.println(produto.getCodigoDeBarra()+"primeiro teste");
		Integer countCad = produtoDao.cadastrar(produto);		
		assertTrue(countCad == 1);
		
		Produto produto2 = produtoDao.buscar("10");
		assertNotNull(produto2);
		assertEquals(produto.getCodigoDeBarra(),produto2.getCodigoDeBarra());
		assertEquals(produto.getProduto(),produto2.getProduto()); 
		
		produto2.setCodigoDeBarra("20");
		produto2.setProduto("Teste99");
		System.out.println(produto2.getCodigoDeBarra()+"mudou");
		Integer countUpdate = produtoDao.cadastrar(produto2);		
		assertTrue(countUpdate == 1);
		
		Produto produtoBD1 = produtoDao.buscar("10");
		System.out.println(produtoBD1+"nulo?");
		assertNotNull(produtoBD1);
		
		
		Produto produtoBD2 = produtoDao.buscar("20");
		assertNotNull(produtoBD2);
		assertEquals(produto2.getCodigoDeBarra(),produtoBD2.getCodigoDeBarra());
		assertEquals(produto2.getProduto(),produtoBD2.getProduto());
		//assertEquals(cli2.getId(),cliBD2.getId());
		
		
		
		List<Produto> list = produtoDao.buscarTodos();	    
		for (Produto produto1 : list ) {
			produtoDao.excluir(produto1);
			
		}
	
		
	}	
	
	
	
	
	
	
	

}
