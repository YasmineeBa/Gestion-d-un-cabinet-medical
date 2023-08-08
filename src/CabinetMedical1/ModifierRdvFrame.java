package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierRdvFrame {

	private JFrame frmModifierLeRendezvous;
	private JTextField textDate;
	private JTextField textHeure;
	public static String NumRendezVous,date,Heure;
	public static int PatientType;//PatientType=0 si le patient est e,registré dans la base de données, 1 sinon

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierRdvFrame window = new ModifierRdvFrame();
					window.frmModifierLeRendezvous.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */

	public ModifierRdvFrame() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	public static String getNumRendezVous() {
		return NumRendezVous;
	}
	public static void setNumRendezVous(String numRendezVous) {
		NumRendezVous = numRendezVous;
	}
	public static String getDate() {
		return date;
	}
	public static void setDate(String date) {
		ModifierRdvFrame.date = date;
	}
	public static String getHeure() {
		return Heure;
	}
	public static void setHeure(String heure) {
		Heure = heure;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifierLeRendezvous = new JFrame();
		frmModifierLeRendezvous.setTitle("Modifier le rendez-vous");
		frmModifierLeRendezvous.setBounds(100, 100, 451, 192);
		frmModifierLeRendezvous.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifierLeRendezvous.getContentPane().setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(50, 35, 46, 14);
		frmModifierLeRendezvous.getContentPane().add(lblDate);
		
		JLabel lblHeure = new JLabel("Heure");
		lblHeure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeure.setBounds(40, 76, 46, 14);
		frmModifierLeRendezvous.getContentPane().add(lblHeure);
		
		textDate = new JTextField();
		textDate.setBounds(104, 34, 143, 20);
		frmModifierLeRendezvous.getContentPane().add(textDate);
		textDate.setColumns(10);
		textDate.setText(date);
		
		textHeure = new JTextField();
		textHeure.setBounds(104, 75, 86, 20);
		frmModifierLeRendezvous.getContentPane().add(textHeure);
		textHeure.setColumns(10);
		textHeure.setText(Heure);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmModifierLeRendezvous.dispose();
			}
		});
		btnAnnuler.setBounds(336, 120, 89, 23);
		frmModifierLeRendezvous.getContentPane().add(btnAnnuler);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date dd = new Date();
				@SuppressWarnings("deprecation")
				Date d = new Date(textHeure.getText());//conversion de la date
				if (d.compareTo(dd)>0) {//Vérifier si la date est valide
					int action = JOptionPane.showConfirmDialog(null,"Voulez-vous modifier le rendez-vous?");//demande de confirmation
					if (action == 0) {//confirmation obtenue
						if (PatientType == 0) {//patient enregistré dans BDD
							Rendezvous.modifierRdv(NumRendezVous,textDate.getText(), textHeure.getText());//enregistré les modifications
						} 
						else {//patient non enregitré dans la BDD
							PRendezvous.modifierRdv(NumRendezVous,textDate.getText(), textHeure.getText());//enregistré les modifications
						}
						if (Login.usertype == 0) {//l'utlisateur est un médecin
							AgendaFrame.refreshAgenda();//actualiser son agenda
						} else {//l'utilisateur est une secretaire
							ListeRdvFrame.refreshRdv();//actualiser la liste des rendez-vous
						}
						frmModifierLeRendezvous.dispose();
					}
				}
				else{//date non valide
					JOptionPane.showMessageDialog(null,"Entrez une date valide");
				}
			}
		});
		btnEnregistrer.setBounds(200, 120, 117, 23);
		frmModifierLeRendezvous.getContentPane().add(btnEnregistrer);
	}
}
