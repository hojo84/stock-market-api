package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    @Query("select concat(e.name, ' (', e.id,  ')') from Exchange e order by e.name")
    List<String> findAllExchangeNames();

    Exchange save(Exchange exchange);

    Optional<Exchange> findById(String id);

    void deleteById(String id);

    @Query("select c from Exchange e join e.companies c where e.id=?1")
    List<Company> getAllCompaniesByExchangeId(String id);

    @Query("select c from Exchange e join e.companies c where e.id=:exchangeId and c.id=:companyId")
    Company getCompanyByIdAndExchangeId(@Param("exchangeId") String exchangeId, @Param("companyId") String companyId);

    @Modifying
    @Transactional
    @Query("update Exchange set country=:country where id=:exchangeId")
    void addExchangeToCountryById(Country country, String exchangeId);
}
