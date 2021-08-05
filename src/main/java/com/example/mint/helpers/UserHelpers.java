package com.example.mint.helpers;

import com.example.mint.models.User;
import com.example.mint.models.enums.PhoneCode;

public class UserHelpers {
    public static User getRandomValidUser() {
        User user = new User();

        user.setLogin(GenerateDataHelper.getRandomString(15));
        user.setPassword(GenerateDataHelper.getRandomString(8));
        user.setFirstName(GenerateDataHelper.getRandomString(12));
        user.setLastName(GenerateDataHelper.getRandomString(20));
        user.setEmail(GenerateDataHelper.getRandomEmail(7));
        user.setPhone(GenerateDataHelper.getRandomPhone(PhoneCode.CODE_7));
        user.setPhoneCode(PhoneCode.CODE_7);

        return user;
    }
}
