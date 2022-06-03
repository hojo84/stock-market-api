package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trading_data")
public class TradingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_trading_data_exchanges"))
    @JsonManagedReference("4")
    private Exchange exchange;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_trading_data_stocks"))
    @JsonManagedReference("7")
    private Stock stock;

    private LocalDate tradingDay;

    private float priceOpen;
    private float priceHigh;
    private float priceLow;
    private float priceClose;
    private int volume;
}
