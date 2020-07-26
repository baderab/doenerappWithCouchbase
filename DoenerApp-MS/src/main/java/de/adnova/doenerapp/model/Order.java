package de.adnova.doenerapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Order {
	@Id
	private String itemId;
	
	private String name;
	private String gericht;
	private String preis;
	
	public String getId() {
		return itemId;
	}
	public void setId(String id) {
		this.itemId = id;
	}
	public String getGericht() {
		return gericht;
	}
	public void setGericht(String gericht) {
		this.gericht = gericht;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreis() {
		return preis;
	}
	public void setPreis(String preis) {
		this.preis = preis;
	}
	
	
}
