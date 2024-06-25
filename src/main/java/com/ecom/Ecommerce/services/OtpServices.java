package com.ecom.Ecommerce.services;

import org.springframework.stereotype.Service;

@Service
public class OtpServices {

    public String generateOtp(){

        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1267890";
        String otp="";
        for(int i = 1; i <= 6; i++){
            int randomIndex= (int) (Math.random()*source.length());
            otp += source.charAt(randomIndex);
        }
        return otp;
    }
}
