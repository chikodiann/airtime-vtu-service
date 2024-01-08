package com.xpresspayments.api.model.repository;

import com.xpresspayments.api.model.entity.TelecomNetworkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelecomNetworkProviderRepository extends JpaRepository<TelecomNetworkProvider, Long> {
    TelecomNetworkProvider findTelecomNetworkProviderByProviderNameEqualsIgnoreCase(String providerName);
}
