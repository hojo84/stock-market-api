package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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