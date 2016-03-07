package servicos;
import java.util.List;

public interface HATEOASEntidade {
	public List<Link> getLinks();
	public void setLinks(List<Link> links);
	public void addLink(Link link);
}