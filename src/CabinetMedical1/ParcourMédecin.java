package CabinetMedical1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ParcourMédecin {

	private JFrame frame;
	public static JTable ParcourMtable;
	private int openingrow;
	public static String parcourM;
	public static String  NameMédecin;
	                   
	// * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParcourMédecin window = new ParcourMédecin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParcourMédecin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 250));
		frame.setBounds(100, 100, 741, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 685, 323);
		frame.getContentPane().add(scrollPane);
		
		ParcourMtable = new JTable();
		scrollPane.setViewportView(ParcourMtable);
		try{
			Connection conn = SqlConnection.dbConnector();
			Statement stmt = conn.createStatement();
			String sql ="select * from Medecin";
			ResultSet rs = stmt.executeQuery(sql);
			ParcourMtable.setModel(DbUtils.resultSetToTableModel(rs));
			
			JButton btnNewButton = new JButton("Ouvrir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openingrow= ParcourMtable.getSelectedRow();
					parcourM=String.valueOf(ParcourMtable.getValueAt(openingrow,0));//parcouM=reçois le matricule du médecin séléctionné
					 NameMédecin = String.valueOf(ParcourMtable.getValueAt(openingrow,1))+" "+String.valueOf(ParcourMtable.getValueAt(openingrow,2));//NameMédecin reçois le nom et le prenom du médecin séléctionné	
					 frame.dispose();
					 Statistiques.main(null);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBounds(287, 347, 132, 25);
			frame.getContentPane().add(btnNewButton);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
}
