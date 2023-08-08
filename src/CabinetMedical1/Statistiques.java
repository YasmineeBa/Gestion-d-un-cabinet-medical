package CabinetMedical1;

import java.awt.EventQueue;




import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.SystemColor;

@SuppressWarnings("unused")
public class Statistiques {
	ImageIcon imgSearch = new ImageIcon("Search.png");
	
	private JFrame frmStaistiques;
	private JLabel lblnbrPmaladie;
	private JTextField textPatientmédecin;
	private JTextField textPatientPeriode;
	private JTextField textPatient_maladie;
	private JLabel lblselectmaladie;
	private JLabel lblSelectMdecin;
	private JTextField textMaladie;
	private JTextField textMatmédecin;
	private JTextField textMédecin;
	private JButton btnNewButton_2;
	private JTextField textAA;
	private JTextField textannee;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistiques window = new Statistiques();
					window.frmStaistiques.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Statistiques() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStaistiques = new JFrame();
		frmStaistiques.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmStaistiques.getContentPane().setIgnoreRepaint(true);
		frmStaistiques.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frmStaistiques.setTitle("Staistiques");
		frmStaistiques.setBounds(200, 100, 528, 514);
		frmStaistiques.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaistiques.getContentPane().setLayout(null);
		
		lblnbrPmaladie = new JLabel("Nombre de Patient par maladie");
		lblnbrPmaladie.setBackground(SystemColor.menu);
		lblnbrPmaladie.setOpaque(true);
		lblnbrPmaladie.setIgnoreRepaint(true);
		lblnbrPmaladie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblnbrPmaladie.setBounds(10, 140, 232, 28);
		frmStaistiques.getContentPane().add(lblnbrPmaladie);
		
		JLabel lblnbrpatient = new JLabel("Nombre de Patient par periode");
		lblnbrpatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblnbrpatient.setOpaque(true);
		lblnbrpatient.setBackground(SystemColor.menu);
		lblnbrpatient.setBounds(10, 11, 232, 28);
		frmStaistiques.getContentPane().add(lblnbrpatient);
		
		JLabel lblnbrPmedecin = new JLabel("Nombre de Patient par m\u00E9decin");
		lblnbrPmedecin.setBackground(SystemColor.menu);
		lblnbrPmedecin.setOpaque(true);
		lblnbrPmedecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblnbrPmedecin.setBounds(10, 287, 232, 28);
		frmStaistiques.getContentPane().add(lblnbrPmedecin);
		
		textPatientmédecin = new JTextField();
		textPatientmédecin.setBounds(10, 410, 79, 28);
		frmStaistiques.getContentPane().add(textPatientmédecin);
		textPatientmédecin.setColumns(10);
		
		textPatientPeriode = new JTextField();
		textPatientPeriode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPatientPeriode.setBounds(10, 105, 135, 24);
		frmStaistiques.getContentPane().add(textPatientPeriode);
		textPatientPeriode.setColumns(10);
		
		textPatient_maladie = new JTextField();
		textPatient_maladie.setBounds(10, 228, 79, 28);
		frmStaistiques.getContentPane().add(textPatient_maladie);
		textPatient_maladie.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Date_Debut");
		lblNewLabel_3.setBackground(SystemColor.menu);
		lblNewLabel_3.setToolTipText("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 35, 84, 28);
		frmStaistiques.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date_Fin");
		lblNewLabel_4.setBackground(SystemColor.menu);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 62, 84, 28);
		frmStaistiques.getContentPane().add(lblNewLabel_4);
		
		
		lblselectmaladie = new JLabel("Maladie");
		lblselectmaladie.setOpaque(true);
		lblselectmaladie.setBackground(SystemColor.menu);
		lblselectmaladie.setHorizontalAlignment(SwingConstants.CENTER);
		lblselectmaladie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblselectmaladie.setBounds(10, 179, 69, 28);
		frmStaistiques.getContentPane().add(lblselectmaladie);
		
		lblSelectMdecin = new JLabel("Matricule");
		lblSelectMdecin.setOpaque(true);
		lblSelectMdecin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectMdecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectMdecin.setBackground(SystemColor.menu);
		lblSelectMdecin.setBounds(10, 358, 64, 28);
		frmStaistiques.getContentPane().add(lblSelectMdecin);
		
		final JComboBox<Integer> JJ = new JComboBox<Integer>();
		JJ.setBounds(103, 41, 42, 20);
		frmStaistiques.getContentPane().add(JJ);
		
		final JComboBox<Integer> MM = new JComboBox<Integer>();
		MM.setBounds(155, 41, 41, 20);
		frmStaistiques.getContentPane().add(MM);
		
		final JComboBox<Integer> Jours = new JComboBox<Integer>();
		Jours.setBounds(104, 68, 41, 20);
		frmStaistiques.getContentPane().add(Jours);
		
