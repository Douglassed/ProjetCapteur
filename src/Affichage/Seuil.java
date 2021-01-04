package Affichage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Seuil {
	int seuilMax = 10;
	int seuilMin = 0;
	public Seuil(JFrame frame/*,Capteur capteur*/) {
		System.out.println(JOptionPane.showInputDialog(frame, "Nouveu seuil max ("+seuilMax+")? (Type) et unité", "choix du seuil", JOptionPane.QUESTION_MESSAGE,null, null, seuilMax));
		System.out.println(JOptionPane.showInputDialog(frame, "Nouveu seuil min ? (Type) et unité", "choix du seuil", JOptionPane.QUESTION_MESSAGE,null, null, seuilMin));
	}
	public int getSeuilMax() {
		return seuilMax;
	}
	public int getSeuilMin() {
		return seuilMin;
	}
}
