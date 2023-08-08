package CabinetMedical1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ListeRdvFrame {

	private JFrame frmGestionDesRendezvous;
	public static JTable table;
	
	ImageIcon img = new ImageIcon("nouveauPatient.png");
	ImageIcon imgLogout = new ImageIcon("Logout.png");
	ImageIcon imgDelete = new ImageIcon("delete.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeRdvFrame window = new ListeRdvFrame();
					window.frmGestionDesRendezvous.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListeRdvFrame() {
		initialize();
	}
	
	
	public static void refreshRdv(){//actualiser la table
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="(select rendezvous.numrendezvous, patient.nom, patient.prenom, rendezvous.datere, rendezvous.heure, rendezvous.matricule, rendezvous.modif "
					+ "from patient, rendezvous where patient.IDPatient = rendezvous.IDPatient ) union"
							+ "(select numrendezvous,nom,prenom, datere, heure,matricule, modif from prendezvous)";
			ResultSet rs = stmt.executeQuery(sql);
			DefaultTableModel model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
			model.addColumn("   ");
			table.setModel(model);
			for(int i=0; i<table.getRowCount();i++){
				if (table.getValueAt(i, 0).toString().contains("p")) {
					table.setValueAt("Nouveau", i,7);
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionDesRendezvous = new JFrame();
		frmGestionDesRendezvous.setTitle("Gestion des rendez-vous");
		frmGestionDesRendezvous.getContentPane().setBackground(SystemColor.text);
		frmGestionDesRendezvous.setBounds(100, 100, 706, 447);
		frmGestionDesRendezvous.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionDesRendezvous.getContentPane().setLayout(null);
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestionDesRendezvous.dispose();
				if(Login.usertype==0){
					MenuMedecin.main(null);
				}
				else{
					Menu.main(null);
				}
			}
		});
		btnRetour.setBounds(579, 379, 89, 23);
		frmGestionDesRendezvous.getContentPane().add(btnRetour);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//initialisation des champs de la fênetre modifier rendez-vous
				ModifierRdvFrame.NumRendezVous=table.getValueAt(table.getSelectedRow(),0).toString();
				ModifierRdvFrame.date=new SimpleDateFormat("dd/MM/yyyy").format(table.getValueAt(table.getSelectedRow(),3));
				ModifierRdvFrame.Heure=table.getValueAt(table.getSelectedRow(),4).toString();
				//appel de la fênetre
				ModifierRdvFrame.main(null);
			}
		});
		btnModifier.setBounds(462, 379, 107, 23);
		frmGestionDesRendezvous.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//demande de la confirmation pour supprimer le rendez-vous
				int action =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer ce rendez-vous?");
				if(action==0){
					if (!table.getValueAt(table.getSelectedRow(), 0).toString().contains("p")) {//cas où le patient est enregistré sur la base de données
						Rendezvous.supprimerRdv(table.getValueAt(table.getSelectedRow(), 0).toString());
					}
					else{//cas où le patient n'est pas enregistré sur la base de données
						PRendezvous.supprimerPRdv(table.getValueAt(table.getSelectedRow(),0).toString());
					}
					JOptionPane.showMessageDialog(null,"Rendez-vous supprimé!");
					refreshRdv();//actualiser la table
				}
			}
		});
		btnSupprimer.setBounds(329, 379, 123, 23);
		frmGestionDesRendezvous.getContentPane().add(btnSupprimer);
		btnSupprimer.setIcon(imgDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 670, 314);
		frmGestionDesRendezvous.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refreshRdv();
		
		
		JButton btnNouveauRendezVous = new JButton("Nouveau rendez vous");
		btnNouveauRendezVous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NouveauRdvFrame.main(null);
			}
		});
		btnNouveauRendezVous.setBounds(298, 4, 204, 23);
		frmGestionDesRendezvous.getContentPane().add(btnNouveauRendezVous);
		btnNouveauRendezVous.setIcon(img);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestionDesRendezvous.dispose();
				Login.main(null);
			}
		});
		btnDeconnexion.setBounds(512, 4, 156, 23);
		frmGestionDesRendezvous.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setIcon(imgLogout);
	}
}

