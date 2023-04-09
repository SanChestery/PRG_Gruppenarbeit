package gruppenarbeit;



import ch.hslu.prg.leds.proxy.LedColor;
import ch.hslu.prg.leds.proxy.LedService;

public class ClientApp {
	
	


	public static void main(String[] args) {
		
		
		LedService service = new LedService();
		
		// countColors(service);
		 // siebDesEratosthenes(service);
		countColorsExt(service);
		
		
		
		
		
		
	}
	
	
	public static void siebDesEratosthenes(LedService service) {
		
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS);

		//Alle LEDS einschalten
		for(int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
		
			//Zahlen 0 und 1 ausschliessen
			service.turnLedOff(0);
			service.turnLedOff(1);
			
			//Algorithmus
			int k = 2;
			while(k*k < LedService.MAX_NUMBER_OF_LEDS) {
				for(int l = k+1; l < LedService.MAX_NUMBER_OF_LEDS; l++) {
						if(l%k == 0) {
							service.turnLedOff(l);			
							}
						}
				k++;
				while(k < LedService.MAX_NUMBER_OF_LEDS) {
					if(service.isOn(k)) {
						break;
					}
					else {
						k++;
					}			
				}		
			}	
		}
	
	
	public static void countColors(LedService service) {
		
		// Variablen initialisieren
		int blue = 0;
		int green = 0;
		int red = 0;
		int yellow = 0;
		
		// Max Anzahl Leds mit zufälliger Farbe hinzufügen
		service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.RANDOM);
		
		// Alle Leds einschalten
		for(int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			service.turnLedOn(i);
		}
		//Ausführung für 2 Sekunden stoppen
		service.stopExecutionFor(2000);
		
		for(int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
			
			
			if(service.color(i) == LedColor.BLUE){
				blue++;			
			}else if(service.color(i) == LedColor.GREEN){
				green++;
			}else if(service.color(i) == LedColor.RED){
				red++;
			}else {
				yellow++;
			}
		}
		System.out.println("BLUE: " + blue);
		System.out.println("GREEN: " + green);
		System.out.println("RED: " + red);
		System.out.println("YELLOW: " + yellow);
	}

	public static void countColorsExt(LedService service) {
		
		// Variablen initialisieren
				int blue = 0;
				int green = 0;
				int red = 0;
				int yellow = 0;
				
				int row_blue = 0;
				int row_green = 0;
				int row_red = 0;
				int row_yellow = 0;
				
				int most_blue = 0;
				int most_green = 0;
				int most_red = 0;
				int most_yellow = 0;
		
		// Max Anzahl Leds mit zufälliger Farbe hinzufügen
				service.addLeds(LedService.MAX_NUMBER_OF_LEDS, LedColor.RANDOM);
				
				// Alle Leds einschalten
				for(int i = 0; i < LedService.MAX_NUMBER_OF_LEDS; i++) {
					service.turnLedOn(i);
				}
				//Ausführung für 2 Sekunden stoppen
				service.stopExecutionFor(2000);
		
				//Schleife für die einzelnen Reihen (16)
				for(int i = 0; i < 16; i++) {
					
					//Schleife für die Lichter in den einzelnen Reihen (16)
					for(int k = i *16; k < (i+1) *16; k++) {
						if(service.color(k) == LedColor.BLUE){
							blue++;		
						}
						else if(service.color(k) == LedColor.GREEN){
							green++;
						}
						else if(service.color(k) == LedColor.RED){
							red++;
						}
						else{
							yellow++;
						}
					}

					if(blue >= most_blue) {
						most_blue = blue;
						row_blue = i;
					}
					if(green >= most_green) {
						most_green = green;
						row_green = i;
					}
					if(red >= most_red) {
						most_red = red;
						row_red = i;
					}
					if(yellow >= most_yellow) {
						most_yellow = yellow;
						row_yellow = i;
					}
					
					blue = 0;
					red = 0;
					green = 0;
					yellow = 0;

				}
				System.out.println("BLUE: " + most_blue + " LED's in der Zeile Nr. : " + row_blue);
				System.out.println("GREEN: " + most_green + " LED's in der Zeile Nr. : " + row_green);
				System.out.println("RED: " + most_red + " LED's in der Zeile Nr. : " + row_red);
				System.out.println("YELLOW: " + most_yellow + " LED's in der Zeile Nr. : " + row_yellow);
	}
	
}
