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

import ch.hslu.prg.leds.proxy.LedColor;
import ch.hslu.prg.leds.proxy.LedService;

import java.util.Random;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {
        
        // Erstellung neuer Instanz der Klasse LedService
        LedService service = new LedService();

        // Aufgabe 1: Call ledsOnOff
        ledsOnOff(service);

        // Aufgabe 2: Call switchEvenOdd
        switchEvenOdd(service);

        // Aufgabe 3: Call switchRandom
        switchRandom(service);
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
                service.setDelayInMillis(500);
            }

            // 5. Anhalten der Ausführung der Methode für 250 milisekunden
            service.stopExecutionFor(250);

            // 6. Ausschaltung aller LEDs von Links nach rechts
            for (int j = 0; j <= addAnzahlLEDs; j++) {
                service.turnLedOff(j);
                service.setDelayInMillis(500);
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
    * Aufgabe 2: Teilaufgaben 2.1 & 2.2 
    * 
    * Methoden Name: switchEvenOdd
    * Variablen:
    *    int addAnzahlLEDs -> anzahl einzufügender LEDs
    *    boolean isVielfachesVonAcht -> true/false für Feststellung ob addAnzahlLEDs vielfaches von 8 ist.
    *    int i, j, k, l -> Zählvariabel
    * 
    * Programmierer: Yann Santschi
    */

    private static void switchEvenOdd(LedService service) {
        
        // Variabel deklaration
        int addAnzahlLEDs;
        boolean isVielfachesVonAcht = false; 

        // Scanner definition
        Scanner sc = new Scanner(System.in);

        // 1. Einlesen von addAnzahlLEDs
        System.out.print("Bitte geben Sie die Anzahl der LEDs ein, welche neu im Steckboard eingesteckt wurden (vielfaches von 8): ");
        do {
            addAnzahlLEDs = sc.nextInt();

            if (addAnzahlLEDs % 8 == 0) {
                isVielfachesVonAcht = true; 
                break; 
            }

            System.out.print(addAnzahlLEDs + " ist nicht durch 8 teilbar und entsprechend Invalid. Bitte geben Sie eine neue Zahl ein: ");

        } while (isVielfachesVonAcht != true);

        // 2. Hinzufügen der LEDs in blau & 2 Sekunden warten
        service.addLeds(addAnzahlLEDs, LedColor.BLUE);
        service.stopExecutionFor(2000);

        for (int i = 0; i < 5; i++) {
    
            // 3. Anschalten aller geraden LEDs
            for (int j = 0; j <= addAnzahlLEDs; j++) {
                if (j == 0 || j % 2 == 0) {
                    service.turnLedOn(j);
                    service.setDelayInMillis(500);
                }
            }

            // 4. Anhalten der Ausführung für 1 Sekunde
            service.stopExecutionFor(1000);

            // 5. Umschaltung der LEDs von ein zu aus und aus zu ein
            for (int k = 0; k <= addAnzahlLEDs; k++) {
                if (k == 0 || k % 2 == 0) {
                    service.turnLedOff(k);
                    service.setDelayInMillis(500);
                } else {
                    service.turnLedOn(k);
                    service.setDelayInMillis(500);
                }
            }

            // 6. Anhalten der Ausführung für 1 Sekunde
            service.stopExecutionFor(1000);

            // 7. Ausschalten aller LEDs und anhalten für 1 Sekunde
            for (int l = 0; l <= addAnzahlLEDs; l++) {
                service.turnLedOff(l);
                service.setDelayInMillis(500);
            }

            service.stopExecutionFor(1000);
        }

        // 9. Zurücksetzen der Anzeige
        service.reset();

        // Scanner schliessen
        sc.close();
    }

    /*
    * Aufgabe 3: Teilaufgaben 3.1 & 3.2 
    * 
    * Methoden Name: switchRandom
    * Variablen:
    *    int addAnzahlLEDs -> anzahl einzufügender LEDs
    *    boolean isVielfachesVonSechzehn -> true/false für Feststellung ob addAnzahlLEDs vielfaches von 16 ist.
    *    int randNum -> Random Nummervariable zum Check ob das LED bereits an ist
    *    int i, j, k, l -> Zählvariabel
    * 
    * Programmierer: Yann Santschi
    */

    private static void switchRandom(LedService service) {

        // Variabel deklaration
        int addAnzahlLEDs;
        boolean isVielfachesVonSechzehn = false; 
        int randNum; 

        // Scanner & Random definition
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();

        // 1. Einlesen von addAnzahlLEDs
        System.out.print("Bitte geben Sie die Anzahl der LEDs ein, welche neu im Steckboard eingesteckt wurden (vielfaches von 16): ");
        do {
            addAnzahlLEDs = sc.nextInt();

            if (addAnzahlLEDs % 16 == 0) {
                isVielfachesVonSechzehn = true; 
                break; 
            }

            System.out.print(addAnzahlLEDs + " ist nicht durch 16 teilbar und entsprechend Invalid. Bitte geben Sie eine neue Zahl ein: ");

        } while (isVielfachesVonSechzehn != true);

        // 2. Hinzufügen der LEDs & anhalten der Ausführung
        service.addLeds(addAnzahlLEDs, LedColor.RANDOM);
        service.stopExecutionFor(2000);

        // 8. Schritte 3, 4, 5, 6, 7 insgesamt 1 durchlaufen und 3 mal wiederholen -> 4 iterationen
        for (int i = 0; i < 5; i++) {

            // 3. Random Anschaltung der Hälfte aller LEDs  
            for (int j = 0; j < addAnzahlLEDs / 2; j++) {

                // Generation der Random Nummer & Check ob dieses LED bereits eingeschalten ist
                do {
                    randNum = rn.nextInt(0, addAnzahlLEDs); 
                } while(service.isOn(randNum) == true);

                // Einschaltung LEDs
                service.turnLedOn(randNum);
                service.setDelayInMillis(500);
            }

            // 4. Anhalten der Methode für 1 Sekunde
            service.stopExecutionFor(1000);

            // 5. Umschaltung aller LEDs von ein zu aus und aus zu ein
            for (int k = 0; k < addAnzahlLEDs; k++) {
                if (service.isOn(k) == true) {
                    service.turnLedOff(k);
                    service.setDelayInMillis(500);
                } else {
                    service.turnLedOn(k);
                    service.setDelayInMillis(500);
                }
            }

            // 6. Anhalten der Methode für 1 Sekunde
            service.stopExecutionFor(1000);

            // 7. Ausschalten aller LEDs und anhalten für 1 Sekunde
            for (int l = 0; l <= addAnzahlLEDs; l++) {
                service.turnLedOff(l);
                service.setDelayInMillis(500);
            }
        } 
   
        // 9. Zurücksetzen der Anzeige
        service.reset();

        // Scanner schliessen
        sc.close();
    }
}
