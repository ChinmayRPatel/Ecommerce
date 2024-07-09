package com.ecom.Ecommerce.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPStorage {
    private static final Map<String, String> otpStore = new ConcurrentHashMap<>();

    public static void storeOTP(String key, String otp) {
        otpStore.put(key, otp);
    }

    public static String getOTP(String key) {
        return otpStore.get(key);
    }

    public static void removeOTP(String key) {
        otpStore.remove(key);
    }
}