package com.alexnail.mrcrtestasnmt.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.entities.ExchangeId;

public interface ExchangeRepository extends JpaRepository<Exchange, ExchangeId> {

    @Modifying
    @Query("update Exchange e set e.requestsCount = e.requestsCount + 1"
           + " where e.currencyFrom = :from and e.currencyTo = :to and e.date = :date")
    void incrementRequestsCounter(@Param("from") String from, @Param("to") String to, @Param("date")LocalDate date);

}