		for(int i=1;i<32;i++){
			JJ.addItem(i);
			Jours.addItem(i);
		}
		
		
		final JComboBox<Integer> Mois = new JComboBox<Integer>();
		Mois.setBounds(155, 68, 41, 20);
		frmStaistiques.getContentPane().add(Mois);
		
		for(int i=1;i<13;i++){
			MM.addItem(i);
			Mois.addItem(i);
		}
		
		JButton btnPeriode = new JButton("Calculer");
		btnPeriode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String DateDeb = JJ.getSelectedItem().toString()+"/"+MM.getSelectedItem().toString()+"/"+textAA.getText();
					String DateFin= Jours.getSelectedItem().toString()+"/"+Mois.getSelectedItem().toString()+"/"+textannee.getText();
					if (DateFin.compareTo(DateDeb)>0) {//vérifier si la date du début < date de fin
						String sql = "select count(*)from patient where DateInsc between '"
								+ DateDeb + "' and'" + DateFin + "'";
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							textPatientPeriode.setText(rs.getString(1));
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Entrez des dates valides");
					}
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"erreur");
				}
			
			}
			
		});
		btnPeriode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPeriode.setBounds(165, 100, 121, 26);
		frmStaistiques.getContentPane().add(btnPeriode);
		btnPeriode.setIcon(imgSearch);
		
		textMaladie = new JTextField();
		textMaladie.setBounds(80, 183, 162, 24);
		frmStaistiques.getContentPane().add(textMaladie);
		textMaladie.setColumns(10);
		
		textMatmédecin = new JTextField();
		textMatmédecin.setBounds(80, 362, 151, 24);
		frmStaistiques.getContentPane().add(textMatmédecin);
		textMatmédecin.setColumns(10);
		textMatmédecin.setText(ParcourMédecin.parcourM);
		
		JButton ParcourirMédecin = new JButton("New button");
		ParcourirMédecin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmStaistiques.dispose();
				ParcourMédecin.main(null);
			}
		});
		
		
		
		JButton btnNewButton = new JButton("Calculer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					
					String maladie=textMaladie.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String DateDeb = JJ.getSelectedItem().toString()+"/"+MM.getSelectedItem().toString()+"/"+textAA.getText();
					String DateFin= Jours.getSelectedItem().toString()+"/"+Mois.getSelectedItem().toString()+"/"+textannee.getText();
					String sql="select count(*)from fiche_maladie where nommaladie='"+maladie+"'";
					ResultSet rs = stmt.executeQuery(sql);
					textPatient_maladie.setText(String.valueOf(rs));
					if(rs.next()) {
						String add = rs.getString("count(*)");
						textPatient_maladie.setText(add);}
					

				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,"erreur");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(99, 228, 143, 25);
		frmStaistiques.getContentPane().add(btnNewButton);
		btnNewButton.setIcon(imgSearch);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					
					String sql="select count(*)from fichemedicale  where Matricule ='"+ParcourMédecin.parcourM+"'";
                    
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						String add1 = rs.getString("count(*)");
						textPatientmédecin.setText(add1);}
					
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,"erreur");
				}
			}
			
		});
		btnCalculer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCalculer.setBounds(99, 410, 143, 25);
		frmStaistiques.getContentPane().add(btnCalculer);
		btnCalculer.setIcon(imgSearch);
		
		textMédecin = new JTextField();
		textMédecin.setName("textNomm\u00E9decin");
		textMédecin.setBounds(80, 316, 151, 26);
		frmStaistiques.getContentPane().add(textMédecin);
		textMédecin.setColumns(10);
		textMédecin.setText(ParcourMédecin.NameMédecin);
		
		btnNewButton_2 = new JButton("Retour");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmStaistiques.dispose();
				if(Login.usertype==0){
					MenuMedecin.main(null);
				}
				else{
					Menu.main(null);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(363, 441, 139, 24);
		frmStaistiques.getContentPane().add(btnNewButton_2);
		
		textAA = new JTextField();
		textAA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAA.setBounds(206, 39, 79, 24);
		frmStaistiques.getContentPane().add(textAA);
		textAA.setColumns(10);
		
		textannee = new JTextField();
		textannee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textannee.setColumns(10);
		textannee.setBounds(206, 66, 79, 24);
		frmStaistiques.getContentPane().add(textannee);
		
		JLabel lblNameMedecin = new JLabel("m\u00E9decin");
		lblNameMedecin.setOpaque(true);
		lblNameMedecin.setBackground(SystemColor.menu);
		lblNameMedecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameMedecin.setBounds(10, 313, 184, 29);
		frmStaistiques.getContentPane().add(lblNameMedecin);
		
		
		ParcourirMédecin.setBounds(241, 362, 30, 25);
		frmStaistiques.getContentPane().add(ParcourirMédecin);
	}	
}