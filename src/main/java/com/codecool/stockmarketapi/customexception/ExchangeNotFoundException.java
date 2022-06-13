package com.codecool.stockmarketapi.customexception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ExchangeNotFoundException extends AbstractThrowableProblem {

    public ExchangeNotFoundException(String id) {
        super(URI.create("exchanges/exchange-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Exchange with id %s not found", id));
    }
}
