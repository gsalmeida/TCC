package cliente;

import javax.swing.JOptionPane;
import servicos.InterceptadorClienteRESTful;

public class MostrarProdutoJSON {
	public static void main(String[] args) {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		int id = 0;
		try{
			id =  Integer.parseInt(JOptionPane.showInputDialog("Informe o id do produto: "));
		} catch (NumberFormatException e) {  
		    System.out.println(e.getMessage());
		}
		String retorno = "";
		retorno = interceptadorClienteRESTful.obterRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/json/"+id, "json");
		System.out.println("Retorno do método GET (/json/"+id+"):\n"+retorno + "\n");
	}
}