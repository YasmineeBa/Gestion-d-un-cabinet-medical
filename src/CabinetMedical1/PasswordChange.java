package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class PasswordChange {
	
	ImageIcon imgSave = new ImageIcon("save.png");

	private JFrame frame;
	private JTextField textMdpActu;
	private JTextField textNvMdp;
	private JTextField textConfirmation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordChange window = new PasswordChange();
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
	public PasswordChange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 162);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMotDePass = new JLabel("Mot de passe actuel:");
		lblMotDePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMotDePass.setBounds(58, 11, 153, 14);
		frame.getContentPane().add(lblMotDePass);
		
		JLabel lblConfirmerLeMot = new JLabel("Confirmer le mot de passe:");
		lblConfirmerLeMot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmerLeMot.setBounds(10, 61, 201, 14);
		frame.getContentPane().add(lblConfirmerLeMot);
		
		JLabel lblNouvezuMotDe = new JLabel("Nouveau mot de passe:");
		lblNouvezuMotDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNouvezuMotDe.setBounds(37, 36, 167, 14);
		frame.getContentPane().add(lblNouvezuMotDe);
		
		textMdpActu = new JTextField();
		textMdpActu.setBounds(208, 10, 193, 20);
		frame.getContentPane().add(textMdpActu);
		textMdpActu.setColumns(10);
		
		textNvMdp = new JTextField();
		textNvMdp.setBounds(208, 36, 193, 20);
		frame.getContentPane().add(textNvMdp);
		textNvMdp.setColumns(10);
		
		textConfirmation = new JTextField();
		textConfirmation.setBounds(208, 60, 193, 20);
		frame.getContentPane().add(textConfirmation);
		textConfirmation.setColumns(10);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				PersonnelSpace.main(null);
			}
		});
		btnAnnuler.setBounds(312, 94, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JButton btnEnregsitrer = new JButton("Enregsitrer");
		btnEnregsitrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = SqlConnection.dbConnector();
					Statement stmt= conn.createStatement();
					String sql ="";
					ResultSet rs;
					if(Login.usertype==0){//vérifier le type de l'utilisateur-> l'utilisateur est un médecin
						//vérifier si le mot de passe saisie est corret
						sql =" select * from medecin where (matricule='"+Login.user+"' and mdp ='"+textMdpActu.getText()+"')";
						rs = stmt.executeQuery(sql);
						if(rs.next()){//mot de passe correct
								if(textNvMdp.getText().equals((String)textConfirmation.getText())){//vérifier que les deux nouveaux mot de passes sont identiques
									Medecin.Modifier(Login.user,textNvMdp.getText());//enregistrer les modifications
								}
								else{
									JOptionPane.showMessageDialog(null, "Vous devez saisir le même mot de passe deux fois.");
									textNvMdp.setText("");  textMdpActu.setText(""); textConfirmation.setText("");//réinitialisation des champs
								}
						}
						else{//mot de passe incorrect
							JOptionPane.showMessageDialog(null,"Mot de passe incorect");
						}
					}
					else{//l'utilisateur est une secrétaire
						//vérifier si le mot de passe saisie est corret
						sql =" select * from secretaire where (matricule='"+Login.user+"' and mdp ='"+textMdpActu.getText()+"')";
						rs = stmt.executeQuery(sql);
						if(rs.next()){//mot de passe correct
								if(textNvMdp.getText().equals((String)textConfirmation.getText())){//vérifier que les deux nouveaux mot de passes sont identiques
									Secretaire.Modifier(Login.user,textNvMdp.getText());//enregistrer les modifications
								}
								else{
									JOptionPane.showMessageDialog(null, "Vous devez saisir le même mot de passe deux fois.");
									textNvMdp.setText("");  textMdpActu.setText(""); textConfirmation.setText("");//réinitialisation des champs
								}
						}
						else{//mot de passe incorrect
							JOptionPane.showMessageDialog(null,"Mot de passe incorect");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"Mot de passe enregistré");
				frame.dispose();
				PersonnelSpace.main(null);
			}
		});
		btnEnregsitrer.setBounds(147, 94, 160, 23);
		frame.getContentPane().add(btnEnregsitrer);
	}
}
