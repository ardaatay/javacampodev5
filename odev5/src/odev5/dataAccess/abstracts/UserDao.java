package odev5.dataAccess.abstracts;

import odev5.entities.concretes.User;

public interface UserDao {
	void add(User user);

	void update(User user);

	void delete(User user);

	User get(String email, String password);
	
	boolean checkEmail(String email);
}
