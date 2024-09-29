package com.github.GraycenMacNeill.BulkEmailBomber;

import java.util.Scanner;

// This class handles user input for the Bulk Email Bomber program.
// TODO - Add more validation and error handling for user input.
// TODO - Add a while loop for continuous input until valid data is provided.

public class UserInteraction {

    static Scanner input = new Scanner(System.in);  // Initialize scanner object for user input

    public String getSenderEmail() {
        System.out.print("\u001B[37m" + "Enter your sender email address: ");
        return input.nextLine();
    }

    public String getPassword() {
        System.out.print("\u001B[37m" + "Enter your Gmail App Password: ");
        return input.nextLine();
    }

    public String getRecipientEmail() {
        System.out.print("\u001B[37m" + "Please enter the recipient email address: ");
        return input.nextLine();
    }

    public int getNumberOfEmailsToSend() {
        System.out.print("\u001B[37m" + "Please enter the number of emails to send: ");
        return input.nextInt();
    }


}