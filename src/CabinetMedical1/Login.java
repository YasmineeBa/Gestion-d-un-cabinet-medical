package CabinetMedical1;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	ImageIcon imageLogin = new ImageIcon("Login.png");
	ImageIcon img = new ImageIcon("Ok.png");
	
	public static String user;//contient le matricule de l'utilisateur
	public static int usertype;//usertype=0 si l'utilisateur est un médecin et 1 sinon

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
					window.frmLogin.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 576, 239);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblMatricule = new JLabel("Matricule ");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatricule.setBounds(300, 51, 95, 20);
		frmLogin.getContentPane().add(lblMatricule);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMotDePasse.setBounds(283, 97, 112, 28);
		frmLogin.getContentPane().add(lblMotDePasse);
		
		textField = new JTextField();
		textField.setBounds(405, 53, 145, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(405, 103, 145, 20);
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnConnexion = new JButton("connexion");
		btnConnexion.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					//rechercher de l'utilisateur parmi les médecins
					String sql ="select*from medecin where matricule='"+textField.getText()+"' and mdp = '"+passwordField.getText()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()){//utilisateur trouvé
						user=textField.getText();
						frmLogin.dispose();
						usertype=0;
						MenuMedecin.main(null);
					}
					else{//recherche de l'utilisateur parmi les secretaire
						sql="select*from secretaire where matricule='"+textField.getText()+"' and mdp='"+passwordField.getText()+"'";
						rs=stmt.executeQuery(sql);
						if(rs.next()){//utilisateur trouvé
							user=textField.getText();
							frmLogin.dispose();
							usertype=1;
							Menu.main(null);
						}
						else{//l'utilisateur n'existe pas 
							JOptionPane.showMessageDialog(null, "matricule ou mot de passe invalid, ressayez");
						}
					}
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
		});
		btnConnexion.setBounds(379, 152, 171, 38);
		frmLogin.getContentPane().add(btnConnexion); 
		btnConnexion.setIcon(img);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 252, 179);
		frmLogin.getContentPane().add(lblNewLabel);
		
		lblNewLabel.setIcon(imageLogin);
	}
}
