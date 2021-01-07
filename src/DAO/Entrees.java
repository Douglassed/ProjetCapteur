package DAO;
import java.sql.*;

public class Entrees {
	Connection connection;
	long id_capteur;
	long id_valeur;
	long id_batiment;
	long id_etage;
	
	public Entrees() {
		try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://127.0.0.1:3306/projetcapteurs?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		id_capteur=0;
		id_valeur=0;
		id_batiment=0;
		id_etage=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT Capteurs.id_capteur FROM Capteurs");
			while (rst.next()) {
				id_capteur++;
			}
			
			rst = stmt.executeQuery("SELECT valeurs.id_valeur FROM Valeurs");
			while (rst.next()) {
				id_valeur++;
			}
			
			rst = stmt.executeQuery("SELECT Capteurs.id_capteur FROM Capteurs");
			while (rst.next()) {
				id_batiment++;
			}
			
			rst = stmt.executeQuery("SELECT Capteurs.id_capteur FROM Capteurs");
			while (rst.next()) {
				id_capteur++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void capteur(String nom, String entree) {
		String[] colonnes=entree.split(":");
		long id_batCapteur, id_etaCapteur;
		float seuil_minCapteur=0, seuil_maxCapteur=0;
		
		switch(colonnes[0]) {
			case "EAU": seuil_maxCapteur=10;
						break;
			case "ELECTRICITE": seuil_minCapteur=10;
								seuil_maxCapteur=500;
								break;
			case "TEMPERATURE": seuil_minCapteur=17;
								seuil_maxCapteur=22;
								break;
			case "AIRCOMPRIME": seuil_maxCapteur=5;
								break;
			default: break;
		}
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT id_batiment FROM Batiments WHERE Batiments.nom='"+colonnes[1]+"'");
					
			if (rst.next()) {
				id_batCapteur=rst.getLong("id_batiment");
			}
			else {//ajout du batiment si il existe pas
				stmt.executeUpdate("INSERT INTO Batiments VALUES ('"+id_batiment+"','"+colonnes[1]+"')");
				id_batCapteur=id_batiment;
				id_batiment++;
			}
			
			rst = stmt.executeQuery("SELECT Etages.id_etage FROM Etages, Batiments WHERE Etages.id_batiment="+id_batCapteur+" AND etages.numero="+colonnes[2]);
			
			if (rst.next()) {
				id_etaCapteur=rst.getLong("id_etage");
			}
			else{ //ajout de l'etage dans le batiment si il existe pas
				stmt.executeUpdate("INSERT INTO Etages VALUES ('"+id_etage+"','"+id_batCapteur+"','"+colonnes[2]+"')");
				id_etaCapteur=id_etage;
				id_etage++;
			}
			//ajout du capteur avec les id qu'on vient de récuperer
			
			rst = stmt.executeQuery("SELECT Capteurs.id_capteur FROM Capteurs WHERE nom='"+nom+"'");
			
			if (!rst.next()) {
				stmt.executeUpdate("INSERT INTO Capteurs VALUES ('"+id_capteur+"','"+id_etaCapteur+"','"+nom+"','"+seuil_maxCapteur+"','"+seuil_minCapteur+"','"+colonnes[3]+"','"+colonnes[0]+"')");
				id_capteur++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void valeur(String nom, String valeur) {
		long id_cap=-1;
		
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rst = stmt.executeQuery("SELECT id_capteur FROM Capteurs WHERE nom='"+nom+"'");
			
			if (rst.next()) {
				id_cap=rst.getLong("id_capteur");
			}
			
			stmt.executeUpdate("INSERT INTO Valeurs VALUES ('"+id_valeur+"','"+id_cap+"',NOW(),'"+ valeur+"')");
			
			id_valeur++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void seuils(String nom, float seuilMax, float seuilMin) {
		
		try {
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE Capteurs SET seuil_max='"+seuilMax+"', seuil_min='"+seuilMin+"' WHERE nom='"+nom+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnectCapteur(String nom) {
		try {
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE Capteurs SET actif='"+0+"' WHERE nom='"+nom+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void emptyBase() {
		try {
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate("DELETE FROM Valeurs");
			stmt.executeUpdate("DELETE FROM Capteurs");
			stmt.executeUpdate("DELETE FROM Etages");
			stmt.executeUpdate("DELETE FROM Batiments");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
