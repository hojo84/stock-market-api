package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("3")
    private List<Exchange> exchanges;

    @OneToMany(mappedBy = "stock")
    @JsonBackReference("6")
    private List<IndexComponent> indexComponents;

    @OneToMany(mappedBy = "stock")
    @JsonBackReference("7")
    private List<TradingData> tradingData;
}