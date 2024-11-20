import java.util.Scanner;
import java.time.*;

public class Erinnerung {
    public static void showMenu () {
        System.out.println("Wählen Sie den Menüpunkt anhand der Nummer aus.\n");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        System.out.println("|                                               |");
        System.out.println("|  1: Neue Erinnerung erstellen                 |");
        System.out.println("|  2: Bestehende Erinnerung löchen              |");
        System.out.println("|  3: Erinnerung ansehen                        |");
        System.out.println("|                                               |");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
        System.out.println("|                                               |");
        System.out.println("|  0: Zum Hauptmenu                             |");
        System.out.println("|                                               |");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
    }
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
                    String eingabe = eingabeStunde + ":" + eingabeMinute;
                    Database.erinnerung();
                    Database.set(eingabe);
                    System.out.println("Erinnerung für " + eingabe + " erfolgreich gespeichert.");

                    korrekteEingabe = true;
                }
                else {
                    System.out.println("Eingabe nicht korrekt.");
                }
            }
            else {
                System.out.println("Eingabe nicht korrekt.");
            }
        }
        input.close();
    }

    public static void loeschen(){
      Scanner input = new Scanner(System.in);

      boolean istKorrekteEingabe = false;

    while(!istKorrekteEingabe) {
        System.out.println("Welche Erinnerung möchten Sie löschen?");
        int eingabe = input.nextInt();
        if ((Database.getAlarms().length > eingabe) & (eingabe > 0)) {
            Database.erinnerung();
            Database.deleteAlarm(eingabe-1);
            System.out.println("Erinnerung " + eingabe + " erfolgreich gelöscht.");
            istKorrekteEingabe = true;
        } else {
            System.out.println("Keine gültige Eingabe!!");
        }
    }


    }

    public static String[] ansehen(){
        return Database.getAlarms();
    }

    public static String checkErinnerung() {
        LocalTime currentTime = LocalTime.now();
        String[] liste = ansehen();

        for (int i = 0; i < liste.length; i++) {
            LocalTime currentAlarm = LocalTime.parse(liste[0]);
            System.out.println(currentAlarm);
        }
        return "";
    }

    public static void main(String[] args) {

        String result = checkErinnerung();

        }
    }