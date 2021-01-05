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
	
	public String getLieu(String nom) {
		String lieu="";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT b.nom, e.numero, c.lieu FROM Batiments AS b, Etages AS e, Capteurs AS c WHERE c.nom='" + nom +"' AND b.id_batiment=e.id_batiment AND c.id_etage=e.id_etage");
						
			if (rst.next()) {
				lieu+= "batiment " +rst.getString("nom");
				lieu+=" etage "+rst.getString("numero");
				lieu+=" "+rst.getString("lieu");
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
	
	public double getSeuilMin(String nom) {
		double seuilMin=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT seuil_min FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				seuilMin=rst.getDouble("seuil_min");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seuilMin;
	}
	
	public double getSeuilMax(String nom) {
		double seuilMax=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT seuil_max FROM Capteurs WHERE nom='" + nom + "'");

			if (rst.next()) {
				seuilMax=rst.getDouble("seuil_max");
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

			if (rst.next()) {
				capteurs.add(rst.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return capteurs;
	}
	
	public double getLastVal(String nom) {
		double valeur=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT valeurs.valeur FROM valeurs, capteurs WHERE capteurs.id_capteur=valeurs.id_capteur AND capteurs.nom='" + nom + "' ORDER BY valeurs.id_valeur DESC");
					
			if (rst.next()) {
				valeur=rst.getDouble("valeur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valeur;
	}
	
}

