package email;

import java.util.*;
import javax.jms.Message;
import javax.jms.Session;
//import javax.mail.*;
//import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{

    //TODO: download and instal James server from https://james.apache.org/download.cgi#Apache_James_Server
    //TODO: setup docker to run james server.
    //TODO: integrate Apache James into java source:
    //JEE6
//    public static void main(String [] args){
//        String to = "sonoojaiswal1988@gmail.com";//change accordingly
//        String from = "sonoojaiswal1987@gmail.com";// change accordingly
//        String host = "localhost";//or IP address
//
//        //Get the session object
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        Session session = Session.getDefaultInstance(properties);
//
//        //compose the message
//        try{
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject("Ping");
//            message.setText("Hello, this is example of sending email  ");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("message sent successfully....");
//
//        }catch (MessagingException mex) {mex.printStackTrace();}
//    }
}