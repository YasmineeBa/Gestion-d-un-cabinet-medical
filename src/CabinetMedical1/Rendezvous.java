package CabinetMedical1;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
public class Rendezvous extends Visite{
	//attributs
	private Patient patient;
	//constructeur
	public Rendezvous(String NumRendezVous,Patient patient, Medecin medecin, Date date,String Heure,
			String createur) {
		this.NumRendezVous=NumRendezVous;
		this.patient=patient;
		this.medecin=medecin;
		this.date=date;
		this.heure=Heure;
		this.createur=createur;
	}
	public Rendezvous() {
		super();
	}
	
	//setters&getters
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	//méthodes d'instance
	
	public static String genererNumRdv(){
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select numrendezvous from rendezvous order by numrendezvous desc";
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
	
	public void ajouterRdv(){
			try{
				Connection conn= SqlConnection.dbConnector();
				Statement stmt=conn.createStatement();
				//conversion des dates
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dr= sdf.format(this.getDate());
				//création de l'instruction
				String sql ="insert into RendezVous values ('"+this.getNumRendezVous()+"','"+this.getPatient().getCode()+"','"+
				this.getMedecin().getMatricule()+"','"+dr+"','"+this.getHeure()+"','"+this.getCreateur()+"')";
				//execution
				stmt.executeQuery(sql);
			}
			catch(Exception e){
				System.out.println(e);
			}
	}
	public static void supprimerRdv(String NumRdv){
		try{
			Connection conn= SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="delete from RendezVous where NumRendezVous = '"+NumRdv+"'";
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
			String sql = "update RendezVous set  DateRe='"+date+"', heure='"+Heure+"', modif='"+Login.user
					+"' where NumRendezvous='"+NumRendezVous+"'";
			stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}	
	}
}
