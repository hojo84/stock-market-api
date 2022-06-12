package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<String> listAll() {
        return companyService.listAll();
    }

    @PostMapping
    public Company save(@RequestBody CreateCompanyDTO createCompanyDTO) {
        return companyService.save(createCompanyDTO);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable("id") String id, @RequestBody UpdateCompanyDTO updateCompanyDTO) {
        updateCompanyDTO.setId(id);
        return companyService.update(updateCompanyDTO);
    }

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable("id") String id) {
        return companyService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        companyService.deleteById(id);
    }

    @PostMapping("/{companyId}/listings/{exchangeId}")
    public void addCompanyToExchangeById(@PathVariable("companyId") String companyId,
                                         @PathVariable("exchangeId") String exchangeId) {
        companyService.addCompanyToExchangeById(companyId, exchangeId);
    }

    @DeleteMapping("/{companyId}/listings/{exchangeId}")
    public void removeCompanyFromExchangeById(@PathVariable("companyId") String companyId,
                                              @PathVariable("exchangeId") String exchangeId) {
        companyService.removeCompanyFromExchangeById(companyId, exchangeId);
    }
}
