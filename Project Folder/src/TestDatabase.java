import java.util.Arrays;
public class TestDatabase {
    public static void main(String[] args) {

        // Testing database
        Database db = new Database();
        db.add(50);

        String[][] data = db.getAll();
        for (String[] line : data) {
            System.out.println(Arrays.toString(line));
        }
    
        System.out.println(Arrays.toString(db.getDates()));
        System.out.println(Arrays.toString(db.getTime()));
        System.out.println(Arrays.toString(db.getValues()));
        db.save();

        // Testing erinnerungen
        Database.erinnerung();
        Database.set("12:00");
        Database.deleteAlarm(0);
        System.out.println(Arrays.toString(Database.getAlarms()));
        
    }
}
