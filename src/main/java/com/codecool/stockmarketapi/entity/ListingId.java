package com.codecool.stockmarketapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ListingId implements Serializable {

    @Column(name = "exchange_id")
    private String exchangeId;

    @Column(name = "company_id")
    private String companyId;
}