package org.ictact.webproceedings.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

	private List<InvalidFieldMessage> fieldErrors = new ArrayList<InvalidFieldMessage>();

	public void addFieldError(String path, String message) {
		InvalidFieldMessage error = new InvalidFieldMessage(path, message);
		fieldErrors.add(error);
	}

	public List<InvalidFieldMessage> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<InvalidFieldMessage> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
