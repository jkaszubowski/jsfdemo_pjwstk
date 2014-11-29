package com.example.jsfdemo.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pinValidator")
public class PinValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String pin = (String) value;
		String pesel = (String) value;
		
		if (pin.length() != 4) {
			FacesMessage message = new FacesMessage();
			message.setDetail("PIN musi składać się z 4 cyfr");
			message.setSummary("PIN musi składać się z 4 cyfr");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		if (pesel.length() != 11) {
			FacesMessage message = new FacesMessage();
			message.setDetail("PESEL musi składać się z 11 cyfr");
			message.setSummary("PESEL musi składać się z 11 cyfr");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
