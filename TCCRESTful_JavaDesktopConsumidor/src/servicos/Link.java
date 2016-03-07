package servicos;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://www.w3.org/1999/xlink")
public class Link {
	private String href;
	private String rel;
	private String metodo;
	private String tipoDeMidia;
	
	public Link(String href, String rel, String metodo) {
		this.href = href;
		this.rel = rel;
		this.metodo = metodo;
	}
	
	public Link() {}
	
	@XmlAttribute
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@XmlAttribute
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
	
	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((rel == null) ? 0 : rel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (rel == null) {
			if (other.rel != null)
				return false;
		} else if (!rel.equals(other.rel))
			return false;
		return true;
	}

	public String getTipoDeMidia() {
		return tipoDeMidia;
	}

	public void setTipoDeMidia(String tipoDeMidia) {
		this.tipoDeMidia = tipoDeMidia;
	}
	
}

