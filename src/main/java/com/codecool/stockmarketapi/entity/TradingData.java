package com.codecool.stockmarketapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "trading_data")
public class TradingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exchange exchange;

    @ManyToOne
    private Stock stock;

    private LocalDate tradingDay;

    private float priceOpen;
    private float priceHigh;
    private float priceLow;
    private float priceClose;
    private int volume;
}
