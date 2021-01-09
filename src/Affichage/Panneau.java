package Affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Panneau {
	public Panneau(TypeCapteurs type, List<String> capteursChoisis, List<String> list) {
		JFrame frame = new JFrame("nouvelle fenetre !");
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
		Timer chrono = new Timer(2000,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempsReel.refreshData();
				tempsReel.repaint();
			}
		});
		chrono.start();
	}
	
}
