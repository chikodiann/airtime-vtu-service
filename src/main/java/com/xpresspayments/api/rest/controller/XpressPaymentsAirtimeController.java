package com.xpresspayments.api.rest.controller;

import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.model.dto.user.PurchaseAirtimeRequest;
import com.xpresspayments.api.rest.service.XpressPaymentsAirtimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/vtu/airtime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class XpressPaymentsAirtimeController {

    private final XpressPaymentsAirtimeService xpressPaymentsAirtimeService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseAirtime(@RequestBody @Valid PurchaseAirtimeRequest purchaseAirtimeRequest, Principal principal) {
        BaseResponse<?> br = xpressPaymentsAirtimeService.purchaseAirtime(purchaseAirtimeRequest, principal);
        if (br.getResponseCode() == HttpStatus.OK.value()) {
            return ResponseEntity.ok().body(br);
        } else {
            return ResponseEntity.badRequest().body(br);
        }
    }
}
