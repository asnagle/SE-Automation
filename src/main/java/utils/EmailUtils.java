package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static File getLatestReportFile(String ReportPath) {
		File dir = new File(ReportPath);
		if (!dir.exists() || !dir.isDirectory()) {
			throw new IllegalArgumentException("Invalid report directory path: " + ReportPath);
		}

		return Arrays.stream(dir.listFiles((f) -> f.isFile() && f.getName().endsWith(".html")))
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))
				.orElseThrow(() -> new RuntimeException("No report files found in the directory"));
	}

	public static void sendTestReport(String ReportPath) {

		try {
			System.out.println(">>> Inside sendTestReport(), path = " + ReportPath);

			final String emailSender = "yznagle@gmail.com";
			final String emailAppPwd = "uocebdeafiahubco"; // Password should be entered without space
			final String emailRecepient = "yznagle@gmail.com";

			File reportFile = getLatestReportFile(ReportPath);

//				SMTP Server Details =>

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.port", "587");

//				Create Session with Authentication

			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new jakarta.mail.PasswordAuthentication(emailSender, emailAppPwd);
				}
			});
			session.setDebug(true);

//				Create Email Message

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(emailSender));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRecepient));
				message.setSubject("Selenium Automation Test Reports.");
//					message.setText("Hello \nThis is a test email from Java Selenium Automation Code\n\n\nRegards,\n\nQA Team");
//					messate.setText is commented as we are composing email body with attachment in below Code. this was added for testing email send.

				MimeBodyPart emailBody = new MimeBodyPart();
				emailBody.setText(
						"Hello \\nThis email is sent by Java Selenium Automation Code\\n\\n\\nRegards,\\n\\nQA Team");

//					Email Attachments

				MimeBodyPart emailAttachment = new MimeBodyPart();
//					String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
//					String filePath = getLatestReportFile(ReportPath);
				System.out.println("Attachment fie is in following folder: " + ReportPath);
				emailAttachment.attachFile(reportFile);

//					Combine Email Body and Attachment

				MimeMultipart completeMail = new MimeMultipart();
				completeMail.addBodyPart(emailBody);
				completeMail.addBodyPart(emailAttachment);
				message.setContent(completeMail);

//					Send Email Message
				Transport.send(message);
				System.out.println("Email Sent Successfully...");

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(">>> Email sent successfully");
		} catch (Exception e) {
			System.err.println("!!! Exception in sendTestReport: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		sendTestReport("reports/");

	}

}
