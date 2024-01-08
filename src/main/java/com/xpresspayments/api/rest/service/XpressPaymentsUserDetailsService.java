package com.xpresspayments.api.rest.service;

import com.xpresspayments.api.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface XpressPaymentsUserDetailsService extends UserDetailsService {
    User resolveUserByEmailAddress(String emailAddress);
    User resolveUserById(Long userId);
}
