package com.github.GraycenMacNeill.BulkEmailBomber;

import java.util.Scanner;

// This class handles user input for the Bulk Email Bomber program.
// TODO - Add more validation and error handling for user input.
// TODO - Add a while loop for continuous input until valid data is provided.

public class UserInteraction {

    static Scanner input = new Scanner(System.in);  // Initialize scanner object for user input

    public String getSenderEmail() {
        String email;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Declare emailRegex here

        do {
            System.out.print("\u001B[0m" + "Enter your sender email address: ");
            email = input.nextLine();

            if (!email.matches(emailRegex)) {
                System.out.println("\u001B[31m" + "Enter a valid email address!");
            }
        } while (!email.matches(emailRegex));
        return email;
    }

    public String getPassword() {
        System.out.print("\u001B[0m" + "Enter your Gmail App Password: ");
        return input.nextLine();
    }

    public String getRecipientEmail() {
        String email;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        do {
            System.out.print("\u001B[0m" + "Please enter the recipient email address: ");
            email = input.nextLine();

            if (!email.matches(emailRegex)) {
                System.out.println("\u001B[31m" + "Enter a valid email address!");
            }
        } while (!email.matches(emailRegex));
        return email;
    }

    public int getNumberOfEmailsToSend() {
        System.out.print("\u001B[0m" + "Please enter the number of emails to send: ");

        while (!input.hasNextInt()) {
            System.out.println("\u001B[31m" + "Invalid input. Enter an whole number value.");
            System.out.print("\u001B[0m" + "Please enter the number of emails to send: ");
            input.next(); // Consume the invalid input
        }

        return input.nextInt();
    }


}