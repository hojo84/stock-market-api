package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.dto.ListingDTO;
import com.codecool.stockmarketapi.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    @Query("select new com.codecool.stockmarketapi.dto.ListingDTO(l.exchange.id, l.id) from Listing l where l.company.id=:companyId")
    List<ListingDTO> getListingsByCompanyId(String companyId);
}