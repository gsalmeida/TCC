package cliente;

import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.httpclient.HttpException;
import com.google.gson.Gson;
import model.Produto;
import servicos.InterceptadorClienteRESTful;

public class EditarProduto {
	public static void main(String[] args) throws HttpException, IOException {
		InterceptadorClienteRESTful interceptadorClienteRESTful;	
		interceptadorClienteRESTful = new InterceptadorClienteRESTful();
		int id = 0;
		try { 
			id =  Integer.parseInt(JOptionPane.showInputDialog("Informe o id do produto: "));
		} catch (NumberFormatException e) {  
		    System.out.println(e.getMessage());
		}
		Produto p1 = new Produto();
		p1.setNome("GM VECTRA GT 2009/2009");
		p1.setPreco(30800.0);
		p1.setDataDeAtualizacao(new Date());
		String retorno = "";
		Gson gson = new Gson();
		retorno = interceptadorClienteRESTful.atualizarRecurso(
					"http://localhost:8080/TCCRESTful/rest/produtos/"+id, "json", gson.toJson(p1));
		System.out.println("Retorno do método PUT (ID = "+id+"):\n"+retorno + "\n");
	}
}