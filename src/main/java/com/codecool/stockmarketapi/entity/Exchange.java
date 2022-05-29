package com.codecool.stockmarketapi.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "iso_mic")
    private String marketIdentifierCode;

    private String name;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_exchanges_countries"))
    private Country country;

    private String location;

    private String currency;

    private String website;

    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL)
    private List<Index> indices;

    @ManyToMany
    @JoinTable(
            name = "listings",
            joinColumns = @JoinColumn(name = "exchange_id"),
            foreignKey = @ForeignKey(name = "fk_listings_exchanges"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"),
            inverseForeignKey = @ForeignKey(name = "fk_listings_stocks")
    )
    private List<Stock> stocks;

    @OneToMany(mappedBy = "exchange")
    private List<TradingData> tradingData;
}
