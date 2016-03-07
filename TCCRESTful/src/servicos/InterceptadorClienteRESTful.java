package servicos;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class InterceptadorClienteRESTful {
	
	public String adicionaRecurso(String stringObjetoJSON, String url, String formatoDeDados) throws IOException, HttpException{
		if( (formatoDeDados != "json") && (formatoDeDados != "xml") ) formatoDeDados = "json";
		
		PostMethod  postMethod = new PostMethod(url);
		RequestEntity  entity = new InputStreamRequestEntity(new ByteArrayInputStream(stringObjetoJSON.getBytes()), "application/"+formatoDeDados);

		postMethod.setRequestEntity(entity);
		HttpClient client = new HttpClient();
		
		String retorno = "0";
		try {
			int result = client.executeMethod(postMethod);
			if(formatoDeDados == "json") {
				retorno = "{"+
								"\"codigoResposta\": '"+ result+"',";
			retorno += 			"\"headersRespostaCabecalhos\":[";
			Header[] headers = postMethod.getResponseHeaders();
			for(int i = 0; i < headers.length; i++) retorno += (i != (headers.length-1)) ? 
																"{\"header\":\""+headers.clone()[i].toString()+"\"}," 
																: 
																"{\"header\":\""+headers.clone()[i].toString()+"\"}";
			retorno += 							 			  "]"+
						   "}";
			} else {
				retorno = "<?xml version=\"1.0\"?> ";
				retorno +=  "<codigoResposta>"+result+"</codigoResposta>";
				retorno += "<headersRespostaCabecalhos>";
				Header[] headers = postMethod.getResponseHeaders();
				for(int i = 0; i < headers.length; i++) retorno += (i != (headers.length-1)) ? 
								"<header>"+headers.clone()[i].toString()+"</header>" 
								: 
								"<header>"+headers.clone()[i].toString()+"</header>";
				retorno += "</headersRespostaCabecalhos>";
				
			}
		}
		finally {
			postMethod.releaseConnection();
		}
		System.out.println("InterceptadorClienteRESTful=>adicionaRecurso: \n"+retorno+"\n");
		return retorno;
	}

	public String obterRecurso(String url, String formatoDeDados) {
//		if( (formatoDeDados != "json") && (formatoDeDados != "xml") && 
//			(formatoDeDados != "html") && (formatoDeDados != "text") ) formatoDeDados = "json";
		String retorno = "null";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = null;
			if (formatoDeDados == "html") response = webResource.accept("text/"+formatoDeDados).get(ClientResponse.class);
			else if(formatoDeDados == "text") response = webResource.accept("text/plain").get(ClientResponse.class);
			else if((formatoDeDados == "json") || (formatoDeDados == "xml")) response = webResource.accept("application/"+formatoDeDados).get(ClientResponse.class);
			else response = webResource.accept(formatoDeDados).get(ClientResponse.class);
			
			int result = response.getStatus();
			
			if ( ((formatoDeDados == "xml") || (formatoDeDados == "application/xml")) && (response.getStatus() != 200) ) {
				retorno = "<?xml version=\"1.0\"?> ";
				retorno +=  "<codigoResposta>"+result+"</codigoResposta>";
				return retorno;
			} else if (response.getStatus() != 200) {
				return "{\"codigoResposta\":\""+String.valueOf(result)+"\"}";
			}
			retorno = response.getEntity(String.class);
			System.out.println("InterceptadorClienteRESTful=>obterRecurso: \n"+retorno+"\n");
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("InterceptadorClienteRESTful=>obterRecurso: \n"+retorno+"\n");
		return retorno;
	}
	
	
	public String atualizarRecurso(String url, String formatoDeDados, String stringObjetoJSON)  throws IOException, HttpException{
		if( (formatoDeDados != "json") && (formatoDeDados != "xml") ) formatoDeDados = "json";

		PutMethod putMethod = new PutMethod(url);
		RequestEntity entity = new InputStreamRequestEntity( new ByteArrayInputStream(stringObjetoJSON.getBytes()), "application/"+formatoDeDados);
		
		putMethod.setRequestEntity(entity);
		putMethod.getRequestEntity();
		
		HttpClient client = new HttpClient();
		String retorno = "0";
		try {
			int result = client.executeMethod(putMethod);
			if(formatoDeDados == "json") {
				retorno = "{"+
							"\"codigoResposta\": '"+ result+"',";
				retorno += 	"\"headersRespostaCabecalhos\":[";
				Header[] headers = putMethod.getResponseHeaders();
				for(int i = 0; i < headers.length; i++) retorno += (i != (headers.length-1)) ? 
															"{\"header\":'"+headers[i].toString()+"'}," 
															: 
															"{\"header\":\""+headers[i].toString()+"\"}";
				retorno += 								 "]"+
						   "}";
			} else {
				retorno = "<?xml version=\"1.0\"?> ";
				retorno +=  "<codigoResposta>"+result+"</codigoResposta>";
				retorno += "<headersRespostaCabecalhos>";
				Header[] headers = putMethod.getResponseHeaders();
				for(int i = 0; i < headers.length; i++) retorno += (i != (headers.length-1)) ? 
								"<header>"+headers.clone()[i].toString()+"</header>" 
								: 
								"<header>"+headers.clone()[i].toString()+"</header>";
				retorno += "</headersRespostaCabecalhos>";
			}
			System.out.println("InterceptadorClienteRESTful=>atualizarRecurso: \n"+retorno+"\n");
			return retorno;
		} finally{
			putMethod.releaseConnection();
		}
	}
	
	public String excluirRecurso(String url) throws HttpException, IOException{
		DeleteMethod deleteMhetod = new DeleteMethod(url);
		HttpClient client = new HttpClient();
		String retorno = "0";
		try {
			int result = client.executeMethod(deleteMhetod);
			retorno = "{"+
							"\"codigoResposta\": '"+ result+"',";
			retorno += 		"\"headersRespostaCabecalhos\":[";
			Header[] headers = deleteMhetod.getResponseHeaders();
			for(int i = 0; i < headers.length; i++) retorno += (i != (headers.length-1)) ? 
															"{\"header\":'"+headers[i].toString()+"'}," 
															: 
															"{\"header\":\""+headers[i].toString()+"\"}";
			retorno += 									 "]"+
					   "}";
			System.out.println("InterceptadorClienteRESTful=>excluirRecurso: \n"+retorno+"\n");
			return retorno;
		} finally {
			deleteMhetod.releaseConnection();
		}
	}

	
}







