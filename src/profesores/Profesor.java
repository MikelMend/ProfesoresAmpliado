package profesores;

import java.util.Scanner;

public class Profesor extends Persona {
	private static String curso;
	private static double pagoPorHoraExtra; // es un atributo de clase
	private double sueldoBase;
	private int[] horasExtras;
	private double tipoIRPF;
	private String cuentaIBAN;
	
	// constructor de profesor por defecto
	public Profesor() {
		
	}
	public Profesor(String nombre,String dni,double sueldoBase, int[] horasExtras, double tipoIRPF, String cuentaIBAN) {
		super();
		this.sueldoBase = sueldoBase;
		this.horasExtras = horasExtras;
		this.tipoIRPF = tipoIRPF;
		this.cuentaIBAN = cuentaIBAN;
	}
	
	// creamos los metodos getters y setters
	public static String getCurso() {
		return curso;
	}

	public static void setCurso(String curso) {
		Profesor.curso = curso;
	}

	public static double getPagoPorHoraExtra() {
		return pagoPorHoraExtra;
	}

	public static void setPagoPorHoraExtra(double pagoPorHoraExtra) {
		Profesor.pagoPorHoraExtra = pagoPorHoraExtra;
	}

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
		
		return horasExtras[mes]*pagoPorHoraExtra;
		
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
	
	// aquí pedimos los datos de un nuevo profesor para meterlo despues en el array
	
	public void nuevoProfesor() {
		Scanner sc= new Scanner(System.in);
		String dni,ssueldoBase,stipoIRPF;
		boolean correcto= false;
		do {
			System.out.print("Profesor: ");
			nombre= sc.nextLine();
			System.out.print("DNI: ");
			dni = sc.nextLine();
			System.out.print("Cuenta IBAN: ");
			cuentaIBAN = sc.nextLine();
			System.out.print("Sueldo Base: ");
			ssueldoBase = sc.nextLine();
			System.out.print("Tipo de IRPF: ");
			stipoIRPF = sc.nextLine();
			
			try {
				verificaDNI(dni);
				Cuenta.filtroCuenta(cuentaIBAN);
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
			
		}while(!correcto);
		
	}
	
	

	public String toString() {
		String resultado="";
		StringBuilder sb= new StringBuilder();
		sb.append("Nombre: ");
		sb.append(nombre);
		sb.append("DNI :");
		sb.append(dni);
		sb.append("Cuenta IBAN: ");
		sb.append(cuentaIBAN);
		sb.append("Sueldo Base: ");
		sb.append(sueldoBase);
		sb.append("Tipo de IRPF :");
		sb.append(tipoIRPF);
		resultado= sb.toString();
		
		return resultado;
		
	}
	
	public String ImprimirNominas(int mes) {
		String nombreMes[]= {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Nomviembre","Diciembre"};
		StringBuilder sb= new StringBuilder();
		sb.append("Nombre: ");
		sb.append(nombre);
		sb.append("DNI :");
		sb.append(dni);
		sb.append("Cuenta IBAN: ");
		sb.append(cuentaIBAN);
		sb.append("Curso: ");
		sb.append(curso);
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
