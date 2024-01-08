package com.xpresspayments.api.rest.service;

import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.model.dto.user.LoginRequest;
import com.xpresspayments.api.model.dto.user.SignUpRequest;

public interface XpressPaymentsUserService {
    BaseResponse<?> handleUserRegistration(SignUpRequest signUpRequest);

    BaseResponse<?> handleUserLogin(LoginRequest loginRequest);
}
