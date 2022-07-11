package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.ListingNotFoundException;
import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.entity.Listing;
import com.codecool.stockmarketapi.entity.Trading;
import com.codecool.stockmarketapi.mapper.TradingMapper;
import com.codecool.stockmarketapi.repository.ListingRepository;
import com.codecool.stockmarketapi.repository.TradingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradingService {

    private final TradingRepository tradingRepository;
    private final ListingRepository listingRepository;
    private final TradingMapper tradingMapper;

    public TradingService(TradingRepository tradingRepository, ListingRepository listingRepository, TradingMapper tradingMapper) {
        this.tradingRepository = tradingRepository;
        this.listingRepository = listingRepository;
        this.tradingMapper = tradingMapper;
    }

    public TradeDTO save(CreateTradeDTO dto) {
        final Listing listing = listingRepository.findById(dto.getListingId())
                .orElseThrow(() -> new ListingNotFoundException(dto.getListingId()));
        Trading newTrading = new Trading(listing, dto.getTradingDay(), dto.getPriceOpen(),
                dto.getPriceHigh(), dto.getPriceLow(), dto.getPriceClose(), dto.getVolume());
        return tradingMapper.toDto(tradingRepository.save(newTrading));
    }

    public List<Trading> listAll(Optional<String> listingId) {
        if (listingId.isPresent() && !listingId.get().isBlank())
            return tradingRepository.findAllByListingId(listingId.get());
        else
            return tradingRepository.findAll();
    }
}