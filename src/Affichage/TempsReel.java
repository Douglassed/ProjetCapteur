package Affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

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
		nbCapteur = 3;//REQUQTE SQL
		donnees = new Object[nbCapteur][6];
		for (int i = 0; i < nbCapteur; i++) {//REGQUETE SQL
			donnees[i][0] = "Capteur1_03" + i;
			donnees[i][1] = TypeCapteurs.ELECTRICITE;
			donnees[i][2] = "U2";
			donnees[i][3] = 1;
			donnees[i][4] = "Salle 412 arriere de la salle";
			donnees[i][5] = 45f;
		}
		donnees[0][5] = -10f;
	}
}
