// Integrante1: Alvaro Elgueta - Rut: 21.806.097-8 - Carrera: ICCI

package Taller01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static Scanner s;
	
	// atributos de alumnos
	private static int contAlumnos;
	private static String[] nombresAlumnos = new String[100];
	private static String[] apellidosAlumnos = new String[100];
	private static String[] rutsAlumnos = new String[100];
	private static String[] paralelosAlumnos = new String[100];
	
	//atributos de solicitudes
	private static int contSolicitudes;
	private static String[] nombresSolicitudes = new String[100];
	private static String[] apellidosSolicitudes = new String[100];
	
	//atributos de admitidos
	private static int contAdmitidos;
	private static String[] nombresAdmitidos = new String[100];
	private static String[] apellidosAdmitidos = new String[100];
	private static String[] rutsAdmitidos = new String[100];
	private static String[] paralelosAdmitidos = new String[100];
	
	//atributos de rechazados
	private static int contRechazados;
	private static String[] rechazados = new String[100];
	
	public static void main(String[] args) {
		menu();
	}
	
	//se encarga de mostrar el menu
	private static void menu() {
		boolean salir = false;
		s = new Scanner(System.in);
		do {
			System.out.println("===== Sistema de Control del Grupo POO =====\r\n"
					+ "1) Cargar archivos (Alumnos y Solicitudes)\r\n"
					+ "2) Procesar solicitudes (Filtrado automatico)\r\n"
					+ "3) Inscripcion manual al grupo\r\n"
					+ "4) Administracion del curso\r\n"
					+ "5) Generar reportes\r\n"
					+ "6) Analisis estadistico\r\n"
					+ "7) Salir\r\n");
			int opcion = leerOpcion();
			switch(opcion) {
			case 1: {
				contAdmitidos = 0;
				contRechazados = 0;
				cargarAlumnos();
				cargarSolicitudes();
				s = new Scanner(System.in);
				System.out.println("Archivos cargados con exito!\r\n"
						+ "- " + contAlumnos + " alumnos en la lista.\r\n"
						+ "- " + contSolicitudes + " solicitudes de ingreso.\r\n");
				break;
			}
			case 2: {
				procesarSolicitudes();
				break;
			}
			case 3: {
				menuInscripcionManual();
				break;
			}
			case 4: {
				
				break;
			}
			case 5: {

				break;
			}
			case 6: {
				
				break;
			}
			case 7: {
				System.out.println("Saliendo...");
				salir = true;
				break;
			}
			default: {
				System.out.println("Debe ser una opcion valida(1/2/3/4/5/6/7)\r\n");
				break;
			}
			}
		} while (!salir);	
	}
	
	//
	private static void menuInscripcionManual() {
		if (contAlumnos == 0) {
			System.out.println("Primero debe cargar los archivos\r\n");
			return;
		}
		boolean salir = false;
		do {
			System.out.println("Como desea inscribir a la persona?\r\n"
					+ "1) Por nombre completo\r\n"
					+ "2) Por RUT\r\n"
					+ "3) Volver\r\n");
			int opcion = leerOpcion();
			switch (opcion) {
			case 1: {
				inscripcionManualNombre();
				break;
			}
			case 2: {
				inscripcionManualRut();
				break;
			}
			case 3: {
				salir = true;
				break;
			}
			default: {
				System.out.println("Debe ser una opcion valida(1/2/3)\r\n");
				break;
			}
			}
		} while (!salir);
	}

	//	//se encarga de ingresar al grupo un alumno que este inscrito en el ramo por su rut
	private static void inscripcionManualRut() {
		System.out.println("Ingrese rut: ");
		String rut = s.nextLine();
		boolean encontrado = false;
		for (int i = 0; i < contAlumnos; i++) {
			if (rut.equalsIgnoreCase(rutsAlumnos[i])) {
				boolean repetido = false;
				for (int j = 0; j < contAdmitidos; j++) {
					if (rut.equalsIgnoreCase(rutsAdmitidos[j])) {
						repetido = true;
						break;
					}
				}
				if (!repetido) {
					nombresAdmitidos[contAdmitidos] = nombresAlumnos[i]; 
					apellidosAdmitidos[contAdmitidos] = apellidosAlumnos[i];
					rutsAdmitidos[contAdmitidos] = rutsAlumnos[i];
					paralelosAdmitidos[contAdmitidos] = paralelosAlumnos[i];
					System.out.println("[OK]       " + nombresAdmitidos[contAdmitidos] + " " + apellidosAdmitidos[contAdmitidos] + " -> admitido en " + paralelosAdmitidos[contAdmitidos]);
					contAdmitidos++;
				} else {
					System.out.println("[OK]       La persona ya estaba admitida");
				}
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			rechazados[contRechazados] = rut;
			System.out.println("[RECHAZO]  " + rechazados[contRechazados] + " -> no tiene nombre por lo que se registro solo su rut en rechazados");
			contRechazados++;			
		}
	}

	//se encarga de ingresar al grupo un alumno que este inscrito en el ramo por su nombre completo
	private static void inscripcionManualNombre() {
		System.out.println("Ingrese nombre: ");
		String nombre = s.nextLine();
		System.out.println("Ingrese apellido: ");
		String apellido = s.nextLine();
		boolean encontrado = false;
		for (int i = 0; i < contAlumnos; i++) {
			if (nombre.equalsIgnoreCase(nombresAlumnos[i]) && apellido.equalsIgnoreCase(apellidosAlumnos[i])) {
				boolean repetido = false;
				for (int j = 0; j < contAdmitidos; j++) {
					if (nombre.equalsIgnoreCase(nombresAdmitidos[j]) && apellido.equalsIgnoreCase(apellidosAdmitidos[j])) {
						repetido = true;
						break;
					}
				}
				if (!repetido) {
					nombresAdmitidos[contAdmitidos] = nombresAlumnos[i]; 
					apellidosAdmitidos[contAdmitidos] = apellidosAlumnos[i];
					rutsAdmitidos[contAdmitidos] = rutsAlumnos[i];
					paralelosAdmitidos[contAdmitidos] = paralelosAlumnos[i];
					System.out.println("[OK]       " + nombresAdmitidos[contAdmitidos] + " " + apellidosAdmitidos[contAdmitidos] + " -> admitido en " + paralelosAdmitidos[contAdmitidos]);
					contAdmitidos++;
				} else {
					System.out.println("[OK]       La persona ya estaba admitida");
				}
				encontrado = true;
				break;					
			}
		}
		if (!encontrado) {
			rechazados[contRechazados] = nombre + " " + apellido;
			System.out.println("[RECHAZO]  " + rechazados[contRechazados] + " -> no pertenece a ningun paralelo");
			contRechazados++;
		}
	}

	//valida si un usuario esta inscrito en el ramo
	private static void procesarSolicitudes() {
		if (contAlumnos == 0 || contSolicitudes == 0) {
			System.out.println("Primero debe cargar los archivos\r\n");
			return;
		}
		System.out.println("Procesando solicitudes...\r\n");
		for (int i = 0; i < contSolicitudes; i++) {
			boolean encontrado = false;
			for (int j = 0; j < contAlumnos; j++) {
				boolean repetido = false;
				if (nombresSolicitudes[i].equalsIgnoreCase(nombresAlumnos[j]) && apellidosSolicitudes[i].equalsIgnoreCase(apellidosAlumnos[j])) {
					for (int k = 0; k < contAdmitidos; k++) {
						if (rutsAlumnos[j].equalsIgnoreCase(rutsAdmitidos[k])) {
							repetido = true;
							break;
						}	
					}
					if (!repetido) {
						nombresAdmitidos[contAdmitidos] = nombresAlumnos[j]; 
						apellidosAdmitidos[contAdmitidos] = apellidosAlumnos[j];
						rutsAdmitidos[contAdmitidos] = rutsAlumnos[j];
						paralelosAdmitidos[contAdmitidos] = paralelosAlumnos[j];
						System.out.println("[OK]       " + nombresAdmitidos[contAdmitidos] + " " + apellidosAdmitidos[contAdmitidos] + " -> admitido en " + paralelosAdmitidos[contAdmitidos]);
						contAdmitidos++;
					}
					encontrado = true;						
					break;
				}
			}
			if (!encontrado) {
				rechazados[contRechazados] = nombresSolicitudes[i] + " " + apellidosSolicitudes[i];
				System.out.println("[RECHAZO]  " + rechazados[contRechazados] + " -> no pertenece a ningun paralelo");
				contRechazados++;
			}
		} System.out.println("\r\nResumen: " + contAdmitidos + " admitidos / " + contRechazados + " rechazados.\r\n");
	} 
	
	//valida que el usuario no ingrese una letra y el programa se caiga
	private static int leerOpcion() {
		while(true) {
			try {
				System.out.print("Ingrese opcion: ");
				int opcion = Integer.parseInt(s.nextLine());
				return opcion;
			} catch (NumberFormatException e) {
				return -1;
			}
		}
	}
	
	//se encarga de cargar el archivo solicitudes
	private static void cargarSolicitudes() {
		try {
			s = new Scanner(new File("Solicitudes.txt"));
			contSolicitudes = 0;
			while (s.hasNextLine()) {
				String[] datos = s.nextLine().split("-");
				if (datos.length == 2) {
					if (contSolicitudes < 100) {
						nombresSolicitudes[contSolicitudes] = datos[0];
						apellidosSolicitudes[contSolicitudes] = datos[1];
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
			contAlumnos = 0;
			while (s.hasNextLine()) {
				String[] datos = s.nextLine().split(";");
				if (datos.length == 4) {
					if (contAlumnos < 100) {
						nombresAlumnos[contAlumnos] = datos[0];
						apellidosAlumnos[contAlumnos] = datos[1];
						rutsAlumnos[contAlumnos] = datos[2];
						paralelosAlumnos[contAlumnos] = datos[3];
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
