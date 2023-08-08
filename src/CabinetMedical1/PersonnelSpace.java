package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class PersonnelSpace {
	
	ImageIcon password = new ImageIcon("password.png");
	ImageIcon img = new ImageIcon("ID.png");

	private JFrame frmEspacePersonnel;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textMatricule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonnelSpace window = new PersonnelSpace();
					window.frmEspacePersonnel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PersonnelSpace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEspacePersonnel = new JFrame();
		frmEspacePersonnel.setTitle("Espace personnel");
		frmEspacePersonnel.setBounds(100, 100, 404, 181);
		frmEspacePersonnel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEspacePersonnel.getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNom.setBounds(200, 10, 53, 14);
		frmEspacePersonnel.getContentPane().add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom:");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrnom.setBounds(200, 40, 71, 14);
		frmEspacePersonnel.getContentPane().add(lblPrnom);
		
		JLabel lblMatricule = new JLabel("Matricule:");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatricule.setBounds(200, 70, 71, 14);
		frmEspacePersonnel.getContentPane().add(lblMatricule);
		
		
		textNom = new JTextField();
		textNom.setBounds(275, 10, 98, 20);
		frmEspacePersonnel.getContentPane().add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(275, 40, 98, 20);
		frmEspacePersonnel.getContentPane().add(textPrenom);
		textPrenom.setColumns(10);
		
		Connection conn = SqlConnection.dbConnector();
		try {//recherche de l'utilisateur dans la base de données
			Statement stmt = conn.createStatement();
			String sql = "";
			ResultSet rs;
			if(Login.usertype==0){//l'utilisateur est un médecin
				sql="select nom, prenom from medecin where matricule='"+Login.user+"'";
			}
			else{//l'utilisateur est une secrétaire
				sql="select nom, prenom from medecin where secretaire='"+Login.user+"'";

			}
			rs = stmt.executeQuery(sql);
			if(rs.next()){//remplir les champs nom et prenom
				textNom.setText(rs.getString(1)); textPrenom.setText(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		textMatricule = new JTextField();
		textMatricule.setBounds(275, 70, 98, 20);
		frmEspacePersonnel.getContentPane().add(textMatricule);
		textMatricule.setColumns(10);
		textMatricule.setText(Login.user);//remplir le champs matricule
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmEspacePersonnel.dispose();
				if(Login.usertype==0){
					MenuMedecin.main(null);
				}
				else{
					Menu.main(null);
				}
			}
		});
		btnNewButton.setBounds(284, 109, 89, 23);
		frmEspacePersonnel.getContentPane().add(btnNewButton);
		
		JButton btnChangerLeMot = new JButton("Changer le mot de passe");
		btnChangerLeMot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmEspacePersonnel.dispose();
				PasswordChange.main(null);//appel de la frame changer le mot de passe
			}
		});
		btnChangerLeMot.setBounds(56, 109, 215, 23);
		frmEspacePersonnel.getContentPane().add(btnChangerLeMot);
		btnChangerLeMot.setIcon(password);
		
		JLabel Person = new JLabel("");
		Person.setBounds(53, 0, 96, 96);
		frmEspacePersonnel.getContentPane().add(Person);
		Person.setIcon(img);
	}
}
