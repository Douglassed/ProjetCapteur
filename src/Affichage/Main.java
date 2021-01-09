package Affichage;

import javax.swing.SwingUtilities;

import Serveur.Serveur;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FenetreAccueil();
			}
		});
	}
}
