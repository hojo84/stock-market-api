package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Trading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingRepository extends JpaRepository<Trading, Long> {

    @Query("select t from Trading t where t.listing.id=:listingId")
    List<Trading> findAllByListingId(String listingId);
}