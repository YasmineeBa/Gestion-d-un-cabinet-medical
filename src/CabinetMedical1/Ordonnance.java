package CabinetMedical1;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Ordonnance {
	//les attributs
	private String NumOrdonnance;
	private Vector<Medicament> medicaments;
	//constructeurs
	public Ordonnance() {
		this.NumOrdonnance=genererNumOrdonnance();
		this.medicaments=new Vector<Medicament>();
	}
	//setters and getters
	public String getNumOrdonnance() {
		return NumOrdonnance;
	}
	public void setNumOrdonnance(String numOrdrdonnance) {
		NumOrdonnance = numOrdrdonnance;
	}
	public Vector<Medicament> getMedicament() {
		return medicaments;
	}
	public void setMedicament(Vector<Medicament> médicament) {
		this.medicaments = médicament;
	}
   
	//méthodes d'instance
		public static String genererNumOrdonnance(){
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql="select count(*)  from Ordonnance";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					return(String.valueOf(rs.getInt(1)+1));
				}
				else{
					return("01");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				return " ";
			}
		}
		
		
		public static void ajouterOrdonnance(String CleCons,Ordonnance Ord){
			try {
				Connection conn = SqlConnection.dbConnector();
				Statement stmt = conn.createStatement();
				String sql = "insert into Ordonnance values ('"+Ord.getNumOrdonnance()+"','"+CleCons+"')";
				stmt.executeQuery(sql);
				for(int i=0; i<Ord.getMedicament().size();i++){
					Medicament.ajouterMedicament(Ord.getNumOrdonnance(), Ord.getMedicament().get(i));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		public static void SupprimerOrdonnace(String numOrdonnance){
			try{
				Connection conn= SqlConnection.dbConnector();
				Statement stmt=conn.createStatement();
				String sql="delete from Ordonnance where NumOrdonnance = '"+numOrdonnance+"'";
				
				stmt.executeQuery(sql);
				 sql = "delete from ordonnance_medoc  where NumOrdonnance = '"+numOrdonnance+"'";
				 stmt.executeQuery(sql);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
}
