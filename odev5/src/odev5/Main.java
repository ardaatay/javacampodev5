package odev5;

import odev5.business.abstracts.UserService;
import odev5.business.concretes.UserManager;
import odev5.core.adapters.GoogleManager;
import odev5.core.adapters.MailManager;
import odev5.core.result.Result;
import odev5.dataAccess.concretes.hibernate.HibernateUserDao;
import odev5.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		UserService userService = new UserManager(new HibernateUserDao(), new MailManager(), new GoogleManager());

		User user = new User(1, "Arda", "Atay", "test@ardaatay.com", "123456");
		Result result = userService.signUp(user);
		System.out.println(result.getMessage());

		result = userService.login("test@gmail.com", "123456");
		System.out.println(result.getMessage());
	}

}
