package model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import servicos.HATEOASEntidade;
import servicos.Link;

@Entity
public class Produto extends BaseDeEntidade implements HATEOASEntidade {
	
	public Produto(Long id, String nome, double preco) {
		super.setId(id);
		this.nome = nome;
		this.preco = preco;
	}
	@Column
	private String nome;
	
	@Column
	private double preco;
	
	@Transient
	private List<Link> links = new ArrayList<>();
	
	public Produto() {}
	
	public String getNome() { return nome; }
	
	public void setNome(String nome) { this.nome = nome; }
	
	public double getPreco() { return preco; }
	
	public void setPreco(double preco) { this.preco = preco; }
	
	@Override
	public List<Link> getLinks() { return links; }
	
	@Override
	public void setLinks(List<Link> links) { for(int a = 0; a < links.size(); a++) addLink(links.get(a)); }

	@Override
	public void addLink(Link link) {
		links.add(link);
	}
	
}
