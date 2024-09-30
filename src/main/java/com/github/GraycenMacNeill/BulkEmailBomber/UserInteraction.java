package com.github.GraycenMacNeill.BulkEmailBomber;

import java.util.Scanner;

// TODO - Add more validation and error handling for user input.
// TODO - Add a while loop for continuous input until valid data is provided.


public class UserInteraction {

    private static final String RED = "\u001B[31m";
    private static final String WHITE = "\u001B[0m";

    static Scanner input = new Scanner(System.in);

    public String getSenderEmail() {
        String email;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Declare emailRegex here

        do {
            System.out.print(WHITE + "\nEnter the sender email address: ");
            email = input.nextLine();

            if (!email.matches(emailRegex)) {
                System.out.println(RED + "\nEnter a valid email address!");
            }
        } while (!email.matches(emailRegex));
        return email;
    }

    public String getPassword() {
        System.out.print(WHITE + "Enter your Gmail App Password: ");
        return input.nextLine();
    }

    public String getRecipientEmail() {
        String email;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        do {
            System.out.print(WHITE + "\nEnter the recipient email address: ");
            email = input.nextLine();

            if (!email.matches(emailRegex)) {
                System.out.println(RED + "\nEnter a valid email address!");
            }
        } while (!email.matches(emailRegex));
        return email;
    }

    public int getNumberOfEmailsToSend() {
        System.out.print(WHITE + "Enter the number of emails to send: ");

        while (!input.hasNextInt()) {
            System.out.println(RED + "\nInvalid input. Enter an whole number value.");
            System.out.print(WHITE + "\nEnter the number of emails to send: ");
            input.next(); // Consume the invalid input
        }

        return input.nextInt();
    }
    public int getEmailTemplate() {
        int template;  // Initialized to ensure variable is declared outside loop

        do {
            System.out.println("\n1) Numbers (0-50)");
            System.out.println("2) Words");
            System.out.println("3) Special characters (§¶•ª≠œ∑)");
            System.out.println("4) Symbols (♣♧♥♔♕♚♛)");
            System.out.println("5) Biggest byte size (Ǆஹ௸ဪ)");
            System.out.print("\nChoose an email template (1-5): ");

            // Check if the input is an integer and within the range 1-5
            while (!input.hasNextInt()) {
                System.out.println(RED + "\nInvalid input. Enter a whole number between 1 and 5.");
                input.next();  // Consume the invalid input
                System.out.print(WHITE + "\nChoose an email template (1-5): ");
            }

            template = input.nextInt();  // Store the valid integer input

            // Check if the input is within the valid range
            if (template < 1 || template > 5) {
                System.out.println(RED + "\nInvalid input. Please choose a number between 1 and 5.");
            }

        } while (template < 1 || template > 5);  // Continue until valid input is provided

        return template;
    }

}