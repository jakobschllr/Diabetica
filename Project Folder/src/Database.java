/*
 * Database Programm um den Database mit CSV Dateien zu managen
 */
import java.time.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
// import com.opencsv.CSVReader;


public class Database {
    
    //Die offentliche-Klasse nimmt einen Blutzuckerwert und speichert es in einer csv Datei
    public static void speichern(double blutZuckerWert) {
        LocalDate todayDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
    }

    // Liefert den Verlauf von Blutzuckerwerten
    public static double[] verlauf(int dates) {
        double[] verlauf = new double[0];
        return verlauf;
    }

    
    public static double[] verlauf() {
        int dates = 7;
        double[] verlauf = new double[0];
        return verlauf;
    }

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalTime hour = LocalTime.of(time.getHour(), 0);

        System.out.println(hour);
        System.out.println(date);

        time = LocalTime.parse("15:30");
        date = LocalDate.parse("2024-11-13");

        System.out.println(time);
        System.out.println(date);
        // System.out.println(LocalTime.now());
        // System.out.println(LocalDateTime.now());
    }
}
