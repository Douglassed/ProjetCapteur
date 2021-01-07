package Affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panneau {
	public Panneau(TypeCapteurs type, List<String> capteursChoisis, List<String> list) {
		JFrame frame = new JFrame("nouvelle fenetre !");
		TempsReel tempsReel = new TempsReel();
		Posteriori posteriori = new Posteriori(frame, type, capteursChoisis, list);
		JPanel gestion = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JButton seuil = new JButton("Modifier seuils");
		seuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seuil seuil = new Seuil(frame);
				seuil.getSeuilMax();
				seuil.getSeuilMin();
			}
		});
		gestion.add(seuil);

		frame.add(tempsReel, BorderLayout.LINE_START);
		frame.add(posteriori, BorderLayout.CENTER);
		frame.add(gestion, BorderLayout.SOUTH);
		frame.setSize(1348,513);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Timer chrono = new Timer(1000,  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempsReel.rafraichir();
				tempsReel.repaint();
			}
		});
		chrono.start();
	}
	
}
