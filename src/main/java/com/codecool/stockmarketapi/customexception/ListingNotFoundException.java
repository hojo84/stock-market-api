package com.codecool.stockmarketapi.customexception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ListingNotFoundException extends AbstractThrowableProblem {

    public ListingNotFoundException(String id) {
        super(URI.create("listings/listing-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Listing with id %s not found", id));
    }
}
