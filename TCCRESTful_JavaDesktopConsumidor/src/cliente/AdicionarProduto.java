package cliente;

import java.io.IOException;
import java.util.Date;
import org.apache.commons.httpclient.HttpException;
import com.google.gson.Gson;
import model.Produto;
import servicos.InterceptadorClienteRESTful;

public class AdicionarProduto {
	public static void main(String[] args) throws HttpException, IOException {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		Produto p2 = new Produto();
		p2.setNome("HONDA CIVIC 2012/2013");
		p2.setPreco(29500.0);
		p2.setDataDeCriacao(new Date());
		p2.setDataDeAtualizacao(new Date());		
		Gson gson = new Gson();
		String p2JSON = "[" + gson.toJson(p2) + "]";
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		String retorno = "";
		retorno = interceptadorClienteRESTful.adicionaRecurso(
				p2JSON, "http://localhost:8080/TCCRESTful/rest/produtos/", "json");
		System.out.println("Retorno do método POST:\n"+retorno + "\n");
	}
}
