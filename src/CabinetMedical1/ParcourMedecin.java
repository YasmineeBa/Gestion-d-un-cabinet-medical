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

public class ParcourMedecin {

	private JFrame frame;
	public static JTable Ptable;
	private int openingrow;
	public static String parcourm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParcourMedecin window = new ParcourMedecin();
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
	public ParcourMedecin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 647, 235);
		frame.getContentPane().add(scrollPane);
		
		Ptable = new JTable();
		scrollPane.setViewportView(Ptable);
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql ="select Matricule, Nom, Prenom, Specialite from medecin";
			ResultSet rs = stmt.executeQuery(sql);
			Ptable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(578, 288, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openingrow= Ptable.getSelectedRow();
				parcourm=String.valueOf(Ptable.getValueAt(openingrow,0));//parcoum=reçois le matricule du médecin séléctionné
				frame.dispose();
				NouveauRdvFrame.main(null);
			}
		});
		btnOuvrir.setBounds(479, 288, 89, 23);
		frame.getContentPane().add(btnOuvrir);
	}

}
