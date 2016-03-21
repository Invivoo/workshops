package com.invivoo.springboot.securedrestapi.service;

import java.util.List;

import com.invivoo.springboot.securedrestapi.entity.Stock;

public interface StockService {
	List<Stock> findAll();

	Stock findOne(long id);

	Stock save(Stock stock);
}