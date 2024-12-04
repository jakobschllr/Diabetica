import java.util.Scanner;
import java.time.*;

/**
 * Die Klasse Erinnerung ermöglicht das Erstellen, Anzeigen, Löschen und Überprüfen von Erinnerungen.
 * Sie verwendet eine Datenbank-Klasse, um Erinnerungen zu speichern und zu verwalten.
 */
public class Erinnerung {

    /**
     * Erstellt eine neue Erinnerung, indem der Benutzer die Uhrzeit (Stunde und Minute) eingibt.
     * Die Eingabe wird auf Korrektheit geprüft, bevor die Erinnerung in der Datenbank gespeichert wird.
     */
    public static void erstellen() {
        Scanner input = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Uhrzeit an.");

        boolean korrekteEingabe = false;

        while (!korrekteEingabe) {
            System.out.println("Die Stunden:");
            int eingabeStunde = input.nextInt();
            if ((eingabeStunde < 24) && (eingabeStunde >= 0)) {
                System.out.println("Die Minuten:");
                int eingabeMinute = input.nextInt();
                if ((eingabeMinute < 60) && (eingabeMinute >= 0)) {
                    String minute;
                    if (eingabeMinute < 10) {
                        minute = "0" + eingabeMinute;
                    } else {
                        minute = eingabeMinute + "";
                    }

                    String eingabe = eingabeStunde + ":" + minute;
                    Database.erinnerung(); // Initialisiert die Datenbank
                    Database.set(eingabe); // Speichert die Erinnerung
                    System.out.println("Erinnerung für " + eingabe + " erfolgreich gespeichert.");

                    korrekteEingabe = true;
                } else {
                    System.out.println("Eingabe nicht korrekt.");
                }
            } else {
                System.out.println("Eingabe nicht korrekt.");
            }
        }
        input.close();
    }

    /**
     * Löscht eine bestehende Erinnerung, indem der Benutzer eine Auswahl trifft.
     * Die Eingabe wird auf Gültigkeit geprüft, bevor die Erinnerung aus der Datenbank entfernt wird.
     */
    public static void loeschen() {
        Database.erinnerung(); // Initialisiert die Datenbank
        Scanner input = new Scanner(System.in);

        boolean istKorrekteEingabe = false;

        while (!istKorrekteEingabe) {
            System.out.println("Welche Erinnerung möchten Sie löschen?");
            int eingabe = input.nextInt();
            if ((Database.getAlarms().length > eingabe) & (eingabe > 0)) {
                Database.deleteAlarm(eingabe - 1); // Löscht die Erinnerung an der angegebenen Position
                System.out.println("Erinnerung " + eingabe + " erfolgreich gelöscht.");
                istKorrekteEingabe = true;
            } else {
                System.out.println("Keine gültige Eingabe!!");
            }
        }
    }

    /**
     * Zeigt alle gespeicherten Erinnerungen an.
     * @return Ein Array von Strings, die die gespeicherten Erinnerungen repräsentieren.
     */
    public static String[] ansehen() {
        Database.erinnerung(); // Initialisiert die Datenbank
        return Database.getAlarms(); // Gibt alle Erinnerungen zurück
    }

    /**
     * Überprüft, ob eine Erinnerung innerhalb der nächsten 30 Minuten fällig ist.
     * @return Eine Nachricht, falls eine Erinnerung innerhalb von 30 Minuten fällig ist, ansonsten ein leerer String.
     */
    public static String checkErinnerung() {
        LocalTime currentTime = LocalTime.now(); // Aktuelle Uhrzeit
        String[] liste = ansehen(); // Liste aller Erinnerungen abrufen
        for (int i = 0; i < liste.length; i++) {
            System.out.println(liste[i]);
        }

        for (int i = 0; i < liste.length; i++) {
            LocalTime currentAlarm = LocalTime.parse(liste[i]);
            int vergleich = currentTime.compareTo(currentAlarm); // Vergleich der aktuellen Zeit mit der Erinnerung
            if (vergleich <= 0) {
                Duration differenz = Duration.between(currentTime, currentAlarm);
                if (differenz.toMinutes() <= 30) {
                    return "Erinnerung!!! \nLangzeitinsulin um " + currentAlarm + " in " + differenz.toMinutes() + " Minuten einnehmen.";
                }
            }
        }
        return "";
    }

   /* public static void main(String[] args) {



        String[] results = Erinnerung.ansehen();
        System.out.print(java.util.Arrays.toString(results));

        Erinnerung.loeschen();


        String[] results2 = Erinnerung.ansehen();
        System.out.print(java.util.Arrays.toString(results2));

    }*/
}