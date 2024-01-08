package com.xpresspayments.api.core.builders;

import com.xpresspayments.api.model.dto.airtime.AirtimeVtuRequest;
import com.xpresspayments.api.model.entity.User;
import com.xpresspayments.api.model.entity.VtuAirtimeTransaction;

public class VtuAirtimeTransactionBuilder {
    public static VtuAirtimeTransaction mapResponseToVtuAirtimeTransaction(AirtimeVtuRequest airtimeVtuRequest, User user) {
        return VtuAirtimeTransaction.builder().amount(airtimeVtuRequest.getDetails().getAmount()).mobileNumber(airtimeVtuRequest.getDetails().getPhoneNumber()).user(user).build();
    }
}
