package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.domain.Produto;
import br.com.jdbc.ConnectionFactory;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception{
		Connection connection = null;
		PreparedStatement stm = null;
		
	    try {
	    	connection = ConnectionFactory.getConnection();
	    	String sql = getSqlInsert();
	    	stm =  connection.prepareStatement(sql);
	    	adicionarParametrosInsert(stm,produto);
	    	return stm.executeUpdate();
	    }
	    catch(Exception e) {
	    	throw e;
	    } finally {
	    	closeConnection(connection,stm,null);
	    	
	    }
	    
		
	};


	@Override
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		 try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlUpdate();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosUpdate(stm,produto);
		    	return stm.executeUpdate();
		    	
		    }
		    catch(Exception e) {
		    	throw e;
		    } finally {
		    	closeConnection(connection,stm,null);
		    	
		    }
			
	};

	@Override
	public Produto buscar(String code) throws Exception  {
		
		System.out.println(code+"<--- codigo");
		Connection connection = null;
		PreparedStatement stm = null;
		Produto produto = null;
		ResultSet rs = null;
		 try {
			  connection = ConnectionFactory.getConnection();
		    	String sql = getSqlSelect();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosSelect(stm,code);
		    	rs = stm.executeQuery();
		    	
		    	if(rs.next()) {
		    		produto = new Produto();
		    		Long id = rs.getLong("ID");
		    		String cd= rs.getString("CODIGODEBARRA");
		    		String produtoDesc = rs.getString("PRODUTO");
		    		produto.setId(id);
		    		produto.setCodigoDeBarra(cd);
		    		produto.setProduto(produtoDesc);
		    	}  	
		    }
		    catch(Exception e) {
		    	throw e;
		    }finally {
		    	closeConnection(connection,stm,rs);
		    	 }
	  return produto;	    	
	};

	
	@Override
	public Integer excluir(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		 try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlDelete();
		    	stm =  connection.prepareStatement(sql);
		    	adicionarParametrosDelete(stm,produto);
		    	return  stm.executeUpdate();
		    	
		    }
		    catch(Exception e) {
		    	throw e;
		    } finally {
		    	closeConnection(connection,stm,null);
		    	
		    }
		
		
	};
	
	
	@Override
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		Produto produto = null;
		ResultSet rs = null;
		List<Produto> list = new ArrayList<>();
		try {
			 connection = ConnectionFactory.getConnection();
		    	String sql = getSqlSelectAll();
		    	stm =  connection.prepareStatement(sql);
		    	rs = stm.executeQuery();
		    	
		    	while(rs.next()) {
		    		produto = new Produto();
		    		Long id = rs.getLong("ID");
		    		String nome = rs.getString("PRODUTO");
		    		String cd= rs.getString("CODIGODEBARRA");
		    		produto.setId(id);
		    		produto.setCodigoDeBarra(cd);
		    		produto.setProduto(nome);
		    		list.add(produto);
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
		sb.append("INSERT INTO TB_PRODUTO (ID,CODIGODEBARRA,PRODUTO)");
		sb.append("VALUES (nextval('TB_PRODUTO_ID_SEQ'),?,?)");
		return sb.toString();
	};
	
	
	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_PRODUTO");
		return sb.toString();
	};
	
	
	private void adicionarParametrosInsert(PreparedStatement stm,Produto produto) throws SQLException {
		 stm.setString(1,produto.getCodigoDeBarra());
		 stm.setString(2,produto.getProduto());
		};	
	
	
	
	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_PRODUTO");
		sb.append(" WHERE CODIGODEBARRA = ?");
		return sb.toString();
	};
	
	private void adicionarParametrosSelect(PreparedStatement stm,String codigo) throws SQLException {
	 stm.setString(1,codigo);
	};
	
	
	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_PRODUTO");
		sb.append(" SET PRODUTO = ?, CODIGODEBARRA = ?");
		sb.append("WHERE ID = ?");
		return sb.toString();
	}; 
	
	private void adicionarParametrosDelete(PreparedStatement stm,Produto produto) throws SQLException {
		 stm.setString(1,produto.getCodigoDeBarra());
		
		}  
	
	
	private String getSqlDelete() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM TB_PRODUTO");
		sb.append(" WHERE CODIGODEBARRA = ?");
		return sb.toString();
	};
	
	private void adicionarParametrosUpdate(PreparedStatement stm,Produto produto) throws SQLException {
		 stm.setString(1,produto.getCodigoDeBarra());
		 stm.setString(2,produto.getProduto());
		 stm.setLong(3,produto.getId());
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



