package com.example.jsfdemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.example.jsfdemo.domain.Patient;

@ApplicationScoped
public class PatientManager {
	private List<Patient> db2 = new ArrayList<Patient>();

	public void addPatient(Patient patient) {
		Patient newPatient = new Patient();

		newPatient.setpatientFirstName(patient.getpatientFirstName());
		newPatient.setpatientLastName(patient.getpatientLastName());
		newPatient.setkodPocztowy(patient.getkodPocztowy());
		newPatient.setpesel(patient.getpesel());
		newPatient.setdataWizyty(patient.getdataWizyty());
		newPatient.setstanCywilny(patient.stanCywilny());
		newPatient.setwaga(patient.getwaga());
		newPatient.setliczbaDzieci(patient.getliczbaDzieci());

		db2.add(newPatient);
	}

	// Usuwa pacjenta po podanym numerze PESEL
	public void deletePatient(Patient patient) {
		Patient patientToRemove = null;
		for (Patient p2 : db2) {
			if (patient.getpesel().equals(p2.getpesel())) {
				patientToRemove = p2;
				break;
			}
		}
		if (patientToRemove != null)
			db2.remove(patientToRemove);
	}

	public List<Patient> getAllPatients() {
		return db2;
	}
}
