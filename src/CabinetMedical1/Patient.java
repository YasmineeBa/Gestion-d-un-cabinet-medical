package CabinetMedical1;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

import javax.swing.JOptionPane;
public class Patient {
	//attribut
	private String code;
	private String nom;
	private String prenom;
	private String sexe;
	private Date DateDeNaissance;
	private String adresse;
	private String Tel;
	private String mail;
	private Date DateInsr;
	private String Modif;
	//contruteurs
	public Patient() {
	}
	public Patient(String code,String nom, String prenom, String sexe,
			Date dateDeNaissance, String adresse, String tel, String mail,
			Date dateInsr, String modif) {
		this.code = code ;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		DateDeNaissance = dateDeNaissance;
		this.adresse = adresse;
		Tel = tel;
		this.mail = mail;
		DateInsr = dateInsr;
		Modif = modif;
	}
	//getters
	public String getCode() {
		return code;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public Date getDateDeNaissance() {
		return DateDeNaissance;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getTel() {
		return Tel;
	}
	public String getMail() {
		return mail;
	}
	public String getModif(){
		return this.Modif;
	}
	public Date getDateInsr() {
		return DateInsr;
	}
	//setters
	public void setCode(String code) {
		this.code = code;
	}	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		DateDeNaissance = dateDeNaissance;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setModif(String modif){
		this.Modif= modif;
	}
	public void setDateInsr(Date dateInsr) {
		DateInsr = dateInsr;
	}
	//méthodes d'intance
	public static boolean chercherPatient(String code){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="select*from patient where IDPatient = '"+code+"'";
			ResultSet rs= stmt.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void AjouterPatient(Patient p){
			if (chercherPatient(p.getCode())==false) {
				try {
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					//conversion des dates
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String bd = sdf.format(p.getDateDeNaissance());
					String sd = sdf.format(p.getDateInsr());
					//création de l'instruction
					String sql = "insert into patient values ('" + p.getCode()
							+ "','" + p.getNom() + "','" + p.getPrenom()
							+ "','" + p.getSexe() + "','" + bd + "','"
							+ p.getAdresse() + "','" + p.getTel() + "','"
							+ p.getMail() + "','" + sd + "','" + p.getModif()
							+ "')";
					//execution
					stmt.executeQuery(sql);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Le patient existe déja dans la base de données");
			}
	}
	public static void SupprimerPatient(String Code){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
		    String sql = "select cleconsultation from consultation where idpatient='"+Code+"'"; //suppression des consultation
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				Consultation.supprimerConsultation(rs.getString(1));
			}
			FicheMedicale.SupprimerFicheMedicale(Code); //suppression de sa fiche médicale
			sql="delete from RendezVous where IDPatient = '"+ Code+"'"; //suppression des rendez-vous
			stmt.executeQuery(sql);
		    sql="delete from patient where IDPatient = '"+ Code+"'";//suppression du patient
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void ModifierPatient(String code, String nom, String prenom, String sexe,
			String dateDeNaissance, String adresse,String tel, String mail){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			//Modifier les information des patients
			String sql = "update patient set nom='"+nom+"',prenom='"+prenom+"',sexe='"+sexe+"', DateNaiss='"+dateDeNaissance+
					"', adresse='"+adresse+"', tel='"+tel+"', email='"+mail+"',modif='"+Login.user+"' where IDPatient='"+code+"'";
			stmt.executeQuery(sql);
			//Modifier l'âge dans la fiche médicale
			int Age = new Date().getYear()-new Date(dateDeNaissance).getYear();
			sql="update fichemedicale set age ="+Age+" where numfiche='"+code+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
}
