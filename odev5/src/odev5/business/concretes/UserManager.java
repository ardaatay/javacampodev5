package odev5.business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import odev5.business.abstracts.UserService;
import odev5.core.adapters.GoogleService;
import odev5.core.adapters.MailService;
import odev5.core.result.Result;
import odev5.dataAccess.abstracts.UserDao;
import odev5.entities.concretes.User;

public class UserManager implements UserService {

	private UserDao userDao;
	private MailService mailService;
	private GoogleService googleService;

	public UserManager(UserDao userDao, MailService mailService, GoogleService googleService) {
		this.userDao = userDao;
		this.mailService = mailService;
		this.googleService = googleService;
	}

	@Override
	public Result signUp(User user) {
		if (!notNull(user)) {
			return new Result("Lütfen tüm alanları doldurunuz!", false);
		}

		if (!emailCheck(user.getEmail())) {
			return new Result("Lütfen geçerli bir mail adresi giriniz!", false);
		}

		if (!passwordstrength(user.getPassword())) {
			return new Result("Şifreniz en az 6 karakter olmalıdır!", false);
		}

		if (checkEmail(user.getEmail())) {
			return new Result("Bu email adresi daha önceden kayıt edilmiştir!", false);
		}

		if (!checkFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
			return new Result("Ad soyad en az iki karakter olmalıdır!", false);
		}

		this.userDao.add(user);
		this.mailService.send(user.getEmail(), "Aktivasyon mailiniz gönderilmiştir.");

		return new Result("Kullanıcı kayıt işleminiz yapılmıştır", true);
	}

	@Override
	public Result login(String email, String password) {
		if (!notNull(email, password)) {
			return new Result("Eposta ve şifre boş olmamalıdır!", false);
		}

		if (email.contains("@gmail.com")) {
			boolean status = this.googleService.signIn(email, password);
			if (status) {
				return new Result("Oturum açma başarılı Google", status);
			}
			return new Result("Oturum açma başarısız", status);
		}

		User user = this.userDao.get(email, password);
		if (user != null) {
			return new Result("Oturum açma başarılı", true);
		} else {
			return new Result("Oturum açma başarısız", false);
		}
	}

	@Override
	public Result activation(String code) {
		return new Result("Aktivasyon işleminiz tamamlanmıştır", true);
	}

	boolean notNull(User user) {
		if (!user.getFirstName().isEmpty() && !user.getLastName().isEmpty() && !user.getEmail().isEmpty()
				&& !user.getPassword().isEmpty()) {
			return true;
		}
		return false;
	}

	boolean notNull(String email, String password) {
		if (!email.isEmpty() && !password.isEmpty()) {
			return true;
		}
		return false;
	}

	boolean emailCheck(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	boolean passwordstrength(String password) {
		if (password.length() >= 6) {
			return true;
		}
		return false;
	}

	boolean checkEmail(String email) {
		return this.userDao.checkEmail(email);
	}

	boolean checkFirstNameAndLastName(String firstName, String lastName) {
		if (firstName.length() >= 2 && lastName.length() >= 2) {
			return true;
		}
		return false;
	}
}
