package com.example.trabalhoweb4bi.repository;

import com.example.trabalhoweb4bi.domain.ContasPagRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasPagRecRepository extends JpaRepository<ContasPagRec, Long> {
}
