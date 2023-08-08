package CabinetMedical1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParcourPatient {

	private JFrame frame;
	public static JTable ParcourPtable;
	private int openingrow;
	public static String parcourp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParcourPatient window = new ParcourPatient();
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
	public ParcourPatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 721, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 685, 323);
		frame.getContentPane().add(scrollPane);
		
		ParcourPtable = new JTable();
		scrollPane.setViewportView(ParcourPtable);
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql ="select IDPatient, Nom, prenom, Sexe, DateNaiss as date_de_naissance, adresse, tel, email,DateInsc as date_dinscription, modif as dernier_modificateur from patient";
			ResultSet rs = stmt.executeQuery(sql);
			ParcourPtable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnAnnuler.setBounds(594, 351, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openingrow= ParcourPtable.getSelectedRow();
				parcourp=String.valueOf(ParcourPtable.getValueAt(openingrow,0));//parcoup=reçois la matricule du patient séléctionné
				frame.dispose();
				NouveauRdvFrame.main(null);
			}
		});
		btnOuvrir.setBounds(478, 351, 89, 23);
		frame.getContentPane().add(btnOuvrir);
	}
}
