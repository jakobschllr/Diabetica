import java.util.Arrays;
public class TestDatabase {
    public static void main(String[] args) {

        Database db = new Database();
        db.add(70);
        String[][] data = db.getAll();
        for (String[] line : data) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println(Arrays.toString(db.getDates()));
        System.out.println(Arrays.toString(db.getTime()));
        System.out.println(Arrays.toString(db.getValues()));
        db.save();

        // System.out.println(db.dates.isEmpty());

        
        
    }
}
