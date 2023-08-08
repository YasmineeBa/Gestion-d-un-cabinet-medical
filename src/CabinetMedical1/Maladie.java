package CabinetMedical1;


import java.sql.*;

import javax.swing.JOptionPane;

public class Maladie {
	//attributs
	private String nom;
	private int NbrMalade;
	//constructeur
	public Maladie( String nom,int NbrMalade) {
		this.nom = nom;
		this.NbrMalade=NbrMalade;
	}
	public Maladie() {
	}
	//setters&getters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbrMalade() {
		return NbrMalade;
	}
	public void setNbrMalade(int nbrMalade) {
		NbrMalade = nbrMalade;
	}
	//m�thodes d'instances
	public static Maladie ChercherMaladie(String Nom){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "select*from maladie where nomMaladie='"+Nom+"'";
			ResultSet rs =stmt.executeQuery(sql);
			if(rs.next()){
				Maladie m= new Maladie(rs.getString(1),rs.getInt(2));
				return m;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static boolean chercherMaladieFiche(String nom, String numFiche){
		try {
			Connection conn= SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "select * from fiche_maladie where numFiche='"+numFiche+"' and nomMaladie='"+nom+"'";
			ResultSet rs=stmt.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public static void AjouterMaladie(String Nom, String numFiche,String cleConsultation){
		Maladie m = new Maladie();
		m=ChercherMaladie(Nom);
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql;
				if (m == null) {//la maladie n'existe pas>>Ajouter la maladie dans la base donn�es
					sql = "insert into maladie values ('" + Nom + "'," + 1
							+ ")";
					stmt.executeQuery(sql);
				} else {//sinon incremener le nombre de malades ayant cette maladie
					ModifierMaladie(m.getNom(), (m.getNbrMalade() + 1));
				}
				sql = "insert into fiche_maladie values ('" + numFiche + "','"
						+ Nom + "')";
				stmt.executeQuery(sql);
				sql ="insert into maladie_consultation values('"+Nom+"','"+cleConsultation+"')";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void ModifierMaladie(String nom, int NbrMalade){
		if (ChercherMaladie(nom)==null) {
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "update maladie set Nom='" + nom + "', NbrMalade="
						+ NbrMalade;
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	public static void SupprimerMaladie(String Nom){
		if (ChercherMaladie(Nom)==null) {
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "delete from maladie where nom = " + Nom;
				stmt.executeQuery(sql);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
