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
		input.close();
	}
	
	public static String ruckmeldung(int blutzuckerwert ) {
		String ruckmeldung;
		if (blutzuckerwert > 80 && blutzuckerwert < 140) {
			ruckmeldung = "Blutzuckerspiegel befindet sich im typischen Bereich. Keine Handlungsbedarf.";
		}else if (blutzuckerwert < 79) {
			ruckmeldung = "Sie haben Unterzucker. ";
		}else{
			ruckmeldung = "Sie haben Hochrzucker. ";
		
	}return  ruckmeldung;
	
}
	public static String empfehlung(int blutzuckerwert) {
		int y;
		String empfehlung;
		if (blutzuckerwert > 140 ) {
			y = blutzuckerwert - 120;
			int insulieEinheit = y / 30;
			empfehlung = "Sie sollen " + insulieEinheit + " Einheiten Insulin spritzen. ";
		}else if (blutzuckerwert < 79){
			y= 120 - blutzuckerwert ;
			int Kohlenhydrate = y/15;
			empfehlung = "Sie sollen " + Kohlenhydrate + " g Kohlenhydrate essen. ";
		}else {
			empfehlung = " ";
		}
		return empfehlung;
	}
}