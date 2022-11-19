package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.entity.Sale;
import com.devsuperior.dsmeta.services.SMSService;
import com.devsuperior.dsmeta.services.SalesService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SalesService service;
	
	@Autowired
	private SMSService smsService;

	@GetMapping
	public Page<Sale> findSales(@RequestParam(value = "minDate", defaultValue = "") String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate, 
			Pageable pageable) {
		return service.findSales(minDate, maxDate, pageable);
	}
	
	@GetMapping("/{id}/notification")
	public void notifySMS(@PathVariable Long id) {
		smsService.sendSms(id);
	}

}
