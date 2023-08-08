package CabinetMedical1;

import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class AgendaFrame {
	ImageIcon img = new ImageIcon("nouveauPatient.png");
	ImageIcon imgLogout = new ImageIcon("Logout.png");
	ImageIcon imgDelete = new ImageIcon("delete.png");

	private JFrame frmAgenda;
	public static  JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaFrame window = new AgendaFrame();
					window.frmAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgendaFrame() {
		initialize();
	}
	public static void refreshAgenda(){//actualiser la table
		//default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();
		//Création de l'instance localDate
		LocalDate dd= LocalDate.now(); // date du jour
		LocalDate week= dd.plus(1,ChronoUnit.WEEKS); //date apres une semaine
		//Converssion en instance Date
		Date daydate= Date.from(dd.atStartOfDay(defaultZoneId).toInstant());
		Date weekdate = Date.from(week.atStartOfDay(defaultZoneId).toInstant());
		// Converssion en String
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ddString = sdf.format(daydate);
		String weekdString = sdf.format(weekdate);
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="(select rendezvous.numrendezvous, patient.nom, patient.prenom, rendezvous.datere, rendezvous.heure, rendezvous.modif "
					+ "from patient, rendezvous where (patient.IDPatient=rendezvous.IDpatient and rendezvous.matricule='"+Login.user+"' and rendezvous.datere between"
					+" '"+ddString+"' and '"+weekdString+"' )) union"
					+ "(select numrendezvous,nom,prenom, datere, heure, modif from prendezvous where (matricule='"+Login.user+"' and datere "
					+ " between '"+ddString+"' and '"+weekdString+"' ))";
			ResultSet rs = stmt.executeQuery(sql);
			DefaultTableModel model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
			model.addColumn("   ");
			table.setModel(model);
			for(int i=0; i<table.getRowCount();i++){
				if (table.getValueAt(i, 0).toString().contains("p")) {
					table.setValueAt("Nouveau", i,6);
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgenda = new JFrame();
		frmAgenda.getContentPane().setBackground(Color.WHITE);
		frmAgenda.setTitle("Agenda");
		frmAgenda.setBounds(100, 100, 779, 478);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgenda.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 745, 353);
		frmAgenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refreshAgenda();
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAgenda.dispose();
				MenuMedecin.main(null);
			}
		});
		btnRetour.setBounds(659, 413, 89, 23);
		frmAgenda.getContentPane().add(btnRetour);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//initialisation des champs de la fênetre modifier rendez-vous
				ModifierRdvFrame.NumRendezVous=table.getValueAt(table.getSelectedRow(),0).toString();
				ModifierRdvFrame.date=new SimpleDateFormat("dd/MM/yyyy").format(table.getValueAt(table.getSelectedRow(),3));
				ModifierRdvFrame.Heure=table.getValueAt(table.getSelectedRow(),4).toString();
				//appel de la fênetre
				ModifierRdvFrame.main(null);
			}
		});
		btnModifier.setBounds(560, 413, 89, 23);
		frmAgenda.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//demande de la confirmation pour supprimer le rendez-vous
				int action =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer ce rendez-vous?");
				if(action==0){
					if (!table.getValueAt(table.getSelectedRow(), 0).toString().contains("p")) {//cas où le patient est enregistré sur la base de données
						Rendezvous.supprimerRdv(table.getValueAt(table.getSelectedRow(), 0).toString());
					}
					else{//cas où le patient n'est pas enregistré sur la base de données
						PRendezvous.supprimerPRdv(table.getValueAt(table.getSelectedRow(),0).toString());
					}
					JOptionPane.showMessageDialog(null,"Rendez-vous supprimé!");
					refreshAgenda();//actualiser la table
				}
			}
		});
		btnSupprimer.setBounds(435, 413, 115, 23);
		frmAgenda.getContentPane().add(btnSupprimer);
		btnSupprimer.setIcon(imgDelete);
		
		JButton btnNouveauRendezvous = new JButton("Nouveau rendez-vous");
		btnNouveauRendezvous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NouveauRdvFrame.main(null);//appel de la frame nouveau rendez-vous
				refreshAgenda();//actualiser la table
			}
		});
		btnNouveauRendezvous.setBounds(398, 11, 185, 23);
		frmAgenda.getContentPane().add(btnNouveauRendezvous);
		btnNouveauRendezvous.setIcon(img);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setBackground(SystemColor.activeCaption);
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAgenda.dispose();
				Login.main(null);
			}
		});
		btnDeconnexion.setBounds(593, 11, 150, 23);
		frmAgenda.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setIcon(imgLogout);
	}
}
