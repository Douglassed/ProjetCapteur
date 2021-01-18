package Affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;

import DAO.Requetes;


public class ModeleDonnees extends AbstractTableModel{

	private static final long serialVersionUID = 5807321557577123009L;
	private String[] titres;
	private Requetes req;
	private int nbCapteur;
	private ArrayList<String> nomCapteurs;
	TempsReel tempsReel;


	public ModeleDonnees(Object[][] donnees, String[] titres, Requetes req, TempsReel tempsReel) {
		this.tempsReel = tempsReel;
		nomCapteurs = new ArrayList<>();
		if (donnees.length > 0) {
			nbCapteur = donnees[0].length;
			for (Object str : donnees[0]) {
				nomCapteurs.add(str.toString());
			}
		}
		this.titres = titres;
		this.req = req; 
		Timer chrono = new Timer(2000,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireTableDataChanged();
				tempsReel.refreshBox();
			}
		});
		chrono.start();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		nomCapteurs = req.getNomsCapteurs();

		for(ListIterator<String> iter = nomCapteurs.listIterator();iter.hasNext();) {
			String capt = iter.next();
			if (!req.isActif(capt))
				iter.remove();
			else if (!isFilter(capt))
				iter.remove();
		}
		if (nbCapteur != nomCapteurs.size()) {
			this.fireTableDataChanged();
		}
		nbCapteur = nomCapteurs.size();
		return nbCapteur;
	}

	public Object getValueAt(final int indiceLigne, final int indiceColonne) {
		String capteur = nomCapteurs.get(indiceLigne);
		switch (indiceColonne) {
		case 0: {
			return capteur;
		}
		case 1: {
			return req.getType(capteur);
		}
		case 2: {
			return req.getBatiment(capteur);
		}
		case 3: {
			return req.getEtage(capteur);
		}
		case 4: {
			return req.getLieu(capteur);
		}
		case 5: {
			return req.getLastVal(capteur);
		}
		case 6: {
			return TypeCapteurs.stringToUnite(req.getType(capteur));
		}
		default: {
			return null;
		}
		}
	}

	@Override
	public String getColumnName(final int indiceColonne) {
		return titres[indiceColonne];
	}

	@Override
	public Class<? extends Object> getColumnClass(final int indiceColonne) {
		return this.getValueAt(0, indiceColonne).getClass();
	}

	@Override
	public boolean isCellEditable(final int indiceLigne, final int indiceColonne) {
		return false;
	}

	private boolean isFilter(String capteur) {
		boolean valid = true;
		String batiment = tempsReel.getBatiment();
		String type = tempsReel.getType();
		if (batiment != "Aucun") {
			if (!req.getBatiment(capteur).equals(batiment))
				valid = false;
		}
		if (type != "Aucun") {
			if (!req.getType(capteur).equals(type.toString()))
				valid = false;
		}
		return valid;
	}
}
