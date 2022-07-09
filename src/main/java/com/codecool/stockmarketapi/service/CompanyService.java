package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.CompanyNotFoundException;
import com.codecool.stockmarketapi.customexception.ExchangeNotFoundException;
import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Listing;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ExchangeRepository exchangeRepository;
    private final ListingRepository listingRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ExchangeRepository exchangeRepository, ListingRepository listingRepository) {
        this.companyRepository = companyRepository;
        this.exchangeRepository = exchangeRepository;
        this.listingRepository = listingRepository;
    }

    public List<String> listAll() {
        return companyRepository.findAllCompanyNames();
    }

    public Company save(CreateCompanyDTO createCompanyDTO) {
        Company newCompanySaved = companyRepository.save(new Company(createCompanyDTO));
        createCompanyDTO.getListings().forEach(createListingDTO -> {
            Listing newListing = new Listing(
                    createListingDTO.getId(),
                    exchangeRepository.findById(createListingDTO.getExchangeId())
                            .orElseThrow(() -> new ExchangeNotFoundException(createListingDTO.getExchangeId())),
                    newCompanySaved,
                    createListingDTO.getEquityType(),
                    createListingDTO.getIpo()
            );
            listingRepository.save(newListing);
        });
        return newCompanySaved;
    }

    public Company update(UpdateCompanyDTO updateCompanyDTO) {
        Company companyToBeUpdated = findById(updateCompanyDTO.getId());
        companyToBeUpdated.setName(updateCompanyDTO.getName());
        companyToBeUpdated.setSector(updateCompanyDTO.getSector());
        companyToBeUpdated.setIndustry(updateCompanyDTO.getIndustry());
        return companyRepository.save(companyToBeUpdated);
    }

    public Company findById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public void deleteById(String id) {
        findById(id);
        companyRepository.deleteById(id);
    }
}
