package com.codecool.stockmarketapi.model;

import lombok.Data;

@Data
public class IndexComponent {

    private Long id;
    private Long indexId;
    private Long stockId;
    private float stockWeight;
}
