package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.service.ExchangeService;
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
@RequestMapping("/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<String> listAll() {
        return exchangeService.listAll();
    }

    @PostMapping
    public ResponseEntity<Exchange> save(@Valid @RequestBody ExchangeDTO exchangeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("INVALID EXCHANGE INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(exchangeService.save(exchangeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exchange> update(@PathVariable("id") String id, @Valid @RequestBody ExchangeDTO exchangeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("INVALID EXCHANGE INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        exchangeDTO.setId(id);
        return ResponseEntity.ok(exchangeService.save(exchangeDTO));
    }

    @GetMapping("/{id}")
    public Exchange findById(@PathVariable("id") String id) {
        return exchangeService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
