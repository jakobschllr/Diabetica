import java.util.Scanner;
import java.time.*;

public class Erinnerung {

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
                    }
                    else
                        minute = eingabeMinute + "";

                    String eingabe = eingabeStunde + ":" + minute;
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
    }

    public static void loeschen(){
        Database.erinnerung();
        Scanner input = new Scanner(System.in);

        boolean istKorrekteEingabe = false;

        while(!istKorrekteEingabe) {
            System.out.println("Welche Erinnerung möchten Sie löschen?");
            int eingabe = input.nextInt();
            if ((Database.getAlarms().length >= eingabe) & (eingabe > 0)) {

                Database.deleteAlarm(eingabe-1);
                System.out.println("Erinnerung " + eingabe + " erfolgreich gelöscht.");
                istKorrekteEingabe = true;
            } else {
                System.out.println("Keine gültige Eingabe!!");
            }
        }


    }

    public static String[] ansehen(){
        Database.erinnerung();
        return Database.getAlarms();
    }

    public static String checkErinnerung() {
        LocalTime currentTime = LocalTime.now();
        String[] liste = ansehen();

        for (int i = 0; i < liste.length; i++) {
            LocalTime currentAlarm = LocalTime.parse(liste[i]);
            int vergleich = currentTime.compareTo(currentAlarm); // negative Zahl: currentTime < currentAlarm
            if (vergleich <= 0){
                Duration differenz = Duration.between(currentTime, currentAlarm);
                if (differenz.toMinutes() <= 30){
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