package fr.univ_lille1.iut.lightringposition.struct;

import java.io.File;

import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

public class User {

	private String login;
	private String password;
	private String email;
	private String nickname;
	private File avatar;

	public User() {}

	public User(String login, String password, String email, String nickname) throws InvalidUserException {
		if (login.length() > 20  || email.length() > 50
				|| nickname.length() > 20)
			throw new InvalidUserException();
		this.login = login;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
	}

	public User(String login, String password, String email, String nickname,
			File avatar) throws InvalidUserException {
		this(login, password, email, nickname);
		this.avatar = avatar;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public boolean isValidNewUser() {
		return this.login != null && this.password != null && this.password.length() <= 20
				&& this.email != null && this.nickname != null && this.avatar == null;
	}
}