package profesores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Persona { 			//CREAMOS LOS ATRIBUTOS
	protected String nombre;
	protected String apellidos;
	protected String calle;
	protected String codigoPostal;
	protected String ciudad;
	protected String dni;
	protected String fechaNacimiento;

	
	public Persona() {			// CONSTRUCTOR POR DEFECTO
		
	}
	
		//SE CREA EL CONSTRUCTOR CON TODOS LOS ATRIBUTOS PARA DESPUES HEREDAR LAS DEMAS CLASES
	public Persona(String nombre, String apellidos, String calle, String codigoPostal, String ciudad, String dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;

		
	}
			// CREAMOS LOS GETTERS Y SETTERS
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
	
	
			// PEDIMOS LOS DATOS DE PERSONA QUE SERVIRÁ COMO HERENCIA
	public void pideDatos() {
		Scanner sc= new Scanner(System.in);
		boolean correcto = true;
		do {
			// SE CREAN LAS VARIABLES AUXILIARES PEDIDAS COMO STRING PARA VERIFICARLAS
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
	
			// VALIDAMOS LOS DATOS
		try {
			validarDni(dni);
			validarFecha(fechaNacimiento);
			correcto= true;
		}catch(Exception e) {
			correcto = false;
			System.out.println("error "+ e.getMessage());
			sc.nextLine();
		}
			
			}while(!correcto); // MIENTRAS SEA DISTINTO DE CORRECTO SEGUIMOS EN EL BUCLE
		}
	@Override		// MÉTODO TOSTRING PARA MOSTRAR LOS DATOS DE PERSONA
	public String toString() {
		String resultado="";
		StringBuilder sb= new StringBuilder("Datos Personales: ");
		sb.append("Nombre: ");
		sb.append(nombre);
		sb.append("\n");
		sb.append("Apellidos: ");
		sb.append(apellidos);
		sb.append("\n");
		sb.append("Calle: ");
		sb.append(calle);
		sb.append("\n");
		sb.append("Código Postal: ");
		sb.append(codigoPostal);
		sb.append("\n");
		sb.append("Ciudad: ");
		sb.append(ciudad);
		sb.append("\n");
		sb.append("DNI :");
		sb.append(dni);
		sb.append("\n");
		sb.append("Fecha Nacimiento :");
		sb.append(fechaNacimiento);
		
		resultado= sb.toString();
		return resultado;
	}
		
	
			// METODO PARA VALIDAR DNI
	public static void validarDni(String dni) throws Exception {
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        String numeros = dni.substring(0, dni.length() - 1);
        int numerosDni = 0;
        char letra = (char) dni.toUpperCase().charAt(dni.length() - 1); // Aseguramos que todas las letras queden en mayusculas y extraemos la ultima letra del dni

        if (numeros.length() != 8) {
            throw new Exception("la longuitud del DNI debe de ser de ocho caracteres numÃ©ricos.");
        }

        try {
            numerosDni = parseInt(numeros);
        } catch (NumberFormatException e) {
            throw new Exception("Debe introducir ocho caracteres numÃ©ricos al principio.");
        }
        int resto = numerosDni % 23;

        if (letras[resto] != letra) {
            throw new Exception("La letra " + letra + " del dni no es la correcta.");
        }
    }

			// METODO PARA VALIDAR FECHA
						// SE PASA LA FECHA COMO UN STRING
    public static void validarFecha(String s) throws Exception {
        if (s.length() != 10) {
            throw new Exception("El fecha debe contener un total de 10 caracteres.");
        }
        if ((s.charAt(2) != '/') || (s.charAt(5) != '/')) {
            throw new Exception("El formato fecha debe ser DD/MM/AAAA.");
        }
        char letra;
        for (int i = 0; i < s.length(); i++) {
            letra = s.charAt(i);
            if (letra != '/') {
                if (letra < '0' && letra > '9') {
                    throw new Exception("Debe introducir caracteres numéricos.");
                }
            }
        }
        String sDia,sMes,sAnio, sFecha;  // CREAMOS AUXILIARES
        sDia = s.substring(0, 2);	// SE SEPARA LA FECHA EN STRING PARA AGRUPARLO POR DIA,MES,AÑO
        sMes = s.substring(3, 5);
        sAnio = s.substring(6, 10);
        
        int dia, mes, anio; 
        
        try{                       // SE CONVIERTEN A ENTERO
            dia= Integer.parseInt(sDia);
            mes= Integer.parseInt(sMes);
            anio= Integer.parseInt(sAnio);
        }catch(NumberFormatException e){
            throw new Exception("No se ha podido convertir la cadena String a número.");
        }
        String mensajeExcepcion = "";
        boolean correcto = true;
 
        switch (mes) {
            case 1: case 3:case 5: case 7: case 8:case 10:case 12:
                if (dia < 1 || dia > 31) {
                    correcto = false;
                    mensajeExcepcion = "El día de nacimiento no es correcto.";
                }
                break;
            case 2:
                int max = 28;
                if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
                    max = 29;
                }
                if (dia < 1 || dia > max) {
                    correcto = false;
                    mensajeExcepcion = "El año de nacimiento no es bisiestro, por lo tanto, febrero solo dispone de 28 días.";
                }

                break;
            case 4: case 6: case 9: case 11:
                if (dia < 1 || dia > 30) {
                    correcto = false;
                    mensajeExcepcion = "El día de nacimiento no es correcto. El mes solo corresponde a 30 días";
                }
                break;
            default:
                correcto = false;
                mensajeExcepcion = "Error en la fecha de nacimiento.";
        }
        
        if (! correcto) throw new Exception(mensajeExcepcion);// MIENTRAS SEA DISNTITO DE CORRECTO MUESTRA UNA EXCEPCIÓN
    }

	
}

	