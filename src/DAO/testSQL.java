package DAO;

public class testSQL {

	public static void main(String[] args) {
		Requetes rq=new Requetes();
		
		System.out.println("lieu de EA1G : " + rq.getLieu("EA1G"));
		System.out.println("seuil min de EL3L : " + rq.getSeuilMin("EL3L"));
		System.out.println("seuil max de EA1G : " + rq.getSeuilMax("EA1G"));
		System.out.println("liste capteurs de type EAU : " + rq.getNomsCapteursParType("EAU"));
		System.out.println("liste des capteurs : " + rq.getNomsCapteurs());
		
		System.out.println("derniere valeur de EA1G : " + rq.getLastVal("EA1G"));
	}

}
