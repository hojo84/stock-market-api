package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "exchange")
    @JsonIgnore
    private Set<Listing> companies = new HashSet<>();

    public Exchange(ExchangeDTO exchangeDTO) {
        this.id = exchangeDTO.getId();
        this.name = exchangeDTO.getName();
        this.location = exchangeDTO.getLocation();
        this.currency = exchangeDTO.getCurrency();
        this.website = exchangeDTO.getWebsite();
    }

    public void addCompany(Company company) {
        Listing listing = new Listing(this, company);
        companies.add(listing);
        company.getExchanges().add(listing);
    }

    public void removeCompany(Company company) {
        for (Iterator<Listing> iterator = companies.iterator(); iterator.hasNext(); ) {
            Listing listing = iterator.next();
            if (listing.getExchange().equals(this) && listing.getCompany().equals(company)) {
                iterator.remove();
                listing.getCompany().getExchanges().remove(listing);
                listing.setExchange(null);
                listing.setCompany(null);
            }
        }
    }
}
