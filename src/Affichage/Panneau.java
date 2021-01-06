package Affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.TableColumn;

public class Panneau {
	int nbCapteur = 3;
	Object[][] donnees;// = {{"Capteur1_03","ELECTRICITE","U2",2,"DevantSalle202",95.2d},{"Capteur1_04","AIR_COMPRIME","U2",2,"salle 201",18d}};
	public Panneau(String string) {
		JFrame frame = new JFrame("nouvelle fenetre !");
		JPanel tempsReel = new JPanel();
		JPanel posteriori = new JPanel();
		JPanel gestion = new JPanel();

		String[] titre = {"Nom", "Type du fluide", "Batiment","Etage", "Pi�ce", "Valeur"};
		rafraichir();
		tempsReel.setLayout(new FlowLayout());
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
		tempsReel.add(scroll);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		posteriori.add(new JLabel(string));
		JButton graphe = new JButton("nouveaux graphes");
		graphe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChoixGraph();
			}
		});
		JButton seuil = new JButton("Modifier seuils");
		seuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seuil seuil = new Seuil(frame);
				seuil.getSeuilMax();
				seuil.getSeuilMin();
			}
		});
		posteriori.add(graphe);
		gestion.add(seuil);
		System.out.println(tempsReel.getPreferredSize());

		frame.add(tempsReel, BorderLayout.LINE_START);
		frame.add(posteriori, BorderLayout.CENTER);
		frame.add(gestion, BorderLayout.SOUTH);
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Timer chrono = new Timer(1000,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(donnees[1][5]);
//				donnees[0][5] = (double)(donnees[0][5])+2;
				rafraichir();
				tempsReel.repaint();
			}
		});
		chrono.start();
	}

	private Object[][] rafraichir(){
		nbCapteur = 3;//modif
		donnees = new Object[nbCapteur][6];
		for (int i = 0; i < nbCapteur; i++) {
			donnees[i][0] = "Capteur1_03" + i;
			donnees[i][1] = TypeCapteurs.ELECTRICITE;
			donnees[i][2] = "U2";
			donnees[i][3] = 1;
			donnees[i][4] = "Salle 412 arriere de la salle";
			donnees[i][5] = 45d;
		}
		return null;
	}
}
