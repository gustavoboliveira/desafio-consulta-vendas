package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
    public Page<ReportDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable) {
		if(Objects.isNull(maxDate))
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		if(Objects.isNull(minDate))
			minDate = maxDate.minusYears(1L);

		if (!StringUtils.hasText(name)) {
			name = null;
		}

		return repository.findReport(minDate, maxDate, name, pageable);
    }

	@Transactional(readOnly = true)
	public List<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		if(Objects.isNull(maxDate))
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		if(Objects.isNull(minDate))
			minDate = maxDate.minusYears(1L);

		return repository.findSummary(minDate, maxDate);
	}
}
