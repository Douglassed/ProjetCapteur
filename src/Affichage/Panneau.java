package Affichage;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class Panneau {
	public Panneau(TypeCapteurs type, List<String> capteursChoisis, List<String> list) {
		JFrame frame = new JFrame("Interface Graphique");
		TempsReel tempsReel = new TempsReel();
		Posteriori posteriori = new Posteriori(frame, type, capteursChoisis, list);
		GestionSeuil gestion = new GestionSeuil(frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(tempsReel, BorderLayout.LINE_START);
		frame.add(posteriori, BorderLayout.CENTER);
		frame.add(gestion, BorderLayout.SOUTH);
		frame.setSize(frame.getPreferredSize());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}
