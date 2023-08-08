package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.*;

public class NouvelleConsultationFrame {

	private JFrame frmConsultation;

	ImageIcon imgFicheMedicale = new ImageIcon("FicheMedicale.png");
	ImageIcon imgAjouter = new ImageIcon("nouveauPatient.png");
	ImageIcon imgPrint = new ImageIcon("print.png");
	ImageIcon imgSave = new ImageIcon("save.png");
	
	private JTextField textPatient=null;
	private JTable table;
	private JTextField textObservation;
	private JTextField textField_2;
	private JTextField textMaladie;
	private JTable table_1;
	private JTextField textAnalyse;
	private JTextField textNomMedoc;
	private JTextField textDosage;
	private JTextField textDuree;
	private JTable TableMedoc;
	private JTable table_2;
	static DefaultTableModel modelMedoc;
	
	int cmptAnalyse = 0;
	int cmptMedicament=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouvelleConsultationFrame window = new NouvelleConsultationFrame();
					window.frmConsultation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NouvelleConsultationFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public int getCmptAnalyse() {
		return cmptAnalyse;
	}

	public int getCmptMedicament() {
		return cmptMedicament;
	}
	
	private void initialize() {
		final Consultation consultation = new Consultation();
		consultation.getMedecin().setMatricule(Login.user);
		
		frmConsultation = new JFrame();
		frmConsultation.setTitle("Consultation");
		frmConsultation.setBounds(100, 100, 1184, 619);
		frmConsultation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConsultation.getContentPane().setLayout(null);
		
		JLabel lblCodePatient = new JLabel("Patient:");
		lblCodePatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodePatient.setBounds(10, 11, 69, 14);
		frmConsultation.getContentPane().add(lblCodePatient);
		
		textPatient = new JTextField();
		textPatient.setBounds(89, 10, 207, 20);
		frmConsultation.getContentPane().add(textPatient);
		textPatient.setColumns(10);
		textPatient.setText(ParcourPatientCons.ChosenName);
		consultation.getPatient().setCode(ParcourPatientCons.OpeningCode);
		
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultation.dispose();
				ParcourPatientCons.main(null);
			}
		});
		button.setBounds(306, 9, 29, 23);
		frmConsultation.getContentPane().add(button);
		
		JLabel lblMaladiesDiagnostiques = new JLabel("Maladies diagnostiqu\u00E9es:");
		lblMaladiesDiagnostiques.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaladiesDiagnostiques.setBounds(6, 53, 182, 29);
		frmConsultation.getContentPane().add(lblMaladiesDiagnostiques);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 79, 329, 124);
		frmConsultation.getContentPane().add(scrollPane);
		
		final DefaultTableModel model=new DefaultTableModel(new Object[]{"Maladie"}, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnAjouterMaladie = new JButton("Ajouter");
		btnAjouterMaladie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!consultation.chercherMaladieConsultation(textMaladie.getText())) {//recherche de la maladie parmis les maladies diagnostiquées pendant cette consultation
					if (!Maladie.chercherMaladieFiche(textMaladie.getText(), ParcourPatientCons.OpeningCode)) {
						//Ajout de la maladie das l'objet consultation
						consultation.ajouterMaladie(textMaladie.getText());
						//Ajout de la maladie dans la table de l'interface
						model.addRow(new Object[] { textMaladie.getText() });
						textMaladie.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null,"Cette a été diagnostiquée dans une consultation précédente");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Cette maladie a déja été diagnostiquée");
					textMaladie.setText("");
				}
			}
		});
		btnAjouterMaladie.setBounds(438, 109, 110, 23);
		frmConsultation.getContentPane().add(btnAjouterMaladie);
		btnAjouterMaladie.setIcon(imgAjouter);
		
		JLabel lblObservation = new JLabel("Observation:");
		lblObservation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObservation.setBounds(10, 415, 100, 14);
		frmConsultation.getContentPane().add(lblObservation);
		
		textObservation = new JTextField();
		textObservation.setBounds(105, 415, 443, 63);
		frmConsultation.getContentPane().add(textObservation);
		textObservation.setColumns(10);
		textObservation.setText("");
		
		JLabel lblMontant = new JLabel("Montant:");
		lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMontant.setBounds(298, 490, 74, 14);
		frmConsultation.getContentPane().add(lblMontant);
		
		textField_2 = new JTextField();
		textField_2.setBounds(382, 489, 110, 20);
		frmConsultation.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText("0");
		
		JLabel lblDa = new JLabel("DA");
		lblDa.setBounds(502, 489, 46, 14);
		frmConsultation.getContentPane().add(lblDa);
		
		JButton btnFicheMedicale = new JButton("Fiche Medicale");
		btnFicheMedicale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FicheMedicaleFrame.main(null);
			}
		});
		btnFicheMedicale.setBounds(345, 9, 156, 23);
		frmConsultation.getContentPane().add(btnFicheMedicale);
		btnFicheMedicale.setIcon(imgFicheMedicale);
		
		textMaladie = new JTextField();
		textMaladie.setBounds(345, 78, 203, 20);
		frmConsultation.getContentPane().add(textMaladie);
		textMaladie.setColumns(10);
		
		JLabel lblAnalyseDemandes = new JLabel("Analyse demand\u00E9es:");
		lblAnalyseDemandes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnalyseDemandes.setBounds(10, 218, 156, 14);
		frmConsultation.getContentPane().add(lblAnalyseDemandes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 243, 325, 161);
		frmConsultation.getContentPane().add(scrollPane_1);
		
		final DefaultTableModel modelAnalyse=new DefaultTableModel(new Object[]{"Bilan"}, 0);
		table_1 = new JTable(modelAnalyse);
		scrollPane_1.setViewportView(table_1);
		
		textAnalyse = new JTextField();
		textAnalyse.setBounds(350, 243, 198, 20);
		frmConsultation.getContentPane().add(textAnalyse);
		textAnalyse.setColumns(10);
		
		
		JButton btnAjouterAnalyse = new JButton("Ajouter");
		btnAjouterAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ajout de l'analyse dans l'objet consultation
				consultation.ajouterAnalyse(Analyse.genererNumAnalyser()+String.valueOf(getCmptAnalyse()), textAnalyse.getText());
				cmptAnalyse++; //numéro séquentiel ajouté au code de l'analyse
				//Ajout de l'analyse dans la table de l'interface
				String analyse=textAnalyse.getText();
				modelAnalyse.addRow(new Object[]{analyse});
				textAnalyse.setText("");
			}
		});
		btnAjouterAnalyse.setBounds(438, 274, 110, 23);
		frmConsultation.getContentPane().add(btnAjouterAnalyse);
		btnAjouterAnalyse.setIcon(imgAjouter);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultation.dispose();
			}
		});
		btnAnnuler.setBounds(1042, 552, 116, 23);
		frmConsultation.getContentPane().add(btnAnnuler);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (consultation.getPatient().getCode()!=null) {//vérifier que l'utilisateur a séléctionné un patient
					consultation.setObservation(textObservation.getText());
					consultation.setMontant(Double.parseDouble(textField_2.getText()));
					//demande de la confirmation pour enregistrer la consultation
					int action = JOptionPane.showConfirmDialog(null,
							"Voulez-vous enregistrer?");
					if (action == 0) {//confirmation obtenu
						consultation.ajouterConsultation();
						frmConsultation.dispose();
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Vous devez choisir un patient.");
				}
			}
		});
		btnEnregistrer.setBounds(902, 552, 130, 23);
		frmConsultation.getContentPane().add(btnEnregistrer);
		btnEnregistrer.setIcon(imgSave);
		
		JLabel lblTraitement = new JLabel("Traitement:");
		lblTraitement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTraitement.setBounds(565, 60, 94, 14);
		frmConsultation.getContentPane().add(lblTraitement);
		
		JLabel lblNomMdicament = new JLabel("Nom m\u00E9dicament: ");
		lblNomMdicament.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomMdicament.setBounds(850, 276, 143, 14);
		frmConsultation.getContentPane().add(lblNomMdicament);
		
		JLabel lblDosage = new JLabel("Dosage: ");
		lblDosage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDosage.setBounds(924, 314, 69, 20);
		frmConsultation.getContentPane().add(lblDosage);
		
		JLabel lblNombreDeDoses = new JLabel("Nombre de doses");
		lblNombreDeDoses.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDeDoses.setBounds(850, 345, 143, 29);
		frmConsultation.getContentPane().add(lblNombreDeDoses);
		
		JLabel lblParJours = new JLabel("par jours: ");
		lblParJours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblParJours.setBounds(902, 373, 82, 29);
		frmConsultation.getContentPane().add(lblParJours);
		
		JLabel lblDureDuTraiement = new JLabel("Dur\u00E9e du traiement:");
		lblDureDuTraiement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDureDuTraiement.setBounds(841, 415, 143, 14);
		frmConsultation.getContentPane().add(lblDureDuTraiement);
		
		textNomMedoc = new JTextField();
		textNomMedoc.setBounds(1015, 275, 143, 20);
		frmConsultation.getContentPane().add(textNomMedoc);
		textNomMedoc.setColumns(10);
		
		textDosage = new JTextField();
		textDosage.setBounds(1015, 316, 143, 20);
		frmConsultation.getContentPane().add(textDosage);
		textDosage.setColumns(10);
		
		textDuree = new JTextField();
		textDuree.setBounds(1015, 414, 143, 20);
		frmConsultation.getContentPane().add(textDuree);
		textDuree.setColumns(10);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(1015, 362, 46, 29);
		frmConsultation.getContentPane().add(spinner);
		
		modelMedoc=new DefaultTableModel(new Object[]{"Nom","Dosage","Nombre de doses par jours","Durée"}, 0);
		TableMedoc = new JTable(modelMedoc);
		
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(558, 79, 600, 184);
		frmConsultation.getContentPane().add(scrollPane_3);
		
		table_2 = new JTable();
		scrollPane_3.setViewportView(table_2);
		table_2.setModel(modelMedoc);
		
		JButton btnAjouterMedoc = new JButton("Ajouter");
		btnAjouterMedoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ajout du médicament dans l'objet consultation
				String nbr = spinner.getValue()+"";
				consultation.getOrdonnance().getMedicament().add(new Medicament(textNomMedoc.getText(),textDosage.getText(),
					Integer.parseInt(nbr),textDuree.getText()));
				//Ajout du médicament dans la table de l'interface
				modelMedoc.addRow(new Object[]{textNomMedoc.getText(),textDosage.getText(),
					Integer.parseInt(nbr),textDuree.getText()});
				//Réinitialisation des champs
				textNomMedoc.setText(""); textDosage.setText(""); spinner.setValue(0); textDuree.setText("");
			}
		});
		btnAjouterMedoc.setBounds(1015, 455, 143, 23);
		frmConsultation.getContentPane().add(btnAjouterMedoc);
		btnAjouterMedoc.setIcon(imgAjouter);
		
		JButton btnImprimerOrdonnance = new JButton("Imprimer ordonnance");
		btnImprimerOrdonnance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//initialisation des champs
				OrdonnanceFrame.labelPatient.setText(textPatient.getText());
				OrdonnanceFrame.lblDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(consultation.getDate()));
				try{//obtenir le nom et prénom du médecin
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					String sql = "select nom, prenom from medecin where matricule='"+Login.user+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()){
						OrdonnanceFrame.labelMedecin.setText(rs.getString(1)+" "+rs.getString(2));
					}
				}
				catch(Exception e){
					System.out.println(e);
				}
				OrdonnanceFrame.main(null);
			}
		});
		btnImprimerOrdonnance.setBounds(682, 552, 207, 23);
		frmConsultation.getContentPane().add(btnImprimerOrdonnance);
		btnImprimerOrdonnance.setIcon(imgPrint);

	}

}
