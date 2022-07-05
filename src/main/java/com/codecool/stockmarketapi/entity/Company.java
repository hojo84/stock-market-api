package com.codecool.stockmarketapi.entity;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
}