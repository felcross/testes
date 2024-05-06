package br.com.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.dao.ClienteDAO;
import br.com.domain.Cliente;

public class ClienteTest {
	
	private ClienteDAO clienteDao;
	
	@Test
	public void cadastrarTest() throws Exception {
		clienteDao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setCodigo("10");
		cli.setNome("clienteTeste");
		System.out.println(cli.getCodigo()+"primeiro teste");
		Integer countCad = clienteDao.cadastrar(cli);		
		assertTrue(countCad == 1);
		
	
		/*Cliente cliBD = clienteDao.buscar("10");
		System.out.println(cliBD.getCodigo());
	 	assertNull(cliBD);
		assertEquals(cli.getCodigo(),cliBD.getCodigo());
		assertEquals(cli.getNome(),cliBD.getNome()); */
		
		/*Integer countDel = clienteDao.excluir(cli);
		assertTrue(countDel == 1);
		System.out.println();*/ 
		
		
	}
	
	
	@Test
	public void buscarTest() throws Exception {
		clienteDao = new ClienteDAO();
		
		//Cliente cli = new Cliente();
		/*cli.setCodigo("10");
		cli.setNome("clienteTeste");
		System.out.println(cli.getCodigo()+"primeiro teste");
		Integer countCad = clienteDao.cadastrar(cli);		
		assertTrue(countCad == 1);*/
		Cliente cli = new Cliente();
		 cli = clienteDao.buscar("10");
		System.out.println(cli.getCodigo()+"correto");
	 	//assertNull(cli);
		assertEquals("10",cli.getCodigo());
		//assertEquals(cli.getNome(),cliBD.getNome());
		
		/*Integer countDel = clienteDao.excluir(cli);
		assertTrue(countDel == 1);
		System.out.println();*/
		
		
	}
	
	
	
	
	
	

}
