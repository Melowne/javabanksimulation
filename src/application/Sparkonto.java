package application;



public class Sparkonto extends Konto{
private double zins=0.04;
public Sparkonto(int kontonr,Kunde kunde,Bank bank) {
	super(kontonr,kunde,bank);
	
}	
public void zins() {
	System.out.println("Zinsen liegen gerade bei "+getGuthaben()*zins);
	
}
public String typ() {
	return "Sparkonto";
}
}