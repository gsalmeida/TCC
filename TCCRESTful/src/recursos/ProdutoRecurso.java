package recursos;

import javax.ws.rs.Path;
import servicos.BaseDeServicoInterface;
import servicos.InterceptadorClienteRESTful;

@Path("/produtos")
public class ProdutoRecurso implements BaseDeServicoInterface {
	
	private InterceptadorClienteRESTful interceptadorClienteRESTful;
	
	public ProdutoRecurso() {
		super();
	}
	
	@Override
	public String create(String objetoJSONComDados) {
		try {
			interceptadorClienteRESTful = new InterceptadorClienteRESTful();
			return interceptadorClienteRESTful.adicionaRecurso(objetoJSONComDados, 
																"http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/", 
																"json");
		} catch (Exception e) {
			e.printStackTrace();
			return "null";
		}
	}
	
	@Override
	public String get(Long id) {
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		return interceptadorClienteRESTful.obterRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/"+id, "json");
	}
	
	@Override
	public String get() {
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		return interceptadorClienteRESTful.obterRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/", "json");
	}
	
	@Override
	public String update(Long id, String objetoJSONComDados) {
		try {
			interceptadorClienteRESTful = new InterceptadorClienteRESTful();
			return interceptadorClienteRESTful.atualizarRecurso(
																"http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/"+id, 
																"json", objetoJSONComDados);
		} catch (Exception e) {
			e.printStackTrace();
			return "null";
		}
	}
	
	@Override
	public String delete(Long id) {
		try {
			interceptadorClienteRESTful = new InterceptadorClienteRESTful();
			return interceptadorClienteRESTful.excluirRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/"+id+"/");
		} catch (Exception e) {
			e.printStackTrace(); return "null";
		}
	}
	
	@Override
	public String getJSON() {
		return get();
	}
	@Override
	public String getJSON(Long id) {
		return get(id);
	}
	
	@Override
	public String getXML() {
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		return interceptadorClienteRESTful.obterRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/xml", "xml");
	}
	@Override
	public String getXML(Long id) {
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		return interceptadorClienteRESTful.obterRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/xml/"+id, "xml");
	}
	
	@Override
	public String getHTML() {
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		return interceptadorClienteRESTful.obterRecurso("http://localhost:8080/TCCRESTfulImplementacao/rest/produtosI/html", "html");
	}
	@Override
	public String getHTML(Long id) {
		return "";
	}
	
	@Override
	public String getText() {
		return "";
	}
	@Override
	public String getText(Long id) {
		return "";
	}
	
}

