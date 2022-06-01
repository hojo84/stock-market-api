package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "indices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String symbol;

    private String name;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_indices_exchanges"))
    private Exchange exchange;

    private int constituents;

    @OneToMany(mappedBy = "index")
    private List<IndexComponent> indexComponents;
}
