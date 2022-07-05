package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
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
@Table(name = "companies")
public class Company {

    @Id
    private String id;

    private String name;

    private String sector;

    @Enumerated(EnumType.STRING)
    private EquityType equityType;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private Set<Listing> exchanges = new HashSet<>();

    public Company(CreateCompanyDTO createCompanyDTO) {
        this.id = createCompanyDTO.getId();
        this.name = createCompanyDTO.getName();
        this.sector = createCompanyDTO.getSector();
        this.equityType = createCompanyDTO.getEquityType();
    }

    public Company(UpdateCompanyDTO updateCompanyDTO) {
        this.id = updateCompanyDTO.getId();
        this.name = updateCompanyDTO.getName();
        this.sector = updateCompanyDTO.getSector();
        this.equityType = updateCompanyDTO.getEquityType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}