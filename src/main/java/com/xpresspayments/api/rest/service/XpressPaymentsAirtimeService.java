package com.xpresspayments.api.rest.service;

import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.model.dto.user.PurchaseAirtimeRequest;

import java.security.Principal;

public interface XpressPaymentsAirtimeService {
    BaseResponse<?> purchaseAirtime(PurchaseAirtimeRequest purchaseAirtimeRequest, Principal principal);
}
