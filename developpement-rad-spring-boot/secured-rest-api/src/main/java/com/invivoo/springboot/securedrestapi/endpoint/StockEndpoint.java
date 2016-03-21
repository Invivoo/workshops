package com.invivoo.springboot.securedrestapi.endpoint;

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
@RequestMapping(value = { "/", "/stock" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class StockEndpoint {
	@Resource
	private StockService stockService;

	/**
	 * Service qui retourne au format JSON l'intégralité des actions présentes
	 * en base.
	 * 
	 * Url : GET /stock
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Stock> findAll() {
		return stockService.findAll();
	}

	/**
	 * Fournit la redirection vers la page HTML
	 * src/main/resources/resources/stocks.html.
	 * 
	 * Url : GET /stock
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getStocksPage() {
		return "stocks.html";
	}

	/**
	 * Service qui retourne au format JSON l'action en fonction de l'identifiant
	 * fourni.
	 * 
	 * Url : GET /stock/1
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Stock findOne(@PathVariable long id) {
		return stockService.findOne(id);
	}

	/**
	 * Service de sauvegarde de l'action passé dans le corps de la requête HTTP.
	 * Retourne le résultat de la sauvegarde.
	 * 
	 * Url : POST /stock
	 * 
	 * @param stock
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Stock save(@RequestBody Stock stock) {
		return stockService.save(stock);
	}
}
