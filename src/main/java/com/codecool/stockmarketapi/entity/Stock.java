package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.StockDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    private String id;

    private String companyName;

    private String sector;

    @Enumerated(EnumType.STRING)
    private EquityType equityType;

    @ManyToMany(mappedBy = "stocks")
    @JsonManagedReference("exchange-stock")
    private Set<Exchange> exchanges;

    public Stock(StockDTO stockDTO) {
        this.id = stockDTO.getId();
        this.companyName = stockDTO.getCompanyName();
        this.sector = stockDTO.getSector();
        this.equityType = stockDTO.getEquityType();
    }
}