package assignment.user.common;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import assignment.user.models.User;

@Component
public class MailUtils {
	@Autowired
	private JavaMailSender sender;
	
	@Value("${login.url}")
	private String loginUrl;

	public boolean sendLoginMail(User user, String token) {
		boolean status=true;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		StringBuffer msg=new StringBuffer();
		msg.append("Hi ").append(user.getName())
		.append(",").append("\n\n\n")
		.append("Your login has been activated. Click on below link to login").append("\n\n\n")
		.append(loginUrl).append("/"+token);
		
		try {
			helper.setTo(user.getEmail());
			helper.setText(msg.toString());
			helper.setSubject("Login activated..");
			System.out.println("MAIL "+msg);
			sender.send(message);
		} catch (MessagingException e) {
			status=false;  
			e.printStackTrace();
		}
		return status;
	}

}
