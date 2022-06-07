package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    private String id;

    private String name;

    private String location;

    private String currency;

    private String website;

    @ManyToMany
    @JoinTable(
            name = "listings",
            joinColumns = @JoinColumn(name = "exchange_id"),
            foreignKey = @ForeignKey(name = "fk_listings_exchanges"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"),
            inverseForeignKey = @ForeignKey(name = "fk_listings_stocks")
    )
    @JsonBackReference("exchange-stock")
    private List<Stock> stocks;

    public Exchange(ExchangeDTO exchangeDTO) {
        this.id = exchangeDTO.getId();
        this.name = exchangeDTO.getName();
        this.location = exchangeDTO.getLocation();
        this.currency = exchangeDTO.getCurrency();
        this.website = exchangeDTO.getWebsite();
    }
}
