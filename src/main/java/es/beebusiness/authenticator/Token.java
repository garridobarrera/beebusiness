package es.beebusiness.authenticator;

import java.io.Serializable;

public class Token implements Serializable {

	private static final long serialVersionUID = 604586136005072100L;
	private boolean valid;
	private String value;
	private String error;

	public Token(String value, boolean valid) {
		this.value=value;
		this.valid=valid;
	}

	public Token(String error) {
		this.valid=false;
		this.error=error;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
