package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Listing;
import com.codecool.stockmarketapi.entity.ListingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, ListingId> {

    @Modifying
    @Transactional
    @Query("delete from Listing l where l.id.companyId=:companyId and l.id.exchangeId=:exchangeId")
    void removeCompanyFromExchangeById(String companyId, String exchangeId);
}