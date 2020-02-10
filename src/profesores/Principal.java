package profesores;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal extends Persona {
	static Scanner sc= new Scanner(System.in);
	static ArrayList<Profesor> lista = new ArrayList<>();
	static ArrayList<Persona> listaPersona= new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double importe=0;
		String curso;
		System.out.print("Curso: ");
		curso = sc.nextLine();
		Profesor.setCurso(curso);
		System.out.println();
		
		boolean correcto= false;
		do {
			System.out.print("Importe Horas Extra: ");
			String simporte = sc.nextLine(); 
			int posicion = simporte.indexOf(','); // si introduce una coma se cambia por un punto.
			if(posicion != -1)
				simporte=simporte.replace(',', '.');
			try {
				importe= Double.parseDouble(simporte);
				Profesor.setPagoPorHoraExtra(importe);
				System.out.println();
				correcto= true;
			}catch (NumberFormatException e) {
				System.out.println("error numbre");
			}catch(Exception e) {
				System.out.println("error en caracteres Importe Horas Extra");
				sc.nextLine();
				
			}
			
		}while(!correcto);
		
		// ahora creamos un menu con las distintas opciones
		boolean salir = false;
		int opcion=0;
		do {
			for(int i=0; i<3; i++) {
				System.out.println();
			}
			System.out.println(" S E L E C C I O N E   U N A   O P C I O N");
			System.out.println();
			System.out.println(" 1. ALTA DE UN PROFESOR");
			System.out.println();
			System.out.println(" 2. BAJA DE UN PROFESOR");
			System.out.println();
			System.out.println(" 3. CONSULTA DE DATOS PERSONALES DE UN PROFESOR");
			System.out.println();
			System.out.println(" 4. INTRODUCIR HORAS EXTRAORDINARIAS DE UN MES");
			System.out.println();
			System.out.println(" 5. LISTADO DE PROFESORES");
			System.out.println();
			System.out.println(" 6. LISTADO DE NOMINAS DE UN MES");
			System.out.println();
			System.out.println(" 0. SALIR DEL PROGRAMA");
			System.out.println();
			System.out.println();
			System.out.print("OPCION SELECCIONADA : ");
			opcion = sc.nextInt();
			System.out.println();
			int i;
			switch(opcion) {
			case 1: // alta profesor
				Profesor p= new Profesor();
				p.nuevoProfesor();
				lista.add(p);
				break;
			case 2: // dar de baja a profesor
				do {
					System.out.print("Numero del profesor: ");
					i= sc.nextInt();
				}while (i<0 || i>=lista.size());
				lista.remove(i -1);
				break;
			case 3: // Consultar Profesor
				do {
					System.out.print("Numero de profesor: ");
					i= sc.nextInt();
				}while(i<1 || i>lista.size());
				i=i-1;
				System.out.println(lista.get(i).toString());
				break;
			case 4: // Introducir horas extraordinarias de un mes
				int mes=0;
				correcto= false;
				do {
					System.out.print("Horas extraordinarias realizadas por los profesores en el mes: ");
					try {
						mes= sc.nextInt();
						if(mes<1 || mes>12) throw new Exception("Mes inexistente");
						correcto= true;
					}catch(InputMismatchException e) {
						System.out.println("Numero Incorrecto");
					}catch(Exception e) {
						System.out.println("Error "+ e.getMessage());
					}
				}while (!correcto);
				System.out.println();
				mes= mes-1; // corregimos el indice para el array
				for(i=0; i<lista.size();i++) {
					int horas;
					System.out.print("Nombre Profesor: ");
					System.out.println(lista.get(i).getNombre());
					correcto= false;
					do {
						System.out.print("Horas realizadas: ");
						try {
							horas= sc.nextInt();
							// limitamos el número de horas extras a 20 al mes
							if(horas>20) throw new Exception("El número máximo de horas permitidas al mes es de 20");
							lista.get(i).setHorasExtras(mes, horas);
							System.out.println();
							correcto = true;	
			
			}catch(InputMismatchException e) {
				System.out.println("Numero de horas incorrecto");
				
			}catch(Exception e) {
				System.out.println("Error: " + e.getMessage());
					}
				}while(!correcto);
			}
				break;
			case 5: // listado de datos personales de todos los profesores
				for(i=0;i<lista.size();i++) {
					System.out.println("Número profesor "+ (i+1));
					System.out.println(lista.get(i).toString());
					System.out.println();
				}
				break;
			case 6: // listado de nominas de un mes
				do {
					System.out.print("Nominas del mes: ");
					mes= sc.nextInt();
				}while(mes<1|| mes>12);
				System.out.println();
				mes=mes-1; // corregimos el inidce del array
				for(i=0;i<lista.size(); i++) {
					System.out.println("Número profesor: "+(i+1));
					System.out.println(lista.get(i).ImprimirNominas(mes));
				}
				break;
			
				default: salir=true;
				}
			}while(salir == false);
		}
	}
	


