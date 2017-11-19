package com.zxg.tongxunlu.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import java.util.Locale;

/**
 * @author zxg
 * @date 2017/11/2
 */
public class LocationUtils {
    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    static PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();

    /**
     * 获取手机号码的归属地
     *
     * @param number
     * @return
     */
    public static String location(String number) {
        String language = "CN";
        Phonenumber.PhoneNumber referencePhoneNumber = null;
        try {
            referencePhoneNumber = phoneUtil.parse(number, language);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
//      手机号码归属城市 city
        String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhoneNumber, Locale.CHINA);
        return city;
    }
}
