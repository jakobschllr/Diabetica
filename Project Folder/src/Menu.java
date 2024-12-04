import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	
	private static final String HORIZ_BORDER = "+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+";
	private static final String SIDE_BORDERS = "|                                               |";
	private static final String BACK_TO_MAIN_MENU = "|  0: Zurück zum Hauptmenü                      |";
	
	/**
	 * Die private Klassenmethode 'textPrint' erhält einen String als Parameter und gibt die einzelnen Zeichen
	 * des String mit einer zeitlichen Verzögerung von 40 Millisekunden auf der Konsole aus. Dadurch wird eine 'Tipp-Animation' erzeugt.
	 * @param text ist der String dessen Zeichen mit Zeitverzögerung auf der Konsole ausgegeben werden sollen.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void textPrint (String text) throws InterruptedException {
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			TimeUnit.MILLISECONDS.sleep(40);
		}
		System.out.println("\n");
	}
	
	/**
	 * Die private Klassenmethode 'menuPrint' erhält einen String der Elemente des Menüs das in der Konsole angezeigt wird enthält und
	 * gibt die Zeichen aus denen der String besteht mit einer zeitlichen Verzögerung von 2 Millisekunden auf der Konsole aus.
	 * @param text ist der String dessen Zeichen mit Zeitverzögerung auf der Konsole ausgegeben werden sollen.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void menuPrint (String text) throws InterruptedException {
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			if (text.charAt(i) != ' ') 
				TimeUnit.MILLISECONDS.sleep(2);
		}
		System.out.print("\n");
	}
	
	/**
	 * Die private Klassenmethode 'outroPrint' wird ausgeführt wenn das Programm vom User beendet wird und gibt den Namen der
	 * App - 'Diabetica' - auf der Konsole aus.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void outroPrint () throws InterruptedException {
		
		String line1 = "*********     **          **          ********      *********     **********     **       *******          **";
		String line2 = "**********    **        ******        *********     *********     **********     **     *********        ******";
		String line3 = "**      **    **       **    **       **     **     **                **         **     **              **    **";
		String line4 = "**      **    **      **      **      **     **     **                **         **     **             **      **";
		String line5 = "**      **    **      **********      *********     *******           **         **     **             **********";
		String line6 = "**      **    **      **********      *********     *******           **         **     **             **********";
		String line7 = "**      **    **      **      **      **     **     **                **         **     **             **      **";
		String line8 = "**      **    **     **        **     **     **     **                **         **     **            **        **";
		String line9 = "**********    **     **        **     *********     *********         **         **     *********     **        **";
		String line10 ="*********     **     **        **     ********      *********         **         **       *******     **        **";

		
		String[] lines = {line1, line2, line3, line4, line5, line6, line7, line8, line9, line10};
		
		System.out.println("\n\n\n");
		
		String orangeColor = "\u001B[33m";
		
		for (String line: lines) {
			for (int c = 0; c < line.length(); c++) {
				System.out.print(orangeColor + line.charAt(c));
				TimeUnit.MILLISECONDS.sleep(2);
			}
			System.out.println();
		}
		
		
		Menu.textPrint("\nby Boby, Zaid, Bardia & Jakob");
	}
		
	/**
	 * Die private Klassenmethode 'menuMain' gibt das Hauptmenü auf der Konsole aus.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void mainMenu () throws InterruptedException {	
		System.out.println("\n");
		
		String menuName = "|                  Hauptmenü                    |";
		String option1 = "|  1: Aktuelle Blutzuckerwerte eingeben         |";
		String option2 = "|  2: Neue Erinnerung erstellen oder löschen    |";
		String option3 = "|  3: Auswertungen ansehen                      |";
		String exitOption = "|  0: Programm beenden                          |";
		
		Menu.menuPrint(HORIZ_BORDER);
		Menu.menuPrint(menuName);
		Menu.menuPrint(HORIZ_BORDER);
		Menu.menuPrint(SIDE_BORDERS);
		
		Menu.menuPrint(option1);
		Menu.menuPrint(option2);
		Menu.menuPrint(option3);
		
		Menu.menuPrint(SIDE_BORDERS);
		Menu.menuPrint(HORIZ_BORDER);
		Menu.menuPrint(SIDE_BORDERS);
		Menu.menuPrint(exitOption);
		Menu.menuPrint(SIDE_BORDERS);
		Menu.menuPrint(HORIZ_BORDER);
		
		System.out.println("\n");
	}
	
	/**
	 * Die private Klassenmethode 'erinnerungenAnsehen' erhält einen String Array der alle gespeicherten Erinnerungen enthält und diese 
	 * dann übersichtlich auf der Konsole ausgibt.
	 * @param erinnerungen String Array der alle gespeicherten Erinnerungen enthält
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void erinnerungenAnsehen(String[] erinnerungen)  throws InterruptedException {
		String menuName =    "|           Gespeicherten Erinnerungen          |";
		
		Menu.menuPrint(HORIZ_BORDER);
		Menu.menuPrint(menuName);
		Menu.menuPrint(HORIZ_BORDER);
		Menu.menuPrint(SIDE_BORDERS);
		
		for (int e = 0; e < erinnerungen.length; e++) {
			System.out.println("|                " + (e+1) + ":  " + erinnerungen[e] + "                      |");
		}
		
		Menu.menuPrint(SIDE_BORDERS);
		Menu.menuPrint(HORIZ_BORDER);
		System.out.println("\n");
		
	}
	
	/**
	 * Die private Klassenmethode 'erinnerungMenu' gibt das Menü für die Erinnerungsverwaltung des Programms auf der Konsole aus.
	 * @param eingabe Scanner Object aus dem Main Menu, damit User im Erinnerungs Menü Eingaben machen kann.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void erinnerungMenu(Scanner eingabe) throws InterruptedException {
		System.out.println("\n");
		
		String menuName = "|            Erinnerungen verwalten             |";
		String option1 = "|  1: Neue Erinnerung erstellen                 |";
		String option2 = "|  2: Bestehende Erinnerung löschen             |";
		String option3 =  "|  3: Alle Erinnerungen ansehen                 |";
		
		
		boolean erinnerungMenuRunning = true;
		
		while (erinnerungMenuRunning) {
			
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(menuName);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(option1);
			Menu.menuPrint(option2);
			Menu.menuPrint(option3);
			
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(BACK_TO_MAIN_MENU);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);
			
			Menu.textPrint("Ihre Eingabe");
			int input = eingabe.nextInt();
			
			if (input == 1) {
				Erinnerung.erstellen();
			}
			else if (input == 2) {
				Menu.erinnerungenAnsehen(Erinnerung.ansehen());
				Erinnerung.loeschen();
			}
			else if (input == 3) {
				Menu.erinnerungenAnsehen(Erinnerung.ansehen());
			}
			else if (input == 0) {
				erinnerungMenuRunning = false;
			}
			else
				Menu.textPrint("Ungültige Eingabe.");
			
		}
		
		System.out.println("\n");
	}
	
	/**
	 * Die private Klassenmethode 'blutzuckerMenu' gibt das Menü für die Eingabe von Erinnerungen ass der Konsole aus
	 * @param eingabe Scanner Object aus dem Main Menu, damit User im Blutzucker Menü Eingaben machen kann.
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void blutzuckerMenu(Scanner eingabe) throws InterruptedException {		
		System.out.println("\n");
		
		String menuName = "|                   Blutzucker                  |";
		String option1 =  "|  1: Aktuellen Blutzuckerwert eingeben         |";
		
		boolean blutzuckerMenuRunning = true;
		
		while (blutzuckerMenuRunning) {
			
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(menuName);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(option1);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(BACK_TO_MAIN_MENU);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);

			
			Menu.textPrint("Ihre Eingabe");
			int input = eingabe.nextInt();
			

			if (input == 0) {
				blutzuckerMenuRunning = false;
			}
			else if (input == 1) {
				String[] arg = new String[0];
				Blutzucker.main(arg);
			}
			else
				Menu.textPrint("Ungültige Eingabe.");
			}
		
	}
	
	/**
	 * Die private Klassenmethode 'auswertungMenu' gibt das Menü für die Auswertungen auf der Konsole aus und kommuniziert mit der
	 * Auswertung Klasse.
	 * @param eingabe Scanner Objekt aus Menu
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	private static void auswertungMenu(Scanner eingabe) throws InterruptedException {
		
		String menuName = "|                   Auswertung                  |";
		String option1 =  "|  1: Auswertung erhalten                       |";
		
			
			
		
		boolean runAuswertungMenu = true;
		
		while (runAuswertungMenu) {
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(menuName);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(option1);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(BACK_TO_MAIN_MENU);
			Menu.menuPrint(SIDE_BORDERS);
			Menu.menuPrint(HORIZ_BORDER);
			System.out.println("Wählen Sie eine Option.");
			int input = eingabe.nextInt();
			if (input == 1) {
				Auswertung.getAuswertung(eingabe);
			}
			else if (input == 0) {
				runAuswertungMenu = false;
			}
			else {
				System.out.println("\nUngültige Eingabe\n");
			}
			
			
		}
		
	}
	
	/**
	 * Öffentliche Klassenmethode 'main'  begrüßt den User und führt die while-Schleife aus, in der alle anderen Methoden des Programms
	 * aufgerufen werden. Immer wenn der Code in der While-Schleife von vorne beginnt wird geprüft, ob in den nächsten 30 Minuten eine
	 * Erinnerung zutreffen muss. Wenn dies so ist, erhält der User die Erinnerung.
	 * @param args Standard Parameter für main-Methode
	 * @throws InterruptedException notwendig damit die Zeitverzögerung mit java.util.concurrent.TimeUnit ohne Error funktioniert
	 */
	public static void main(String[] args) throws InterruptedException {
		
		boolean runProgram = true;
		
		Scanner eingabe = new Scanner(System.in);
		
		String greeting = "Willkommen zu Diabetica, deinem Diabetis Assistenten!";
		String expl = "\nWähle den Menüpunkt anhand der Nummer aus";
		
		Menu.textPrint(greeting);
		Menu.textPrint(expl);
		
		while (runProgram) {
			
			
			String redColor = "\u001B[31m";
			String resetColor = "\u001B[0m";
			
			
			String erinnerung = Erinnerung.checkErinnerung();
			System.out.println(redColor + erinnerung + resetColor);
			
			Menu.mainMenu();
			
			Menu.textPrint("Ihre Eingabe: ");
			int userInput = eingabe.nextInt();
			
			if (userInput == 1) {
				Menu.blutzuckerMenu(eingabe);
			}
			else if (userInput == 2) {
				Menu.erinnerungMenu(eingabe);
			}
			else if (userInput == 3) {
				Menu.auswertungMenu(eingabe);
			}
			else if (userInput == 0) {
				runProgram = false;
			}
			else {
				Menu.textPrint("Ungültige Eingabe, bitte erneut auswählen.");
			}
				
		}
		
		Menu.outroPrint();
	}
}
