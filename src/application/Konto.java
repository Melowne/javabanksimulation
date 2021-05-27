package application;

public class Konto {
	private int kontonr;
	private Kunde kunde;
	private Bank bank;

    private double Guthaben=500;	
	public Konto(int kontonr,Kunde kunde,Bank bank){
		this.kontonr=kontonr;
		this.kunde=kunde;
		this.bank=bank;
	}

	public String überweisen(Konto k,double val) {
		if(Guthaben>=val) {
	 k.setGuthaben(k.getGuthaben()+val);
	 Guthaben-=val;return "Überweisung erfolgreich";
		} return "überziehen nicht möglich";
	}
	public void abheben(double val) {
		if(Guthaben>=val) {
	 Guthaben-=val;
		}else System.out.println("überziehen nicht möglich");
	}
	public void einzahlen(double val) {
		Guthaben+=val;
	}
	
	public String typ() {
		return "Standardkonto";
	}
	public void zins() {};
	public int getKontonr() {
		return kontonr;
	}
	public void setKontonr(int kontonr) {
		this.kontonr = kontonr;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public double getGuthaben() {
		return Guthaben;
	}

	public void setGuthaben(double guthaben) {
		Guthaben = guthaben;
	}

	public void ktyp() {
		// TODO Auto-generated method stub
		
	}

}
