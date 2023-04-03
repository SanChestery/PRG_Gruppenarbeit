/*
 * Name: Klasse ClientApp
 * Version: 0.1 
 * Datum: 03.04.2023
 * 
 * Beschreibung: 
 * Verwendete Klassen: 
 * 
 * Programmierer: Michael Lüthi, Christian Kellenberg, Luca Felix, Yann Santschi
 * Projekt der HSLU PRG FS23
 */

import java.util.Scanner;

import ch.hslu.prg.leds.proxy.LedColor;
import ch.hslu.prg.leds.proxy.LedService; 

public class ClientApp {

    public static void main(String[] args) {
        
        // Erstellung neuer Instanz der Klasse LedService
        LedService service = new LedService();

        // Call ledsOnOff
        ledsOnOff(service);

        // Call switchEvenOdd
        switchEvenOdd(service);
    }

    
    /*
    * Aufgabe 1, Teilaufgaben 1.1 & 1.2 
    * Methoden Name: ledsOnOff
    * Variablen:
    *   int addAnzahlLEDs -> Eingabe wie viele LED hinzugefügt werden sollen
    *   int i, j, k -> Zählvariablen
    *   int ausgewaehlteFarbe -> Variable zur Wahl der Farbe 
    * Programmierer: Yann Santschi
    */

    private static void ledsOnOff(LedService service) {
        
        // Variabel deklaration
        int addAnzahlLEDs; 
        String[] colors = {"Rot", "Grün", "Blau", "Gelb", "Zufällig"};
        int ausgewaehlteFarbe; 
       

        // Scanner definition
        Scanner sc = new Scanner(System.in);

        // 1. Einlesen von addAnzahlLEDs
        System.out.print("Bitte geben Sie die Anzahl der LEDs ein, welche neu im Steckboard eingesteckt wurden: ");
        addAnzahlLEDs = sc.nextInt();

        // Auswahl Farbe
        for(int i = 0; i < 5; i++) {
            System.out.println(i + ": " + colors[i]); 
        }
        System.out.print("Bitte wählen sie eine der oberen Farben anhand der Nummer: ");
        ausgewaehlteFarbe = sc.nextInt();

        // 2. LEDs mit entsprechender Farbe hinzufügen
        switch(ausgewaehlteFarbe) {
            case 0: 
                service.addLeds(addAnzahlLEDs, LedColor.RED); 
                break; 
            
            case 1: 
                service.addLeds(addAnzahlLEDs, LedColor.GREEN); 
                break; 

            case 2: 
                service.addLeds(addAnzahlLEDs, LedColor.BLUE); 
                break; 

            case 3: 
                service.addLeds(addAnzahlLEDs, LedColor.YELLOW); 
                break; 

            case 4: 
                service.addLeds(addAnzahlLEDs, LedColor.RANDOM); 
                break; 
            
            default:
                System.out.println("Your Answer was invalid, Color Red was chosen by default.");
                service.addLeds(addAnzahlLEDs, LedColor.RED);
        }

        // 3. Methode für zwei Sekunden anhalten
        service.stopExecutionFor(2000);

        // 8. 1 + 3x Wiederholungen der Schritte 4 - 7 
        for (int k = 0; k <= 4; k++) {
            // 4. Einschaltung der LEDs von rechts nach links 
            for (int i = addAnzahlLEDs; i >= 0; i--) {
                service.turnLedOn(i);
                service.setDelayInMillis(300);
            }

            // 5. Anhalten der Ausführung der Methode für 250 milisekunden
            service.stopExecutionFor(250);

            // 6. Ausschaltung aller LEDs von Links nach rechts
            for (int j = 0; j <= addAnzahlLEDs; j++) {
                service.turnLedOff(j);
                service.setDelayInMillis(300);
            }

            // 7. Anhalten der Ausführung der Methode für 250 milisekunden
            service.stopExecutionFor(250);

        }

        // 9. Anhalten der Methode für 2 Sekunden (= 2000 Milisekunden)
        service.stopExecutionFor(2000);

        // 10. Zurücksetzen der Anzeige
        service.reset();

        // Scanner schliessen
        sc.close();
    }

    /*
    * Aufgabe 2: 
    * 
    * Methoden Name:   switchEvenOdd
    * Variablen: 
    * 
    * Programmierer: Yann Santschi
    */

    private static void switchEvenOdd(LedService service) {
        
        // Variabel deklaration
    }

}
