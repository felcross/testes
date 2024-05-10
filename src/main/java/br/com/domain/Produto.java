package br.com.domain;

public class Produto {


	private Long id;
	private String codigoDeBarra;
	private String produto;
	
	
	public Produto(Long id, String codigoDeBarra, String produto) {
		this.id = id;
		this.codigoDeBarra = codigoDeBarra;
		this.produto = produto;
	}
	public Produto() {
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}
	public void setCodigoDeBarra(String codigo) {
		this.codigoDeBarra = codigo;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String nome) {
		this.produto = nome;
	}
	
	
	
	
}
