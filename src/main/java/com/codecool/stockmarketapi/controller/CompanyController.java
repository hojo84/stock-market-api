package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.CreateListingDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.function.Predicate;

@RestController
@RequestMapping("/companies")
@Tag(name = "Operations on companies")
public class CompanyController {

    private final CompanyService companyService;

    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    public ResponseEntity<Company> save(@Valid @RequestBody CreateCompanyDTO createCompanyDTO, BindingResult bindingResult) {
        final Predicate<String> testIdEquality = id -> id.equals(createCompanyDTO.getId());
        if (bindingResult.hasErrors() ||
                !createCompanyDTO.getListings().stream()
                        .map(CreateListingDTO::getCompanyId)
                        .allMatch(testIdEquality)) {
            logger.error("INVALID COMPANY INPUT");
            bindingResult.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(companyService.save(createCompanyDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates an existing company by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    public Company findById(@PathVariable("id") String id) {
        return companyService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes company by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    public void deleteById(@PathVariable("id") String id) {
        companyService.deleteById(id);
    }
}
