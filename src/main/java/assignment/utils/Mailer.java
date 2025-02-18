package assignment.utils;

import java.io.File;
import javax.mail.Authenticator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
	private static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		props.setProperty("account.email", "phamkhoi9988@gmail.com");
		props.setProperty("account.appas", "vyeo ruoj iyue ppke");
		return Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = props.getProperty("account.email");
				String password = props.getProperty("account.appas");
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public static void sendAttachFile(String tieude, String noidung,
			String to,String[] cc, String[] bcc, List<String> filenames) {
		MimeMessage message = new MimeMessage(Mailer.getSession());
        try {
            message.setFrom(new InternetAddress("phamkhoi9988@gmail.com"));
            message.setReplyTo(message.getFrom());
            message.setRecipients(Message.RecipientType.TO, to);
            if (cc.length != 0) {
				List<InternetAddress> addressesCC = new ArrayList<>();
				int id = 0;
				for (String mail : cc) {
					if (!mail.isEmpty()) {
						addressesCC.add(new InternetAddress(mail.trim()));
						id++;
					}
				}
				message.setRecipients(Message.RecipientType.CC, addressesCC.toArray(new InternetAddress[0]));
			}
			if (bcc.length != 0) {
				List<InternetAddress> addressesBCC = new ArrayList<>();
				int idd = 0;
				for (String mail : bcc) {
					if (!mail.isEmpty()) {
						addressesBCC.add(new InternetAddress(mail.trim()));
						idd++;
					}
				}
				message.setRecipients(Message.RecipientType.BCC, addressesBCC.toArray(new InternetAddress[0]));
			}
            message.setSubject(tieude, "utf-8");
            message.setText(noidung, "utf-8", "html");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(noidung, "utf-8", "html");
            multipart.addBodyPart(textBodyPart);
            for(String filename: filenames) {
                MimeBodyPart fileBodyPart = new MimeBodyPart();
                System.out.println(filename);
    			fileBodyPart.attachFile(new File(filename));
    			multipart.addBodyPart(fileBodyPart);
            }
            
            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	} 
	public static void sendSimpleText(String tieude, String noidung, String to, String[] cc,
			List<String> bcc) {
		MimeMessage message = new MimeMessage(Mailer.getSession());
		try {
			message.setFrom(new InternetAddress("phamkhoi9988@gmail.com"));
			message.setReplyTo(message.getFrom());
			if (cc != null) {
				message.setRecipients(Message.RecipientType.TO, to.trim());
			}
			if (cc!= null) {
				List<InternetAddress> addressesCC = new ArrayList<>();
				int id = 0;
				for (String mail : cc) {
					if (!mail.isEmpty()) {
						addressesCC.add(new InternetAddress(mail.trim()));
						id++;
					}
				}
				message.setRecipients(Message.RecipientType.CC, addressesCC.toArray(new InternetAddress[0]));
			}
			if (bcc!= null) {
				List<InternetAddress> addressesBCC = new ArrayList<>();
				int idd = 0;
				for (String mail : bcc) {
					if (!mail.isEmpty()) {
						addressesBCC.add(new InternetAddress(mail.trim()));
						idd++;
					}
				}
				message.setRecipients(Message.RecipientType.BCC, addressesBCC.toArray(new InternetAddress[0]));
			}
			message.setSubject(tieude, "utf-8");
			message.setText(noidung, "utf-8", "html");
			Transport.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}