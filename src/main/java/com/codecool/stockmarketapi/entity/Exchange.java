package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    private String id;

    private String name;

    private String location;

    private String currency;

    private String website;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_exchanges_countries"))
    private Country country;

    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Listing> companies = new HashSet<>();

    public Exchange(ExchangeDTO exchangeDTO) {
        this.id = exchangeDTO.getId();
        this.name = exchangeDTO.getName();
        this.location = exchangeDTO.getLocation();
        this.currency = exchangeDTO.getCurrency();
        this.website = exchangeDTO.getWebsite();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange that = (Exchange) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
