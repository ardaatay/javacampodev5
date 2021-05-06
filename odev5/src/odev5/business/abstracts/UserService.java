package odev5.business.abstracts;

import odev5.core.result.Result;
import odev5.entities.concretes.User;

public interface UserService {
	Result signUp(User user);

	Result login(String email, String password);

	Result activation(String code);
}
