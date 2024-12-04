import java.time.*;
import java.util.Scanner;

public class Auswertung {
	
	/**
	 * Die öffentliche Klassenmethode 'getAuswertung' lässt den Nutzer eine Auswertung über die Blutzuckerwerte in einem beliebigem
	 * Zeitpunkt erstellen. Sie gibt dem Nutzer außerdem den höchsten und niedrigsten Blutzuckerwert in dem Zeitraum.
	 * @param eingabe Scanner Objekt aus Menu Klasse, um Eingaben machen zu können.
	 */
	public static void getAuswertung (Scanner eingabe) {
		Database database = new Database();
		double[] values = database.getValues();
		LocalTime[] times = database.getTime();
		LocalDate[] dates = database.getDates();
		
		LocalDate today = LocalDate.now();
		
		String hoechsterWert = "";
		String niedrigsteWert = "";
		
		double maxBlutzucker = 0.0;
		double minBlutzucker = 5000.0;
		
		
		System.out.println("\nFür wie viele Tage zurück in die Vergangenheit\nmöchten Sie die Blutzuckerwerte anschauen?");
		int anzahlTage = eingabe.nextInt();
		if (anzahlTage >= 1) {
			System.out.println("\n");
			
			System.out.printf("%-14s %-10s %-20s%n", "Datum", "Uhrzeit", "Blutzuckerwerte");
			
			for (int i = anzahlTage -1; i >= 0; i--) {
				LocalDate todayMinusSeven = today.minusDays(i);
				for (int j = 0; j < values.length; j++) {
					if (dates[j].equals(todayMinusSeven)) {
						if (values[j] >= maxBlutzucker) {
							maxBlutzucker = values[j];
							hoechsterWert = "" + dates[j] + " um " + times[j];
						}
						if (values[j] <= minBlutzucker) {
							minBlutzucker = values[j];
							niedrigsteWert = "" + dates[j] + " um " + times[j];
						}
						System.out.printf("%-14s %-10s %-10.1f%n", dates[j], times[j], values[j]);
					}
				}
			}
			
			System.out.println("\n");
			System.out.println("Höchster Wert am " + hoechsterWert + " mit " + maxBlutzucker);
			System.out.println("Niedrigster Wert am " + niedrigsteWert + " mit " + minBlutzucker);
			
			System.out.println("\n");
		}
		else {
			System.out.println("\nUngültige Eingabe.\n");
		}

		
	}
}