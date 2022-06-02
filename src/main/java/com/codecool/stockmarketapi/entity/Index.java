package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_indices_exchanges"))
    @JsonManagedReference("2")
    private Exchange exchange;

    private int constituents;

    @OneToMany(mappedBy = "index")
    @JsonBackReference("5")
    private List<IndexComponent> indexComponents;
}
