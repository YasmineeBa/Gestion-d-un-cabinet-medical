package CabinetMedical1;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoriqueConsFrame {
	ImageIcon imgSearch = new ImageIcon("Search.png");

	private JFrame frame;
	private JTextField textRecherche;
	private JTable table;
	public static String OpeningRow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoriqueConsFrame window = new HistoriqueConsFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HistoriqueConsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 786, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textRecherche = new JTextField();
		textRecherche.setBounds(10, 11, 150, 20);
		frame.getContentPane().add(textRecherche);
		textRecherche.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 11, 117, 20);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Patient");
		comboBox.addItem("Médecin");
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//séparer le nom du prénom
					String research=textRecherche.getText();
					String[] parts=research.split("\\s+");
					Connection conn=SqlConnection.dbConnector();
					Statement stmt=conn.createStatement();
					String sql=" ";
					if (parts.length==2) {//vérifier que l'utlisateur a entré un nom complet
						if (((String) comboBox.getSelectedItem()) == "Patient") {//dans le cas où l'utilisateur fait une rechercher par patient
							sql = "select consultation.CleConsultation, Patient.Nom, Patient.Prenom, Medecin.Nom as Nom_Medecin,"
									+ " Medecin.Prenom as prenom_medecin, "
									+ "consultation.DateConsu, consultation.montant, consultation.observation from consultation, patient, medecin"
									+ " where (lower(Patient.Nom)='"
									+ parts[0].toLowerCase()
									+ "' and lower(Patient.Prenom)='"
									+ parts[1].toLowerCase()
									+ "' and Patient.IDPatient= Consultation.IDPatient"
									+ " and medecin.matricule= consultation.matricule) order by CleConsultation";
						} else {//dans le cas où l'utilisateur fait une rechercher par médecin
							sql = "select consultation.CleConsultation, Patient.Nom, Patient.Prenom, Medecin.Nom as Nom_Medecin,"
									+ " Medecin.Prenom as prenom_medecin, "
									+ "consultation.DateConsu, consultation.montant, consultation.observation from consultation, patient, medecin"
									+ " where (lower(medecin.Nom)='"
									+ parts[0].toLowerCase()
									+ "' and lower(medecin.Prenom)='"
									+ parts[1].toLowerCase()
									+ "' and medecin.matricule= consultation.matricule"
									+ " and Patient.IDPatient= Consultation.IDPatient) order by CleConsultation";
						}
						ResultSet rs=stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					else{
						JOptionPane.showMessageDialog(null,"Vous devez saisir le nom complet");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRecherche.setBounds(297, 10, 117, 23);
		frame.getContentPane().add(btnRecherche);
		btnRecherche.setIcon(imgSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 750, 344);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{//remplissage de la table
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select consultation.CleConsultation, Patient.Nom, Patient.Prenom, Medecin.Nom as Nom_Medecin,"
									+ " Medecin.Prenom as prenom_medecin, "
									+ "consultation.DateConsu, consultation.montant, consultation.observation from consultation, patient, medecin"
									+" where (medecin.matricule= consultation.matricule"
									+ " and Patient.IDPatient= Consultation.IDPatient)"
									+ " order by CleConsultation";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JButton btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.dispose();
				}
			});
			btnRetour.setBounds(671, 408, 89, 23);
			frame.getContentPane().add(btnRetour);
			
			JButton btnOuvrir = new JButton("Ouvrir");
			btnOuvrir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					OpeningRow = String.valueOf((String)table.getValueAt(table.getSelectedRow(),0));//sélectionner la consultation
					ConsultationFrame.main(null);//appeler la frame qui affiche les détails de la consultation
				}
			});
			btnOuvrir.setBounds(572, 408, 89, 23);
			frame.getContentPane().add(btnOuvrir);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
