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
    @JsonBackReference("1")
    private Country country;

    private String location;

    private String currency;

    private String website;

    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL)
    @JsonManagedReference("2")
    private List<Index> indices;

    @ManyToMany
    @JoinTable(
            name = "listings",
            joinColumns = @JoinColumn(name = "exchange_id"),
            foreignKey = @ForeignKey(name = "fk_listings_exchanges"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"),
            inverseForeignKey = @ForeignKey(name = "fk_listings_stocks")
    )
    @JsonManagedReference("3")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "exchange")
    @JsonManagedReference("4")
    private List<TradingData> tradingData;
}
