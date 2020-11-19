package observers;

import model.Account;
import model.AccountEntry;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

public class EmailSender implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        //TODO send email
        if (o instanceof Account){
            Account acc = (Account)o;
            AccountEntry entry = (AccountEntry) arg;
            String txtMessage;
            if (entry.getDescription().equals("deposit")) {
                try {
                    txtMessage = "The deposit amount $" + entry.getAmount() + " is greater than $400";
                    System.out.println(acc.getCustomer().getEmail());
                    System.out.println(txtMessage);
                    sendEmail(acc.getCustomer().getEmail(), txtMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (entry.getDescription().equals("withdraw")){
                try {
                    txtMessage = "The withdraw amount $" + -entry.getAmount() + " is greater than $400";
                    System.out.println(acc.getCustomer().getEmail());
                    System.out.println(txtMessage);
                    sendEmail(acc.getCustomer().getEmail(), txtMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendEmail(String toEmailAddress, String txtMessage) {
        try {
            Properties props = new Properties();
            String userName = "nvhbaoTestmail@gmail.com";
            String password = "testmail";
            props.setProperty("email.account", userName);
            props.setProperty("email.password", password);
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.ssl.enable", "true");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
            message.setSubject("Your account balance changed.");
            message.setDescription(txtMessage);


            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(txtMessage, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
