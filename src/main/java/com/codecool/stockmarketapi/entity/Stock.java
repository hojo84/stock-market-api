package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.CreateStockDTO;
import com.codecool.stockmarketapi.dto.UpdateStockDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Exchange> exchanges = new HashSet<>();

    public Stock(CreateStockDTO createStockDTO) {
        this.id = createStockDTO.getId();
        this.companyName = createStockDTO.getCompanyName();
        this.sector = createStockDTO.getSector();
        this.equityType = createStockDTO.getEquityType();
    }

    public Stock(UpdateStockDTO updateStockDTO) {
        this.id = updateStockDTO.getId();
        this.companyName = updateStockDTO.getCompanyName();
        this.sector = updateStockDTO.getSector();
        this.equityType = updateStockDTO.getEquityType();
    }
}