package controlador;

import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoControlador {

	ProdutoDAO produtoDAO = null;
	
	public ProdutoControlador() {
		this.produtoDAO = new ProdutoDAO();
	}
	
	public void save(Produto classeEntidade) {
		produtoDAO.save(classeEntidade);
	}
	
	public Produto find(Long id) {
		return produtoDAO.find(id);
	}
	
	public List<Produto> findAll() {
		return produtoDAO.findAll();
	}

	public void delete(Class<Produto> classeEntidade, Long id) {
		produtoDAO.delete(classeEntidade, id);
	}

	public Produto update(Produto classeEntidade) {
		return produtoDAO.update(classeEntidade);
	}
	
	public void beginTransaction() {
		produtoDAO.beginTransaction();
	}

	public void commit() {
		produtoDAO.commit();
	}

	public void rollback() {
		produtoDAO.rollback();
	}

	public void closeTransaction() {
		produtoDAO.closeTransaction();
	}

	public void commitAndCloseTransaction() {
		produtoDAO.commitAndCloseTransaction();
	}

	public void flush() {
		produtoDAO.flush();
	}

	public void joinTransaction() {
		produtoDAO.joinTransaction();
	}

	public Produto findReferenceOnly(int idEntidade) {
		return findReferenceOnly(idEntidade);
	}
	
	public Long getIdAnteriorRegistro(Long id) {
		return produtoDAO.getIdAnteriorRegistro(id);
	}
	
	public Long getIdProximoRegistro(Long id) {
		return produtoDAO.getIdProximoRegistro(id);
	}
	
	public Long getIdPrimeiroRegistro() {
		return produtoDAO.getIdPrimeiroRegistro();
	}
	
	public Long getIdUltimoRegistro() {
		return produtoDAO.getIdUltimoRegistro();
	}
	
	
}

