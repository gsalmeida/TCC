package cliente;

import servicos.InterceptadorClienteRESTful;

public class MostrarProdutosXML {
	public static void main(String[] args) {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		String retorno = "";
		retorno = interceptadorClienteRESTful.obterRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/xml/", "xml");
		System.out.println("Retorno do método GET (/xml):\n"+retorno + "\n");
	}
}