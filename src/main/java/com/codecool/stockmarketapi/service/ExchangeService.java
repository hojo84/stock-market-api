package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository, CompanyRepository companyRepository) {
        this.exchangeRepository = exchangeRepository;
        this.companyRepository = companyRepository;
    }

    public List<String> listAll() {
        return exchangeRepository.findAllExchangeNames();
    }

    public Exchange save(ExchangeDTO exchangeDTO) {
        Exchange exchangeToBeSaved = new Exchange(exchangeDTO);
        return exchangeRepository.save(exchangeToBeSaved);
    }

    public Optional<Exchange> findById(String id) {
        return exchangeRepository.findById(id);
    }

    public void deleteById(String id) {
        exchangeRepository.deleteById(id);
        companyRepository.deleteAllOrphanCompanies();
    }

    public List<Company> getAllCompaniesByExchangeId(String id) {
        return exchangeRepository.getAllCompaniesByExchangeId(id);
    }

    public Company getCompanyByIdAndExchangeId(String exchangeId, String companyId) {
        return exchangeRepository.getCompanyByIdAndExchangeId(exchangeId, companyId);
    }
}
