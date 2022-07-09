package com.codecool.stockmarketapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "listings")
public class Listing {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id", foreignKey = @ForeignKey(name = "fk_listings_exchanges"))
    private Exchange exchange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_listings_companies"))
    private Company company;

    @Enumerated(EnumType.STRING)
    private EquityType equityType = EquityType.COMMON_STOCK;

    private LocalDate ipo;

    public Listing(String id, Exchange exchange, Company company, EquityType equityType, LocalDate ipo) {
        this.id = id;
        this.exchange = exchange;
        this.company = company;
        this.equityType = equityType;
        this.ipo = ipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing that = (Listing) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}