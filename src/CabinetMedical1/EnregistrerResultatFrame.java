package CabinetMedical1;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class EnregistrerResultatFrame {

	private JFrame frame;
	private JTextField textBilan;
	private JTextField textResultat;
	private JTextField textCommentaire;
	private static Vector<Analyse> analyses = new Vector<Analyse>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnregistrerResultatFrame window = new EnregistrerResultatFrame();
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
	public EnregistrerResultatFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 274);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBilan = new JLabel("Bilan");
		lblBilan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBilan.setBounds(10, 12, 46, 14);
		frame.getContentPane().add(lblBilan);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(518, 13, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblDatetext = new JLabel("New label");
		lblDatetext.setBounds(574, 13, 109, 14);
		frame.getContentPane().add(lblDatetext);
		
		JLabel lblResultat = new JLabel("Resultat");
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResultat.setBounds(10, 37, 60, 14);
		frame.getContentPane().add(lblResultat);
		
		JLabel lblCommentaire = new JLabel("Commentaire");
		lblCommentaire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCommentaire.setBounds(10, 62, 105, 14);
		frame.getContentPane().add(lblCommentaire);
		
		textBilan = new JTextField();
		textBilan.setBounds(66, 11, 187, 20);
		frame.getContentPane().add(textBilan);
		textBilan.setColumns(10);
		
		textResultat = new JTextField();
		textResultat.setBounds(83, 36, 170, 20);
		frame.getContentPane().add(textResultat);
		textResultat.setColumns(10);
		textResultat.setText(" ");
		
		textCommentaire = new JTextField();
		textCommentaire.setBounds(10, 87, 673, 110);
		frame.getContentPane().add(textCommentaire);
		textCommentaire.setColumns(10);
		textCommentaire.setText(" ");
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnAnnuler.setBounds(594, 208, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		//remplir les champs bilan et date
		try {
			Connection conn = SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql = "select Bilan, DateAnalyse from analyse where numAnalyse='"+FicheMedicaleFrame.OpeningAnalyse+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				textBilan.setText(rs.getString(1));
				Date d  = rs.getDate(2);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				lblDatetext.setText(sdf.format(d));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ajouter de l'analyse dans le vector qui contient les analyses modifi�es pendant la consultation
				analyses.add(new Analyse(FicheMedicaleFrame.OpeningAnalyse,textBilan.getText(), textResultat.getText(), textCommentaire.getText()));
				frame.dispose();
				//retour � la fiche m�dicale
				FicheMedicaleFrame.main(null);
			}
		});
		btnEnregistrer.setBounds(495, 208, 89, 23);
		frame.getContentPane().add(btnEnregistrer);
	}
	public static void EnregistrerModifications(){
		for(int i = 0; i<analyses.size(); i++){
			Analyse.modifierAnalyse(analyses.get(i).getNumAnalyse(),analyses.get(i).getBilan(),analyses.get(i).getResultat(),analyses.get(i).getCommentaire());
			analyses.clear();
		}
	}
}

