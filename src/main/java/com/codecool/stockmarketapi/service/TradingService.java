package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.ListingNotFoundException;
import com.codecool.stockmarketapi.customexception.TradingNotFoundException;
import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.entity.Listing;
import com.codecool.stockmarketapi.entity.Trading;
import com.codecool.stockmarketapi.mapper.TradingMapper;
import com.codecool.stockmarketapi.repository.ListingRepository;
import com.codecool.stockmarketapi.repository.TradingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<TradeDTO> listAll(Optional<String> listingId) {
        if (listingId.isPresent() && !listingId.get().isBlank())
            return tradingMapper.toDto(tradingRepository.findAllByListingId(listingId.get()));
        else
            return tradingMapper.toDto(tradingRepository.findAll());
    }

    public List<TradeDTO> listBetweenDateRange(LocalDate from, LocalDate to) {
        return tradingMapper.toDto(tradingRepository.listBetweenDateRange(from, to));
    }

    public TradeDTO getTradeByListingIdAndDate(String listingId, LocalDate tradingDay) {
        return tradingMapper.toDto(tradingRepository.getTradeByListingIdAndDate(listingId, tradingDay));
    }

    public TradeDTO getTradeForPrevTradingDay(String listingId) {
        final Trading prevTradingData = tradingRepository.findFirstByListingIdOrderByTradingDayDesc(listingId)
                .orElseThrow(() -> new TradingNotFoundException(listingId));
        return tradingMapper.toDto(prevTradingData);
    }
}