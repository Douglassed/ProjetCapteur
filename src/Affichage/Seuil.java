package Affichage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;

import DAO.Requetes;

public class Seuil {
	float seuilMax;
	float seuilMin;
	public Seuil(JFrame frame, float seuilMn, float seuilMx, String capteur, Requetes req) {
		seuilMin = seuilMn;
		seuilMax = seuilMx;
		Object min = JOptionPane.showInputDialog(frame, 
				"Nouveu seuil minimum ? ("+TypeCapteurs.stringToUnite(req.getType(capteur))+")", 
				"choix du seuil min : "+req.getType(capteur), JOptionPane.QUESTION_MESSAGE,null, null, seuilMin);
		if (min != null) {
			Object max = JOptionPane.showInputDialog(frame, 
					"Nouveu seuil maximum ? ("+TypeCapteurs.stringToUnite(req.getType(capteur))+")", 
					"choix du seuil max : "+req.getType(capteur), JOptionPane.QUESTION_MESSAGE,null, null, seuilMax);
			if (max != null) {
				try {
					float mini = Float.parseFloat(min.toString());
					float maxi = Float.parseFloat(max.toString());
					if (mini > maxi) {
						JOptionPane.showMessageDialog(frame, "Le seuil mini strictement supérieur au maxi. \nVeuillez recommmencer","Erreur", JOptionPane.ERROR_MESSAGE);
					}else {
						seuilMax = maxi;
						seuilMin = mini;
					}
				} catch ( NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, "Mauvaise entrée. Veuillez recommmencer","Erreur", JOptionPane.ERROR_MESSAGE);
				}	
			}
		}
	}
	public float getSeuilMax() {
		return seuilMax;
	}
	public float getSeuilMin() {
		return seuilMin;
	}
}
