package com.abc.asms.services;

import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public static void sendMail(String address,String url) {
		try {
			// GmailのSMTPを使用する
			Properties property = new Properties();
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.auth", "true");
			property.put("mail.smtp.starttls.enable", "true");
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.port", "587");
			property.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(property, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
//					return new PasswordAuthentication("sie.tsd2018@gmail.com", "!sie.tsd2018");
					return new PasswordAuthentication("sie.tsd2018@gmail.com", "yuwoycimzylymdag");
				}
			});

			// toアドレス
			InternetAddress toAddress = new InternetAddress(address);
			// fromアドレス
			InternetAddress fromAddress = new InternetAddress("sie.tsd2018@gmail.com", "物品売上管理システム");

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject("【物品売上管理システム】パスワード再設定", "ISO-2022-JP");
			mimeMessage.setText("パスワードの再設定を行います。\n"
					+ "以下のURLより新パスワードの入力・変更を行って下さい。\n\n"
					+ url + URLEncoder.encode(address , "UTF-8"), "ISO-2022-JP");

			Transport.send(mimeMessage);

			System.out.println("メールを送信しました。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
