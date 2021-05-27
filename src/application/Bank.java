package application;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	Random ran=new Random();
	private ArrayList<Kunde> kundenliste=new ArrayList<Kunde>();
	private ArrayList<Konto> kontenliste=new ArrayList<Konto>();
	private String name;
	private int blz;
	Bank(String name,int blz){
		this.name=name;
		this.blz=blz;
	}
	
	public void makekunde(String s) {
			kundenliste.add(new Kunde(s));
	}
	public void makekonto(Kunde kunde,Konto k) {
		kunde.getKontos().add(k);kontenliste.add(k);
	}
	

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlz() {
		return blz;
	}

	public void setBlz(int blz) {
		this.blz = blz;
	}

	public ArrayList<Kunde> getKundenliste() {
		return kundenliste;
	}

	public void setKundenliste(ArrayList<Kunde> kundenliste) {
		this.kundenliste = kundenliste;
	}

	public ArrayList<Konto> getKontenliste() {
		return kontenliste;
	}

	public void setKontenliste(ArrayList<Konto> kontenliste) {
		this.kontenliste = kontenliste;
	}

}
