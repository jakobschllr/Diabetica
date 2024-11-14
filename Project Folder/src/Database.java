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
