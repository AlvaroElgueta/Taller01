// Integrante1: Alvaro Elgueta - Rut: 21.806.097-8 - Carrera: ICCI

package Taller01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static Scanner s;
	
	// atributos de alumnos
	private static int contAlumnos = 0;
	
	private static String[] nombres = new String[100];
	private static String[] apellidos = new String[100];
	private static String[] ruts = new String[100];
	private static String[] paralelos = new String[100];
	
	//atributos de solicitudes
	private static int contSolicitudes = 0;
	
	private static String[] nombresS = new String[100];
	private static String[] apellidosS = new String[100];
	
	public static void main(String[] args) {
	
		cargarAlumnos();
		cargarSolicitudes();			

		s = new Scanner(System.in);
	
	}

	//se encarga de cargar el archivo solicitudes
	private static void cargarSolicitudes() {
		try {
			s = new Scanner(new File("Solicitudes.txt"));
			while (s.hasNextLine()) {
				String[] datos = s.nextLine().split("-");
				if (datos.length == 2) {
					if (contSolicitudes < 100) {
						nombresS[contSolicitudes] = datos[0];
						apellidosS[contSolicitudes] = datos[1];
						contSolicitudes++;					
					} else {
						System.out.println("No hay espacio para guardar más solicitudes");
						break;
					}
				} else {
					System.out.println("Linea con formato incorrecto en Solicitudes.txt");
				}
			} s.close();
		} catch (IOException e) {
			System.out.println("Carga de Solicitudes.txt fallida");
		}
	}
	
	//se encarga de cargar el archivo alumnos
	private static void cargarAlumnos() {
		try {
			s = new Scanner(new File("Alumnos.txt"));
			while (s.hasNextLine()) {
				String[] datos = s.nextLine().split(";");
				if (datos.length == 4) {
					if (contAlumnos < 100) {
						nombres[contAlumnos] = datos[0];
						apellidos[contAlumnos] = datos[1];
						ruts[contAlumnos] = datos[2];
						paralelos[contAlumnos] = datos[3];
						contAlumnos++;					
					} else {
						System.out.println("No hay espacio para guardar más alumnos");
						break;
					}
				} else {
					System.out.println("Linea con formato incorrecto en Alumnos.txt");
				}
			} s.close();	
		} catch (IOException e) {
			System.out.println("Carga de Alumnos.txt fallida");
		}
	}

}
