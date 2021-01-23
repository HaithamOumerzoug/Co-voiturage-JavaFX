/*package application;
import com.email.durgesh.Email;

public class SendEmail{
	public static boolean Send(String toEmail,String MsgContent,String Subject,String name) {
		try {
			Email email = new Email("CovoiturageMaroc2021@gmail.com","Covoadmin123");
			email.setFrom("CovoiturageMaroc2021@gmail.com","App Covoiturage");
			email.setSubject(Subject);
			email.setContent("<div>Salut <b>"+name+"</b></div></br><div>Mot de passe oublié?</div></br><div>Nous avons reçu une demande d'envoi de votre mot de passe pour votre compte.</div></br><div>"+MsgContent+"</label>","text/html");
			email.addRecipient(toEmail);
			email.send();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}*/
package application;
import com.email.durgesh.Email;
public class SendEmail{
	public static boolean Send(String toEmail,String MsgContent,String Subject) {
		try {
			Email email = new Email("CovoiturageMaroc2021@gmail.com","Covoadmin123");
			email.setFrom("CovoiturageMaroc2021@gmail.com","App Covoiturage");
			email.setSubject(Subject);
			email.setContent(""+MsgContent+"","text/html");
			email.addRecipient(toEmail);
			email.send();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}