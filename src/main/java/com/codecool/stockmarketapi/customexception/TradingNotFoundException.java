package com.codecool.stockmarketapi.customexception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TradingNotFoundException extends AbstractThrowableProblem {

    public TradingNotFoundException(String listingId) {
        super(URI.create("trading/trading-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Trading data with stock ticker %s not found", listingId));
    }
}