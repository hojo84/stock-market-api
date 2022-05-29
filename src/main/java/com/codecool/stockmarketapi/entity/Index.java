package com.codecool.stockmarketapi.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "indices")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String symbol;

    private String name;

    @ManyToOne
    private Exchange exchange;

    private int constituents;

    @OneToMany(mappedBy = "index")
    private List<IndexComponent> indexComponents;
}
