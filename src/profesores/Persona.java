package profesores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Persona {
	protected String nombre;
	protected String apellidos;
	protected String calle;
	protected String codigoPostal;
	protected String ciudad;
	protected String dni;
	protected String fechaNacimiento;

	
	public Persona() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void nuevaPersona() {
		boolean correcto = false;
		Scanner sc= new Scanner(System.in);
		String nombre,apellidos,calle,codigoPostal,ciudad,dni,fechaNacimiento;
		System.out.print("Nombre :");
		nombre = sc.nextLine();
		System.out.print("Apellidos:");
		apellidos = sc.nextLine();
		System.out.print("Calle: ");
		calle = sc.nextLine();
		System.out.print("Codigo Postal: ");
		codigoPostal = sc.nextLine();
		System.out.print("Ciudad :");
		ciudad= sc.nextLine();
		System.out.print("Dni: ");
		dni = sc.nextLine();
		System.out.println("Fecha Nacimiento (DDMMAAAA) :");
		fechaNacimiento = sc.nextLine();
	
		
		try {
			verificaDNI(dni);
			validarFecha(fechaNacimiento);
			correcto= true;
		}catch(Exception e) {
			System.out.println("error "+ e.getMessage());
			sc.nextLine();
			}while(!correcto);
		}
	public String toString() {
		String resultado="";
		StringBuilder sb= new StringBuilder();
		sb.append("Nombre: ");
		sb.append(nombre);
		sb.append("Apellidos: ");
		sb.append(apellidos);
		sb.append("Calle: ");
		sb.append(calle);
		sb.append("Código Postal: ");
		sb.append(codigoPostal);
		sb.append("Ciudad: ");
		sb.append(ciudad);
		sb.append("DNI :");
		sb.append(dni);
		sb.append("Fecha Nacimiento :");
		sb.append(fechaNacimiento);
		
		resultado= sb.toString();
		return resultado;
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
	
	public static boolean validarFecha(String fechaNacimiento) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fechaNacimiento);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	
}

	