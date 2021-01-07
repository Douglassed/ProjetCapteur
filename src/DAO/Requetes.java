package DAO;
import java.sql.*;
import java.util.*;

public class Requetes {
	Connection connection;
	
	public Requetes() {
		try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://127.0.0.1:3306/projetcapteurs?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	 public String getBatiment(String nom){
		 String batiment="";
			try {
				Statement stmt = connection.createStatement();
				ResultSet rst = stmt.executeQuery("SELECT b.nom FROM Batiments AS b, Etages AS e, Capteurs AS c WHERE c.nom='" + nom +"' AND b.id_batiment=e.id_batiment AND c.id_etage=e.id_etage");
							
				if (rst.next()) {
					batiment=rst.getString("nom");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return batiment;
	 }
	 
	 public String getEtage(String nom){
		 String batiment="";
			try {
				Statement stmt = connection.createStatement();
				ResultSet rst = stmt.executeQuery("SELECT e.numero FROM Etages AS e, Capteurs AS c WHERE c.nom='" + nom +"' AND c.id_etage=e.id_etage");
							
				if (rst.next()) {
					batiment=rst.getString("numero");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return batiment;
	 }
	 
	 public String getLieu(String nom){
		 String lieu="";
			try {
				Statement stmt = connection.createStatement();
				ResultSet rst = stmt.executeQuery("SELECT lieu FROM Capteurs WHERE nom='" + nom +"'");
							
				if (rst.next()) {
					lieu=rst.getString("lieu");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return lieu;
	 }
	
	public String getType(String nom) {
		String type="";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT type FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				type=rst.getString("type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public float getSeuilMin(String nom) {
		float seuilMin=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT seuil_min FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				seuilMin=rst.getFloat("seuil_min");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seuilMin;
	}
	
	public float getSeuilMax(String nom) {
		float seuilMax=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT seuil_max FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				seuilMax=rst.getFloat("seuil_max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seuilMax;
	}	
	
	public ArrayList<String> getNomsCapteurs(){
		ArrayList<String> capteurs=new ArrayList<String>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT nom FROM Capteurs");

			while (rst.next()) {
				capteurs.add(rst.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return capteurs;
	}

	public ArrayList<String> getNomsCapteursParType(String type){
		ArrayList<String> capteurs=new ArrayList<String>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT nom FROM Capteurs WHERE type='" + type +"'");

			while (rst.next()) {
				capteurs.add(rst.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return capteurs;
	}
	
	public float getLastVal(String nom) {
		float valeur=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT valeurs.valeur FROM valeurs, capteurs WHERE capteurs.id_capteur=valeurs.id_capteur AND capteurs.nom='" + nom + "' ORDER BY valeurs.id_valeur DESC");
					
			if (rst.next()) {
				valeur=rst.getFloat("valeur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valeur;
	}
	
	public Map<Long, Float> getAllValOfDay(String nom, String jour) {
		Map<Long, Float> assoc=new HashMap<Long, Float>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT valeurs.valeur, UNIX_TIMESTAMP(valeurs.date_val)*1000 FROM valeurs, capteurs WHERE capteurs.id_capteur=valeurs.id_capteur AND capteurs.nom='" + nom + "' AND DATE(valeurs.date_val)='"+ jour +"' ORDER BY valeurs.date_val ASC");
					
			while (rst.next()) {
				assoc.put(rst.getLong("UNIX_TIMESTAMP(valeurs.date_val)*1000"), rst.getFloat("valeur"));
				System.out.println("une valeur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assoc;
	}
	
	public boolean isActif(String nom) {
		boolean actif=false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT actif FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				actif=rst.getBoolean("actif");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actif;
	}
	
}

