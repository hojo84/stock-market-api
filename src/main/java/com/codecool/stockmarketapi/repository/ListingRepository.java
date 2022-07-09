package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {
}