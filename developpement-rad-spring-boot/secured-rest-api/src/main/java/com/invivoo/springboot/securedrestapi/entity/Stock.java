package com.invivoo.springboot.securedrestapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Stock {
	private long id;
	private String name;
	private String isin;
	private List<StockQuote> stockQuotes;

	public Stock() {

	}

	public Stock(long id, String name, String isin, List<StockQuote> stockQuotes) {
		this.id = id;
		this.name = name;
		this.isin = isin;
		this.stockQuotes = stockQuotes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
	public List<StockQuote> getStockQuotes() {
		return stockQuotes;
	}

	public void setStockQuotes(List<StockQuote> stockQuotes) {
		this.stockQuotes = stockQuotes;
	}
}