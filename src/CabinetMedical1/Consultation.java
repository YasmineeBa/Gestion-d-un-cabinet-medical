package CabinetMedical1;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Consultation {
	private String cle;
	private Patient patient;
	private Medecin medecin;
	private Date date;
	private Vector<Maladie> maladies;
	private Vector<Analyse> analyses;
	private String Observation;
	private Ordonnance ordonnance;
	private double Montant;
	
	public Consultation() {
		this.patient = new Patient();
		this.medecin = new Medecin();
		this.maladies= new Vector<Maladie>();
		this.analyses= new Vector<Analyse>();
		this.ordonnance = new Ordonnance();
		this.cle = genererCode();
		this.date = new Date();
	}
	
	
	public String getCle() {
		return cle;
	}
	public void setCle(String cle) {
		this.cle = cle;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
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
	public String getObservation() {
		return Observation;
	}
	public void setObservation(String observation) {
		Observation = observation;
	}
	public double getMontant() {
		return Montant;
	}
	public void setMontant(double montant) {
		Montant = montant;
	}
	public Vector<Maladie> getMaladies() {
		return maladies;
	}
	public void setMaladies(Vector<Maladie> maladies) {
		this.maladies = maladies;
	}
	public Vector<Analyse> getAnalyses() {
		return analyses;
	}
	public void setAnalyses(Vector<Analyse> analyses) {
		this.analyses = analyses;
	}
	public Ordonnance getOrdonnance() {
		return ordonnance;
	}
	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}
	
	
	public static String genererCode(){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String concatDate =new SimpleDateFormat("ddMMyy").format(new Date());
			String sql =" select count(*) from (select*from consultation where (cleconsultation like '"+concatDate+"%'))";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				int sequence = rs.getInt(1)+1;
				return concatDate+String.valueOf(sequence);
			}
			else{
				return concatDate+"00";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public boolean chercherMaladieConsultation(String nom){
		for (int i = 0; i < this.maladies.size(); i++) {
			if (this.maladies.get(i).getNom().compareTo(nom) == 0) {
				return true;
			}
		}
		return false;
	}
	public void ajouterMaladie(String nom){
		this.getMaladies().add(new Maladie(nom, 0));
	}
	public void ajouterAnalyse(String numAnalyse, String analyse){
		this.getAnalyses().add(new Analyse(numAnalyse,analyse,new Date()));
	}
	public void ajouterConsultation(){
		//Ajout du reste des éléments
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			//convertion de la date
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String d = sdf.format(this.getDate());
			String sql = "insert into consultation values('"+this.getCle()+"','"+this.getPatient().getCode()+"','"
					+this.getMedecin().getMatricule()+"','"+d+"','"+this.getObservation()+"',"+this.Montant+")";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Ajout des maladies
		for(int i=0;i<this.getMaladies().size();i++){
			Maladie.AjouterMaladie(this.getMaladies().get(i).getNom(), this.getPatient().getCode(),this.getCle());
		}
		//Ajout des analyses
		for(int i=0;i<this.getAnalyses().size();i++){
			Analyse.ajouterAnalyse(this.getPatient().getCode(), this.getCle(), this.getAnalyses().get(i).getNumAnalyse(),
					this.getAnalyses().get(i).getBilan(), this.getAnalyses().get(i).getDateAnalyse());
		}
		if (this.getOrdonnance().getMedicament().size()>0) {
			//Ajout de l'ordonnance
			Ordonnance.ajouterOrdonnance(this.getCle(), this.getOrdonnance());
		}
	}
	public static void supprimerConsultation(String cle){
		try {
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			//Supression des ordonnances
			String sql="select NumOrdonnance from ordonnance where CleConsultation ='"+cle+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Ordonnance.SupprimerOrdonnace(rs.getString(1));
			}
			//suppression des maladies
			sql = "select nommaladie from maladie_consultation where cleconsultation='"+cle+"'"; //selectionner les maladies
			rs=stmt.executeQuery(sql);
			//descrementer le nomber de malades
			String sql2="";
			ResultSet rsm ;
			while(rs.next()){
				sql2= "select*from maladie where nommaladie='"+rs.getString(1)+"'";
				rsm=stmt.executeQuery(sql2);
				if (rs.next()) {
					Maladie.ModifierMaladie(rsm.getString(1), rsm.getInt(2) - 1);
				}
			}
			sql="delete from maladie_consultation where cleconsultation='"+cle+"'";//surrpimer les maladies
			stmt.executeQuery(sql);
			//suppression des analyses
			sql="delete from Analyse where cleconsultation = '"+ cle+"'";
			stmt.executeQuery(sql);
			//suppression de la consultation 
			sql="delete from consultation where cleconsultation='"+cle+"'";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
