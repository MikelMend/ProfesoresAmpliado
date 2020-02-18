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
	public Persona(String nombre, String dni, String apellidos, String calle, String codigoPostal, String ciudad, String fechaNacimiento) {
		this.nombre=nombre;
		this.dni=dni;
		this.apellidos= apellidos;
		this.calle= calle;
		this.codigoPostal= codigoPostal;
		this.ciudad= ciudad;
		this.fechaNacimiento=fechaNacimiento;
		
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
		String pnombre,papellidos,pcalle,pcodigoPostal,pciudad,pdni,pfechaNacimiento;
		System.out.println("Nombre :");
		pnombre = sc.nextLine();
		setNombre(pnombre);
		System.out.println("Apellidos:");
		papellidos = sc.nextLine();
		setApellidos(papellidos);
		System.out.println("Calle: ");
		pcalle = sc.nextLine();
		setCalle(pcalle);
		System.out.println("Codigo Postal: ");
		pcodigoPostal = sc.nextLine();
		setCodigoPostal(pcodigoPostal);
		System.out.println("Ciudad :");
		pciudad= sc.nextLine();
		setCiudad(pciudad);
		System.out.println("Dni: ");
		pdni = sc.nextLine();
		setDni(pdni);
		System.out.println("Fecha Nacimiento (DDMMAAAA) :");
		pfechaNacimiento = sc.nextLine();
		setFechaNacimiento(pfechaNacimiento);
	
		
		try {
			verificaDNI(dni);
			validarFecha(fechaNacimiento);
			correcto= true;
		}catch(Exception e) {
			System.out.println("error "+ e.getMessage());
			sc.nextLine();
			}while(!correcto);
		}
	@Override
	public String toString() {
		String resultado="";
		StringBuilder sb= new StringBuilder("Datos Personales: ");
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

	