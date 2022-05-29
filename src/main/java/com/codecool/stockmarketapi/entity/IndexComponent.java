package com.codecool.stockmarketapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "index_components")
public class IndexComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Index index;

    @ManyToOne
    private Stock stock;

    private float componentWeight;
}
