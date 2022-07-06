package com.codecool.stockmarketapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private EquityType equityType = EquityType.COMMON_STOCK;

    public Listing(Exchange exchange, Company company) {
        this.exchange = exchange;
        this.company = company;
        this.id = new ListingId(exchange.getId(), company.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing that = (Listing) o;
        return Objects.equals(exchange, that.exchange) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, company);
    }
}