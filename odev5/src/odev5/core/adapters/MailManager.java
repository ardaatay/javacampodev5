package odev5.core.adapters;

public class MailManager implements MailService {

	@Override
	public void send(String from, String message) {
		System.out.println("Mail gönderildi " + from);
	}
}
