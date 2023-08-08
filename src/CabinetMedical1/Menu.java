package CabinetMedical1;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JSpinner;

import java.awt.Image;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import java.awt.Font;
public class Menu {
	//création des icon utilisé pour l'interface
	ImageIcon img = new ImageIcon("Menu.jpg");
	ImageIcon imgCons = new ImageIcon("nouveauPatient.png");
	ImageIcon imgGestionP=new ImageIcon("GestionP.png");
	ImageIcon history = new ImageIcon("history.png");
	ImageIcon agenda = new ImageIcon("Agenda.png");
	ImageIcon stats = new ImageIcon("stats.png");
	ImageIcon staff = new ImageIcon("staff.png");
	ImageIcon imgPerso = new ImageIcon("personal.png");
	ImageIcon logout = new ImageIcon("Logout.png");

	private JFrame frmMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.setBounds(100, 100, 618, 266);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		
		//Bouton liste du personnel
		JButton btnListeDeMedecins = new JButton("Liste du Personnel");
		btnListeDeMedecins.setBackground(SystemColor.textHighlight);
		btnListeDeMedecins.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListeDeMedecins.setForeground(SystemColor.text);
		btnListeDeMedecins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				ListePersonnelFrame.main(null);//appel de la frame contenant les liste du personnel du cabinet
			}
		});
		btnListeDeMedecins.setBounds(0, 152, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnListeDeMedecins);
		btnListeDeMedecins.setIcon(staff);
		
		//bouton liste des rendez-vous
		JButton btnListeDesRendezvous = new JButton("Gestion des rendez-vous");
		btnListeDesRendezvous.setBackground(SystemColor.textHighlight);
		btnListeDesRendezvous.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListeDesRendezvous.setForeground(SystemColor.text);
		btnListeDesRendezvous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				ListeRdvFrame.main(null);//appel de la frame liste des endez-vous
			}
		});
		btnListeDesRendezvous.setBounds(0, 76, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnListeDesRendezvous);
		btnListeDesRendezvous.setIcon(agenda);
		
		//bouton gestion des patients
		JButton btnGestionDePatients = new JButton("Gestion de patients");
		btnGestionDePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				GestionPatients.main(null);//appel de la frame gestion des patients
			}
		});
		btnGestionDePatients.setBackground(SystemColor.textHighlight);
		btnGestionDePatients.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGestionDePatients.setForeground(SystemColor.window);
		btnGestionDePatients.setBounds(0, 0, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnGestionDePatients);
		btnGestionDePatients.setIcon(imgGestionP);
		
		//buton statistiques
		JButton btnNewButton = new JButton("Statistiques");
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				Statistiques.main(null);//appel de la frame statistiques
			}
		});
		btnNewButton.setBounds(0, 114, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnNewButton);
		btnNewButton.setIcon(stats);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setBackground(SystemColor.activeCaption);
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				Login.main(null);
			}
		});
		btnDeconnexion.setBounds(451, 8, 142, 23);
		frmMenuPrincipal.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setIcon(logout);
		
		//bouton espace personnel
		JButton btnEspacePersonnel = new JButton("Espace personnel");
		btnEspacePersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				PersonnelSpace.main(null);//appel de la frame espace personnel
			}
		});
		btnEspacePersonnel.setBackground(SystemColor.textHighlight);
		btnEspacePersonnel.setForeground(SystemColor.text);
		btnEspacePersonnel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEspacePersonnel.setBounds(0, 190, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnEspacePersonnel);
		btnEspacePersonnel.setIcon(imgPerso);
		
		//bouton historique des consultations
		JButton btnHistoriqueDesConsultations = new JButton("Historique des consultations");
		btnHistoriqueDesConsultations.setBackground(SystemColor.textHighlight);
		btnHistoriqueDesConsultations.setForeground(SystemColor.text);
		btnHistoriqueDesConsultations.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHistoriqueDesConsultations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistoriqueConsFrame.main(null);//appel de la frame historique des consultations
			}
		});
		btnHistoriqueDesConsultations.setBounds(0, 38, 175, 38);
		frmMenuPrincipal.getContentPane().add(btnHistoriqueDesConsultations);
		btnHistoriqueDesConsultations.setIcon(history);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(178, 0, 501, 228);
		frmMenuPrincipal.getContentPane().add(lblNewLabel);
		
		
		lblNewLabel.setIcon(img);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
