package application;

public class Message {
	private String EmailSrc;
	private String message;
	public Message() {
		super();
	}
	public Message(String emailSrc, String message) {
		super();
		EmailSrc = emailSrc;
		this.message = message;
	}
	public String getEmailSrc() {
		return EmailSrc;
	}
	public void setEmailSrc(String emailSrc) {
		EmailSrc = emailSrc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}