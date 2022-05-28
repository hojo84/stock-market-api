package com.codecool.stockmarketapi.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String tickerSymbol;

    private String companyName;

    private String sector;

    private String industry;

    @Enumerated(EnumType.STRING)
    private EquityType equityType;

    @ManyToMany(mappedBy = "stocks")
    private List<Exchange> exchanges;

    @OneToMany(mappedBy = "stock")
    private List<IndexComponent> indexComponents;

    @OneToMany(mappedBy = "stock")
    private List<TradingData> tradingData;
}