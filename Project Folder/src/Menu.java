
import java.util.Scanner;

public class Menu {
	
	public static void showMenu () {
		System.out.println("Wählen Sie den Menüpunkt anhand der Nummer aus.\n");
		System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
		System.out.println("|                                               |");
		System.out.println("|  1: Aktuelle Blutzuckerwerte eingeben         |");
		System.out.println("|  2: Neue Erinnerung erstellen oder löschen    |");
		System.out.println("|  3: Auswertungen ansehen                      |");
		System.out.println("|                                               |");
		System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
		System.out.println("|                                               |");
		System.out.println("|  0: Programm beenden                          |");
		System.out.println("|                                               |");
		System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+");
	}
	
	public static void main(String[] args) {
		
		boolean runProgram = true;
		
		System.out.println("Willkommen zu Diabetica, deinem Diabetis Assistenten!\n");
		
		Scanner eingabe = new Scanner(System.in);
		
		while (runProgram) {
			
			Menu.showMenu();
			
			System.out.println("Ihre Eingabe: ");
			int userInput = eingabe.nextInt();
			
			if (userInput == 1) {
				System.out.println("Methode für Blutzucker");
			}
			else if (userInput == 2) {
				System.out.println("Methode für Erinnerungen");
			}
			else if (userInput == 3) {
				System.out.println("Methode für Auswertungen");
			}
			else if (userInput == 0) {
				runProgram = false;
			}
			else {
				System.out.println("Ungültige Eingabe, bitte erneut auswählen.");
			}
				
		}
		System.out.println("Auf Wiedersehen!");
	}
}
