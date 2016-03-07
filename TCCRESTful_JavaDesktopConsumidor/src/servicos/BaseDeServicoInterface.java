package servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BaseDeServicoInterface {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String create(String objetoJSONComDados);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@PathParam("id") Long id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get();
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(@PathParam("id") Long id, String objetoJSONComDados);
	
	@DELETE
	@Path("/{id}")
	public String delete(@PathParam("id")Long id);
	
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSON();
	
	
	@GET
	@Path("/json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSON(@PathParam("id") Long id);
	
	
	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String getXML();
	
	
	@GET
	@Path("/xml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String getXML(@PathParam("id") Long id);
	
	
	@GET
	@Path("/html")
	@Produces(MediaType.TEXT_HTML)
	public String getHTML();
	
	@GET
	@Path("/html/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getHTML(@PathParam("id") Long id);
	
	@GET
	@Path("/text")
	@Produces("text/plain")
	public String getText();
	
	@GET
	@Path("/text/{id}")
	@Produces("text/plain")
	public String getText(@PathParam("id") Long id);
	
	
}

