package br.com.dao;

import java.util.List;

import br.com.domain.Cliente;

public interface IClienteDAO {

	public Integer cadastrar(Cliente cli) throws Exception;
	public Integer atualizar(Cliente cli) throws Exception;
	public Cliente buscar(String code) throws Exception;
	public List<Cliente> buscarTodos() throws Exception;
	public Integer excluir(Cliente cli) throws Exception;
	

}
