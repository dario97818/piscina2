package it.unirc.bd.gui.iscritto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InserisciIscritto extends JDialog {
	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNOME;
	private JTextField txtCOGNOME;
	private JTextField txtETA;
	private JTextField txtCELLULARE;
	//-----VARIABILI ISCRITTO DA PASSARE ALLA QUERY------
	private int idIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private int eta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciIscritto dialog = new InserisciIscritto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciIscritto() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 435, 267);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtID = new JTextField();
			txtID.setBounds(64, 16, 116, 22);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		
		JLabel lblID = new JLabel("ID: ");
		lblID.setBounds(12, 18, 56, 19);
		contentPanel.add(lblID);
		
		JLabel lblNOME = new JLabel("Nome:");
		lblNOME.setBounds(12, 54, 56, 16);
		contentPanel.add(lblNOME);
		
		txtNOME = new JTextField();
		txtNOME.setBounds(64, 51, 116, 22);
		contentPanel.add(txtNOME);
		txtNOME.setColumns(10);
		
		txtCOGNOME = new JTextField();
		txtCOGNOME.setBounds(293, 51, 116, 22);
		contentPanel.add(txtCOGNOME);
		txtCOGNOME.setColumns(10);
		
		txtETA = new JTextField();
		txtETA.setBounds(64, 88, 116, 22);
		contentPanel.add(txtETA);
		txtETA.setColumns(10);
		
		txtCELLULARE = new JTextField();
		txtCELLULARE.setBounds(293, 86, 116, 22);
		contentPanel.add(txtCELLULARE);
		txtCELLULARE.setColumns(10);
		
		JLabel lblETA = new JLabel("Et\u00E0:");
		lblETA.setBounds(12, 91, 56, 16);
		contentPanel.add(lblETA);
		
		JLabel lblCOGNOME = new JLabel("Cognome:");
		lblCOGNOME.setBounds(208, 54, 73, 16);
		contentPanel.add(lblCOGNOME);
		
		JLabel lblCELLULLARE = new JLabel("Cellulare");
		lblCELLULLARE.setBounds(208, 88, 73, 22);
		contentPanel.add(lblCELLULLARE);
		
		JLabel lblSESSO = new JLabel("Sesso:");
		lblSESSO.setBounds(12, 128, 56, 16);
		contentPanel.add(lblSESSO);
		
		JComboBox cbSESSO = new JComboBox();
		cbSESSO.setBounds(64, 125, 84, 22);
		cbSESSO.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbSESSO.setToolTipText("");
		contentPanel.add(cbSESSO);
		
		JButton btnINSERISCI = new JButton("Inserisci");
		btnINSERISCI.setEnabled(false);
		btnINSERISCI.addActionListener(new ActionListener() { //INSERIMENTO ISCRITTO
			public void actionPerformed(ActionEvent arg0) {
				idIscritto=Integer.parseInt(txtID.getText()); //CASTING DA STRING A INT
				nome = txtNOME.getText();
				cognome = txtCOGNOME.getText();
				sesso=(String) cbSESSO.getModel().getElementAt(cbSESSO.getSelectedIndex());	//ATTENZIONE AL CASTING
				cellulare = txtCELLULARE.getText();
				eta = Integer.parseInt(txtETA.getText()); //CASTING DA STRING A INT
				Iscritto i = new Iscritto(idIscritto, nome, cognome, sesso, cellulare, eta);
				iDAOP.salvaIscritto(i);
			}
		});
	
		btnINSERISCI.setBounds(173, 177, 97, 25);
		contentPanel.add(btnINSERISCI);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(235, 19, 56, 16);
		contentPanel.add(lblNewLabel);
		
		
	}
}
