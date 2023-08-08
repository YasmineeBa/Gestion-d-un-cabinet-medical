package CabinetMedical1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ParcourPatientCons {

	private JFrame frame;
	private int openingrow;
	public static String ChosenName;
	public static JTable table;
	public static String OpeningCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParcourPatientCons window = new ParcourPatientCons();
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
	public ParcourPatientCons() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 791, 453);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 755, 363);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql ="select IDPatient, Nom, prenom, Sexe, DateNaiss as date_de_naissance, adresse, tel, email,DateInsc as date_dinscription, modif as dernier_modificateur from patient";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
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
		btnAnnuler.setBounds(664, 381, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openingrow=table.getSelectedRow();
				ChosenName=String.valueOf(table.getValueAt(openingrow,1))+" "+String.valueOf(table.getValueAt(openingrow,2));//chosenName reçois le nom et le prenom du patient séléctionné
				OpeningCode=String.valueOf(table.getValueAt(openingrow,0));//OpeningCode =reçois le code du patient séléctionné
				frame.dispose();
				NouvelleConsultationFrame.main(null);
			}
		});
		btnOuvrir.setBounds(565, 381, 89, 23);
		frame.getContentPane().add(btnOuvrir);
		
		
	}
}
