package cliente;

import servicos.InterceptadorClienteRESTful;

public class MostrarProdutosJSON {
	public static void main(String[] args) {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		String retorno = "";
		retorno = interceptadorClienteRESTful.obterRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/json/", "json");
		System.out.println("Retorno do método GET (/json):\n"+retorno + "\n");
	}
}