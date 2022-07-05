package com.codecool.stockmarketapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "listings")
public class Listing {

    @EmbeddedId
    private ListingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exchangeId")
    private Exchange exchange;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("companyId")
    private Company company;

    public Listing(Exchange exchange, Company company) {
        this.exchange = exchange;
        this.company = company;
        this.id = new ListingId(exchange.getId(), company.getId());
    }
}