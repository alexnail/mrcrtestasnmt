package com.alexnail.mrcrtestasnmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.entities.ExchangeId;

public interface ExchangeRepository extends JpaRepository<Exchange, ExchangeId> {

}
