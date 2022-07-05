package com.codecool.stockmarketapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class ListingId implements Serializable {

    @Column(name = "exchange_id")
    private String exchangeId;

    @Column(name = "company_id")
    private String companyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListingId that = (ListingId) o;
        return Objects.equals(exchangeId, that.exchangeId) && Objects.equals(companyId, that.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeId, companyId);
    }
}