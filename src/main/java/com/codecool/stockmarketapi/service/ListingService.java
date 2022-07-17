package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.CompanyNotFoundException;
import com.codecool.stockmarketapi.customexception.ExchangeNotFoundException;
import com.codecool.stockmarketapi.customexception.ListingNotFoundException;
import com.codecool.stockmarketapi.dto.CreateListingDTO;
import com.codecool.stockmarketapi.dto.ListingDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Listing;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final ExchangeRepository exchangeRepository;
    private final CompanyRepository companyRepository;

    public ListingService(ListingRepository listingRepository, ExchangeRepository exchangeRepository, CompanyRepository companyRepository) {
        this.listingRepository = listingRepository;
        this.exchangeRepository = exchangeRepository;
        this.companyRepository = companyRepository;
    }

    public void addCompanyToExchangeById(CreateListingDTO createListingDTO) {
        Exchange exchange = exchangeRepository.findById(createListingDTO.getExchangeId())
                .orElseThrow(() -> new ExchangeNotFoundException(createListingDTO.getExchangeId()));
        Company companyToBeAdded = companyRepository.findById(createListingDTO.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException(createListingDTO.getCompanyId()));
        Listing newListing = new Listing(
                createListingDTO.getId(),
                exchange,
                companyToBeAdded,
                createListingDTO.getEquityType(),
                createListingDTO.getIpoDate()
        );
        listingRepository.save(newListing);
    }

    public void removeCompanyFromExchangeById(String id) {
        findById(id);
        listingRepository.deleteById(id);
    }

    public Listing findById(String id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new ListingNotFoundException(id));
    }

    public List<ListingDTO> getListingsByCompanyId(String companyId) {
        return listingRepository.getListingsByCompanyId(companyId);
    }
}