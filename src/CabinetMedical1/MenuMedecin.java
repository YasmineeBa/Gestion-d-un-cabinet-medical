package CabinetMedical1;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JLabel;

public class MenuMedecin {
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
	private JButton btnAgenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuMedecin window = new MenuMedecin();
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
	public MenuMedecin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu principal");
		frmMenuPrincipal.setBounds(100, 100, 667, 304);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		
		//Bouton liste du personnel
		JButton btnListeDeMedecins = new JButton("Liste du personnel");
		btnListeDeMedecins.setForeground(SystemColor.text);
		btnListeDeMedecins.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListeDeMedecins.setBackground(SystemColor.textHighlight);
		btnListeDeMedecins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				ListePersonnelFrame.main(null);//appel de la frame contenant les liste du personnel du cabinet
			}
		});
		btnListeDeMedecins.setBounds(0, 190, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnListeDeMedecins);
		btnListeDeMedecins.setIcon(staff);
		
		//bouton agenda
		btnAgenda = new JButton("Agenda");
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAgenda.setForeground(SystemColor.text);
		btnAgenda.setBackground(SystemColor.textHighlight);
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				AgendaFrame.main(null);//appel de la frame agenda
			}
		});
		btnAgenda.setBounds(0, 114, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnAgenda);
		btnAgenda.setIcon(agenda);
		
		//bouton gestion des patients
		JButton btnGestionDePatients = new JButton("Gestion de patients");
		btnGestionDePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				GestionPatients.main(null);//appel de la frame gestion des patients
			}
		});
		btnGestionDePatients.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGestionDePatients.setForeground(SystemColor.text);
		btnGestionDePatients.setBackground(SystemColor.textHighlight);
		btnGestionDePatients.setBounds(0, 38, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnGestionDePatients);
		btnGestionDePatients.setIcon(imgGestionP);
		
		//buton statistiques
		JButton btnNewButton = new JButton("Statistiques");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				Statistiques.main(null);//appel de la frame statistiques
			}
		});
		btnNewButton.setBounds(0, 152, 198, 38);
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
		btnDeconnexion.setBounds(482, 8, 159, 23);
		frmMenuPrincipal.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setIcon(logout);
		
		//bouton espace personnel
		JButton btnEspacePersonnel = new JButton("Espace personnel");
		btnEspacePersonnel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEspacePersonnel.setForeground(SystemColor.text);
		btnEspacePersonnel.setBackground(SystemColor.textHighlight);
		btnEspacePersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMenuPrincipal.dispose();
				PersonnelSpace.main(null);//appel de la frame espace personnel
			}
		});
		btnEspacePersonnel.setBounds(0, 228, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnEspacePersonnel);
		btnEspacePersonnel.setIcon(imgPerso);
		
		//bouton historique des consultations
		JButton btnHisConsultations = new JButton("Historique des consultations");
		btnHisConsultations.setForeground(SystemColor.text);
		btnHisConsultations.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHisConsultations.setBackground(SystemColor.textHighlight);
		btnHisConsultations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistoriqueConsFrame.main(null);//appel de la frame historique des consultations
			}
		});
		btnHisConsultations.setBounds(0, 76, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnHisConsultations);
		btnHisConsultations.setIcon(history);
		
		JButton btnNouvelleConsultation = new JButton("Nouvelle consultation");
		btnNouvelleConsultation.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNouvelleConsultation.setForeground(SystemColor.text);
		btnNouvelleConsultation.setBackground(SystemColor.textHighlight);
		btnNouvelleConsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NouvelleConsultationFrame.main(null);
			}
		});
		btnNouvelleConsultation.setBounds(0, 0, 198, 38);
		frmMenuPrincipal.getContentPane().add(btnNouvelleConsultation);
		btnNouvelleConsultation.setIcon(imgCons);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(206, 0, 445, 266);
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
