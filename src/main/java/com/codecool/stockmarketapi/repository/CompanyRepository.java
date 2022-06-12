package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("select concat(c.name, ' (', c.id, ')') from Company c order by c.name")
    List<String> findAllCompanyNames();

    Company save(Company company);

    Optional<Company> findById(String id);

    @Modifying
    @Query("delete from Company c where c.id=?1")
    void deleteById(String id);

    @Modifying
    @Query("delete from Company c where c.exchanges is empty")
    @Transactional
    void deleteAllOrphanCompanies();
}
