package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAO.Requetes;

public class TempsReel extends JPanel{
	int nbCapteur = 3;
	Object[][] donnees;
	Requetes req = new Requetes();
	JTable table;
	String[] titre = {"Nom", "Type du fluide", "Batiment","Etage", "Pièce", "Valeur", "Unite"};
	ModeleDonnees md;
	JComboBox<String> liste1;
	String batiment;
	String type;
	int nbItems;

	private static final long serialVersionUID = 1L;

	public TempsReel() {
		setLayout(new BorderLayout());
		
		JPanel jolie = new JPanel(new BorderLayout());

		jolie.setLayout(new BorderLayout());
		add(jolie,BorderLayout.CENTER);
		JLabel txt = new JLabel(" Panneau de visualisation en temps réel ");
		txt.setFont(new Font(txt.getText(), Font.PLAIN, 20));
		txt.setBorder(BorderFactory.createLineBorder(Color.black));
		add(txt,BorderLayout.NORTH);

		refreshData();
		md = new ModeleDonnees(donnees,titre,req,this);
		table = new JTable(md);
		TableColumnModel tcol = table.getColumnModel();
		tcol.getColumn(0).setPreferredWidth(90);
		tcol.getColumn(1).setPreferredWidth(120);
		tcol.getColumn(4).setPreferredWidth(120);
		tcol.getColumn(6).setPreferredWidth(45);
		TableColumn tucol = table.getColumnModel().getColumn(5);
		tucol.setCellRenderer(new Tableau(req, donnees));
		JScrollPane scroll = new JScrollPane(table);


		String[] elem = {"Aucun", "EAU", "ELECTRICITE", "TEMPERATURE", "AIRCOMPRIME"};
		JComboBox<String> liste2 = new JComboBox<String>(elem);
		type = (String) liste2.getSelectedItem();
		liste2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = (String) liste2.getSelectedItem();
			}
		});

		liste1 = new JComboBox<String>();
		liste1.addItem("Aucun");
		nbItems = 1;
		batiment = (String) liste1.getSelectedItem();
		liste1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				batiment = (String) liste1.getSelectedItem();
			}
		});

		JLabel lab1 = new JLabel("Choisissez un filtre :");
		lab1.setHorizontalAlignment(JLabel.CENTER);
		lab1.setVerticalAlignment(JLabel.BOTTOM);
		JLabel lab2 = new JLabel("Batiment");
		lab2.setHorizontalAlignment(JLabel.CENTER);
		JLabel lab3 = new JLabel("Type");
		lab3.setHorizontalAlignment(JLabel.CENTER);


		JPanel filtre = new JPanel(new GridLayout(2, 3));
		filtre.add(lab1);
		filtre.add(lab2);
		filtre.add(liste1);
		filtre.add(new JLabel());
		filtre.add(lab3);
		filtre.add(liste2);


		jolie.add(filtre,BorderLayout.NORTH);
		jolie.add(scroll,BorderLayout.CENTER);

	}

	public void refreshData(){
		List<String> nomCapteurs = req.getNomsCapteurs();
		for(ListIterator<String> iter = nomCapteurs.listIterator();iter.hasNext();) {
			String capt = iter.next();
			if (!req.isActif(capt))
				iter.remove();
		}
		nbCapteur = nomCapteurs.size();
		donnees = new Object[nbCapteur][7];
		table = new JTable(donnees, titre);
		for (int i = 0; i < nbCapteur; i++) {
			String capteur = nomCapteurs.get(i);
			donnees[i][0] = capteur;
			donnees[i][1] = req.getType(capteur);
			donnees[i][2] = req.getBatiment(capteur);
			donnees[i][3] = req.getEtage(capteur);
			donnees[i][4] = req.getLieu(capteur);
			donnees[i][5] = req.getLastVal(capteur);
			donnees[i][6] = TypeCapteurs.stringToUnite(req.getType(capteur));
		}

	}

	public int getNbCapteur() {
		return nbCapteur;
	}

	public void refreshBox() {
		TreeSet<String> s = new TreeSet<String>(req.getAllBatiments());
		if (s.size()+1 != liste1.getItemCount()) {
			Object select = liste1.getSelectedItem();
			liste1.removeAllItems();
			liste1.addItem("Aucun");
			for (String string : s) {
				liste1.addItem(string);
			}
			nbItems = liste1.getItemCount();
			liste1.setSelectedItem(select);
		}
	}

	public String getBatiment() {
		return batiment;
	}

	public String getType() {
		return type;
	}

}