/*
//200 OK
//Essa resposta indica que a requisição foi bem sucedida. 
if(result == 200) { //sucesso.
} 
//201 criação OK
//Este indica que a requisição e a criação de um recurso foi bem sucedidas. 
//É utilizado para confirmar se as requisições PUT ou POST foram bem sucedidas.
else if(result == 201) { //criação OK.
}
//204 = nenhum conteudo.
//400 solicitação inválida
//A requisição não foi feita corretamente. Isso acontece especialmente com requisições POST e PUT, 
//quando as informações não são validadas, ou estão no formato errado.
else if(result == 400) { //solicitação inválida.
}
//401 não autorizado
//Esse erro indica que você precisa primeiro estar autenticado para acessar o recurso.
else if(result == 401) { //não autorizado.
}
//404 página não encontrada
//Essa resposta indica que o recurso requisitado não pode ser encontrado. 
//Essa reposta é geralmente utilizada para as requisições que apontam para uma URL inexistente.
else if(result == 404) { //página não encontrada.
}
//405 método não permitido
//O método HTTP utilizado não é suportado por este recurso.
else if(result == 405) { //método não permitido.
}
//409 conflito
//Este indica um conflito na requisição. 
//Por exemplo, você está utilizando uma requisição PUT para criar o mesmo recurso duas vezes.
else if(result == 409) { //conflito.
}
//500 erro interno do servidor
//Quando todos os outros falham; geralmente, 
//o código de reposta 500 é apresentado quando o processamento falha devido a circunstâncias do servidor, 
//o que causa erro no servidor.
else if(result == 500) { //erro interno do servidor.
}
*/