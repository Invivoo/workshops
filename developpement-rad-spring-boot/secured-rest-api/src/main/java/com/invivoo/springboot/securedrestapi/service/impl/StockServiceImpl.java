package com.invivoo.springboot.securedrestapi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.invivoo.springboot.securedrestapi.entity.Stock;
import com.invivoo.springboot.securedrestapi.repository.StockRepository;
import com.invivoo.springboot.securedrestapi.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Resource
	private StockRepository stockRepository;

	@Override
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	@Override
	public Stock findOne(long id) {
		return stockRepository.findOne(id);
	}

	@Override
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}
}
