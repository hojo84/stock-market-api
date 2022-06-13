package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<String> listAll() {
        return companyService.listAll();
    }

    @PostMapping
    public ResponseEntity<Company> save(@Valid @RequestBody CreateCompanyDTO createCompanyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("INVALID COMPANY INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(companyService.save(createCompanyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable("id") String id, @Valid @RequestBody UpdateCompanyDTO updateCompanyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("INVALID COMPANY INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        updateCompanyDTO.setId(id);
        return ResponseEntity.ok(companyService.update(updateCompanyDTO));
    }

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable("id") String id) {
        return companyService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id) {
        companyService.deleteById(id);
    }

    @PutMapping("/{companyId}/listings/{exchangeId}")
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
