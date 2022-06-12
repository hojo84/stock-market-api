package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name = "listings",
            joinColumns = @JoinColumn(name = "exchange_id"),
            foreignKey = @ForeignKey(name = "fk_listings_exchanges"),
            inverseJoinColumns = @JoinColumn(name = "company_id"),
            inverseForeignKey = @ForeignKey(name = "fk_listings_companies")
    )
    @JsonBackReference("exchange-company")
    private Set<Company> companies;

    public Exchange(ExchangeDTO exchangeDTO) {
        this.id = exchangeDTO.getId();
        this.name = exchangeDTO.getName();
        this.location = exchangeDTO.getLocation();
        this.currency = exchangeDTO.getCurrency();
        this.website = exchangeDTO.getWebsite();
    }

    public void addCompany(Company company) {
        companies.add(company);
        company.getExchanges().add(this);
    }

    public void removeCompany(Company company) {
        companies.remove(company);
        company.getExchanges().remove(this);
    }
}
