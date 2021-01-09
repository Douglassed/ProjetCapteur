package Affichage;

public enum TypeCapteurs {
	EAU, ELECTRICITE, TEMPERATURE, AIRCOMPRIME;

	public String unite() {
		switch (this) {
		case EAU:
			return "m�";
		case ELECTRICITE:
			return "kWh";
		case TEMPERATURE:
			return "�C";
		case AIRCOMPRIME:
			return "m�/h";
		default:
			return "t'as merd�";
		}
	}
	
	public static String stringToUnite(String str) {
		switch (str) {
		case "EAU":
			return "m�";
		case "ELECTRICITE":
			return "kWh";
		case "TEMPERATURE":
			return "�C";
		case "AIRCOMPRIME":
			return "m�/h";
		default:
			return "NULLLL";
		}
	}
}
