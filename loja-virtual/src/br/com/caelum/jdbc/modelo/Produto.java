package br.com.caelum.jdbc.modelo;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[produto: %d %s %s]", id, nome, descricao);
	}
	
}
