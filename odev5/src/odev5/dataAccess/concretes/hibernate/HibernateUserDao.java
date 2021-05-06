package odev5.dataAccess.concretes.hibernate;

import odev5.dataAccess.abstracts.UserDao;
import odev5.entities.concretes.User;

public class HibernateUserDao implements UserDao {

	@Override
	public void add(User user) {
		System.out.println("Hibernate ile eklendi " + user.getEmail());
	}

	@Override
	public void update(User user) {
		System.out.println("Hibernate ile eklendi " + user.getEmail());
	}

	@Override
	public void delete(User user) {
		System.out.println("Hibernate ile eklendi " + user.getEmail());
	}

	@Override
	public User get(String email, String password) {
		if (email == "test@ardaatay.com" && password == "123456") {
			User user = new User();
			user.setId(1);
			user.setFirstName("Arda");
			user.setLastName("Atay");
			user.setEmail(email);
			user.setPassword(password);

			return user;
		}
		return null;
	}

	@Override
	public boolean checkEmail(String email) {
		return false;
	}

}
