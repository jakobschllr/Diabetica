import java.util.Scanner;

public class Blutzucker{
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Geben Sie ihre aktuelle Blutzucker: ");
		int blutzuckerwert = input.nextInt();
		String ruckmeldung = ruckmeldung(blutzuckerwert);
		String empfehlung = empfehlung(blutzuckerwert);
		System.out.println(ruckmeldung + "\n" + empfehlung);
		Database db = new Database();
		db.add(blutzuckerwert);
		db.save();
		
	}
	/**
	 * Überprüft den übergebenen Blutzuckerwert und gibt eine passende Rückmeldung.
	 * 
	 * Diese Funktion analysiert den Blutzuckerwert und ordnet ihn in drei Kategorien ein:
	 * - Typischer Bereich: Wenn der Wert zwischen 81 und 139 liegt (inklusive).
	 * - Unterzucker (Hypoglykämie): Wenn der Wert kleiner als 80 ist.
	 * - Hochzucker (Hyperglykämie): Wenn der Wert 140 oder höher ist.
	 *
	 * Abhängig von der Kategorie wird eine spezifische Nachricht zurückgegeben:
	 * - "Blutzuckerspiegel befindet sich im typischen Bereich. Keine Handlungsbedarf." für typische Werte.
	 * - "Sie haben Unterzucker." für niedrige Werte.
	 * - "Sie haben Hochzucker." für hohe Werte.
	 *
	 * @param blutzuckerwert der gemessene Blutzuckerwert (als Ganzzahl)
	 * @return eine Textnachricht mit der Bewertung des Blutzuckerwertes
	 */
	public static String ruckmeldung(int blutzuckerwert ) {
		String ruckmeldung;
		if (blutzuckerwert > 80 && blutzuckerwert < 140) {
			ruckmeldung = "\nBlutzuckerspiegel befindet sich im typischen Bereich. Keine Handlungsbedarf.";
		}else if (blutzuckerwert < 79) {
			ruckmeldung = "\nSie haben Unterzucker. ";
		}else{
			ruckmeldung = "\nSie haben Hochzucker. ";
		
	}return  ruckmeldung;
	
}
	/**
	 * Gibt eine Empfehlung basierend auf dem Blutzuckerwert.
	 *
	 * - Über 140: Berechnet die Insulineinheiten ((Blutzuckerwert - 120) / 30) und empfiehlt diese zu spritzen.
	 * - Unter 79: Berechnet die benötigten Kohlenhydrate ((120 - Blutzuckerwert) / 15) und empfiehlt diese zu essen.
	 * - 79 bis 140: Keine Empfehlung, weil der Blutzucker im gesunden Bereich ist.
	 *
	 * @param blutzuckerwert der gemessene Blutzuckerwert
	 * @return eine Nachricht mit der entsprechenden Empfehlung
	 */
	public static String empfehlung(int blutzuckerwert) {
		int y;
		String empfehlung;
		if (blutzuckerwert > 140 ) {
			y = blutzuckerwert - 120;
			int insulieEinheit = y / 30;
			empfehlung = "Sie sollten " + insulieEinheit + " Einheiten Insulin spritzen.\n";
		}else if (blutzuckerwert < 79){
			y= 120 - blutzuckerwert ;
			int Kohlenhydrate = y/15;
			empfehlung = "Sie sollten " + Kohlenhydrate + " g Kohlenhydrate essen.\n";
		}else {
			empfehlung = " ";
		}
		return empfehlung;
	}
}