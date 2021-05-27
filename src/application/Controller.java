package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public class Controller {
	 public ArrayList<Bank> banken=new ArrayList<Bank>();
	
	 @FXML 
	   private Label bankheading;
    @FXML
    private TextField banknametext;

    @FXML
    private TextField blztext;

    @FXML
    private Button bankcr;
    @FXML
    private Label bisherigekontos;

    @FXML
    private Label bankinfo;

    @FXML
    private ComboBox<String> bankcombo;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private Label kontoinfo;

    @FXML
    private Label kundeheading;
    @FXML
    private TextField kundnnametext;

    @FXML
    private Label kundeinfo;
    @FXML
    private Label kontoaddinfo;
    @FXML
    private Label outputtext;
    @FXML
    private ComboBox<String> kundecombo;
    @FXML
    private VBox vboxkunde;
    @FXML
    private ComboBox<String> typauswahl;
    @FXML
    private ComboBox<String> kontocombo;
    @FXML
    private VBox transfervbox;

    @FXML
    private TextField transferbanktext;

    @FXML
    private TextField transfernametext;

    @FXML
    private TextField transferkontotext;

    @FXML
    private TextField transferbetragtext;
    @FXML
    void resetcombo(ComboBox c) {
    c.getItems().clear();
    	for (Bank bank : banken) {
if(bank.getName()==bankheading.getText()) {
	for (Kunde k : bank.getKundenliste()) {
		c.getItems().add(k.getName());
	}
}
	}
    }
    Boolean numcheck(String s) {
    	return s.length()==0?true:s.charAt(s.length()-1)>47&&s.charAt(s.length()-1)<58
    		?numcheck(s.substring(0,s.length()-1)):false;
    }
     @FXML
    void erstellebank(MouseEvent event) {
    	if(!banknametext.getText().isEmpty()&&!blztext.getText().isEmpty()&&!bankcombo.getItems().contains(banknametext.getText())&&numcheck(blztext.getText())) {
    	Bank temp=new Bank(banknametext.getText(),Integer.parseInt(blztext.getText()));
    	bankcombo.getItems().add(temp.getName());
    	banken.add(temp);
    	bankinfo.setText(banknametext.getText()+" erstellt!");
    	}
    	else if(!numcheck(blztext.getText()))bankinfo.setText("Bankleitzahl nicht korrekt!");
    	else if(bankcombo.getItems().contains(banknametext.getText()))bankinfo.setText(banknametext.getText()+" existiert bereits!"); 
    	else bankinfo.setText("Bankname und Bankleitzahl eingeben bitte !");
    }

    @FXML
    void auswahlbank(MouseEvent event) {
    if(bankcombo.getSelectionModel().getSelectedItem()!=null) {
      bankheading.setText(bankcombo.getSelectionModel().getSelectedItem());
      vbox2.setVisible(true);resetcombo(kundecombo);
     vbox3.setVisible(false);vboxkunde.setVisible(false);outputtext.setText("");
    }
    }


    @FXML
    void löschebank(MouseEvent event) {outputtext.setText("");
      if(bankcombo.getSelectionModel().getSelectedItem()!=null) {
         banken.remove(bankcombo.getSelectionModel().getSelectedIndex());
    	  bankcombo.getItems().remove(bankcombo.getSelectionModel().getSelectedIndex());
            vbox2.setVisible(false);
         }
    }
    
    @FXML
    void erstellekunde(MouseEvent event) {
    	if(!kundnnametext.getText().isEmpty()&&!kundecombo.getItems().contains(kundnnametext.getText())) {
    	Kunde temp=new Kunde(kundnnametext.getText());
    	kundecombo.getItems().add(temp.getName());
    	kundeinfo.setText(temp.getName()+" erstellt!");
    	for (Bank bank : banken) {
if(bank.getName()==bankheading.getText())bank.makekunde(kundnnametext.getText());
	}
    	}
    	else if(kundecombo.getItems().contains(kundnnametext.getText()))kundeinfo.setText("Kunde existiert bereits!");
    	else  kundeinfo.setText("Namen eingeben bitte!");
    	
    	  vbox3.setVisible(false);	
    }
    
    @FXML
    void auswahlkunde(MouseEvent event) {
        if(kundecombo.getSelectionModel().getSelectedItem()!=null) {
            kundeheading.setText(kundecombo.getSelectionModel().getSelectedItem());
            vbox3.setVisible(true);vboxkunde.setVisible(true);
        	for (Bank bank : banken) {
	    		if(bank.getName()==bankheading.getText())
	    			bisherigekontos.setText("Bisherige Kontos: "+bank.getKundenliste().get(kundecombo.getSelectionModel().getSelectedIndex()).getKontos().size());} 
                 }
    //updatet bei kunden wechsel kontocombo
	for (Bank bank : banken) {
		if(bank.getName()==bankheading.getText()) {
		for (Kunde k : bank.getKundenliste()) {
			if(k.getName().equals(kundeheading.getText())) {
int i=0;    kontocombo.getItems().clear();
for (Konto kon : k.getKontos()) {
i++;kontocombo.getItems().add(i+". "+kon.typ());}
bisherigekontos.setText("Bisherige Kontos: "+k.getKontos().size());		
			}
	
			}
		}}transfervbox.setVisible(false);
    
    }
    
    @FXML
    void löschekunde(MouseEvent event) {outputtext.setText("");
    	 if(kundecombo.getSelectionModel().getSelectedItem()!=null) {
    	    	for (Bank bank : banken) {
    	    		if(bank.getName()==bankheading.getText())bank.getKundenliste().remove(kundecombo.getSelectionModel().getSelectedIndex());}
    	     	  kundecombo.getItems().remove(kundecombo.getSelectionModel().getSelectedIndex());
    	     	 vbox3.setVisible(false);vboxkunde.setVisible(false);
             }
    }
    @FXML
    void addkonto(MouseEvent event) {
    	Random ran=new Random();
	    	for (Bank bank : banken) {
	    		if(bank.getName()==bankheading.getText()) {//bisherigekontos.setText(bank.getKundenliste().get(0).getName());
	    		for (Kunde k : bank.getKundenliste()) {//bisherigekontos.setText(kundeheading.getText()+" "+k.getName());
					if(k.getName().equals(kundeheading.getText())) {//bisherigekontos.setText(k.getName());
if(typauswahl.getSelectionModel().getSelectedIndex()==0) {bank.makekonto(k, new Konto(ran.nextInt(999999999-100000000)+100000000,k,bank));kontoaddinfo.setText("Standardkonto erstellt!");}
else if(typauswahl.getSelectionModel().getSelectedIndex()==1) {bank.makekonto(k, new Girokonto(ran.nextInt(999999999-100000000)+100000000,k,bank));kontoaddinfo.setText("Girodkonto erstellt!");}
else if(typauswahl.getSelectionModel().getSelectedIndex()==2) {bank.makekonto(k, new Sparkonto(ran.nextInt(999999999-100000000)+100000000,k,bank));kontoaddinfo.setText("Sparkonto erstellt!");}
int i=0;    kontocombo.getItems().clear();
for (Konto kon : k.getKontos()) {
	i++;kontocombo.getItems().add(i+". "+kon.typ());}
	bisherigekontos.setText("Bisherige Kontos: "+k.getKontos().size());		
					}transfervbox.setVisible(false);
			
					}
	    		}}
    }
    @FXML
    void auswahlkonto(MouseEvent event) {
    	for (Bank bank : banken) {
    		if(bank.getName()==bankheading.getText()) {
    		for (Kunde k : bank.getKundenliste()) {
    			if(k.getName().equals(kundeheading.getText())) {
    kontoinfo.setText(""+(kontocombo.getSelectionModel().getSelectedIndex()+1));
    			
    		    	}
             	}
    		}}transfervbox.setVisible(true);
    }
    @FXML
    void löschekonto(MouseEvent event) {
    	for (Bank bank : banken) {
    		if(bank.getName()==bankheading.getText()) {
    		for (Kunde k : bank.getKundenliste()) {
    			if(k.getName().equals(kundeheading.getText())) {
    kontoinfo.setText(k.getKontos().get(kontocombo.getSelectionModel().getSelectedIndex()).typ()+" gelöscht!");
    		k.getKontos().remove(kontocombo.getSelectionModel().getSelectedIndex());
    		kontocombo.getItems().remove(kontocombo.getSelectionModel().getSelectedIndex());
    			}
             	}
    		}}transfervbox.setVisible(false);
    }
    @FXML
    void zeigekonten(MouseEvent event) {
    	String s="";
       	for (Bank bank : banken) {
    		if(bank.getName()==bankheading.getText()) {
    		for (Kunde k : bank.getKundenliste()) {
    			if(k.getName().equals(kundeheading.getText())) {
    				for (int i = 0; i < k.getKontos().size(); i++) {
		s+="Konto "+(i+1)+":\n Typ: "+k.getKontos().get(i).typ()+"\n  Guthaben: "+
				k.getKontos().get(i).getGuthaben()+" Euro\n";
					}
    			
    			}}
    		}}outputtext.setText(s);
    }

    @FXML
    void zeigekunden(MouseEvent event) {
    	String s="";
    	for (Bank bank : banken) {
    		if(bank.getName()==bankheading.getText()) {
    			for (int i = 0; i < bank.getKundenliste().size(); i++) {
    				s+="Kunde "+(i+1)+": "+bank.getKundenliste().get(i).getName()+"\n  ";
    				for (int j = 0; j < bank.getKundenliste().get(i).getKontos().size(); j++) {
						s+="Konto "+(j+1)+": "+bank.getKundenliste().get(i).getKontos().get(j).typ()+"\n  Guthaben: "+bank.getKundenliste().get(i).getKontos().get(j).getGuthaben()+" Euro\n  ";
					}
    				s+="\n";
				}
    			
    		}
    		}outputtext.setText(s);
    }
    
    @FXML
    void transferclick(MouseEvent event) {

      	outputtext.setText("Konto existiert nicht !");
  //suche ob banken namen und kontos existieren
for (Bank bank : banken) {
	if(bank.getName().equals(transferbanktext.getText())){	
		for (Kunde kun : bank.getKundenliste()) {
			if(kun.getName().equals(transfernametext.getText())) {	
	for (int k = 0; k < kun.getKontos().size(); k++) {
		if((k)==Integer.parseInt(transferkontotext.getText())) {
			
		   	if(!transferbanktext.getText().isEmpty()&&!transfernametext.getText().isEmpty()&&!transferkontotext.getText().isEmpty()&&numcheck(transferbetragtext.getText())&&kontoinfo.getText()!="")
	    	{
    	for (Bank bankvon : banken) {
	    		if(bankvon.getName()==bankheading.getText()) {
	    		for (Kunde ku : bankvon.getKundenliste()) {
    			if(ku.getName().equals(kundeheading.getText())) {
    	for (int i1 = 0; i1 < ku.getKontos().size(); i1++) {
				if((i1+1)==Integer.parseInt(kontoinfo.getText())) {

				outputtext.setText(	ku.getKontos().get(i1).überweisen(
		kun.getKontos().get(k)
			, Double.parseDouble(transferbetragtext.getText())));
					;
				}
	    	}
			}
				}}}
	    			}	
		}
			}
		}}
}
	}
    }
}
