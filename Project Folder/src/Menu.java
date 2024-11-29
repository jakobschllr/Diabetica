import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	
	public static void textPrint (String text) throws InterruptedException {
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			TimeUnit.MILLISECONDS.sleep(40);
		}
		System.out.println("\n");
	}
	
	public static void menuPrint (String text) throws InterruptedException {
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			if (text.charAt(i) != ' ') 
				TimeUnit.MILLISECONDS.sleep(2);
		}
		System.out.print("\n");
	}
	
	public static void outroPrint () throws InterruptedException {
		
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
		
		for (String line: lines) {
			for (int c = 0; c < line.length(); c++) {
				System.out.print(line.charAt(c));
				TimeUnit.MILLISECONDS.sleep(2);
			}
			System.out.println();
		}
		
		
		Menu.textPrint("\nby Boby, Zaid, Bardia & Jakob");
	}
	
	
	public static void mainMenu () throws InterruptedException {
		
		System.out.println("\n");
		
		String upperBorder = "+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+";
		String sideBorders = "|                                               |";
		
		
		String menuName = "|                  Hauptmenü                    |";
		String option1 = "|  1: Aktuelle Blutzuckerwerte eingeben         |";
		String option2 = "|  2: Neue Erinnerung erstellen oder löschen    |";
		String option3 = "|  3: Auswertungen ansehen                      |";
		String exitOption = "|  0: Programm beenden                          |";
		
		Menu.menuPrint(upperBorder);
		Menu.menuPrint(menuName);
		Menu.menuPrint(upperBorder);
		Menu.menuPrint(sideBorders);
		
		Menu.menuPrint(option1);
		Menu.menuPrint(option2);
		Menu.menuPrint(option3);
		
		Menu.menuPrint(sideBorders);
		Menu.menuPrint(upperBorder);
		Menu.menuPrint(sideBorders);
		Menu.menuPrint(exitOption);
		Menu.menuPrint(sideBorders);
		Menu.menuPrint(upperBorder);
		
		System.out.println("\n");
	}
	
	public static void erinnerungenAnsehen(String[] erinnerungen)  throws InterruptedException {
		String upperBorder = "+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+";
		String sideBorders = "|                                               |";
		String menuName =    "|           Gespeicherten Erinnerungen          |";
		
		Menu.menuPrint(upperBorder);
		Menu.menuPrint(menuName);
		Menu.menuPrint(upperBorder);
		Menu.menuPrint(sideBorders);
		
		for (int e = 0; e < erinnerungen.length; e++) {
			System.out.println("|                " + (e+1) + ":  " + erinnerungen[e] + "                      |");
		}
		
		Menu.menuPrint(sideBorders);
		Menu.menuPrint(upperBorder);
		System.out.println("\n");
		
	}
	
	
	public static void erinnerungMenu(Scanner eingabe) throws InterruptedException {
		System.out.println("\n");
		String upperBorder = "+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+";
		String sideBorders = "|                                               |";
		
		String menuName = "|            Erinnerungen verwalten             |";
		String option1 = "|  1: Neue Erinnerung erstellen                 |";
		String option2 = "|  2: Bestehende Erinnerung löschen             |";
		String option3 =  "|  3: Alle Erinnerungen ansehen                 |";
		String exitOption = "|  0: Zurück zum Hauptmenü                      |";
		
		
		boolean erinnerungMenuRunning = true;
		
		while (erinnerungMenuRunning) {
			
			Menu.menuPrint(upperBorder);
			Menu.menuPrint(menuName);
			Menu.menuPrint(upperBorder);
			Menu.menuPrint(sideBorders);
			Menu.menuPrint(option1);
			Menu.menuPrint(option2);
			Menu.menuPrint(option3);
			
			Menu.menuPrint(sideBorders);
			Menu.menuPrint(upperBorder);
			Menu.menuPrint(sideBorders);
			Menu.menuPrint(exitOption);
			Menu.menuPrint(sideBorders);
			Menu.menuPrint(upperBorder);
			
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
	
	public static void main(String[] args) throws InterruptedException {
		
		boolean runProgram = true;
		
		Scanner eingabe = new Scanner(System.in);
		
		String greeting = "Willkommen zu Diabetica, deinem Diabetis Assistenten!";
		String expl = "\nWähle den Menüpunkt anhand der Nummer aus";
		
		Menu.textPrint(greeting);
		Menu.textPrint(expl);
		
		while (runProgram) {
			
			// checken, ob gerade eine Erinnerung zutreffen muss, wenn ja dann den User erinnern
			System.out.println(Erinnerung.checkErinnerung());
			
			Menu.mainMenu();
			
			Menu.textPrint("Ihre Eingabe: ");
			int userInput = eingabe.nextInt();
			
			if (userInput == 1) {
				System.out.println("Methode für Blutzucker");
			}
			else if (userInput == 2) {
				Menu.erinnerungMenu(eingabe);
			}
			else if (userInput == 3) {
				System.out.println("Methode für Auswertungen");
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
