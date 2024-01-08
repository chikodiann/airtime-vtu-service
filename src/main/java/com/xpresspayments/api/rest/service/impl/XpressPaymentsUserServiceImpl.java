package com.xpresspayments.api.rest.service.impl;

import com.xpresspayments.api.core.builders.UserRecordBuilder;
import com.xpresspayments.api.core.exception.InvalidCredentialsException;
import com.xpresspayments.api.core.security.XpressJwtService;
import com.xpresspayments.api.core.utils.PasswordUtils;
import com.xpresspayments.api.model.dto.base.BaseResponse;
import com.xpresspayments.api.model.dto.user.LoginRequest;
import com.xpresspayments.api.model.dto.user.SignUpRequest;
import com.xpresspayments.api.model.dto.user.AuthenticationToken;
import com.xpresspayments.api.model.entity.User;
import com.xpresspayments.api.model.repository.UserRepository;
import com.xpresspayments.api.rest.service.XpressPaymentsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class XpressPaymentsUserServiceImpl implements XpressPaymentsUserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordUtils passwordUtils;

    private final XpressJwtService xpressJwtService;

    @Override
    public BaseResponse<?> handleUserRegistration(final SignUpRequest signUpRequest) {
        BaseResponse<?> br;
        try {
            if (userExistsByEmailAddress(signUpRequest.getEmailAddress())) {
                br = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), "user already exists", null);
            } else {
                User user = UserRecordBuilder.mapSignupRequestToUser(signUpRequest);
                user.setPassword(passwordUtils.hashedPassword(signUpRequest.getPassword()));
                userRepository.save(user);
                br = new BaseResponse<>(HttpStatus.OK.value(), "user registered successfully", null);
            }
        } catch (Exception e) {
            br = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), "user registration failed", e.getMessage());
        }
        return br;
    }

    @Override
    public BaseResponse<?> handleUserLogin(final LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("wrong username or password");
        }
        User foundUser = userRepository.findUserByContact_EmailAddress(loginRequest.getEmailAddress()).get();
        auditLoginActivity(foundUser);

        AuthenticationToken authenticationToken = xpressJwtService.generateToken(foundUser);
        return new BaseResponse<>(HttpStatus.OK.value(), "user login successful", authenticationToken);
    }

    private boolean userExistsByEmailAddress(String emailAddress) {
        return userRepository.existsByContact_EmailAddress(emailAddress);
    }

    private void auditLoginActivity(User foundUser) {
        if (ObjectUtils.isEmpty(foundUser.getFirstLoginDate())) {
            foundUser.setFirstLoginDate(LocalDateTime.now());
        } else {
            foundUser.setLastLoginDate(LocalDateTime.now());
        }
        userRepository.save(foundUser);
    }
}
