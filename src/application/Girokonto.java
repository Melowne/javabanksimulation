package application;



public class Girokonto extends Konto{
private double dispo=-1000; 
public Girokonto(int kontonr,Kunde kunde,Bank bank) {
	super(kontonr,kunde,bank);
	
}	
public String �berweisen(Konto k,double val) {
	// 500-1000   -1000
	if(getGuthaben()-dispo>=val) {
	k.setGuthaben(k.getGuthaben()+val);
    setGuthaben(getGuthaben()-val);return "�berweisung erfolgreich";
	} return "nur bis 1000";
}
	public void abheben(double val) {
		if(getGuthaben()-dispo>=val) {
		setGuthaben(getGuthaben()-val);
		}
		else System.out.println("nur bis 1000");
	}
public String typ() {
	return "Girokonto";
}
}