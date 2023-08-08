package CabinetMedical1;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Analyse {
	//attributs
	private String NumAnalyse, Bilan, Resultat, Commentaire;
	private Date DateAnalyse;
	//constructeurs
	public Analyse() {
	}
	public Analyse(String numAnalyse, String bilan, Date dateAnalyse) {
		NumAnalyse = numAnalyse;
		Bilan = bilan;
		DateAnalyse = dateAnalyse;
	}
	public Analyse(String numAnalyse, String bilan, String resultat,
			String commentaire) {
		NumAnalyse = numAnalyse;
		Bilan = bilan;
		Resultat = resultat;
		Commentaire = commentaire;
		DateAnalyse = new Date();
	}
	//setters&getters
	public String getNumAnalyse() {
		return NumAnalyse;
	}
	public void setNumAnalyse(String numAnalyse) {
		NumAnalyse = numAnalyse;
	}
	public String getBilan() {
		return Bilan;
	}
	public void setBilan(String bilan) {
		Bilan = bilan;
	}
	public String getResultat() {
		return Resultat;
	}
	public void setResultat(String resultat) {
		Resultat = resultat;
	}
	public String getCommentaire() {
		return Commentaire;
	}
	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}
	public Date getDateAnalyse() {
		return DateAnalyse;
	}
	public void setDateAnalyse(Date dateAnalyse) {
		DateAnalyse = dateAnalyse;
	}
	//méthodes d'instance
	public static String genererNumAnalyser(){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select count(*)  from analyse";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return String.valueOf(rs.getInt(1)+1);
			}
			else{
				return ("01");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public static void ajouterAnalyse(String NumFiche,String cleConsultation,String NumAnalyse, String Bilan, Date dateAnalyse){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			//conversion des dates
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String d = sdf.format(dateAnalyse);
			String sql = "insert into analyse values ('"+NumAnalyse+"','"+NumFiche+"','"+Bilan+"','  ','  ','"
			                                            +d+"','"+cleConsultation+"')";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void modifierAnalyse(String numAnalyse, String bilan, String resultat, String commentaire){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "update analyse set bilan='"+bilan+"', resultat='"+resultat+"', commentaire ='"+commentaire+"' where"
					+" numanalyse='"+numAnalyse+"'";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
