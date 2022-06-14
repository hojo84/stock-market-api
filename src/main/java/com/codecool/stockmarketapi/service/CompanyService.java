package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.CompanyNotFoundException;
import com.codecool.stockmarketapi.customexception.ExchangeNotFoundException;
import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ExchangeRepository exchangeRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ExchangeRepository exchangeRepository) {
        this.companyRepository = companyRepository;
        this.exchangeRepository = exchangeRepository;
    }

    public List<String> listAll() {
        return companyRepository.findAllCompanyNames();
    }

    public Company save(CreateCompanyDTO createCompanyDTO) {
        Company newCompanySaved = companyRepository.save(new Company(createCompanyDTO));
        final List<Exchange> exchangeList = createCompanyDTO.getExchangeIds().stream()
                .map(exchangeId -> exchangeRepository.findById(exchangeId)
                        .orElseThrow(() -> new ExchangeNotFoundException(exchangeId)))
                .toList();
        exchangeList.forEach(exchange -> {
            exchange.addCompany(newCompanySaved);
            exchangeRepository.save(exchange);
        });
        return newCompanySaved;
    }

    public Company update(UpdateCompanyDTO updateCompanyDTO) {
        Company companyToBeUpdated = findById(updateCompanyDTO.getId());
        companyToBeUpdated.setName(updateCompanyDTO.getName());
        companyToBeUpdated.setSector(updateCompanyDTO.getSector());
        companyToBeUpdated.setEquityType(updateCompanyDTO.getEquityType());
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

    public void addCompanyToExchangeById(String companyId, String exchangeId) {
        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new ExchangeNotFoundException(exchangeId));
        Company companyToBeAdded = findById(companyId);
        exchange.addCompany(companyToBeAdded);
        exchangeRepository.save(exchange);
    }

    public void removeCompanyFromExchangeById(String companyId, String exchangeId) {
        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new ExchangeNotFoundException(exchangeId));
        Company companyToBeRemoved = findById(companyId);
        exchange.removeCompany(companyToBeRemoved);
        exchangeRepository.save(exchange);
    }
}
