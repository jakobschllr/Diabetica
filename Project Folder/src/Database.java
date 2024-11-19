/*
 * Database Programm um den Database mit CSV Dateien zu managen
 */
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class Database {

    // Immutable CSV Dateiname
    private static String databaseFileName = "Blutzuckerwerte.csv";
    private static String erinnerungFileName = "Erinnerungen.csv";

    private static File databaseFile = new File(databaseFileName);
    private static File erinnerungFile = new File(erinnerungFileName);

    private List<LocalDate> dates = new ArrayList<>();
    private List<List<Double>> data = new ArrayList<>();


    public Database() {
        
        if(!verifyFile(databaseFile)) {
            if(createFile(databaseFile)) {
                try {
                    this.initialiseFile();
                    this.initialiseDatabase();
                } catch (IOException e) {
                    System.out.println("IO Error...");
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("Already exists db");
        }

        if(!verifyFile(erinnerungFile)) {
            if(!createFile(erinnerungFile)) {
                System.out.println("Error er");
            }
        }
        else{
            System.out.println("Already exists er");
        }
    }
    
    //Die offentliche-Klasse nimmt einen Blutzuckerwert und speichert es in einer csv Datei
    public static void speichern(double blutZuckerWert) {
        LocalDate todayDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        System.out.println(todayDate + "" + timeNow);
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
        System.out.println(dates);
        return verlauf;
    }

    // Update our CSV File
    private static void updateCSV() {

    }

    // Loads data from the CSV file to the database
    private void loadData() throws FileNotFoundException {
        
        try {
            CSVReader reader = new CSVReader(new FileReader(databaseFileName));
            List<String[]> dataRaw = reader.readAll();
            String[] header = dataRaw.get(0);
            System.out.println(header[header.length - 1]);
            reader.close();
        } catch ( Exception e) {
            System.out.println("Error...");
            e.printStackTrace();
        }
    }

    private void initialiseDatabase() {

        for (int i = 0; i < 24; i++) {
            data.add(i, new ArrayList<>());
            data.get(i).addFirst(null);
        }
    }

    private void initialiseFile() throws IOException{
        CSVWriter initialiser = new CSVWriter(new FileWriter(databaseFile));
        String[] Dates = {"  :  ", LocalDate.now().toString()};
        initialiser.writeNext(Dates);

        LocalTime counter = LocalTime.of(0, 0);
        for (int i = 0; i < 24; i++) {
            String[] line = new String[] {counter.toString()};
            counter = counter.plusHours(1);
            initialiser.writeNext(line);
        }
        initialiser.close();

    }

    // Creates a file
    private boolean createFile(File file) {
        try {
            if(file.createNewFile())
                return true;
            else {
                System.out.println("File already exists");
                return false;}
        } catch (IOException e) {
            System.out.println("An error occured!!!");
            e.printStackTrace();
            return false;
        }
    }

    // Verifies if the file already exists if no creates a new file 
    private static boolean  verifyFile(File file) {
        
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
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        // LocalDate date = LocalDate.now();
        // LocalTime time = LocalTime.now();
        // LocalTime hour = LocalTime.of(time.getHour(), 0);

        // time = LocalTime.parse("15:30");
        // date = LocalDate.parse("2024-11-13");

        // System.out.println(time);
        // System.out.println(date);

        // System.out.println(verifyFile(erinnerungFile));

        Database db = new Database();
        try {
            db.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("Error File NOt Found");
            e.printStackTrace();
        }

        // System.out.println(db.dates.isEmpty());

        
        
    }
}
