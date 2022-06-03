package com.codecool.stockmarketapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "index_components")
public class IndexComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_index_components_indices"))
    @JsonManagedReference("5")
    private Index index;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_index_components_stocks"))
    @JsonManagedReference("6")
    private Stock stock;

    private float componentWeight;
}
