package CabinetMedical1;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Visite {
	protected String NumRendezVous;
	protected Medecin medecin;
	protected Date date;
	protected String heure;
	protected String createur;
	
	public String getNumRendezVous() {
		return NumRendezVous;
	}
	public void setNumRendezVous(String numRendezVous) {
		NumRendezVous = numRendezVous;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getCreateur() {
		return createur;
	}
	public void setCreateur(String createur) {
		this.createur = createur;
	}
	
    public abstract void ajouterRdv();
    
    public boolean VerifierValidite(){
    	if(this.date.compareTo(new Date())>0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
