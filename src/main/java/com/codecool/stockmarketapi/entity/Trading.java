package com.codecool.stockmarketapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trading")
public class Trading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id"),
            @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    }, foreignKey = @ForeignKey(name = "fk_trading_listings"))
    private Listing listing;

    private LocalDate tradingDay;

    private float priceOpen;

    private float priceHigh;

    private float priceLow;

    private float priceClose;

    private int volume;
}
