package com.codecool.stockmarketapi.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Exchange> exchanges;
}
