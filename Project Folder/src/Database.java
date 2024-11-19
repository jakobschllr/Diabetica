/*
 * Database Programm um den Database mit CSV Dateien zu managen
 */
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;


public class Database {

    // Immutable CSV Dateiname
    private static String databaseFileName = "Blutzuckerwerte.csv";
    private static String erinnerungFileName = "Erinnerungen.csv";

    private static File databaseFile = new File(databaseFileName);
    private static File erinnerungFile = new File(erinnerungFileName);

    private List<LocalDate> DATUM = new ArrayList<>(); 
    private List<LocalTime> ZEIT = new ArrayList<>();
    private List<Double> WERTE = new ArrayList<>();

    // Konstruktor prueft ob die Datei existiert und lesbar ist
    public Database() {
        
        if(!verifyFile(databaseFile)) {
            if(createFile(databaseFile)) {
                try {
                    this.initialiseFile();
                } catch (IOException e) {
                    System.out.println("IO Error...");
                    e.printStackTrace();
                }
            }
        }

        this.load();
    }

    // Returns an array of dates
    public LocalDate[] getDates() {
        int size = this.DATUM.size();
        LocalDate[] dates = new LocalDate[size];
        for (int i = 0; i < size; i++) {
            dates[i] = this.DATUM.get(i);
        }
        return dates;
    }

    // Returns an array of time
    public LocalTime[] getTime() {
        int size = this.ZEIT.size();
        LocalTime[] time = new LocalTime[size];
        for (int i = 0; i < size; i++) {
            time[i] = this.ZEIT.get(i);
        }
        return time;
    }

    public double[] getValues() {
        int size = this.WERTE.size();
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = this.WERTE.get(i);
        }
        return values;
    }

    public String[][] getAll() {
        int size = this.WERTE.size();
        String[][] data = new String[size][3];
        for (int i = 0; i < size; i++) {
            data[i][0] = this.DATUM.get(i).toString();
            data[i][1] = this.ZEIT.get(i).toString();
            data[i][2] = Double.toString(this.WERTE.get(i));
        }
        return data;
    }

    // Update our CSV File
    public void add(double wert) {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        this.DATUM.add(dateTime.toLocalDate());
        this.ZEIT.add(dateTime.toLocalTime());
        this.WERTE.add(wert);
    }

    // Speichert die Datenbank
    public void save() {
        try {
            this.SaveDatabase();
        } catch (IOException e) {
            System.out.println("Fehler Datenbank nicht gespeichert");
            e.printStackTrace();
        }
    }

    // Ladet die Datenbank
    private void load() {
        try {
            this.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    // Ladet Daten von CSV zu Datenbank
    private void loadData() throws FileNotFoundException {
        
        try {
            CSVReader reader = new CSVReader(new FileReader(databaseFileName));
            List<String[]> dataRaw = reader.readAll();

            for (int i = 1; i < dataRaw.size(); i++) {
                String[] zeile = dataRaw.get(i);
                DATUM.add(i-1, LocalDate.parse(zeile[0]));
                ZEIT.add(i-1, LocalTime.parse(zeile[1]));
                WERTE.add(i-1, Double.parseDouble(zeile[2]));
            }

            reader.close();
        } catch ( Exception e) {
            System.out.println("Error...");
            e.printStackTrace();
        }
    }

    // Save Database to file
    private void SaveDatabase() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(databaseFile));
        String[] zeile = {"  Datum   ", "Zeit ", "Blutzucker"};
        writer.writeNext(zeile);

        for (int i = 0; i < this.DATUM.size(); i++) {
            zeile[0] = this.DATUM.get(i).toString();
            zeile[1] = this.ZEIT.get(i).toString();
            zeile[2] = Double.toString(this.WERTE.get(i));
            writer.writeNext(zeile);
        }
        writer.close();
    }

    // Initialisiert ne neue Datei
    private void initialiseFile() throws IOException{
        CSVWriter initialiser = new CSVWriter(new FileWriter(databaseFile));
        String[] header = {"  Datum   ", "Zeit ", "Blutzucker"};
        initialiser.writeNext(header);
        initialiser.close();

    }

    // Erstellt Datei
    private static boolean createFile(File file) {
        try {
            if(file.createNewFile())
                return true;
            else {
                System.out.println("Datei existiert schon");
                return false;}
        } catch (IOException e) {
            System.out.println("Fehler !!!");
            e.printStackTrace();
            return false;
        }
    }

    // Pruft ob die Datei existiert,
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
            System.out.println("Datei nicht Zugreifbar.\nDatei loeschen oder Zugang erlauben");
            e.printStackTrace();
            return false;
        }
    }

    public static void erinnerung() {
        if(!verifyFile(erinnerungFile)) {
            if(!createFile(erinnerungFile)) {
                System.out.println("Fehler er");
            }
        }
    }

    // Setmalarm
    public static void set(String time) {
        try {
            setAlarm(time);
        } catch (IOException e) {
            System.out.println("IO Error");
            e.printStackTrace();
        }
    }

    // Sets an alarm
    private static void setAlarm(String time) throws IOException {
        try{
            LocalTime.parse(time);
            CSVReader reader = new CSVReader(new FileReader(erinnerungFile));
            List<String[]> alarms = reader.readAll();
            alarms.add(new String[] {time});
            CSVWriter setter = new CSVWriter(new FileWriter(erinnerungFile));
            setter.writeAll(alarms);
            reader.close();
            setter.close();
        } catch (DateTimeParseException e) {
            System.out.println("Cannot Change Time");
            e.printStackTrace();
        } catch (CsvException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    // Returns an array of all alarms
    public static String[] getAlarms() {
        String[] alarmStrings = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(erinnerungFile));
            List<String[]> alarms = reader.readAll();
            alarmStrings = new String[alarms.size()];
            for (int i = 0; i < alarms.size(); i++) {
                alarmStrings[i] = alarms.get(i)[0];
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return alarmStrings;
    }

    // Deletes Alarm at index i
    public static void deleteAlarm(int index) {
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(erinnerungFile));
            List<String[]> alarms = reader.readAll();
            alarms.remove(index);
            CSVWriter setter = new CSVWriter(new FileWriter(erinnerungFile));
            setter.writeAll(alarms);
            reader.close();
            setter.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        
    }
}