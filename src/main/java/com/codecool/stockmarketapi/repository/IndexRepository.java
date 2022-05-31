package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.IndexComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IndexRepository extends JpaRepository<Index, Long> {

    List<Index> findAll();

    Index save(Index index);

    Optional<Index> findById(Long id);

    void deleteById(Long id);

    Index findBySymbol(String symbol);

    @Query("select c from Index i join i.indexComponents c where i.symbol=?1")
    List<IndexComponent> getAllComponentsByIndexSymbol(String symbol);
}
