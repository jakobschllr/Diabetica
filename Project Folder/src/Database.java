/*
 * Database Programm um den Database mit CSV Dateien zu managen
 */
import java.time.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;


public class Database {

    // Immutable CSV Dateiname
    private static final String databaseFileName = "Blutzuckerwerte.csv";
    private static final String erinnerungFileName = "Erinnerungen.csv";
    
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

    // Overloading von velauf
    public static double[] verlauf() {
        int dates = 7;
        double[] verlauf = new double[0];
        return verlauf;
    }

    // Update our CSV File
    private static void updateCSV() {

    }

    //

    // Verifies if the file already exists if no creates a neew file 
    private static boolean  verifyFile(File file) {

        String fileName = file.getName();
        
        try {
            if(!file.isFile()){
                return false;
            }
            else if(file.canRead() && file.canWrite()){
                return true;
            }
            else return false;
        } catch (SecurityException e) {
            System.out.println("File is not accessible.\nDelete File or grant access");
            return false;
        }
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

        File databaseFile = new File(databaseFileName);
        File erinnerungFile = new File(erinnerungFileName);
        System.out.println(verifyFile(databaseFile));
        System.out.println(verifyFile(erinnerungFile));
        // System.out.println(LocalTime.now());
        // System.out.println(LocalDateTime.now());
    }
}
