package fr.univ_lille1.iut.lightringposition.struct;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginPwd {

	private String login;
	private String password;

	public LoginPwd() {}

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
}
