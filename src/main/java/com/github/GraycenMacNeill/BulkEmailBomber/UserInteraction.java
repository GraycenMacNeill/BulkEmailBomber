package com.github.GraycenMacNeill.BulkEmailBomber;

import java.util.Scanner;

public class UserInteraction {

    static Scanner input = new Scanner(System.in);  // Initialize scanner object for user input

    public String getSenderEmail() {
        System.out.println("Please enter your sender email address: ");
        return input.nextLine();
    }

    public String getRecipientEmail() {
        System.out.println("Please enter the recipient email address: ");
        return input.nextLine();
    }

    public int getNumberOfEmailsToSend() {
        System.out.println("Please enter the number of emails to send: ");
        return input.nextInt();
    }

    public String getPassword() {
        System.out.println("Please enter your Gmail App Password: ");
        return input.nextLine();
    }

}