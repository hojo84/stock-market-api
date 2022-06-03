package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String location;

    private String currency;

    private String website;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "listings",
            joinColumns = @JoinColumn(name = "exchange_id"),
            foreignKey = @ForeignKey(name = "fk_listings_exchanges"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"),
            inverseForeignKey = @ForeignKey(name = "fk_listings_stocks")
    )
    @JsonBackReference("3")
    private List<Stock> stocks;
}
