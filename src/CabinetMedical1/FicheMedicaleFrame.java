package CabinetMedical1;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


public class FicheMedicaleFrame {
	
	 ImageIcon imgSave = new ImageIcon("save.png");

	private JFrame frmFicheMdicale;
	private JTable table;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textFieldAge;
	private JTextField textTaille;
	private JTextField textPoids;
	private JTextField textTension;
	private JTextField textDiab;
	private JTable table_1;
	//NumFiche
	final String numFiche=ParcourPatientCons.OpeningCode;
	private JComponent lblTauxDiabte;
	private JTextField textGrpSanguin;
	private AbstractButton lblDoc;
	public static String OpeningAnalyse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FicheMedicaleFrame window = new FicheMedicaleFrame();
					window.frmFicheMdicale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FicheMedicaleFrame() {
		initialize();
	}	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFicheMdicale = new JFrame();
		frmFicheMdicale.setTitle("Fiche M\u00E9dicale");
		frmFicheMdicale.setBounds(100, 100, 968, 611);
		frmFicheMdicale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFicheMdicale.getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNom.setBounds(10, 11, 46, 14);
		frmFicheMdicale.getContentPane().add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom:");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrnom.setBounds(10, 36, 72, 14);
		frmFicheMdicale.getContentPane().add(lblPrnom);
		
		JLabel lblge = new JLabel("\u00C2ge:");
		lblge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblge.setBounds(10, 61, 72, 20);
		frmFicheMdicale.getContentPane().add(lblge);
		
		JLabel lblGroupeSanguin = new JLabel("Groupe sanguin:");
		lblGroupeSanguin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroupeSanguin.setBounds(10, 92, 118, 20);
		frmFicheMdicale.getContentPane().add(lblGroupeSanguin);
		
		JLabel lblTaille = new JLabel("Taille:");
		lblTaille.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTaille.setBounds(10, 123, 46, 14);
		frmFicheMdicale.getContentPane().add(lblTaille);
		
		JLabel lblPoids = new JLabel("Poids:");
		lblPoids.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPoids.setBounds(10, 148, 46, 14);
		frmFicheMdicale.getContentPane().add(lblPoids);
		
		JLabel lblTension = new JLabel("Tension:");
		lblTension.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTension.setBounds(10, 173, 72, 14);
		frmFicheMdicale.getContentPane().add(lblTension);
		
		JLabel lblAnalyse = new JLabel("Analyses:");
		lblAnalyse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnalyse.setBounds(10, 308, 72, 20);
		frmFicheMdicale.getContentPane().add(lblAnalyse);
		
		JLabel lblTauxDiabte = new JLabel("Taux diab\u00E8te:");
		lblTauxDiabte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTauxDiabte.setBounds(214, 203, 104, 14);
		frmFicheMdicale.getContentPane().add(lblTauxDiabte);
		
		textNom = new JTextField();
		textNom.setBounds(73, 10, 186, 20);
		frmFicheMdicale.getContentPane().add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(73, 35, 186, 20);
		frmFicheMdicale.getContentPane().add(textPrenom);
		textPrenom.setColumns(10);
		
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(73, 61, 86, 20);
		frmFicheMdicale.getContentPane().add(textFieldAge);
		textFieldAge.setColumns(10);
		
		//remplir les champs nom et prenom du patient
		
		try {
			Connection conn=SqlConnection.dbConnector();
			Statement stmt=conn.createStatement();
			String sql="select nom, prenom from patient where IDPatient='"+numFiche+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) {
				textNom.setText(rs.getString(1));
				textPrenom.setText(rs.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		textTaille = new JTextField();
		textTaille.setBounds(66, 123, 86, 20);
		frmFicheMdicale.getContentPane().add(textTaille);
		textTaille.setColumns(10);
		textTaille.setText("0");
		
		JLabel lblCm = new JLabel("Cm");
		lblCm.setBounds(162, 125, 46, 14);
		frmFicheMdicale.getContentPane().add(lblCm);
		
		textPoids = new JTextField();
		textPoids.setBounds(66, 147, 86, 20);
		frmFicheMdicale.getContentPane().add(textPoids);
		textPoids.setColumns(10);
		textPoids.setText("0");
		
		JLabel lblKg = new JLabel("Kg");
		lblKg.setBounds(162, 150, 46, 14);
		frmFicheMdicale.getContentPane().add(lblKg);
		
		textTension = new JTextField();
		textTension.setBounds(73, 172, 86, 20);
		frmFicheMdicale.getContentPane().add(textTension);
		textTension.setColumns(10);
		textTension.setText(" ");
		
		textDiab = new JTextField();
		textDiab.setBounds(328, 202, 86, 20);
		frmFicheMdicale.getContentPane().add(textDiab);
		textDiab.setColumns(10);
		textDiab.setText("0");
		
		//v�rifier si le patient est diab�tique
		
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql="select*from fiche_maladie where numfiche='"+numFiche+"' and nommaladie='diabete' ";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){//dans le cas o� le patient est diab�tique on ajoute le champs taux diabete
				lblTauxDiabte.setVisible(true);
				textDiab.setVisible(true);
			}
			else{
				lblTauxDiabte.setVisible(false);
				textDiab.setVisible(false);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmFicheMdicale.dispose();
			}
		});
		btnRetour.setBounds(853, 539, 89, 23);
		frmFicheMdicale.getContentPane().add(btnRetour);
 
 JPanel panel = new JPanel();
 panel.setBounds(10, 340, 923, 153);
 frmFicheMdicale.getContentPane().add(panel);
 panel.setLayout(null);
 
 JScrollPane scrollPane = new JScrollPane();
 scrollPane.setBounds(0, 0, 923, 153);
 panel.add(scrollPane);
 
 table = new JTable();
 scrollPane.setViewportView(table);
 
 JLabel lblMaladies = new JLabel("Maladies:");
 lblMaladies.setFont(new Font("Tahoma", Font.PLAIN, 16));
 lblMaladies.setBounds(10, 203, 86, 14);
 frmFicheMdicale.getContentPane().add(lblMaladies);
 
 JPanel panel_1 = new JPanel();
 panel_1.setBounds(10, 228, 209, 76);
 frmFicheMdicale.getContentPane().add(panel_1);
 panel_1.setLayout(null);
 
 JScrollPane scrollPane_1 = new JScrollPane();
 scrollPane_1.setBounds(0, 0, 209, 76);
 panel_1.add(scrollPane_1);
 
 table_1 = new JTable();
 scrollPane_1.setViewportView(table_1);
 
 JLabel lblModif = new JLabel("Derni\u00E8re modification : ");
 lblModif.setBounds(680, 507, 163, 14);
 frmFicheMdicale.getContentPane().add(lblModif);
 
