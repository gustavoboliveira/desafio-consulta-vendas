package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDTO>> getReport(@RequestParam(name = "minDate", required = false)
													 	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
													 @RequestParam(name = "maxDate", required = false)
													 	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
													 @RequestParam(name = "name", required = false) String name,
													 Pageable pageable) {
		return ResponseEntity.ok(service.getReport(minDate, maxDate, name, pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(@RequestParam(name = "minDate", required = false)
											@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
													   @RequestParam(name = "maxDate", required = false)
											@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {
		return ResponseEntity.ok(service.getSummary(minDate, maxDate));
	}
}
