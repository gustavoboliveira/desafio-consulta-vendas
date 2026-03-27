package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class ReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public ReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }

}
