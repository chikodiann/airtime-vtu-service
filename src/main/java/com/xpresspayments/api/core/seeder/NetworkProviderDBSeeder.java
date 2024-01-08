package com.xpresspayments.api.core.seeder;

import com.xpresspayments.api.core.utils.Constants;
import com.xpresspayments.api.model.entity.TelecomNetworkProvider;
import com.xpresspayments.api.model.enums.NetworkProvider;
import com.xpresspayments.api.model.repository.TelecomNetworkProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NetworkProviderDBSeeder implements CommandLineRunner {

    private final TelecomNetworkProviderRepository networkProviderRepository;
    @Override
    public void run(String... args) throws Exception {
        seedNetworkProviders();
    }

    private void seedNetworkProviders() {
        Map<String, String> uniqueCodes = new HashMap<>();
        uniqueCodes.put("MTN", Constants.NetworkProviderUniqueCodes.MTN);
        // TODO: add other unique codes for other providers

        for (NetworkProvider provider : NetworkProvider.values()) {
            TelecomNetworkProvider networkProvider = new TelecomNetworkProvider();
            networkProvider.setNetworkProvider(provider);
            networkProvider.setProviderName(provider.name().toUpperCase());
            networkProvider.setUniqueCode(uniqueCodes.get(provider.toString()));
            networkProviderRepository.save(networkProvider);
        }
    }
}
