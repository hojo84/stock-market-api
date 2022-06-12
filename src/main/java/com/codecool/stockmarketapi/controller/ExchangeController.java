package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<String> listAll() {
        return exchangeService.listAll();
    }

    @PostMapping
    public Exchange save(@RequestBody ExchangeDTO exchangeDTO) {
        return exchangeService.save(exchangeDTO);
    }

    @PutMapping("/{id}")
    public Exchange update(@PathVariable("id") String id, @RequestBody ExchangeDTO exchangeDTO) {
        exchangeDTO.setId(id);
        return exchangeService.save(exchangeDTO);
    }

    @GetMapping("/{id}")
    public Optional<Exchange> findById(@PathVariable("id") String id) {
        return exchangeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        exchangeService.deleteById(id);
    }

    @GetMapping("/{id}/companies")
    public List<Company> getAllCompaniesByExchangeId(@PathVariable("id") String id) {
        return exchangeService.getAllCompaniesByExchangeId(id);
    }

    @GetMapping("/{exchangeId}/companies/{companyId}")
    public Company getCompanyByIdAndExchangeId(@PathVariable("exchangeId") String exchangeId,
                                               @PathVariable("companyId") String companyId) {
        return exchangeService.getCompanyByIdAndExchangeId(exchangeId, companyId);
    }
}
