package CabinetMedical1;

import java.sql.*;

public class Medicament {
	//attributs
	private String Nom, Dosage, Duree;
	int NbrDse;
	//Constructeurs
	public Medicament(String nom, String dosage, int nbrDse, String duree) {
		Nom = nom;
		Dosage = dosage;
		NbrDse = nbrDse;
		Duree = duree;
	}
	//setters&getters
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDosage() {
		return Dosage;
	}
	public void setDosage(String dosage) {
		Dosage = dosage;
	}
	public int getNbrDse() {
		return NbrDse;
	}
	public void setNbrDse(int nbrDse) {
		NbrDse = nbrDse;
	}
	public String getDuree() {
		return Duree;
	}
	public void setDuree(String duree) {
		Duree = duree;
	}
	//méthodes d'instances
	public static boolean chercherMedicament(String numOrdonnance, String nom){ 
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "select*from ordonnance_medoc  where numordonnance ='"+numOrdonnance+"' and NomMedoc='"+nom+"'";
			ResultSet rs = stmt.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void ajouterMedicament(String numOrdonnance,Medicament m){
		if(chercherMedicament(numOrdonnance,m.getNom())==false){
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "insert into ordonnance_medoc  values('"+numOrdonnance+"','"+m.getNom()+"','"+m.getDosage()
						+"',"+m.getNbrDse()+",'"+m.getDuree()+"')";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void modifierMedicament(String numOrdonnance,String nom, String dosage,int nbrDse, String duree){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "update ordonnance_medoc  set dosage='"+dosage+"', NbrDeDose="+nbrDse+",  Duree ='"+duree+"' where"
					+"(numOrdonnance ='"+numOrdonnance+"' and NomMedoc='"+nom+"')";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void supprimerMedicament(String numOrdonnance, String nom){
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "delete from ordonnance_medoc  where numOrdonnance ='"+numOrdonnance+"' and NomMedoc='"+nom+"'";	
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
