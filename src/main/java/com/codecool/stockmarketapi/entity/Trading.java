package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "listing_id", foreignKey = @ForeignKey(name = "fk_trading_listings"))
    @JsonIgnore
    private Listing listing;

    private LocalDate tradingDay;

    private float priceOpen;

    private float priceHigh;

    private float priceLow;

    private float priceClose;

    private int volume;

    public Trading(Listing listing, LocalDate tradingDay, float priceOpen, float priceHigh, float priceLow, float priceClose, int volume) {
        this.listing = listing;
        this.tradingDay = tradingDay;
        this.priceOpen = priceOpen;
        this.priceHigh = priceHigh;
        this.priceLow = priceLow;
        this.priceClose = priceClose;
        this.volume = volume;
    }
}
