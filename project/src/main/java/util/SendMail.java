package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail {
	
	//매개변수 : 발신자 수신자 제목 내용 
	public static void sendMail(String from, String to, String subject, String content) {
		
		// 1. 메일서버 정보 설정 (property)
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host","smtp.naver.com");
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.ssl.enable","true");
		prop.put("mail.smtp.ssl.protocols","TLSv1.2"); // No appropriate protocols 에러
		
		// 2. 인증을 위해 Session객체 생성
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override // 재정의
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dlghdbs7","gh201321068@@");
			}
		});
		session.setDebug(true);
		
		// 3. MimeMessage 객체 생성 (발신자 수신자 제목 내용 설정하는)
		try {
		MimeMessage mm = new MimeMessage(session);
		mm.setFrom(new InternetAddress(from)); // 발신자
		mm.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 수신자
		mm.setSubject(subject); //제목
		//mm.setText(content); // 내용(text 자체전송)
		mm.setContent(content,"text/html; charset=utf-8"); // 내용(html)
	
		// 4. 메일발송
			Transport.send(mm);
		} catch (Exception e) {
		System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		//sendMail("dlghdbs7@naver.com","dlghdbs3803@gmail.com","testtest", "<b>한글</b>test");
		
		String temp = "";
		for (int i=0; i<2; i++) {
			temp += (char) (Math.random()*26+65);
		}
		for (int i=0; i<2; i++) {
			temp += (int) (Math.random()*9);
		}
		System.out.println(temp);
	}
}