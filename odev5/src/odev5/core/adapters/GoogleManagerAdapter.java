package odev5.core.adapters;

import odev5.google.Google;

public class GoogleManagerAdapter implements GoogleService {

	@Override
	public boolean signIn(String email, String password) {
		Google google = new Google();
		return google.signIn(email, password);
	}
}
