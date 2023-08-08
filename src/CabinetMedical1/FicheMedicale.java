package CabinetMedical1;

import java.sql.*;
import java.util.Vector;

public class FicheMedicale {
	//attributs
	private String NumFiche; //NumFiche=CodePatient
	private Patient patient;
	private Medecin medecin;
	private int age;
	private String GroupeSanguin;
	private int taille, poids;
	private String Tension;
	private double TauxDiabete;
	private Vector<Analyse> analyses;
	private Vector<Maladie> maladies;
	private String modif;
	//constructeurs
	public FicheMedicale(String numFiche, int age,
			String groupeSanguin, int taille, int poids, String tension,
			double tauxDiabete, String modif) {
		this.patient=new Patient();
		this.medecin=new Medecin();
		this.analyses=new Vector<Analyse>();
		this.maladies=new Vector<Maladie>();
		NumFiche = numFiche;
		this.age= age;
		GroupeSanguin = groupeSanguin;
		this.taille = taille;
		this.poids = poids;
		Tension = tension;
		TauxDiabete = tauxDiabete;
		this.modif = modif;
	}
	public FicheMedicale(String NumFiche, int age) {
		this.patient=new Patient();
		this.medecin=new Medecin();
		this.analyses=new Vector<Analyse>();
		this.maladies=new Vector<Maladie>();
		this.NumFiche= NumFiche;
		this.age=age;
		this.GroupeSanguin=" ";
		this.taille=0;
		this.poids=0;
		this.TauxDiabete=0;
	}
	//setters&getters
	public String getNumFiche() {
		return NumFiche;
	}
	public void setNumFiche(String numFiche) {
		NumFiche = numFiche;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public String getGroupeSanguin() {
		return GroupeSanguin;
	}
	public void setGroupeSanguin(String groupeSanguin) {
		GroupeSanguin = groupeSanguin;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
	public String getTension() {
		return Tension;
	}
	public void setTension(String tension) {
		Tension = tension;
	}
	public double getTauxDiabete() {
		return TauxDiabete;
	}
	public void setTauxDiabete(double tauxDiabete) {
		TauxDiabete = tauxDiabete;
	}
	public Vector<Analyse> getAnalyses() {
		return analyses;
	}
	public void setAnalyses(Vector<Analyse> analyses) {
		this.analyses = analyses;
	}
	public String getModif() {
		return modif;
	}
	public void setModif(String modif) {
		this.modif = modif;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Vector<Maladie> getMaladies() {
		return maladies;
	}
	public void setMaladies(Vector<Maladie> maladies) {
		this.maladies = maladies;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//méthodes d'instances
	public static FicheMedicale ChercherFicheMedicale(String NumFiche){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "select*from fichemedicale where numfiche='"+NumFiche+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				FicheMedicale f= new FicheMedicale(NumFiche,rs.getInt(3),rs.getString(4),rs.getInt(6),rs.getInt(7),rs.getString(5),rs.getDouble(8),rs.getString(9));
				return f;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void ajouterFicheMedicale(FicheMedicale fm){
		if(ChercherFicheMedicale(fm.getNumFiche())==null){
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "insert into fichemedicale values('"+fm.getNumFiche()+"','"+fm.getMedecin().getMatricule()
						+"','"+fm.getAge()+"','"
						+fm.getGroupeSanguin()+"',"+fm.getTension()+","+fm.getTaille()+","+fm.getPoids()+","+fm.getTauxDiabete()
						+",'"+fm.getModif()+"')";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void modifierFicheMedicale(String numFiche, String groupeSanguin, int taille, int poids, String tension,
			double tauxDiabete){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql = "update fichemedicale set grpsanguin='"+groupeSanguin+"', taille="+taille+", poids=" +poids+
			", tauxdiab="+tauxDiabete+" ,tension='"+tension+"', modif='"+Login.user+"' where numfiche='"+numFiche+"'";
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void SupprimerFicheMedicale(String numFiche){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="delete from Analyse where numfiche = '"+ numFiche+"'";
			stmt.executeQuery(sql);
			sql ="delete from fiche_maladie where numFiche='"+numFiche+"'";
			stmt.executeQuery(sql);
			sql = "delete from FicheMedicale where numfiche = '"+ numFiche+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
