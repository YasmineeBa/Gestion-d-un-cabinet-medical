package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListePersonnelFrame {

	private JFrame frmListeDuPersonnel;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListePersonnelFrame window = new ListePersonnelFrame();
					window.frmListeDuPersonnel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListePersonnelFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListeDuPersonnel = new JFrame();
		frmListeDuPersonnel.setTitle("Liste du personnel");
		frmListeDuPersonnel.setBounds(100, 100, 931, 577);
		frmListeDuPersonnel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListeDuPersonnel.getContentPane().setLayout(null);
		
		JLabel lblMdeins = new JLabel("M\u00E9decins:");
		lblMdeins.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMdeins.setBounds(20, 25, 104, 14);
		frmListeDuPersonnel.getContentPane().add(lblMdeins);
		
		JLabel lblSecrtaires = new JLabel("Secr\u00E9taires:");
		lblSecrtaires.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSecrtaires.setBounds(533, 25, 104, 14);
		frmListeDuPersonnel.getContentPane().add(lblSecrtaires);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 446, 409);
		frmListeDuPersonnel.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try{//remplissage de la table qui contient les médecins
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select matricule,nom,prenom,nbureau,specialite from medecin";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(543, 50, 362, 409);
		frmListeDuPersonnel.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		try{//remplir la table qui contient les secretaires
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			//String sql ="select IDPatient, Nom, prenom, Sexe, DateNaiss as date_de_naissance, adresse, tel, email,DateInsc as date_dinscription, modif as dernier_modificateur from patient";
			String sql="select Matricule,nom,prenom,nbureau from secretaire";
			ResultSet rs = stmt.executeQuery(sql);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			System.out.println(e);
		}
		JButton btnRetpour = new JButton("Retour");
		btnRetpour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmListeDuPersonnel.dispose();
				if(Login.usertype==0){
					MenuMedecin.main(null);
				}
				else{
					Menu.main(null);
				}
			}
		});
		btnRetpour.setBounds(782, 482, 123, 31);
		frmListeDuPersonnel.getContentPane().add(btnRetpour);
	}
}
