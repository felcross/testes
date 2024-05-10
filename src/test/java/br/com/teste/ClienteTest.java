package br.com.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import java.util.List;

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
		
	
		Cliente cliBD = clienteDao.buscar("10");
		System.out.println(cliBD.getCodigo());
	 	assertNotNull(cliBD);
		assertEquals(cli.getCodigo(),cliBD.getCodigo());
		assertEquals(cli.getNome(),cliBD.getNome()); 
		
		Integer countDel = clienteDao.excluir(cli); 
		assertTrue(countDel == 1);
		System.out.println();
		}
	

	@Test
	public void buscarTodosTest() throws Exception {
       clienteDao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setCodigo("10");
		cli.setNome("Teste01");
		System.out.println(cli.getCodigo()+"primeiro teste");
		Integer countCad = clienteDao.cadastrar(cli);		
		assertTrue(countCad == 1);
		
		
		
		Cliente cli2 = new Cliente();
		cli2.setCodigo("10");
		cli2.setNome("Teste01");
		System.out.println(cli.getCodigo()+"segundo teste");
		Integer countCad2 = clienteDao.cadastrar(cli2);		
		assertTrue(countCad2 == 1);
		
		/*Cliente cli2 = new Cliente();
		 cli2 = clienteDao.buscar("10");
		System.out.println(cli2.getCodigo()+"segundo teste");
		assertEquals("10",cli2.getCodigo());*/
		
		List<Cliente> clientes = clienteDao.buscarTodos();
		assertNotNull(clientes);
		assertEquals(2,clientes.size());
		int countdel= 0;
		for (Cliente cliente : clientes ) {
			clienteDao.excluir(cliente);
			countdel++;
		}
		assertEquals(clientes.size(),countdel);
		clientes = clienteDao.buscarTodos();
		assertEquals(clientes.size(),0);
		
	}	
	
	
	@Test
	public void atualizarTest() throws Exception {
       clienteDao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setCodigo("10");
		cli.setNome("Teste01");
		System.out.println(cli.getCodigo()+"primeiro teste");
		Integer countCad = clienteDao.cadastrar(cli);		
		assertTrue(countCad == 1);
		
		Cliente cli2 = clienteDao.buscar("10");
		assertNotNull(cli2);
		assertEquals(cli.getCodigo(),cli2.getCodigo());
		assertEquals(cli.getNome(),cli2.getNome()); 
		
		cli2.setCodigo("20");
		cli2.setNome("Teste99");
		System.out.println(cli2.getCodigo()+"mudou");
		Integer countUpdate = clienteDao.cadastrar(cli2);		
		assertTrue(countUpdate == 1);
		
		Cliente cliBD1 = clienteDao.buscar("10");
		System.out.println(cliBD1+"nulo?");
		assertNotNull(cliBD1);
		
		
		Cliente cliBD2 = clienteDao.buscar("20");
		assertNotNull(cliBD2);
		assertEquals(cli2.getCodigo(),cliBD2.getCodigo());
		assertEquals(cli2.getNome(),cliBD2.getNome());
		//assertEquals(cli2.getId(),cliBD2.getId());
		
		
		
		List<Cliente> list = clienteDao.buscarTodos();	    
		for (Cliente cliente : list ) {
			clienteDao.excluir(cliente);
			
		}
	
		
	}	
	
	
	
	
	
	
	

}
