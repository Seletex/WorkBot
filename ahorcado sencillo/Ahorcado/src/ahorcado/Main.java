package ahorcado;

import java.util.Random;
import java.util.Scanner;
import java.security.SecureRandom;

public class Main {
	private static final Random random = new SecureRandom();
	static String palabraSecreta = obtenerPalabraSecreta();
	public static void main(String[] args){
		
			
			final char[] palabraGuiones= obtenerGuionesDePalabraSecreta(palabraSecreta);
			boolean juegoTerminado=false;
			final Scanner lector= new Scanner(System.in);
			int intentos=5;
			do{
				System.out.println(intentos);
				System.out.println(palabraGuiones);
				System.out.println("Introduce una letra");
				char letra=lector.next().charAt(0);
				boolean algunaLetraAcertada=false;
				for(int i = 0;i<palabraSecreta.length();i++){
					if(palabraSecreta.charAt(i)==letra){
						palabraGuiones[i]=letra;
						algunaLetraAcertada=true;
					}
				}
				if(!algunaLetraAcertada){System.out.println("No has acertado ninguna plabra");
				--intentos;
				}
				if(intentos==0) {
					System.out.println("Has perdido por que has agotaado todos los intentos");
					juegoTerminado=true;
				}else{
					final boolean juegoGanado= hayGuiones(palabraGuiones);
					if(juegoGanado){
						System.out.println("Has ganado el juego");
						juegoTerminado=true;
					}
				}
			}while(!juegoTerminado);
lector.close();
	}
	
		
			static String obtenerPalabraSecreta() {
		final String[] palabra = { "casa", "perro", "coche" };
		final int numeral = random.nextInt(palabra.length);
		return palabra[numeral];
	}

	static char[] obtenerGuionesDePalabraSecreta(String palabra) {
		final int numeralPalabraSecreta = palabraSecreta.length();
		final char[] palabraGuion = extracted(numeralPalabraSecreta);
		for (int i = 0; i < palabraGuion.length; i++) {
			palabraGuion[i] = "_".charAt(i);
		}
		return palabraGuion;
	}


	private static char[] extracted(int numeralPalabraSecreta) {
		final char[] palabraGuion = new char[numeralPalabraSecreta];
		return palabraGuion;
	}

	static boolean hayGuiones(char[] array) {
		for (char l : array) {
			if (l == "_".charAt(l)) {
				return true;
			}else {
				return false;
			}
			
		}
		return false;
	}
}
