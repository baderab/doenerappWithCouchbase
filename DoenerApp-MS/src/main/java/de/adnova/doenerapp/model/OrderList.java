package de.adnova.doenerapp.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderList implements Comparable<OrderList>{
	@Id
	private String id;
	
	private String restaurant;
	private String abholer;
	private Long bestellungen;
	private String bestellfrist;
	private String timestamp;

	private ArrayList<Order> orders;
	
	public OrderList() {
		
	}

	public OrderList(String place, String abholer, Long bestellungen, String bestellfrist, String timestamp, ArrayList<Order> orders) {
		this.restaurant = place;
		this.abholer = abholer;
		this.bestellungen = bestellungen;
		this.bestellfrist = bestellfrist;
		this.timestamp = timestamp;
		this.orders = orders;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String place) {
		this.restaurant = place;
	}

	public String getAbholer() {
		return abholer;
	}

	public void setAbholer(String abholer) {
		this.abholer = abholer;
	}

	public Long getbestellungen() {
		return bestellungen;
	}

	public void setbestellungen(Long bestellungen) {
		this.bestellungen = bestellungen;
	}
	
	public String getbestellfrist() {
		return bestellfrist;
	}

	public void setbestellfrist(String bestellfrist) {
		this.bestellfrist = bestellfrist;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int compareTo(OrderList o) {
		return this.bestellfrist.compareTo(o.getbestellfrist());
	}

}
