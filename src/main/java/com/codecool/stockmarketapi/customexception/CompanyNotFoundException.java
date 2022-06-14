package com.codecool.stockmarketapi.customexception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CompanyNotFoundException extends AbstractThrowableProblem {

    public CompanyNotFoundException(String id) {
        super(URI.create("companies/company-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Company with id %s not found", id));
    }
}
