package application;
import com.email.durgesh.Email;
public class SendEmail{
	public static boolean Send(String toEmail,String MsgContent,String Subject) {
		try {
			Email email = new Email("CovoiturageMaroc2021@gmail.com","Covoadmin123");
			email.setFrom("CovoiturageMaroc2021@gmail.com","App Covoiturage");
			email.setSubject(Subject);
			email.setContent("<h3>"+MsgContent+"</h3>","text/html");
			email.addRecipient(toEmail);
			email.send();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}