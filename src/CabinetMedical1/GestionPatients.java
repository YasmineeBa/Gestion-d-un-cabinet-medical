package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JTextField;

import java.util.Date;

import javax.swing.JLabel;

public class GestionPatients {
	
	ImageIcon img = new ImageIcon("nouveauPatient.png");
	ImageIcon imgLogout = new ImageIcon("Logout.png");
	ImageIcon imgSearch = new ImageIcon("Search.png");
	ImageIcon imgDelete = new ImageIcon("delete.png");

	private JFrame frmGestionDesPatients;
	public static JTable table;
	public static int UpdatingRow;
	//public static double RowCount;
	private JTextField textRecherche;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPatients window = new GestionPatients();
					window.frmGestionDesPatients.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionPatients() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	public void refresh(){
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select*from patient";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private void initialize() {
		frmGestionDesPatients = new JFrame();
		frmGestionDesPatients.getContentPane().setBackground(Color.WHITE);
		frmGestionDesPatients.setTitle("Gestion des patients");
		frmGestionDesPatients.setBounds(100, 100, 835, 624);
		frmGestionDesPatients.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionDesPatients.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 799, 472);
		frmGestionDesPatients.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refresh();
		
		
		JButton btnNouveauPatient = new JButton("Nouveau patient");
		btnNouveauPatient.setForeground(SystemColor.desktop);
		btnNouveauPatient.setBounds(482, 7, 154, 29);
		btnNouveauPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// RowCount=table.getRowCount();
				frmGestionDesPatients.dispose();
				NouveauPatient.main(null);//appel de la fênetre nouveau patient
			}
		});
		frmGestionDesPatients.getContentPane().add(btnNouveauPatient);
		
		btnNouveauPatient.setIcon(img);
		
		JButton btnSupprimerUnPatient = new JButton("Supprimer");
		btnSupprimerUnPatient.setBounds(394, 552, 141, 23);
		btnSupprimerUnPatient.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Date dd = new Date();
				Date id =(Date) table.getValueAt(table.getSelectedRow(),8);
				if ((dd.getYear())-id.getYear()>5) {//vérifier le patient n'a pas fait de visite depis plus de 5 ans
					int action = JOptionPane.showConfirmDialog(null,
							"Voulez-vous vraiment supprimer ce patient?");
					if (action == 0) {//demander la confirmation pour supprimer le patient
						//suppression du patient
						int row = table.getSelectedRow();
						String code = (String) table.getValueAt(row, 0);
						Patient.SupprimerPatient(code);
						JOptionPane.showMessageDialog(null, "Patient supprimé");
						//actualiser la table
						refresh();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Le patient doit arrêter les visites pendant au moins 5 ans pour pouvoir le supprimer");
				}
			}
		});
		frmGestionDesPatients.getContentPane().add(btnSupprimerUnPatient);
		btnSupprimerUnPatient.setIcon(imgDelete);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(545, 552, 134, 23);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//appel de la fêtre modifier patient
				UpdatingRow= table.getSelectedRow();
				frmGestionDesPatients.dispose();
				ModifierPatientFrame.main(null);
			}
		});
		frmGestionDesPatients.getContentPane().add(btnModifier);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(689, 552, 120, 23);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestionDesPatients.dispose();
				if(Login.usertype==0){//si l'utilsateur est un médecin
					MenuMedecin.main(null);
				}
				else{//l'utilisateur est une secrétaire
					Menu.main(null);
				}
			}
		});
		frmGestionDesPatients.getContentPane().add(btnRetour);

		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setBackground(SystemColor.activeCaption);
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestionDesPatients.dispose();
				Login.main(null);
			}
		});
		btnDeconnexion.setBounds(646, 7, 163, 29);
		frmGestionDesPatients.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setIcon(imgLogout);
		
		textRecherche = new JTextField();
		textRecherche.setBounds(10, 15, 202, 20);
		frmGestionDesPatients.getContentPane().add(textRecherche);
		textRecherche.setColumns(10);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//séparation du nom du prénom
				String research=textRecherche.getText();
				String[] parts=research.split("\\s+");
				if (parts.length==2) {// vérifier que l'utilisateur a entré un nom et un prénom 
					try {//recherche
						Connection conn = SqlConnection.dbConnector();
						Statement stmt = conn.createStatement();
						String sql = "select*from patient where LOWER(nom)='"
								+ parts[0].toLowerCase()
								+ "' and LOWER(prenom)='"
								+ parts[1].toLowerCase() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Vous devez entrer le nom complet");
				}
				
			}
		});
		btnChercher.setBounds(222, 13, 120, 23);
		frmGestionDesPatients.getContentPane().add(btnChercher);
		btnChercher.setIcon(imgSearch);
		
		JLabel lblNouveau = new JLabel("");
		lblNouveau.setBounds(424, 7, 96, 35);
		frmGestionDesPatients.getContentPane().add(lblNouveau);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 819, 586);
		frmGestionDesPatients.getContentPane().add(lblNewLabel);
	}
}