textGrpSanguin = new JTextField();
textGrpSanguin.setBounds(133, 94, 46, 20);
frmFicheMdicale.getContentPane().add(textGrpSanguin);
textGrpSanguin.setColumns(10);
textGrpSanguin.setText(" ");
	
	
	
 refreshChamps();//actualiser les champs contenant l'�tat de sant� du patient 
	
	JLabel lblNewLabelModif = new JLabel("New label");
	lblNewLabelModif.setBounds(853, 507, 80, 14);
	frmFicheMdicale.getContentPane().add(lblNewLabelModif);
	lblNewLabelModif.setText(Login.user);
	
	final JComboBox comboBox = new JComboBox();
	comboBox.setBounds(191, 94, 46, 20);
	frmFicheMdicale.getContentPane().add(comboBox);
	lblNewLabelModif.repaint();
	comboBox.addItem("A-"); comboBox.addItem("A+"); comboBox.addItem("B+"); comboBox.addItem("B-"); 
	comboBox.addItem("O-"); comboBox.addItem("O+"); comboBox.addItem("AB-"); comboBox.addItem("AB+");
	
	
	
	 JButton btnEnregistrerResltat = new JButton("Enregistrer resltat");
	 btnEnregistrerResltat.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent arg0) {
	 		OpeningAnalyse=table.getValueAt(table.getSelectedRow(),0).toString(); //selectionn� l'analyse
	 		frmFicheMdicale.dispose();
	 		EnregistrerResultatFrame.main(null); //appeler la f�netre qui enregistre les r�sultat de l'analyse
	 	}
	 });
	 btnEnregistrerResltat.setBounds(10, 504, 155, 23);
	 frmFicheMdicale.getContentPane().add(btnEnregistrerResltat);	
 
 JButton btnEnregistrerLesModifications = new JButton("Enregistrer les modifications");
 btnEnregistrerLesModifications.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent arg0) {
 		//demande de la confirmation pour enregistrer les modifications
 		int action =JOptionPane.showConfirmDialog(null,"Voulez-vous enregistrer les modifications?");
 		if (action==0) {//confirmation obtenu
			FicheMedicale.modifierFicheMedicale(numFiche,String.valueOf(comboBox.getSelectedItem()),
					Integer.parseInt(textTaille.getText()),Integer.parseInt(textPoids.getText()),textTension.getText(),
					Double.parseDouble(textDiab.getText()));//enregistrer les modification
			EnregistrerResultatFrame.EnregistrerModifications();//enresgitrer le resultat des analyses modifi�es
			frmFicheMdicale.dispose();
		}
 		
 	}
 });
 btnEnregistrerLesModifications.setBounds(597, 539, 246, 23);
 frmFicheMdicale.getContentPane().add(btnEnregistrerLesModifications);
 btnEnregistrerLesModifications.setIcon(imgSave);
	
}	
	
	
	 public void refreshChamps(){//Initialisation des champs / acualisation
			FicheMedicale f=FicheMedicale.ChercherFicheMedicale(numFiche);//recherche de la fiche m�dicale
			if(f!=null){
				//initialisation
				textGrpSanguin.setText(f.getGroupeSanguin());
				textTaille.setText(String.valueOf(f.getTaille()));
				textPoids.setText(String.valueOf(f.getPoids()));
				textTension.setText(f.getTension());
				textDiab.setText(String.valueOf(f.getTauxDiabete()));
				textFieldAge.setText(String.valueOf(f.getAge()));
				try{//remplir la table qui contient les analyse
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					String sql="select numanalyse, bilan, resultat, commentaire from analyse where numfiche='"+numFiche+"'";
					ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e){
					System.out.println(e);
				}

				try{//remplir la table qui contient les maladies du patient
					Connection conn = SqlConnection.dbConnector();
					Statement stmt = conn.createStatement();
					String sql="select nommaladie as maladie from fiche_maladie where Numfiche='"+ParcourPatientCons.OpeningCode+"'";
					ResultSet rs = stmt.executeQuery(sql);
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
	 }	
}