package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, "
            + "obj.date, "
            + "obj.amount, "
            + "obj.seller.name) "
            + "FROM Sale obj WHERE "
            + "(:minDate IS NULL OR obj.date >= :minDate) AND "
            + "(:maxDate IS NULL OR obj.date <= :maxDate) AND "
            + "(:name IS NULL OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<ReportDTO> findReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj WHERE "
            + "(:minDate IS NULL OR obj.date >= :minDate) AND "
            + "(:maxDate IS NULL OR obj.date <= :maxDate) "
            + "GROUP BY obj.seller.name ORDER BY SUM(obj.amount) DESC")
    List<SummaryDTO> findSummary(LocalDate minDate, LocalDate maxDate);
}
