package CabinetMedical1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class OrdonnanceFrame {

	private JFrame frmImpression;
	private JLabel lblDate1;
	private JTable table;
	public static JLabel lblDate = new JLabel("");
	public static JLabel labelMedecin = new JLabel("");
	public static JLabel labelPatient = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdonnanceFrame window = new OrdonnanceFrame();
					window.frmImpression.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrdonnanceFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImpression = new JFrame();
		frmImpression.setTitle("Impression");
		frmImpression.getContentPane().setBackground(Color.WHITE);
		frmImpression.setBounds(100, 100, 499, 695);
		frmImpression.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImpression.getContentPane().setLayout(null);
		
		JLabel lblPatient = new JLabel("A ");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPatient.setBounds(10, 136, 21, 14);
		frmImpression.getContentPane().add(lblPatient);
		
		JLabel lblMdecin = new JLabel("D\u00E9livr\u00E9 par : Docteur");
		lblMdecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMdecin.setBounds(10, 110, 154, 14);
		frmImpression.getContentPane().add(lblMdecin);
		
		lblDate1 = new JLabel("Date:");
		lblDate1.setBounds(317, 46, 46, 14);
		frmImpression.getContentPane().add(lblDate1);
		
		JLabel lblRepubliquealgerienneDemmocratiqueEt = new JLabel("REPUBLIQUEALGERIENNE DEMMOCRATIQUE ET POPULAIRE ");
		lblRepubliquealgerienneDemmocratiqueEt.setBounds(69, 13, 389, 14);
		frmImpression.getContentPane().add(lblRepubliquealgerienneDemmocratiqueEt);
		
		JLabel lblOrdonnance = new JLabel("ORDONNANCE");
		lblOrdonnance.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrdonnance.setBounds(150, 71, 160, 14);
		frmImpression.getContentPane().add(lblOrdonnance);
		

		lblDate.setBounds(353, 46, 105, 14);
		frmImpression.getContentPane().add(lblDate);
		
		
		labelMedecin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelMedecin.setBounds(162, 112, 137, 14);
		frmImpression.getContentPane().add(labelMedecin);
		
		
		labelPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPatient.setBounds(32, 138, 213, 14);
		frmImpression.getContentPane().add(labelPatient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 463, 340);
		frmImpression.getContentPane().add(scrollPane);
		
		table = new JTable(NouvelleConsultationFrame.modelMedoc);
		scrollPane.setViewportView(table);
		
		
		JLabel lblSignatureDuMdecin = new JLabel("Signature du m\u00E9decin");
		lblSignatureDuMdecin.setBounds(10, 519, 154, 14);
		frmImpression.getContentPane().add(lblSignatureDuMdecin);
		
		JLabel lblnePasLaisser = new JLabel("*Ne pas laisser les m\u00E9dicaments \u00E0 la port\u00E9e des enfants*");
		lblnePasLaisser.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblnePasLaisser.setBounds(175, 588, 283, 14);
		frmImpression.getContentPane().add(lblnePasLaisser);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmImpression.dispose();
			}
		});
		btnAnnuler.setBounds(369, 623, 89, 23);
		frmImpression.getContentPane().add(btnAnnuler);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrinterJob pjob = PrinterJob.getPrinterJob();
				PageFormat preformat = pjob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = pjob.pageDialog(preformat);
				//If user does not hit cancel then print.
				if (preformat != postformat) {
				    //Set print component
				    pjob.setPrintable(new Printer(frmImpression), postformat);
				    if (pjob.printDialog()) {
				        try {
							pjob.print();
						} catch (PrinterException e) {
							e.printStackTrace();
						}
				    }
				}
				
			}
		});
		btnImprimer.setBounds(242, 623, 121, 23);
		frmImpression.getContentPane().add(btnImprimer);
	}
}
