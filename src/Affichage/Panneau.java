package Affichage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

public class Panneau {
	int nbCapteur = 3;
	public Panneau(String string) {
		JFrame frame = new JFrame("nouvelle fenetre !");
		JPanel tempsReel = new JPanel();
		JPanel posteriori = new JPanel();
		JPanel gestion = new JPanel();

				
//		for (int i = 0; i <5 ; i++) {
//			JLabel boutton = new JLabel("theoreme");
//			boutton.setBorder(new LineBorder(Color.BLACK));
//			boutton.setPreferredSize(new Dimension(100,100));
//			tempsReel.add(boutton);
//			boutton.setFont(new Font(boutton.getText(), Font.PLAIN, boutton.getFont().getSize()+3));
//		}
		String[] titre = {"nom", "type du fluide", "batiment","étage", "pièce", "valeur"};
		Object[][] donnees = {{"Le théoreme",2,1,1,1,1}};
		tempsReel.setLayout(new FlowLayout());
		tempsReel.add(new JScrollPane(new JTable(donnees, titre)));
		tempsReel.setLayout(new GridLayout(nbCapteur, 5));
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
		frame.add(tempsReel, BorderLayout.LINE_START);
		frame.add(posteriori, BorderLayout.CENTER);
		frame.add(gestion, BorderLayout.SOUTH);
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Timer chrono = new Timer(1000,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donnees[0][2] = (int)donnees[0][2]+1;
				tempsReel.repaint();
			}
		});
		chrono.start();
	}
}
