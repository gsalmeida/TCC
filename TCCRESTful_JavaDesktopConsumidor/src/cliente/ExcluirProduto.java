package cliente;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.commons.httpclient.HttpException;
import servicos.InterceptadorClienteRESTful;

public class ExcluirProduto {
	public static void main(String[] args) throws HttpException, IOException {
		InterceptadorClienteRESTful interceptadorClienteRESTful;
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		int id = 0;
		try{
			id =  Integer.parseInt(JOptionPane.showInputDialog("Informe o id do produto: "));
		} catch (NumberFormatException e) {  
		    System.out.println(e.getMessage());
		}  
		String retorno = "";
		retorno = interceptadorClienteRESTful.excluirRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/"+id);
		System.out.println("Retorno do método DELETE(ID = "+id+"):\n"+retorno + "\n");
	}
}