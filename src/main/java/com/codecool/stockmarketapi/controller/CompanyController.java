package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
@Tag(name = "Operations on companies")
public class CompanyController {

    private final CompanyService companyService;

    Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(summary = "Lists all companies")
    public List<String> listAll() {
        return companyService.listAll();
    }

    @PostMapping
    @Operation(summary = "Creates a company")
    public ResponseEntity<Company> save(@Valid @RequestBody CreateCompanyDTO createCompanyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("INVALID COMPANY INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(companyService.save(createCompanyDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates an existing company by id")
    public ResponseEntity<Company> update(@PathVariable("id") String id, @Valid @RequestBody UpdateCompanyDTO updateCompanyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !id.equals(updateCompanyDTO.getId())) {
            logger.error("INVALID COMPANY INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        updateCompanyDTO.setId(id);
        return ResponseEntity.ok(companyService.update(updateCompanyDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds company by id")
    public Company findById(@PathVariable("id") String id) {
        return companyService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes company by id")
    public void deleteById(@PathVariable("id") String id) {
        companyService.deleteById(id);
    }

    @PutMapping("/{companyId}/listings/{exchangeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Assigns company by id to given exchange")
    public void addCompanyToExchangeById(@PathVariable("companyId") String companyId,
                                         @PathVariable("exchangeId") String exchangeId) {
        companyService.addCompanyToExchangeById(companyId, exchangeId);
    }

    @DeleteMapping("/{companyId}/listings/{exchangeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Removes company by id from given exchange")
    public void removeCompanyFromExchangeById(@PathVariable("companyId") String companyId,
                                              @PathVariable("exchangeId") String exchangeId) {
        companyService.removeCompanyFromExchangeById(companyId, exchangeId);
    }

}
