package com.invivoo.springboot.securedrestapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invivoo.springboot.securedrestapi.entity.Stock;
import com.invivoo.springboot.securedrestapi.service.StockService;

@Controller
@RequestMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {
	@Resource
	private StockService stockService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Stock> findAll() {
		return stockService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getStocksPage() {
		return "stocks.html";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Stock findOne(@PathVariable long id) {
		return stockService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Stock save(@RequestBody Stock stock) {
		return stockService.save(stock);
	}
}
