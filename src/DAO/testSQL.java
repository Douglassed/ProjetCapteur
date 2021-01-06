package DAO;

public class testSQL {

	public static void main(String[] args) {
		Requetes rq=new Requetes();
		Entrees en=new Entrees();
		
		en.capteur("Capteur1", "EAU:U1:2:salle 401");
		en.capteur("Capteur2", "ELECTRICITE:U2:1:salle 6");
		en.valeur("Capteur1", 23);
		en.valeur("Capteur1", 12);
		en.valeur("Capteur1", 14);
		
		System.out.println("liste des capteurs : " + rq.getNomsCapteurs());
		System.out.println("liste capteurs de type EAU : " + rq.getNomsCapteursParType("EAU"));
		
		System.out.println("\nbatiment de Capteur1 : " + rq.getBatiment("Capteur1"));
		System.out.println("etage de Capteur1 : " + rq.getEtage("Capteur1"));
		System.out.println("lieu de Capteur1 : " + rq.getLieu("Capteur1"));
		System.out.println("seuil min de Capteur1 : " + rq.getSeuilMin("Capteur1"));
		System.out.println("seuil max de Capteur1 : " + rq.getSeuilMax("Capteur1"));
		System.out.println("derniere valeur de Capteur1 : " + rq.getLastVal("Capteur1"));
		
		System.out.println("\nchangement de seuils");
		en.seuils("Capteur1", 40, 12);
		System.out.println("seuil min de Capteur1 : " + rq.getSeuilMin("Capteur1"));
		System.out.println("seuil max de Capteur1 : " + rq.getSeuilMax("Capteur1"));
		
	}

}
