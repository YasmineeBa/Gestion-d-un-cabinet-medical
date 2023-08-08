package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

public class NouveauPatient {
	ImageIcon img = new ImageIcon("save.png");

	private JFrame frmNouveauPatient;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textAdresse;
	private JTextField textMail;
	private JTextField textTel;
	private JTextField textAnnee;
	private JTextField textCode;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouveauPatient window = new NouveauPatient();
					window.frmNouveauPatient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NouveauPatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNouveauPatient = new JFrame();
		frmNouveauPatient.setTitle("Nouveau Patient");
		frmNouveauPatient.setBounds(100, 100, 728, 356);
		frmNouveauPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNouveauPatient.getContentPane().setLayout(null);
		
		JLabel lblNomPatient = new JLabel("Nom");
		lblNomPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomPatient.setBounds(100, 54, 56, 14);
		frmNouveauPatient.getContentPane().add(lblNomPatient);
		
		textNom = new JTextField();
		textNom.setBounds(167, 53, 144, 20);
		frmNouveauPatient.getContentPane().add(textNom);
		textNom.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrnom.setBounds(80, 85, 77, 14);
		frmNouveauPatient.getContentPane().add(lblPrnom);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(167, 84, 144, 20);
		frmNouveauPatient.getContentPane().add(textPrenom);
		textPrenom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sexe");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(100, 116, 46, 14);
		frmNouveauPatient.getContentPane().add(lblNewLabel);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Femme");
		comboBox.addItem("Homme");
		comboBox.setBounds(167, 115, 144, 20);
		frmNouveauPatient.getContentPane().add(comboBox);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		lblDateDeNaissance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDateDeNaissance.setBounds(10, 144, 147, 20);
		frmNouveauPatient.getContentPane().add(lblDateDeNaissance);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdresse.setBounds(80, 179, 66, 14);
		frmNouveauPatient.getContentPane().add(lblAdresse);
		
		textAdresse = new JTextField();
		textAdresse.setBounds(167, 178, 214, 20);
		frmNouveauPatient.getContentPane().add(textAdresse);
		textAdresse.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(90, 247, 66, 14);
		frmNouveauPatient.getContentPane().add(lblEmail);
		
		textMail = new JTextField();
		textMail.setBounds(167, 241, 144, 20);
		frmNouveauPatient.getContentPane().add(textMail);
		textMail.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 50, 292, 183);
		frmNouveauPatient.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql ="select Matricule, Nom, Prenom, Specialite from medecin";
			ResultSet rs = stmt.executeQuery(sql);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		final JComboBox<Integer> Jours = new JComboBox<Integer>();
		Jours.setBounds(167, 146, 46, 20);
		frmNouveauPatient.getContentPane().add(Jours);
		for(int i =1; i<32;i++){
			Jours.addItem(i);
		}
		
		final JComboBox<Integer> Mois = new JComboBox<Integer>();
		Mois.setBounds(223, 146, 88, 20);
		frmNouveauPatient.getContentPane().add(Mois);
		
		for(int i=1;i<13;i++){
			Mois.addItem(i);
		}
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date dd = new Date();
				if (textCode.getText()!=null && textNom.getText()!=null && textPrenom.getText()!=null && textAnnee.getText()!=null
						&& textAdresse.getText()!=null && textTel.getText()!=null && textMail.getText()!=null) {//vérifier que les champs ne sont pas vides
					
					//convertion des dates
					String date = Jours.getSelectedItem().toString() + "/"
							+ Mois.getSelectedItem().toString() + "/"
							+ textAnnee.getText();
					@SuppressWarnings("deprecation")
					Date d = new Date(date);
					
					if ((Integer.parseInt(textAnnee.getText()) > 0) && dd.compareTo(d)>0) {//verifier la validité de la date
						//ajout du patients
						String sexe = (String) comboBox.getSelectedItem();
						Patient p = new Patient(textCode.getText(),
								textNom.getText(), textPrenom.getText(), sexe,
								d, textAdresse.getText(), textTel.getText(),
								textMail.getText(), dd, Login.user);
						Patient.AjouterPatient(p);
						//Création de la fiche de la fiche médicale
						@SuppressWarnings("deprecation")
						int difference =(dd.getYear()+1900)-Integer.parseInt(textAnnee.getText());
						FicheMedicale fm = new FicheMedicale(p.getCode(),difference);
						fm.setPatient(p);
						int openingrow = table_1.getSelectedRow();
						fm.getMedecin().setMatricule(String.valueOf(table_1.getValueAt(openingrow, 0)));
						FicheMedicale.ajouterFicheMedicale(fm);
						frmNouveauPatient.dispose();
						GestionPatients.main(null);
					} else {
						JOptionPane.showMessageDialog(null,
								"Entrez une date valide");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Vous devez remplir tous les champs");
				}
			}
		});
		btnEnregistrer.setBounds(484, 284, 119, 23);
		frmNouveauPatient.getContentPane().add(btnEnregistrer);
		btnEnregistrer.setIcon(img);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNouveauPatient.dispose();
				GestionPatients.main(null);
			}
		});
		btnAnnuler.setBounds(613, 284, 89, 23);
		frmNouveauPatient.getContentPane().add(btnAnnuler);
		
		textTel = new JTextField();
		textTel.setBounds(167, 209, 144, 20);
		frmNouveauPatient.getContentPane().add(textTel);
		textTel.setColumns(10);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		lblTlphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTlphone.setBounds(62, 210, 77, 14);
		frmNouveauPatient.getContentPane().add(lblTlphone);
		
		textAnnee = new JTextField();
		textAnnee.setBounds(325, 146, 56, 20);
		frmNouveauPatient.getContentPane().add(textAnnee);
		textAnnee.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCode.setBounds(93, 29, 46, 14);
		frmNouveauPatient.getContentPane().add(lblCode);
		
		textCode = new JTextField();
		textCode.setBounds(167, 22, 144, 20);
		frmNouveauPatient.getContentPane().add(textCode);
		textCode.setColumns(10);
		
		
		JLabel lblMdecinTraitant = new JLabel("M\u00E9decin traitant: ");
		lblMdecinTraitant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMdecinTraitant.setBounds(410, 25, 144, 14);
		frmNouveauPatient.getContentPane().add(lblMdecinTraitant);
		
		
	}
}
