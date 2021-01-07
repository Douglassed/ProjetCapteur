package Affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import DAO.Requetes;

public class TempsReel extends JPanel{
	int nbCapteur = 3;
	Object[][] donnees;// = {{"Capteur1_03","ELECTRICITE","U2",2,"DevantSalle202",95.2d},{"Capteur1_04","AIR_COMPRIME","U2",2,"salle 201",18d}};
	private static final long serialVersionUID = 1L;
	
	public TempsReel() {
		String[] titre = {"Nom", "Type du fluide", "Batiment","Etage", "Pièce", "Valeur"};
		rafraichir();
		this.setLayout(new FlowLayout());
		JTable table = new JTable(donnees, titre);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		
		for(int i = 0; i < 6; i++) {
			TableColumn tcol = table.getColumnModel().getColumn(i);
			tcol.setCellRenderer(new Tableau());
		}
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(500,413));
		this.add(scroll);
	}
	
	public void rafraichir(){
		Requetes req = new Requetes();
		List<String> nomCapteurs = req.getNomsCapteurs();
		for(ListIterator<String> iter = nomCapteurs.listIterator();iter.hasNext();) {
			String capt = iter.next();
			if (!req.isActif(capt))
				iter.remove();
		}
		nbCapteur = nomCapteurs.size();
		donnees = new Object[nbCapteur][6];
		for (int i = 0; i < nbCapteur; i++) {
			String capteur = nomCapteurs.get(i);
			donnees[i][0] = capteur;
			donnees[i][1] = req.getType(capteur);
			donnees[i][2] = req.getBatiment(capteur);
			donnees[i][3] = req.getEtage(capteur);
			donnees[i][4] = req.getLieu(capteur);
			donnees[i][5] = req.getLastVal(capteur);
		}
	}
}
