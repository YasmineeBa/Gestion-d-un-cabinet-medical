package CabinetMedical1;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class PRendezvous extends Visite {
	private String Nom,Prenom;
	
	public PRendezvous(String NumRendezVous, Medecin medecin, Date date,
			String heure, String createur, String nom, String prenom) {
		this.NumRendezVous=NumRendezVous;
		this.medecin=medecin;
		this.date=date;
		this.heure=heure;
		this.createur=createur;
		Nom = nom;
		Prenom = prenom;
	}
	
	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public String getPrenom() {
		return Prenom;
	}


	public void setPrenom(String prenom) {
		Prenom = prenom;
	}


	public static String genererPNumRdv(){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select numrendezvous from prendezvous order by numrendezvous desc";
			ResultSet rs = stmt.executeQuery(sql);
			String code = null;
			if (rs.next()) {
				code = rs.getString(1);
				code=code.substring(1);//supprimer le 'p' au début de numéro
				return("p"+String.valueOf(Integer.parseInt(code)+1));
			}
			else{
				return("p1");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return " ";
		}
	}
	@Override
	public void ajouterRdv() {
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			//conversion des dates
			String d = new SimpleDateFormat("dd/MM/yyyy").format(this.getDate());
			String sql = "insert into PRendezvous values('"+this.getNumRendezVous()+"','"+this.getNom()+"','"+this.getPrenom()
			             +"','"+this.getMedecin().getMatricule()+"','"+d+"','"+this.getHeure()+"','"+this.getCreateur()+"')";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void supprimerPRdv(String NumRdv){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="delete from PRendezVous where NumRendezVous = '"+NumRdv+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void modifierRdv(String NumRendezVous,String date,String Heure){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql = "update PRendezVous set  DateRe='"+date+"', heure='"+Heure+"', modif='"+Login.user
					+"' where NumRendezvous='"+NumRendezVous+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
}
