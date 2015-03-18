package fr.univ_lille1.iut.lightringposition.struct;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

@XmlRootElement
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}