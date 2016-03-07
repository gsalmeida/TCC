package cliente;

import java.io.IOException;
import org.apache.commons.httpclient.HttpException;
import servicos.InterceptadorClienteRESTful;
public class AdicionarProdutos {
	public static void main(String[] args) throws HttpException, IOException {
		InterceptadorClienteRESTful interceptadorClienteRESTful;	
		String objetoJSONVariosProdutos = 
				"[ {" + "\"nome\" : \"CBR 600 RR 2008\"," + "\"preco\" : 33000.0" + "	}, "
				+ "{\"nome\" : \"CB 600F HORNET 2007 LARANJA\"," + "\"preco\" : 23000.0" + "	} , "
				+ "{\"nome\" : \"CB 500R 2016 BRANCA\"," + "\"preco\" : 29800.0" + "	} " + "]";
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		String retorno = "";
		retorno = interceptadorClienteRESTful.adicionaRecurso(objetoJSONVariosProdutos,
				"http://localhost:8080/TCCRESTful/rest/produtos/", "json");
		System.out.println("Retorno do método POST (vários produtos):\n" + retorno + "\n");
	}
}