package com.xpresspayments.api.core.builders;

import com.xpresspayments.api.model.dto.user.SignUpRequest;
import com.xpresspayments.api.model.entity.Contact;
import com.xpresspayments.api.model.entity.User;
import com.xpresspayments.api.model.enums.UserType;

public class UserRecordBuilder {
    public static User mapSignupRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setUserType(UserType.CUSTOMER);
        user.setBlocked(false);
        user.setActive(true);

        Contact contact = new Contact();
        contact.setEmailAddress(signUpRequest.getEmailAddress());
        contact.setPhoneNumber(signUpRequest.getPhoneNumber());

        user.setContact(contact);
        return user;
    }
}
