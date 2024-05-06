package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.domain.Cliente;
import br.com.jdbc.ConnectionFactory;

public class ClienteDAO implements IClienteDAO {

	@Override
	public Integer cadastrar(Cliente cli) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		
	    try {
	    	connection = ConnectionFactory.getConnection();
	    	String sql = getSqlInsert();
	    	stm =  connection.prepareStatement(sql);
	    	adicionarParametrosInsert(stm,cli);
	    	return stm.executeUpdate();
	    }
	    catch(Exception e) {
	    	throw e;
	    } finally {
	    	closeConnection(connection,stm,null);
	    	
	    }
	    
		
	};


	@Override
	public Integer atualizar(Cliente cli) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		 try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlUpdate();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosUpdate(stm,cli);
		    	return stm.executeUpdate();
		    	
		    }
		    catch(Exception e) {
		    	throw e;
		    } finally {
		    	closeConnection(connection,stm,null);
		    	
		    }
			
	};

	@Override
	public Cliente buscar(String code) throws Exception  {
		
		System.out.println(code+"<--- codigo");
		Connection connection = null;
		PreparedStatement stm = null;
		Cliente cli = null;
		ResultSet rs = null;
		 try {
			  connection = ConnectionFactory.getConnection();
		    	String sql = getSqlSelect();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosSelect(stm,code);
		    	rs = stm.executeQuery();
		    	
		    	if(rs.next()) {
		    		cli = new Cliente();
		    		Long id = rs.getLong("ID");
		    		String cd= rs.getString("CODIGO");
		    		String nome = rs.getString("NOME");
		    		cli.setId(id);
		    		cli.setCodigo(cd);
		    		cli.setNome(nome);
		    	}  	
		    }
		    catch(Exception e) {
		    	throw e;
		    }finally {
		    	closeConnection(connection,stm,rs);
		    	 }
	  return cli;	    	
	};

	
	@Override
	public Integer excluir(Cliente cli) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		 try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlDelete();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosDelete(stm,cli);
		    	return  stm.executeUpdate();
		    	
		    }
		    catch(Exception e) {
		    	throw e;
		    } finally {
		    	closeConnection(connection,stm,null);
		    	
		    }
		
		
	};
	
	
	@Override
	public List<Cliente> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		Cliente cli = null;
		ResultSet rs = null;
		List<Cliente> list = new ArrayList<>();
		try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlSelectAll();
		    	stm =  connection.prepareStatement(sql);
		    	rs = stm.executeQuery();
		    	
		    	while(rs.next()) {
		    		cli = new Cliente();
		    		Long id = rs.getLong("ID");
		    		String nome = rs.getString("NOME");
		    		String cd= rs.getString("CODIGO");
		    		cli.setId(id);
		    		cli.setCodigo(cd);
		    		cli.setNome(nome);
		    		list.add(cli);
		    	}
		    }
		    catch(Exception e) {
		    	throw e;
		    } finally {
		    	closeConnection(connection,stm,rs);
		    	
		    }
		return list;
	};
	
	
	private String getSqlInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_CLIENTE (ID,CODIGO,NOME)");
		sb.append("VALUES (nextval('TB_CLIENTE_ID_SEQ'),?,?)");
		return sb.toString();
	};
	
	
	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_CLIENTE");
		return sb.toString();
	};
	
	
	private void adicionarParametrosInsert(PreparedStatement stm,Cliente cli) throws SQLException {
		 stm.setString(1,cli.getCodigo());
		 stm.setString(2,cli.getNome());
		};	
	
	
	
	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_CLIENTE");
		sb.append(" WHERE CODIGO = ?");
		return sb.toString();
	};
	
	private void adicionarParametrosSelect(PreparedStatement stm,String codigo) throws SQLException {
	 stm.setString(1,codigo);
	};
	
	
	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_CLIENTE");
		sb.append(" SET NOME = ?, CODIGO = ?");
		sb.append("WHERE ID = ?");
		return sb.toString();
	}; 
	
	private void adicionarParametrosDelete(PreparedStatement stm,Cliente cli) throws SQLException {
		 stm.setString(1,cli.getCodigo());
		
		}
	
	
	private String getSqlDelete() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM TB_CLIENTE");
		sb.append(" WHERE CODIGO = ?");
		return sb.toString();
	};
	
	private void adicionarParametrosUpdate(PreparedStatement stm,Cliente cli) throws SQLException {
		 stm.setString(1,cli.getCodigo());
		 stm.setString(2,cli.getNome());
		 stm.setLong(3,cli.getId());
		}
	
	
	
	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws SQLException {
		
		 try {
			 if(rs != null && !rs.isClosed()) {
				 rs.close();
			 }
			 if(stm != null && !stm.isClosed()) {
				 stm.close();
			 }
			 if(connection != null && !connection.isClosed()) {
				 connection.close();
			 }
						 
		 } 
		 catch(Exception e1) {
			 throw e1;
		 }
		
		
	}



}



