package com.xpresspayments.api.model.repository;

import com.xpresspayments.api.model.entity.VtuAirtimeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtuAirtimeTransactionRepository extends JpaRepository<VtuAirtimeTransaction, Long> {
}
