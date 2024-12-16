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
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FinalPhoneExtraction {
  
  public static Set<String> phoneExtract(String text){
     Set<String> phoneNumbers = new HashSet<>();
        Set<String> validNumbers = new HashSet<>();
      System.out.print("Going to fetch Phone");
      String phonePattern = "\\+?(\\d[\\d\\-\\+\\(\\) ]{5,}\\d)|(?:(?:\\+|00)(\\d{1,3}))?[\\s.-]?(\\(?\\d{1,4}\\)?[\\s.-]?)?(\\d{1,4})[\\s.-]?(\\d{1,4})[\\s.-]?(\\d{1,9})";
            { //First verification
                // Compile the regex pattern
                Pattern pattern = Pattern.compile(phonePattern);
                Matcher matcher = pattern.matcher(text);
                // List to hold extracted phone numbers
                // Find and store all phone numbers
                while (matcher.find()) {
                    String number = (matcher.group().trim());
                    if(9<number.length() && number.length()<16)
                        phoneNumbers.add(number);
                }
                System.out.println(phoneNumbers);
            }
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            { //Second verification}
                for (String phoneNumber : phoneNumbers) {
                    // Number checking for null
                    try {
                        Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, null);
                        boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);
                        if (isValid) {
                           // System.out.println("Valid Phone Number: " +  phoneNumber);
                            validNumbers.add(phoneNumber.trim());
                          //  System.out.println(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber));
                          //  System.out.println(phoneNumberUtil.getCountryCodeForRegion(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber)));
                        }
                    } catch (NumberParseException e) {
                        // Number checking for IN
                        try {
                            Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "IN");
                            boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);
                            if (isValid) {
                             //   System.out.println("Valid Phone Number: " +"+91 "+ phoneNumber);
                                if(phoneNumber.charAt(0)=='0') phoneNumber=phoneNumber.substring(1, phoneNumber.length()).trim();                         
                                else if(phoneNumber.length()==10) phoneNumber=phoneNumber.trim();
                                validNumbers.add(phoneNumber.trim());
                             //   System.out.println(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber));
                            //    System.out.println(phoneNumberUtil.getCountryCodeForRegion(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber)));
                            }
                        }catch (NumberParseException e1){
                            // Number checking for US
                            try {
                                Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "US");
                                boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);

                                if (isValid) {

                                    System.out.println("Valid Phone Number: " + "+1 "+phoneNumber.trim());
                                    phoneNumber="+1 "+phoneNumber.trim();
                                    validNumbers.add(phoneNumber);
                                    System.out.println(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber));
                                    System.out.println(phoneNumberUtil.getCountryCodeForRegion(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber)));
                                }
                            }catch (NumberParseException e2) {
                                // Number checking for GB
                                try {
                                    Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, "GB");
                                    boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);

                                    if (isValid) {

                                        System.out.println("Valid Phone Number: " + "+44 "+phoneNumber);
                                        phoneNumber="+44 "+phoneNumber.trim();
                                        validNumbers.add(phoneNumber);
                                        System.out.println(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber));
                                        System.out.println(phoneNumberUtil.getCountryCodeForRegion(phoneNumberUtil.getRegionCodeForNumber(parsedPhoneNumber)));
                                    }
                                }catch (NumberParseException e3){
                                }
                            }
                        }

                    }
                } 
  }
            
return validNumbers;            
}
}
