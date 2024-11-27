
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

/**
 * Database Programm um den Database mit CSV Dateien zu managen
 */
public class Database {

    // Immutable CSV Dateiname
    // Der Name der CSV Datei, wo die Blutzuckerwerte und die Zeit gespeichert werden
    private static String databaseFileName = "Blutzuckerwerte.csv"; 
    // Der Name der CSV Datei, wo die Erinnerungen gespeichert werden
    private static String erinnerungFileName = "Erinnerungen.csv"; 

    private static File databaseFile = new File(databaseFileName);
    private static File erinnerungFile = new File(erinnerungFileName);

    private List<LocalDate> DATUM = new ArrayList<>(); 
    private List<LocalTime> ZEIT = new ArrayList<>();
    private List<Double> WERTE = new ArrayList<>();

    /**
     * Konstruktor fuer die Datenbank.
     * prueft ob die CSV Datei schon existiert, und les und schreibbar ist.
     * wenn ja, dann nichts mehr wird gemacht,
     * wenn nein, dann wird eine erstellt und initialisiert.
     * Erstellt dann eine Instanz der Dantenbank und ladet die Daten von CSV
     */
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

    /**
     * Liefert ein LocalDate Array von allen Daten in der Datenbank
     * @return LocalDate[] Array von allen Daten in der Datenbank
     */
    public LocalDate[] getDates() {
        int size = this.DATUM.size();
        LocalDate[] dates = new LocalDate[size];
        for (int i = 0; i < size; i++) {
            dates[i] = this.DATUM.get(i);
        }
        return dates;
    }

    /**
     * Liefert ein LocalTime Array von allen Zeiten in der Datenbank
     * @return LocalTime[] Array von allen Zeiten in der Datenbank
     */
    public LocalTime[] getTime() {
        int size = this.ZEIT.size();
        LocalTime[] time = new LocalTime[size];
        for (int i = 0; i < size; i++) {
            time[i] = this.ZEIT.get(i);
        }
        return time;
    }

    /**
     * Liefert ein double Array von allen gespeicherten Blutzuckerwerten in der Datenbank
     * @return double[] Array von allen Blutzuckerwerten in der Datenbank
     */
    public double[] getValues() {
        int size = this.WERTE.size();
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = this.WERTE.get(i);
        }
        return values;
    }

    /**
     * Gibt alle Daten in der Datenbank zurueck.
     * Jeder Wert und die damit verbundenen Datum und Uhrzeit ist in einem String Array gespeichert. 
     * Beispiel: ["2021-06-01", "12:00", "120.0"]
     * Und jeder dieser Arrays ist in einem 2D Array gespeichert.
     * Beispiel: [["2021-06-01", "12:00", "120.0"], 
     *            ["2021-06-02", "12:00", "120.0"]]
     * 
     * @return String[][] 2D Array mit allen Daten von der Datenbank 
     */
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

    /**
     * Fuegt einen neuen Blutzuckerwert in die Datenbank ein
     * Das Datum und die Uhrzeit werden auch gleichzeitigzur Datenbank hinzugefuegt
     * @param wert der Wert der eingefuegt werden soll
     * @return void
     */
    public void add(double wert) {
        
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        this.DATUM.add(dateTime.toLocalDate());
        this.ZEIT.add(dateTime.toLocalTime());
        this.WERTE.add(wert);
    }

    /**
     * Offentliche Methode:  Speichert die Datenbank von der Instanz in die CSV Datei
     * Fehler werden abgefangen und ausgegeben wenn die Datenbank nicht gespeichert werden kann
     * @return void
     */
    public void save() {
        try {
            this.SaveDatabase();
        } catch (IOException e) {
            System.out.println("Fehler Datenbank nicht gespeichert");
            e.printStackTrace();
        }
    }

    /**
     * Private Methode: Ladet die Daten von der CSV Datei in die Datenbank Instanz 
     * Ruf die Methode loadData auf
     * Gibt "File Not Found" aus wenn die Datei nicht gefunden wird
     * Diese Methode wird nur von dem Konstruktor aufgerufen wenn eine neue Datenbank Instanz erstellt wird
     * @return void
     */
    private void load() {
        try {
            this.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    /**
     * Private Methode: Ladet die Daten von der CSV Datei in die Datenbank Instanz
     * Die Daten werden in ihre jeweiligen Datentyen konvertiert
     * und werden dann in die Listen DATUM, ZEIT und WERTE gespeichert
     * @return void
     * @throws FileNotFoundException wenn die Datei nicht gefunden wird
     */
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

    /**
     * Private Methode: Schreibt die Daten von der Datenbank Instanz in die CSV Datei
     * Alle Daten in der Datenbank Instanz werden in die CSV Datei geschrieben 
     * @return void
     * @throws IOException wenn die Datei nicht geschrieben werden kann
     */
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

    /**
     * Private Methode: Initialisiert die CSV Datei
     * Die CSV Datei wird mit einem Header initialisiert
     * @return void
     * @throws IOException wenn die Datei nicht geschrieben werden kann
     */
    private void initialiseFile() throws IOException{
        CSVWriter initialiser = new CSVWriter(new FileWriter(databaseFile));
        String[] header = {"  Datum   ", "Zeit ", "Blutzucker"};
        initialiser.writeNext(header);
        initialiser.close();

    }

    /**
     * Private Methode: Erstellt eine Datei
     * @param file die Datei die erstellt werden soll
     * @return boolean true wenn die Datei erstellt wurde, false wenn ein Fehler aufgetreten ist oder die Datei schon existiert
     * 
     */
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

    /**
     * Private Methode: Ueberprueft ob die Datei lesbar und schreibbar ist
     * @param file die Datei die ueberprueft werden soll
     * @return boolean true wenn die Datei lesbar und schreibbar ist, false wenn nicht oder wenn die Datei nicht existiert
     * 
     */
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

    /**
     * Oeffentliche Methode: ruft verifyFile und createFile auf um die Datei zu erstellen, wenn sie nicht existiert
     */
    public static void erinnerung() {
        if(!verifyFile(erinnerungFile)) {
            if(!createFile(erinnerungFile)) {
                System.out.println("Fehler er");
            }
        }
    }

    /**
     * Oeffentliche Methode Setzt eine Erinnerung
     * Ruft die private Methode setAlarm auf
     * @param time String Zeit im Format "HH:MM"
     */
    public static void set(String time) {
        try {
            setAlarm(time);
        } catch (IOException e) {
            System.out.println("IO Error");
            e.printStackTrace();
        }
    }

    /**
     * Private Methode: Setzt eine neue Erinnerung
     * Liest alle Errinerungen aus der CSV Datei und speichert es in einer Liste
     * Fuegt die neue Erinnerung hinzu und schreibt die Liste in die CSV Datei
     * @param time String Zeit im Format "HH:MM"
     * @throws IOException wenn die Datei nicht geschrieben werden kann
     * 
     */
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

    /**
     * Oeffentliche Methode: Gibt alle Erinnerungen zurueck
     * Liest alle Errinerungen aus der CSV Datei und speichert es in einer Liste
     * @return String[] Array von allen Erinnerungen in der CSV Datei
     */
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

    /**
     * Oeffentliche Methode: Loescht eine Erinnerung
     * Liest alle Errinerungen aus der CSV Datei und speichert es in einer Liste
     * loescht die Erinnerung und schreibt die Liste in die CSV Datei
     * @param index index der Erinnerung die geloescht werden soll
     * @return void
     */
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