package com.example.jsfdemo.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Patient {
	
	private String patientFirstName = "unknown";
	private String patientLastName = "unknown";
	private String kodPocztowy = "";
	private String pesel = "";
	private Date dataWizyty = new Date();
	private double waga;
	private boolean stanCywilny;
	private int liczbaDzieci;
	
	@Size(min = 2, max = 20)
	public String getpatientFirstName() {
		return patientFirstName;
	}
	public void setpatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	
	@Size(min = 2, max = 20)
	public String getpatientLastName() {
		return patientLastName;
	}
	public void setpatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	
	@Pattern(regexp = "[0-9]{2}-[0-9]{3}")
	public String getkodPocztowy() {
		return kodPocztowy;
	}
	public void setkodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}
	
	@Size(min = 11)
	public String getpesel() {
		return pesel;
	}
	public void setpesel(String pesel) {
		this.pesel = pesel;
	}
	
	@Min(0)
	public int getliczbaDzieci() {
		return liczbaDzieci;
	}
	public void setliczbaDzieci(int liczbaDzieci) {
		this.liczbaDzieci = liczbaDzieci;
	}
	
	@Past
	public Date getdataWizyty() {
		return dataWizyty;
	}
	public void setdataWizyty(Date dataWizyty) {
		this.dataWizyty = dataWizyty;
	}
	
	public double getwaga() {
		return waga;
	}
	public void setwaga(double waga) {
		this.waga = waga;
	}
	
	public boolean stanCywilny() {
		return stanCywilny;
	}
	public void setstanCywilny(boolean stanCywilny) {
		this.stanCywilny = stanCywilny;
	}
	public Patient getRowData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
