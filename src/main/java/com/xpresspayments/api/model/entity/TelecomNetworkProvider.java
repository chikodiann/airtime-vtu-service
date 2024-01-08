package com.xpresspayments.api.model.entity;

import com.xpresspayments.api.model.enums.NetworkProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telco_network_provider")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelecomNetworkProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_name")
    private String providerName;

    @Enumerated(EnumType.STRING)
    private NetworkProvider networkProvider;

    private String uniqueCode;
}
