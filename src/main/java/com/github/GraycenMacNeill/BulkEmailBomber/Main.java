/*----------------------------------------------------------------------------------------------------------------------
    This program sends a specified number of emails to a recipient using the provided sender email address.
    The emails contain randomized text, and the recipient's email address is validated using JavaMail API.
    Note: A Gmail App Password is required to send emails. An alternative method is to use SMTP, but this
    method requires more setup and would be a pain in the long run.

    REMEMBER TO SET UP THE REQUIRED DEPENDENCIES:
    - Add the JavaMail API to your project's classpath. You can download it from https://www.oracle.com/java/technologies/javamail-releases.html.
----------------------------------------------------------------------------------------------------------------------*/

// TODO - Create a third party interface instead of program being console based so that the program can be integrated into a larger system.
// TODO - Enhance the email generation to include more complex text and make it more engaging.
// TODO - Add security measures to protect the Gmail App Password.

package com.github.GraycenMacNeill.BulkEmailBomber; // This package contains the main class for the Bulk Email Bomber program.

import javax.mail.*; // For sending emails
import javax.mail.internet.*; // For backwards compatibility
import java.util.*; // Import for handling emails, scanner, and MIME types
import java.util.concurrent.ThreadLocalRandom; // Import for random number generation

public class Main {

    // Asks for the user's email, the recipient's email, the number of emails to send, and the Gmail App Password.
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
        String subjectBase = "! @ # $ % ^ & * ( ) - _ = + [ { } ; ' : , . < > / ? Ω ≈ ç √ ı ˜ Â Ò ˚ Ô Ó © Ï Î Í Å ∏ Ø ˆ ¨ Á † ® ´ ∑ Œ a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z apple banana cherry date egg frog grape honey imitation jack-fruit kangaroo lemon mango orange pear peach plum pineapple queen rabbit raspberry strawberry tangerine watermelon xanadu yogurt ⴀ ▒ ☠ ☮ ☯ ♠ Ω ♤ ♣ ♧ ♥ ♡ ♦ ♢ ♔ ♕ ♚ ♛ ⚜ ★ ☆ ✮ ✯ ☄ ☾ ☽ ☼ ☀ ☁ ☂ ☃ ☻ ☺ ☹ ۞ ۩ εїз Ƹ̵̡Ӝ̵̨̄Ʒ ξЖЗ εжз ☎ ☏ ¢ ☚ ☛ ☜ ☝ ☞ ☟ ✍ ✌ ☢ ☣ ♨ ๑ ❀ ✿ ψ ♆ ☪ ♪ ♩ ♫ ♬ ✄ ✂ ✆ ✉ ✦ ✧♱ ♰ ∞ ♂ ♀ ☿ ❤ ❥ ❦ ❧ ™ ® © ✖ ✗ ✘ ♒ ■ □ ▢ ▲ △ ▼ ▽ ◆ ◇ ○ ◎ ● ◯ Δ";
        String bodyBase = "! @ # $ % ^ & * ( ) - _ = + [ { } ; ' : , . < > / ? Ω ≈ ç √ ı ˜ Â Ò ˚ Ô Ó © Ï Î Í Å ∏ Ø ˆ ¨ Á † ® ´ ∑ Œ a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z apple banana cherry date egg frog grape honey imitation jack-fruit kangaroo lemon mango orange pear peach plum pineapple queen rabbit raspberry strawberry tangerine watermelon xanadu yogurt ⴀ ▒ ☠ ☮ ☯ ♠ Ω ♤ ♣ ♧ ♥ ♡ ♦ ♢ ♔ ♕ ♚ ♛ ⚜ ★ ☆ ✮ ✯ ☄ ☾ ☽ ☼ ☀ ☁ ☂ ☃ ☻ ☺ ☹ ۞ ۩ εїз Ƹ̵̡Ӝ̵̨̄Ʒ ξЖЗ εжз ☎ ☏ ¢ ☚ ☛ ☜ ☝ ☞ ☟ ✍ ✌ ☢ ☣ ♨ ๑ ❀ ✿ ψ ♆ ☪ ♪ ♩ ♫ ♬ ✄ ✂ ✆ ✉ ✦ ✧♱ ♰ ∞ ♂ ♀ ☿ ❤ ❥ ❦ ❧ ™ ® © ✖ ✗ ✘ ♒ ■ □ ▢ ▲ △ ▼ ▽ ◆ ◇ ○ ◎ ● ◯ Δ";

        // Gmail configuration (use Gmail's implemented App Password)
        // For security reasons, never hardcode your credentials in your code!!!
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
    // To prevent misuse, the program displays a warning message and a link to the GitHub repository.
    public static void displayInformation() {
        System.out.println("https://github.com/GraycenMacNeill/BulkEmailBomber");
        System.out.println("\u001B[31m" + "\nWARNING: This application is designed for educational and experimental use only. Any unauthorized or");
        System.out.println("malicious use of this code is strictly prohibited and may violate applicable data protection laws,");
        System.out.println("such as the CAN-SPAM Act. Such misuse could result in legal penalties, depending on your jurisdiction.\n" + "\u001B[0m");
    }


}
/*----------------------------------------------------------------------------------------------------------------------
Note: The provided code is a simple implementation of a bulk email bomber using JavaMail API. It includes basic
user interaction, email generation, and Gmail configuration, and the following parameters.

To enhance the security and efficiency of this program, you can consider implementing the following improvements:

1. Use a secure and encrypted connection for sending emails.
2. Implement rate limiting to prevent abuse.
3. Implement a more efficient data structure or algorithm for generating random strings.
4. Use a more secure and efficient method for storing and retrieving Gmail credentials.
5. Implement a feature to allow users to choose the recipient email addresses from a list of predefined addresses.
Remember to follow the relevant data protection laws and guidelines when implementing these improvements!
----------------------------------------------------------------------------------------------------------------------*/