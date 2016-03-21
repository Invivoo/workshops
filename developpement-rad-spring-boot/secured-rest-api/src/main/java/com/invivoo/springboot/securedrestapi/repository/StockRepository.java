package com.invivoo.springboot.securedrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invivoo.springboot.securedrestapi.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}