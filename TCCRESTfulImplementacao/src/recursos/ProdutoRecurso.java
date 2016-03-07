package recursos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Path;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.google.gson.Gson;
import model.Produto;
import controlador.ProdutoControlador;
import servicos.BaseDeServicoInterface;
import servicos.Link;

@Path("/produtosI")
public class ProdutoRecurso implements BaseDeServicoInterface {
	public ProdutoRecurso() { super(); }

	@Override
	public String create(String objetoJSONComDados) {
		String objetoJSONComDadosOriginal = objetoJSONComDados;
		objetoJSONComDados = "{produtos:"+objetoJSONComDados+"}";
		JSONObject produtosJSONObject;
		try {
			produtosJSONObject = new JSONObject(objetoJSONComDados);
			JSONArray arrayProdutosJSON = null;
			if(produtosJSONObject.getJSONArray("produtos").length() > 1) {
				arrayProdutosJSON = produtosJSONObject.getJSONArray("produtos");
				Gson gson = new Gson();
				List<Produto> listaDeProdutosConsumidos = new ArrayList<Produto>();
				for(int a = 0; a < arrayProdutosJSON.length(); a++) {
					String produtoEmJSON = arrayProdutosJSON.getString(a);
					listaDeProdutosConsumidos.add(a, gson.fromJson(produtoEmJSON, Produto.class));
				}
				
				ProdutoControlador produtoControlador = new ProdutoControlador();
				produtoControlador.beginTransaction();
				for(int a = 0; a < listaDeProdutosConsumidos.size(); a++) {
					Produto produto = listaDeProdutosConsumidos.get(a);
					produto.setDataDeCriacao(new Date());
					produto.setDataDeAtualizacao(new Date());
					produtoControlador.save(produto);	
				}
				produtoControlador.commit();
				return "200";
			} else if(produtosJSONObject.getJSONArray("produtos").length() == 1) {
				objetoJSONComDadosOriginal = objetoJSONComDadosOriginal.substring(1, (objetoJSONComDadosOriginal.length() -1) );
				ProdutoControlador produtoControlador = new ProdutoControlador();
				produtoControlador.beginTransaction();
				Gson gson = new Gson();
				Produto produto = gson.fromJson(objetoJSONComDadosOriginal, Produto.class);
				produtoControlador.save(produto);
				produtoControlador.commit();
				return "200";
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return "500";
		}
		return "500";
	}
	
	@Override
	public String get(Long id) {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		Produto produto = null;
		produto = produtoControlador.find(id);

		Long idAnterior = null;
		idAnterior = produtoControlador.getIdAnteriorRegistro(id);
		
		Long idProximo = null;
		idProximo = produtoControlador.getIdProximoRegistro(id);

		if(produto != null) {
	        produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "POST"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "PUT"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "DELETE"));
			
			if( (idAnterior != null) && (produto.getId() != idAnterior) ) 
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idAnterior, "anterior", "GET"));
			else {
				idAnterior = produtoControlador.getIdUltimoRegistro();
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idAnterior, "anterior", "GET"));
			}
			
			if( (idProximo != null) && (produto.getId() != idProximo) ) 
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idProximo, "proximo", "GET"));
			else {
				idProximo = produtoControlador.getIdPrimeiroRegistro();
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idProximo, "proximo", "GET"));
			}
			
			Gson gson = new Gson();
			return gson.toJson(produto);
		}
		return "204";			
	}
	
			
	@Override
	public String get() {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		List<Produto> listaDeProdutos = produtoControlador.findAll();
		
		int contador = 0;
		for (Produto produtoLinha : listaDeProdutos) {
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "POST"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text/"+produtoLinha.getId(), "self", "GET"));
			
			
			if (contador == 0) {
				Link link = new Link();
				link.setHref("http://dc544.4shared.com/img/_UEh72Awba/s7/1512ff005b0/CRF250L_Almeida");
				link.setRel("photo");
				link.setTipoDeMidia("image");
				produtoLinha.addLink(link);
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "DELETE"));
				
				Link link2 = new Link();
				link2.setHref("http://dc366.4shared.com/img/Wpt-aKONba/s7/1512ff0c130/Honda-CRF250L-1");
				link2.setRel("photo");
				link2.setTipoDeMidia("image");
				produtoLinha.addLink(link2);
			} else if(contador == (listaDeProdutos.size() -1)) {
				Link link = new Link();
				link.setHref("http://dc254.4shared.com/img/2yaKlk7M/s7/141e56762e0/header_newsletter");
				link.setRel("photo");
				link.setTipoDeMidia("image");
				produtoLinha.addLink(link);
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "PUT"));
			} else {
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "PUT"));
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "DELETE"));
			}
			
			//Implementação da conectividade entre recursos.
			if(contador == 0) { 
				produtoLinha.addLink(
									new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((listaDeProdutos.size() -1)).getId(), 
											"anterior", "GET"));
				if((listaDeProdutos.get(contador + 1)) != null) 
					produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((contador + 1)).getId(), 
													"proximo", "GET"));
			} else if(contador == (listaDeProdutos.size() -1)) { 
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(0).getId(), "proximo", "GET"));
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((listaDeProdutos.size() -2)).getId(), 
												"anterior", "GET"));
			} else {
				if(listaDeProdutos.get(contador-1) != null) produtoLinha.addLink(
																new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(contador-1).getId(), 
																		"anterior", "GET"));
				if(listaDeProdutos.get(contador+1) != null) produtoLinha.addLink(
																new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(contador+1).getId(), 
																		"proximo", "GET"));
			}
			contador++;
		}
		
		if(listaDeProdutos.size() > 0) {
			Gson gson = new Gson();
			return gson.toJson(listaDeProdutos);
		}
		return "204";
	}
	
	
	@Override
	public String update(Long id, String objetoJSONComDados) {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		produtoControlador.beginTransaction();
		Gson gson = new Gson();
		Produto produto = produtoControlador.find(id);
		if(produto != null) {
			Produto produtoAux = gson.fromJson(objetoJSONComDados, Produto.class);
			produto.setDataDeAtualizacao(new Date());
			produto.setLinks(produtoAux.getLinks());
			produto.setNome(produtoAux.getNome());
			produto.setPreco(produtoAux.getPreco());
			produtoControlador.update(produto);
			produtoControlador.commit();
			return "200";
		}
		return "204";
	}


	@Override
	public String delete(Long id) {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		Produto produto = produtoControlador.find(id);
		if(produto != null) {
			produtoControlador.beginTransaction();
			produtoControlador.delete(Produto.class, produto.getId());
			produtoControlador.commit();
			return "200";
		}
		return "500";
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
		ProdutoControlador produtoControlador = new ProdutoControlador();
		List<Produto> listaDeProdutos = produtoControlador.findAll();
		String resultado = "";
		resultado += "<?xml version=\"1.0\"?> " +
				"<produtos>";
		int contador = 0;
		for (Produto produtoLinha : listaDeProdutos) {
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "POST"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text/"+produtoLinha.getId(), "self", "GET"));

			if (contador == 0) { 
				Link link = new Link();
				link.setHref("http://dc544.4shared.com/img/_UEh72Awba/s7/1512ff005b0/CRF250L_Almeida");
				link.setRel("photo");
				link.setTipoDeMidia("image");
				produtoLinha.addLink(link);
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "DELETE"));
				
				Link link2 = new Link();
				link2.setHref("http://dc366.4shared.com/img/Wpt-aKONba/s7/1512ff0c130/Honda-CRF250L-1");
				link2.setRel("photo");
				link2.setTipoDeMidia("image");
				produtoLinha.addLink(link2);
			} else if(contador == (listaDeProdutos.size() -1)) { 
				Link link = new Link();
				link.setHref("http://dc254.4shared.com/img/2yaKlk7M/s7/141e56762e0/header_newsletter");
				link.setRel("photo");
				link.setTipoDeMidia("image");
				produtoLinha.addLink(link);
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "PUT"));
			} else {
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "PUT"));
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "DELETE"));
			}
			
			//Implementação da conectividade entre recursos.
			if(contador == 0) { 
				produtoLinha.addLink(
									new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((listaDeProdutos.size() -1)).getId(), 
											"anterior", "GET"));
				if((listaDeProdutos.get(contador + 1)) != null) 
					produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((contador + 1)).getId(), 
													"proximo", "GET"));
			} else if(contador == (listaDeProdutos.size() -1)) { 
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(0).getId(), "proximo", "GET"));
				produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get((listaDeProdutos.size() -2)).getId(), 
												"anterior", "GET"));
			} else {
				if(listaDeProdutos.get(contador-1) != null) produtoLinha.addLink(
																new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(contador-1).getId(), 
																		"anterior", "GET"));
				if(listaDeProdutos.get(contador+1) != null) produtoLinha.addLink(
																new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+listaDeProdutos.get(contador+1).getId(), 
																		"proximo", "GET"));
			}
			contador++;
			resultado += "<produto>" +
								"<id>"+produtoLinha.getId()+"</id>"+
								"<nome>"+produtoLinha.getNome()+"</nome>"+
								"<preco>"+produtoLinha.getPreco()+"</preco>"+
								"<linksHATEOAS>";
								for(int a = 0; a < produtoLinha.getLinks().size(); a++) {
									resultado += (produtoLinha.getLinks().get(a).getTipoDeMidia() != null) ?
											"<link>" +
											"<href>"+produtoLinha.getLinks().get(a).getHref()+"</href>" +
											"<rel>"+produtoLinha.getLinks().get(a).getRel()+"</rel>" +
											"<tipoDeMidia>"+produtoLinha.getLinks().get(a).getTipoDeMidia()+"</tipoDeMidia>" +
										"</link>"
										:
										"<link>" +
										"<href>"+produtoLinha.getLinks().get(a).getHref()+"</href>" +
										"<rel>"+produtoLinha.getLinks().get(a).getRel()+"</rel>" +
										"<metodo>"+produtoLinha.getLinks().get(a).getMetodo()+"</metodo>" +
									"</link>";
								}
				resultado += 
								"</linksHATEOAS>" +
						 "</produto>";
		}
		resultado += "</produtos>";
		if(listaDeProdutos.size() > 0) return resultado;
		return "204";
	}

	
	@Override
	public String getXML(Long id) {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		Produto produto = produtoControlador.find(id);
		
		Long idAnterior = null;
		idAnterior = produtoControlador.getIdAnteriorRegistro(id);
		
		Long idProximo = null;
		idProximo = produtoControlador.getIdProximoRegistro(id);
		
		if(produto != null) {
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "POST"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text", "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text/"+produto.getId(), "self", "GET"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "PUT"));
			produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produto.getId(), "self", "DELETE"));
			
			Link link = new Link();
			link.setHref("http://dc254.4shared.com/img/2yaKlk7M/s7/141e56762e0/header_newsletter");
			link.setRel("photo");
			link.setTipoDeMidia("image");
			produto.addLink(link);
			
			if( (idAnterior != null) && (produto.getId() != idAnterior) ) produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idAnterior, 
																									"anterior", "GET"));
			else {
				idAnterior = produtoControlador.getIdUltimoRegistro();
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idAnterior, "anterior", "GET"));
			}
			
			if( (idProximo != null) && (produto.getId() != idProximo) ) produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idProximo, 
																									"proximo", "GET"));
			else {
				idProximo = produtoControlador.getIdPrimeiroRegistro();
				produto.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+idProximo, "proximo", "GET"));
			}
			
			String resultado = "";
			resultado += "<?xml version=\"1.0\"?> " +
					"<produtos>";
				
					resultado += "<produto>" +
									"<id>"+produto.getId()+"</id>"+
									"<nome>"+produto.getNome()+"</nome>"+
									"<preco>"+produto.getPreco()+"</preco>"+
									"<linksHATEOAS>";
									for(int a = 0; a < produto.getLinks().size(); a++) {
										resultado += (produto.getLinks().get(a).getTipoDeMidia() != null) ?
												"<link>" +
												"<href>"+produto.getLinks().get(a).getHref()+"</href>" +
												"<rel>"+produto.getLinks().get(a).getRel()+"</rel>" +
												"<tipoDeMidia>"+produto.getLinks().get(a).getTipoDeMidia()+"</tipoDeMidia>" +
											"</link>"
											:
											"<link>" +
											"<href>"+produto.getLinks().get(a).getHref()+"</href>" +
											"<rel>"+produto.getLinks().get(a).getRel()+"</rel>" +
											"<metodo>"+produto.getLinks().get(a).getMetodo()+"</metodo>" +
										"</link>";
									}
					resultado += 
									"</linksHATEOAS>" +
								"</produto>";
			resultado += "</produtos>";
			return resultado;
		}
		return "204";
	}

	@Override
	public String getHTML() {
		ProdutoControlador produtoControlador = new ProdutoControlador();
		List<Produto> listaDeProdutos = produtoControlador.findAll();
		String resultado = "<!DOCTYPE html>"+
							 "<html>"+
								"<head>"+
									"<style>"+
										"table, th, td {"+
										"    border: 1px solid black;"+
										"    border-collapse: collapse;"+
										"}"+
										"th, td {"+
										"    padding: 15px;"+
										"}"+
									"</style>"+
								"</head>"+
								"<body>"+
									"<table style='width:100%'>"+
										" <tr>"+
										"    <th>Id</th>"+
										"    <th>Nome</th>"+		
										"    <th>Preço</th>"+
										"    <th>Imagem 1</th>"+
										"    <th>Imagem 2</th>"+
										" </tr>";
		for (Produto produtoLinha : listaDeProdutos) {
			Link link = new Link();
			link.setHref("http://dc544.4shared.com/img/_UEh72Awba/s7/1512ff005b0/CRF250L_Almeida");
			link.setRel("photo");
			link.setTipoDeMidia("image");
			produtoLinha.addLink(link);
				
			Link link2 = new Link();
			link2.setHref("http://dc366.4shared.com/img/Wpt-aKONba/s7/1512ff0c130/Honda-CRF250L-1");
			link2.setRel("photo");
			link2.setTipoDeMidia("image");
			produtoLinha.addLink(link2);
			
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "POST"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text", "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/xml/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/json/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/html/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/text/"+produtoLinha.getId(), "self", "GET"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "PUT"));
			produtoLinha.addLink(new Link("http://localhost:8080/TCCRESTful/rest/produtos/"+produtoLinha.getId(), "self", "DELETE"));
			resultado += 				"<tr>"+
											"<td>"+produtoLinha.getId()+"</td>"+
											"<td>"+produtoLinha.getNome()+"</td>"+
											"<td>"+produtoLinha.getPreco()+"</td>"+
											"<td><a target='_blank' href='"+produtoLinha.getLinks().get(0).getHref()+"'><img src='http://dc400.4shared.com/img/nS0anBYAba/s7/15146f608e0/iconeImagemPNG'></a></td>"+
											"<td><a target='_blank' href='"+produtoLinha.getLinks().get(1).getHref()+"'><img src='http://dc400.4shared.com/img/nS0anBYAba/s7/15146f608e0/iconeImagemPNG'></a></td>"+
										"</tr>";
		}
		resultado += 				"</table>"+
								"</body>"+
							"</html>";
		
		if(listaDeProdutos.size() > 0) {
			return resultado;
		}
		return "204";
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

