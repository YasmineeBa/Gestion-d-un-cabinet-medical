package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifierPatientFrame {

	private JFrame frmModifierLePatient;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textDateNaiss;
	private JTextField textAdresse;
	private JTextField textTel;
	private JTextField textMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierPatientFrame window = new ModifierPatientFrame();
					window.frmModifierLePatient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifierPatientFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifierLePatient = new JFrame();
		frmModifierLePatient.setTitle("Modifier le patient");
		frmModifierLePatient.setBounds(100, 100, 455, 343);
		frmModifierLePatient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModifierLePatient.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(125, 29, 46, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(106, 67, 61, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sexe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(125, 103, 46, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date de naissance");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(42, 134, 141, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Adresse");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(111, 164, 61, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("T\u00E9l\u00E9phone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(89, 198, 82, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("E-mail");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(120, 231, 46, 14);
		frmModifierLePatient.getContentPane().add(lblNewLabel_6);
		
		textNom = new JTextField();
		textNom.setBounds(192, 28, 124, 20);
		frmModifierLePatient.getContentPane().add(textNom);
		textNom.setColumns(10);
		String init = String.valueOf(GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,1));
		textNom.setText(init);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(192, 66, 124, 20);
		frmModifierLePatient.getContentPane().add(textPrenom);
		textPrenom.setColumns(10);
		init = (String)GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,2);
		textPrenom.setText(init);
		
		final JComboBox comboSexe = new JComboBox();
		comboSexe.setBounds(192, 102, 124, 20);
		frmModifierLePatient.getContentPane().add(comboSexe);
		comboSexe.addItem("Femme");
		comboSexe.addItem("Homme");
		
		textDateNaiss = new JTextField();
		textDateNaiss.setBounds(193, 133, 123, 20);
		frmModifierLePatient.getContentPane().add(textDateNaiss);
		textDateNaiss.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		init= sdf.format(GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,4));
		textDateNaiss.setText(init);
		
		textAdresse = new JTextField();
		textAdresse.setBounds(192, 163, 237, 20);
		frmModifierLePatient.getContentPane().add(textAdresse);
		textAdresse.setColumns(10);
		init = String.valueOf(GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,5));
		textAdresse.setText(init);
		
		textTel = new JTextField();
		textTel.setBounds(192, 194, 124, 20);
		frmModifierLePatient.getContentPane().add(textTel);
		textTel.setColumns(10);
		init = String.valueOf(GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,6));
		textTel.setText(init);
		
		textMail = new JTextField();
		textMail.setBounds(192, 225, 124, 20);
		frmModifierLePatient.getContentPane().add(textMail);
		textMail.setColumns(10);
		init = String.valueOf(GestionPatients.table.getValueAt(GestionPatients.UpdatingRow,7));
		textMail.setText(init);
		 
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmModifierLePatient.dispose();
				GestionPatients.main(null);//retour vers la frame gestion des patients
			}
		});
		btnAnnuler.setBounds(340, 271, 89, 23);
		frmModifierLePatient.getContentPane().add(btnAnnuler);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textNom.getText()!=null && textPrenom.getText()!=null && textDateNaiss.getText()!=null
						&& textAdresse.getText()!=null && textTel.getText()!=null && textMail.getText()!=null) {//Vérifier que tous les champs sont remplis
					Date dd = new Date();
					//conversion de la date
					Date d = new Date(textDateNaiss.getText());
					if (dd.compareTo(d)>0) {//vérifier la validité de la date
						//demande de la confirmation pour enregsitrer les modifications
						int action = JOptionPane.showConfirmDialog(null,"Voulez-vous modifier les informations du patient?");
						if (action == 0) {//confirmation obtenu
							String code = (String) GestionPatients.table.getValueAt(GestionPatients.UpdatingRow, 0);//code du patient
							String sexe = (String) comboSexe.getSelectedItem();
							Patient.ModifierPatient(code, textNom.getText(),textPrenom.getText(), sexe,textDateNaiss.getText(),
									textAdresse.getText(), textTel.getText(),textMail.getText());//enregistrer les modifications
							frmModifierLePatient.dispose();
							GestionPatients.main(null);//retour vers la frame gestions de patients
						}
					}
					else{//date non valide
						JOptionPane.showMessageDialog(null,"Entrez une date valide");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Tous les champs doivent être remplis");
				}
			}
		});
		btnEnregistrer.setBounds(243, 271, 89, 23);
		frmModifierLePatient.getContentPane().add(btnEnregistrer);
		
	}
	

}
