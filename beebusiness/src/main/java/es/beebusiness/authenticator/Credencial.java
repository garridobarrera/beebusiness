package es.beebusiness.authenticator;

import java.io.Serializable;

public class Credencial implements Serializable{


	private static final long serialVersionUID = 9052656020146088432L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
