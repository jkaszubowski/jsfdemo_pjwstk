package com.example.jsfdemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jsfdemo.domain.Patient;
import com.example.jsfdemo.service.PatientManager;

@SessionScoped
@Named("patientBean")
public class PatientFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Patient patient = new Patient();

	private ListDataModel<Patient> patients = new ListDataModel<Patient>();

	@Inject
	private PatientManager ppm;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ListDataModel<Patient> getAllPatients() {
		patients.setWrappedData(ppm.getAllPatients());
		return patients;
	}

	// Actions
	public String addPatient() {
		ppm.addPatient(patient);
		return "showPatient";
		//return null;
	}

	public String deletePerson() {
		Patient patientToDelete = patient.getRowData();
		ppm.deletePatient(patientToDelete);
		return null;
	}

	// Validators

	// Business logic validation
	public void uniquePin(FacesContext context, UIComponent component,
			Object value) {

		String pesel = (String) value;

		for (Patient patient : ppm.getAllPatients()) {
			if (patient.getpesel().equalsIgnoreCase(pesel)) {
				FacesMessage message = new FacesMessage(
						"Pacjent z danym PESELEM już istnieje w bazie");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	// Multi field validation with <f:event>
	// Rule: first two digits of PIN must match last two digits of the year of
	// birth
	public void validatePinDob(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput pin = (UIInput) form.findComponent("pesel");
		UIInput dob = (UIInput) form.findComponent("dataUrodzenia");

		if (pin.getValue() != null && dob.getValue() != null
				&& pin.getValue().toString().length() >= 2) {
			String twoDigitsOfPin = pin.getValue().toString().substring(0, 2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) dob.getValue()));

			String lastDigitsOfDob = ((Integer) cal.get(Calendar.YEAR))
					.toString().substring(2);

			if (!twoDigitsOfPin.equals(lastDigitsOfDob)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"PESEL nie jest zgodny z datą urodzenia"));
				context.renderResponse();
			}
		}
	}
}
