/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author User
 */

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class phone {
    public static void main(String[] args) {

    }
    public static HashMap<String,String> Format(List<String> phoneNumbers){
        HashMap<String ,String > hm = new HashMap<>();
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        // PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();
        //  PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

        for (String number : phoneNumbers) {
            try {
                Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(number, "IN"); // Assuming IN (India) as the default region
                boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);
                String countryCode = String.valueOf(phoneNumber.getCountryCode());
                String region = phoneNumberUtil.getRegionCodeForNumber(phoneNumber);
                // String location = geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH);
                // String carrier = carrierMapper.getNameForNumber(phoneNumber, Locale.ENGLISH);
                String formattedNumber = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
                formattedNumber=formattedNumber.replace(" ", "").substring(1, formattedNumber.length() - 1);

                // Print the results
                System.out.printf("Original Number: %s%n", number);
                System.out.printf("Formatted Number: %s%n", formattedNumber);
                hm.put(formattedNumber , countryCode);
                System.out.printf("Valid: %b%n", isValid);
                System.out.printf("Country Code: %s%n", countryCode);
                System.out.printf("Region: %s%n", region);
                System.out.println(" New Number ");
                //     System.out.printf("Location: %s%n", location);
                //   System.out.printf("Carrier: %s%n%n", carrier);
            } catch (Exception e) {
                System.out.printf("Number: %s is invalid: %s%n%n", number, e.getMessage());
            }

        }
        return hm;
    }
}

