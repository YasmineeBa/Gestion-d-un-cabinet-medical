package CabinetMedical1;

import java.sql.Connection;
import java.sql.Statement;

public class Secretaire {
	//attributs
	private String matricule, Nom, Prenom, NBureau, password;
	//constructeurs

	public Secretaire(String matricule, String nom, String prenom,
			String nBureau, String password) {
		this.matricule = matricule;
		Nom = nom;
		Prenom = prenom;
		NBureau = nBureau;
		this.password=password;
	}

	public Secretaire() {
	}
	//getters & setters

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	public String getNBureau() {
		return NBureau;
	}

	public void setNBureau(String nBureau) {
		NBureau = nBureau;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public static void Modifier(String Matricule, String mdp){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="alter table secretaire set mdp='"+mdp+"'  where matricule = '"+Matricule+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
