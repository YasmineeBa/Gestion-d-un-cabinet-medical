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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;

public class NouveauRdvFrame {
	ImageIcon img = new ImageIcon("warning.png");
	private JFrame frmNouveauRendezvous;
	private JTextField CodePatient;
	private JTextField MatMdecin;
	private JTextField annee;
	private JTextField heure;
	private int PatientType=0;//PatientType=0 si le patient est e,registré dans la base de données, 1 sinon
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouveauRdvFrame window = new NouveauRdvFrame();
					window.frmNouveauRendezvous.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NouveauRdvFrame() {
		initialize();
	}
	
	

	public int getPatientType() {
		return PatientType;
	}

	public void setPatientType(int patientType) {
		PatientType = patientType;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNouveauRendezvous = new JFrame();
		frmNouveauRendezvous.setTitle("Nouveau Rendez-vous");
		frmNouveauRendezvous.setBounds(100, 100, 576, 333);
		frmNouveauRendezvous.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNouveauRendezvous.getContentPane().setLayout(null);
		
		final JLabel lblPatient = new JLabel("Code du patient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPatient.setBounds(24, 91, 120, 14);
		frmNouveauRendezvous.getContentPane().add(lblPatient);
		
		JLabel lblMatriculeDuMdecin = new JLabel("Matricule du m\u00E9decin");
		lblMatriculeDuMdecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatriculeDuMdecin.setBounds(1, 51, 157, 14);
		frmNouveauRendezvous.getContentPane().add(lblMatriculeDuMdecin);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(126, 131, 32, 14);
		frmNouveauRendezvous.getContentPane().add(lblDate);
		
		CodePatient = new JTextField();
		CodePatient.setBounds(186, 90, 120, 20);
		frmNouveauRendezvous.getContentPane().add(CodePatient);
		CodePatient.setColumns(10);
		CodePatient.setText(ParcourPatient.parcourp);
		
		MatMdecin = new JTextField();
		MatMdecin.setBounds(186, 50, 120, 20);
		frmNouveauRendezvous.getContentPane().add(MatMdecin);
		MatMdecin.setColumns(10);
		MatMdecin.setText(ParcourMedecin.parcourm);
		
		annee = new JTextField();
		annee.setBounds(340, 130, 69, 20);
		frmNouveauRendezvous.getContentPane().add(annee);
		annee.setColumns(10);
		
		JLabel lblHeure = new JLabel("Heure");
		lblHeure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeure.setBounds(112, 171, 46, 14);
		frmNouveauRendezvous.getContentPane().add(lblHeure);
		
		heure = new JTextField();
		heure.setBounds(186, 170, 86, 20);
		frmNouveauRendezvous.getContentPane().add(heure);
		heure.setColumns(10);
		
		final JComboBox<Integer> Jours = new JComboBox<Integer>();
		Jours.setBounds(186, 130, 46, 20);
		frmNouveauRendezvous.getContentPane().add(Jours);
		
		final JComboBox<Integer> Mois = new JComboBox<Integer>();
		Mois.setBounds(242, 130, 88, 20);
		frmNouveauRendezvous.getContentPane().add(Mois);
		
		for(int i =1; i<32;i++){
			Jours.addItem(i);
		}
		
		for(int i=1;i<13;i++){
			Mois.addItem(i);
		}
		
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CodePatient.getText()!=null && MatMdecin.getText() != null && heure.getText() != null) {//verifier que tous les champs ont été remplis
					//conversion de la date
					String date = Jours.getSelectedItem().toString() + "/"
							+ Mois.getSelectedItem().toString() + "/"
							+ annee.getText();
					Date d = new Date(date);
					
					//création de l'objet médecin
					Medecin m = new Medecin();
					m.setMatricule(MatMdecin.getText());
					
					if (getPatientType() == 0) {//Le patient est déja enregistré dans la base de données
						Patient p = new Patient();//création de l'objet patient
						p.setCode(CodePatient.getText());
						Rendezvous rdv = new Rendezvous(Rendezvous.genererNumRdv(), p, m, d,heure.getText(), Login.user);//création de l'objet rendezvous
						if (rdv.VerifierValidite()) {//vérifier la validité de la date
							rdv.ajouterRdv();//ajout du rendz-vous
							JOptionPane.showMessageDialog(null,"Rendez-vous enregistré");
							frmNouveauRendezvous.dispose();
						}
						else{//date non valide
							JOptionPane.showMessageDialog(null,"Entrez une date valide");
						}
					}
					else {//Le patient n'est pas enregistré dans la base de données
						String research = CodePatient.getText();
						String[] parts = research.split("\\s+");//séparer le nom du prénom
						PRendezvous prdv = new PRendezvous(PRendezvous.genererPNumRdv(), m, d,heure.getText(), Login.user, parts[0],
								parts[1]);//création de l'objet rendezvous
						if (prdv.VerifierValidite()) {//vérifier la validité de la date
							prdv.ajouterRdv();//ajout du rendez-vous
							JOptionPane.showMessageDialog(null,"Rendez-vous enregistré");
							frmNouveauRendezvous.dispose();
						}
						else{// date non valide
							JOptionPane.showMessageDialog(null,"Entrez une date valide");
						}
					}
					if(Login.usertype==0){//l'utilisateur est un médecin
						AgendaFrame.refreshAgenda();//actualiser son agenda
					}
					else{//l'utilisateur est une secretaire
						ListeRdvFrame.refreshRdv(); // actualiser la liste des rendez vous
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Vous devez remplir tous les champs");
				}
			}
		});
		btnEnregistrer.setBounds(340, 251, 111, 23);
		frmNouveauRendezvous.getContentPane().add(btnEnregistrer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNouveauRendezvous.dispose();
			}
		});
		btnAnnuler.setBounds(461, 251, 89, 23);
		frmNouveauRendezvous.getContentPane().add(btnAnnuler);
		
		final JButton parcourirPatient = new JButton("...");
		parcourirPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNouveauRendezvous.dispose();
				ParcourPatient.main(null);
			}
		});
		parcourirPatient.setBounds(319, 89, 25, 23);
		frmNouveauRendezvous.getContentPane().add(parcourirPatient);
		
		JButton parcourirMedecin = new JButton("...");
		parcourirMedecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNouveauRendezvous.dispose();
				ParcourMedecin.main(null);
			}
		});
		parcourirMedecin.setBounds(319, 49, 25, 23);
		frmNouveauRendezvous.getContentPane().add(parcourirMedecin);
		
		final JButton btnPatientNonenregistr = new JButton("");
		btnPatientNonenregistr.setText("Patient \n non-enregistré");
		btnPatientNonenregistr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPatient.setText("Nom et prénom"); // afficher un champ pour saisir le nom et le prénom (au lieu du code)
				parcourirPatient.setVisible(false);
				btnPatientNonenregistr.setVisible(false);
				setPatientType(1);//changer le type du patient
			}
		});
		btnPatientNonenregistr.setBounds(355, 84, 205, 33);
		frmNouveauRendezvous.getContentPane().add(btnPatientNonenregistr);
		btnPatientNonenregistr.setIcon(img);
		
	}
}