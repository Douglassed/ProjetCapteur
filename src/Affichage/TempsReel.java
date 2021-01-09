package Affichage;

import java.awt.FlowLayout;
import java.util.List;
import java.util.ListIterator;

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

	private static final long serialVersionUID = 1L;

	public TempsReel() {
		refreshData();
		table = new JTable(new ModeleDonnees(donnees,titre,req));
		this.setLayout(new FlowLayout());
		TableColumnModel tcol = table.getColumnModel();
		tcol.getColumn(0).setPreferredWidth(90);
		tcol.getColumn(1).setPreferredWidth(120);
		tcol.getColumn(4).setPreferredWidth(120);
		tcol.getColumn(6).setPreferredWidth(45);
		TableColumn tucol = table.getColumnModel().getColumn(5);
		tucol.setCellRenderer(new Tableau(req, donnees));
		JScrollPane scroll = new JScrollPane(table);
		this.add(scroll);

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
}
