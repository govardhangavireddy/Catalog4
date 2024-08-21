package Hack4;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class Authentication {

    private static String generateCaptcha(){
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7; // specify the desired length
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private static boolean verifyCaptcha(String genCap,String userCap){
        return genCap.equals(userCap);
    }

    private static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        int OTP_LENGTH=6;
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10)); 
        }
        return otp.toString();
    }

    private static void sendOTP(String otp) {
        System.out.println("Sending OTP to your email...");
        System.out.println("Your OTP is: " + otp);
    }

    private static boolean verifyOTP(String genOTP, String userOTP) {
        return genOTP.equals(userOTP);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String,String> users = new HashMap<>();
        users.put("govardhan","Govardhan123");
        users.put("david","David05");
        users.put("john07","John07");
        users.put("jesse","jesse@9");
        

        System.out.println("Login\n");
        System.out.print("USERNAME : ");
        String username = scanner.next();
        System.out.print("PASSWORD : ");
        String password = scanner.next();
        int flag=0;
        for(String u: users.keySet()){
            if(u.equals(username)){
                if(users.get(u).equals(password)){
                    flag=1;
                }
            }
            if(flag==1){
                break;
            }
        }
        if(flag==0){
            System.out.println();
            System.out.println("Invalid USERNAME or PASSWORD");
            System.out.println("Level 1 Authentication Failed.\nLOGIN UNSUCCESSFULL");
            scanner.close();
            return;
        }
        else{
            System.out.println("------------------------------------");
            System.out.println("Level 1 Authentication Successfull.");
            System.out.println("------------------------------------");
        }


        String captcha = generateCaptcha();
        System.out.println("Captcha : "+captcha);
        System.out.print("Enter the captcha : ");
        String userC = scanner.next();

        if(verifyCaptcha(captcha, userC)){
            System.out.println("------------------------------------");
            System.out.println("Level 2 Authentication Successfull.");
            System.out.println("------------------------------------");
        }
        else{
            System.out.println();
            System.out.println("Invalid Captcha");
            System.out.println("Level 2 Authentication Failed.\n LOGIN UNSUCCESSFULL");
            scanner.close();
            return;
        }

        String otp = generateOTP();
        sendOTP(otp);

        System.out.print("Please enter the OTP sent to your email:");
        String userInput = scanner.next();

        if (verifyOTP(otp, userInput)) {
            System.out.println("------------------------------------");
            System.out.println("Level 3Authentication successful!");
            System.out.println("------------------------------------");
            System.out.println("-----------------LOGIN SUCCESSFULL-----------------------");
        } else {
            System.out.println();
            System.out.println("Invalid OTP");
            System.out.println("Level 3 Authentication Failed.\n LOGIN UNSUCCESSFULL");
            scanner.close();
            return;
        }

        scanner.close();
    }
}
