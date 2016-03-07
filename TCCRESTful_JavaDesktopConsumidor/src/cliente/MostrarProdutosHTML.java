package cliente;

import servicos.InterceptadorClienteRESTful;

public class MostrarProdutosHTML {
	public static void main(String[] args) {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		String retorno = "";
		retorno = interceptadorClienteRESTful.obterRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/html/", "html");
		System.out.println("Retorno do método GET (/html):\n"+retorno + "\n");
	}
}
