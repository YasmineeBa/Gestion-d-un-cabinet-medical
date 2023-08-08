package CabinetMedical1;

import java.sql.Connection;
import java.sql.Statement;

public class Medecin extends Secretaire {
	//attributs
	private String sepcialite;
	//constructeurs

	public Medecin(String matricule, String nom, String prenom, String nBureau,
			String password, String sepcialite) {
		super(matricule, nom, prenom, nBureau, password);
		this.sepcialite = sepcialite;
	}
	public Medecin() {
		super();
	}
	//getters&setters
	public String getSepcialite() {
		return sepcialite;
	}
	public void setSepcialite(String sepcialite) {
		this.sepcialite = sepcialite;
	}
	
	public static void Modifier(String Matricule, String mdp){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="update medecin set mdp='"+mdp+"'  where matricule = '"+Matricule+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
