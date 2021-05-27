package application;

import java.util.ArrayList;

public class Kunde {
	
	private String name;
	private ArrayList<Konto>kontos=new ArrayList<Konto>();
	
	Kunde(String name){
		this.name=name;
	}
	
	String zeigekontos() {
		String s="";
		for (Konto konto : kontos) {
			
			s+=konto.getKontonr()+"\t"+konto.getGuthaben()+"\n";
		}
		
		return s;
	}
	
	public  String toString(){
	    return name;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Konto> getKontos() {
		return kontos;
	}
	public void setKontos(ArrayList<Konto> kontos) {
		this.kontos = kontos;
	}

}
