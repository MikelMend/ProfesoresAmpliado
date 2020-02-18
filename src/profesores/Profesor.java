package profesores;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.InputMismatchException;


public class Profesor extends Persona {

	private double sueldoBase;
	private int[] horasExtras;
	private double tipoIRPF;
	private String cuentaIBAN;
	private TreeMap<String, String> tmAsignaturas;
	
	// constructor de profesor por defecto
	public Profesor() {
		
	}
	public Profesor(String nombre,String dni,double sueldoBase, int[] horasExtras, double tipoIRPF, String cuentaIBAN) {
		super(nombre,dni,apellidos,calle,codigoPostal,ciudad,fechaNacimiento);
		this.sueldoBase = sueldoBase;
		this.horasExtras = new int[12];
		this.tipoIRPF = tipoIRPF;
		this.cuentaIBAN = cuentaIBAN;
		tmAsignaturas = new TreeMap<String,String>();
	}
	
	// creamos los metodos getters y setters


	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public int getHorasExtras(int mes) {
		return horasExtras[mes];
	}

	public void setHorasExtras(int mes, int horas) {
		this.horasExtras[mes] = horas;
	}

	public double getTipoIRPF() {
		return tipoIRPF;
	}

	public void setTipoIRPF(double tipoIRPF) {
		this.tipoIRPF = tipoIRPF;
	}

	public String getCuentaIBAN() {
		return cuentaIBAN;
	}

	public void setCuentaIBAN(String cuentaIBAN) {
		this.cuentaIBAN = cuentaIBAN;
	}
	public double calcularImporteHorasExtras(int mes) {
		mes=mes-1;
		
		return horasExtras[mes];
		
	}
	
	public double calcularSueldoBruto(int mes) {
		
		return calcularImporteHorasExtras(mes) + sueldoBase;
		
	}
	
	public double calcularRetencionIrpf(int mes) {
		return (calcularSueldoBruto(mes) * tipoIRPF) /100;
		
	}
	
	public double calcularSueldo(int mes) {
		return calcularSueldoBruto(mes)-calcularRetencionIrpf(mes);
		
	}
	
	// en este punto pedimos las asignaturas de un profesor.
	
	public void asignaturasProfesor() {
		Scanner sc= new Scanner(System.in);
		String key="";
		System.out.println("Profesor: "+this.getApellidos()+ ", "+this.getNombre());
		// muestra las asignaturas que imparte el profesor.
		int opcion=0;
		do {
			System.out.println("");
			if(this.tmAsignaturas.isEmpty()==false) {
			Iterator it=this.tmAsignaturas.keySet().iterator();
			System.out.println("ASIGNATURAS IMPARTIDASA");
			while(it.hasNext()) {
				key= (String) it.next();// codigo de curso+asignatura
				String nombre= this.tmAsignaturas.get(key);// nombre de la asignatura
				System.out.println(key + ": "+nombre);
			}
		}
			System.out.println("");
			System.out.println("1. Añadir Asignatura.");
			System.out.println("2. Quitar Asignatura.");
			System.out.println("0. Salir.");
			boolean correcto= false;
			do {
				try {
					System.out.print("Opción seleccionada: ");
					opcion= sc.nextInt();
					correcto=true;
				}catch(InputMismatchException e) {
					System.out.print("opción no admitida");
					sc.hasNextLine();
				}catch(Exception e) {
			
			
			
			
		}while(asignatura== true);
		}
		
	}
	
	// aquí pedimos los datos de un nuevo profesor para meterlo despues en el array
	
	public void nuevoProfesor() {
		Scanner sc= new Scanner(System.in);
		String ssueldoBase,stipoIRPF;
		super.nuevaPersona();
		boolean correcto= false;
		do {
			
			System.out.print("Cuenta IBAN: ");
			cuentaIBAN = sc.nextLine();
			System.out.print("Sueldo Base: ");
			ssueldoBase = sc.nextLine();
			System.out.print("Tipo de IRPF: ");
			stipoIRPF = sc.nextLine();
			
			
			try {
				verificaDNI(this.dni);
				Cuenta.filtroCuenta(this.cuentaIBAN);
				try {
					int posicion= ssueldoBase.indexOf(',');
					if(posicion != -1) {
						ssueldoBase=ssueldoBase.replace(',', '.');
					}
					sueldoBase= Double.parseDouble(ssueldoBase);
				}catch(Exception e) {
					throw new Exception("Error en el Sueldo Base");
				}
				try { 
					int posicion = stipoIRPF.indexOf(',');
					if(posicion != -1) {
						stipoIRPF=stipoIRPF.replace(',', '.');
					}tipoIRPF= Double.parseDouble(stipoIRPF);
				}catch(Exception e) {
					throw new Exception("Error en el tipo IRPF");
				}
				correcto=true;
				}catch(Exception e) {
					System.out.println("error "+ e.getMessage());
					sc.nextLine();
			}
			horasExtras= new int[12]; // la iniciamos vacía
			TMAsignaturas= new TreeMap<String,String>();
			
		}while(!correcto);
		
	}
	
	
	
@Override
	public String toString() {
		String resultado="";
		StringBuilder sb= new StringBuilder();
		sb.append("Nombre: ");
		sb.append(this.nombre);
		sb.append("DNI :");
		sb.append(this.dni);
		sb.append("Cuenta IBAN: ");
		sb.append(this.cuentaIBAN);
		sb.append("Sueldo Base: ");
		sb.append(this.sueldoBase);
		sb.append("Tipo de IRPF :");
		sb.append(this.tipoIRPF);
		resultado= sb.toString();
		
		return resultado;
		
	}
	
	public String ImprimirNominas(int mes) {
		String nombreMes[]= {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Nomviembre","Diciembre"};
		StringBuilder sb= new StringBuilder();
		sb.append("Nombre: ");
		sb.append(this.nombre);
		sb.append("DNI :");
		sb.append(this.dni);
		sb.append("Cuenta IBAN: ");
		sb.append(cuentaIBAN);
		sb.append("Nomina mes: ");
		sb.append(nombreMes[mes]);
		sb.append("Sueldo Base: ");
		sb.append(sueldoBase);
		sb.append("Horas Extras: ");
		sb.append(getHorasExtras(mes));
		sb.append("Tipo de IRPF :");
		sb.append(tipoIRPF);
		sb.append("Sueldo Bruto: "+ calcularSueldoBruto(mes));
		sb.append("Retención por IRPF: "+ calcularRetencionIrpf(mes));
		sb.append("Sueldo Neto: "+calcularSueldo(mes)+ "\n");
		return sb.toString();
		
		}
	static void verificaDNI(String dni) throws Exception{
		String sNumero;
		char letra;
		String letras_dni="TRWAGMYFPDXBNJZSQVHLCKE";
		int numero;
		sNumero= dni.substring(0,dni.length()-1);
		letra= dni.charAt(dni.length()-1);
		try {// se comprueba que los caracteres son numericos.
			numero=Integer.parseInt(sNumero);
			if(letras_dni.charAt(numero%23) != letra) throw new Exception ("Letra DNI incorrecta");
			
		}catch (NumberFormatException e) {
			System.out.println("error caracteres no númericos");
			
		}catch(Exception e) {
			throw new Exception("error "+ e.getMessage());
		}// si pasa es porque es un entero.
	}

}
