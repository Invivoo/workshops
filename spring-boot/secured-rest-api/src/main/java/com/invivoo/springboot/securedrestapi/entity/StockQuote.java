package com.invivoo.springboot.securedrestapi.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockQuote {
	private long id;
	private BigDecimal quote;
	private Date timestamp;

	public StockQuote() {

	}

	public StockQuote(long id, BigDecimal quote, Date timestamp) {
		this.id = id;
		this.quote = quote;
		this.timestamp = timestamp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getQuote() {
		return quote;
	}

	public void setQuote(BigDecimal quote) {
		this.quote = quote;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
