package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entity.Sale;
import com.devsuperior.dsmeta.repositories.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository repository;

	public Page<Sale> findSales(String mindDate, String maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = mindDate.equals("") ? today.minusDays(365) : LocalDate.parse(mindDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
		
	}

}
