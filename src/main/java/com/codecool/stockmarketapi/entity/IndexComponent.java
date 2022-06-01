package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "index_components")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class IndexComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_index_components_indices"))
    private Index index;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_index_components_stocks"))
    private Stock stock;

    private float componentWeight;
}
