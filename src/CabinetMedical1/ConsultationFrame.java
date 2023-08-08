package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultationFrame {

	private JFrame frame;
	private JTextField textPatient;
	private JTextField textMedecin;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textObservation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultationFrame window = new ConsultationFrame();
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
	public ConsultationFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 721, 522);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPatient = new JLabel("Patient: ");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPatient.setBounds(10, 11, 65, 14);
		frame.getContentPane().add(lblPatient);
		
		JLabel lblMdecin = new JLabel("M\u00E9decin:");
		lblMdecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMdecin.setBounds(10, 36, 65, 14);
		frame.getContentPane().add(lblMdecin);
		
		textPatient = new JTextField();
		textPatient.setBounds(70, 10, 152, 20);
		frame.getContentPane().add(textPatient);
		textPatient.setColumns(10);
		
		textMedecin = new JTextField();
		textMedecin.setBounds(80, 36, 142, 20);
		frame.getContentPane().add(textMedecin);
		textMedecin.setColumns(10);
		
		JLabel lblDiagnotsique = new JLabel("Diagnotsique:");
		lblDiagnotsique.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiagnotsique.setBounds(10, 61, 98, 20);
		frame.getContentPane().add(lblDiagnotsique);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 162, 122);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//remplir la table qui contient les maladies diagnostiquées
		Connection conn = SqlConnection.dbConnector();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select nommaladie as maladie from maladie_consultation where cleconsultation='"+HistoriqueConsFrame.OpeningRow+"'";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblAnalyses = new JLabel("Analyses :");
		lblAnalyses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnalyses.setBounds(186, 64, 87, 15);
		frame.getContentPane().add(lblAnalyses);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(186, 92, 509, 122);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		//remplir la table qui contient les analyses demandées
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from analyse where cleconsultation='"+HistoriqueConsFrame.OpeningRow+"'";
			ResultSet rs = stmt.executeQuery(sql);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblOrdonnance = new JLabel("Ordonnance:");
		lblOrdonnance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrdonnance.setBounds(10, 221, 98, 14);
		frame.getContentPane().add(lblOrdonnance);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 246, 685, 136);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		//remplir la table qui contient les médicaments prescrits
		try {
			Statement stmt = conn.createStatement();
			String sql = "select ordonnance_medoc.nommedoc, ordonnance_medoc.dosage, ordonnance_medoc.nbrdedose, "
					+ " ordonnance_medoc.duree from ordonnance_medoc, ordonnance "
					+ " where (ordonnance.cleconsultation='"
					+HistoriqueConsFrame.OpeningRow+"' and ordonnance_medoc.numordonnance=ordonnance.numordonnance)";
			ResultSet rs = stmt.executeQuery(sql);
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(524, 11, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblNewDate = new JLabel("New label");
		lblNewDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewDate.setBounds(568, 13, 115, 14);
		frame.getContentPane().add(lblNewDate);
		
		JLabel lblObservation = new JLabel("Observation:");
		lblObservation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObservation.setBounds(10, 390, 98, 14);
		frame.getContentPane().add(lblObservation);
		
		textObservation = new JTextField();
		textObservation.setBounds(10, 415, 401, 69);
		frame.getContentPane().add(textObservation);
		textObservation.setColumns(10);
		
		JLabel lblMontantPay = new JLabel("Montant pay\u00E9:");
		lblMontantPay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMontantPay.setBounds(485, 415, 115, 14);
		frame.getContentPane().add(lblMontantPay);
		
		JLabel lblMontant = new JLabel("New label");
		lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMontant.setBounds(593, 415, 102, 14);
		frame.getContentPane().add(lblMontant);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnRetour.setBounds(594, 450, 89, 23);
		frame.getContentPane().add(btnRetour);
		
		//remplir les champs patient, medecin, observation et montant payé
		try {
			Statement stmt = conn.createStatement();
			String sql = "select patient.nom,patient.prenom, medecin.nom, medecin.prenom, consultation.dateconsu, consultation.observation, consultation.montant"
					+" from patient, medecin, consultation where (consultation.cleconsultation = '"+HistoriqueConsFrame.OpeningRow
					+"' and patient.idpatient=consultation.idPatient and medecin.matricule=consultation.matricule)";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				textPatient.setText(rs.getString(1)+" "+rs.getString(2));
				textMedecin.setText(rs.getString(3)+" "+rs.getString(4));
				lblNewDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(5)));
				textObservation.setText(rs.getString(6));
				lblMontant.setText(String.valueOf(rs.getDouble(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
