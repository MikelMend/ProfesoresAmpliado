package profesores;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Profesor extends Persona{

    private double sueldoBase;
    private int[] horasExtras;
    private double tipoIRPF;
    private String cuentaIBAN;
    

	private TreeMap<String, String> tmAsignaturas;//TREEMAP DE LAS ASIGNATURAS IMPARTIDAS.

    public Profesor() {}
    
    
    public Profesor(double sueldoBase, double tipoIRPF, String cuentaIBAN, String nombre, String apellidos, String calle, String codigoPostal, String ciudad, String dni, String fechaNacimiento) {
        super(nombre, apellidos, calle, codigoPostal, ciudad, dni, fechaNacimiento);
        this.sueldoBase = sueldoBase;
        this.horasExtras = new int[12];
        this.tipoIRPF = tipoIRPF;
        this.cuentaIBAN = cuentaIBAN;
        this.tmAsignaturas = new TreeMap<String, String>();
    }



	// CREAMOS UNA FUNCION PARA CREAR PROFESORES.
    public void nuevoProfesor() throws Exception {
        Scanner sc = new Scanner(System.in);
        //LLAMAMOS A LA FUNCION DEL PADRE PIDEDATOS PARA COGER LOS ATRIBUTOS POR HERENCIA.
        super.pideDatos();
        String sueldo, irpf;
        boolean correcto = false;
        
        // CREAMOS UN BUCLE PARA QUE NO SALGA HASTA QUE LOS DATOS SEAN CORRECTOS.
        do {            
            System.out.print("Número de cuenta bancaria IBAN: ");
            this.cuentaIBAN = sc.nextLine();
            System.out.print("Sueldo base: "); // SE INSERTAN TODOS LOS DATOS COMO STRINGS PARA COMPROBAR
            sueldo = sc.nextLine();
            System.out.print("Tipo de IRPF: ");
            irpf = sc.nextLine();
         
            try {             
                Cuenta.filtroCuenta(cuentaIBAN);
                try {	// FILTRAMOS LOS DATOS. SUELDO,IRPF.
                    if (sueldo.contains(",")) { // SI EL SUELDO INTRODUCIDO TIENE UNA "," SE CAMBIA POR UN PUNTO.
                        sueldo = sueldo.replace(",", ".");
                    }
                    this.sueldoBase = Double.parseDouble(sueldo); // DESPUES DE HABER HECHO LA COMPROBACIÓN DEL AUXILIAR 
                    //SE PASA A DOUBLE Y SE INSERTA EN SU VARIABLE.
                    correcto= true;
                } catch (Exception e) {
                    throw new Exception("Error en el sueldo base.");
                }
                try {
                    if (irpf.contains(",")) { // SI EL IRPF INTRODUCIDO TIENE UNA "," SE CAMBIA POR UN PUNTO.
                        irpf = irpf.replace(",", ".");
                    }
                    this.tipoIRPF = Double.parseDouble(irpf);// DESPUES DE HABER HECHO LA COMPROBACIÓN DEL AUXILIAR 
                    //SE PASA A DOUBLE Y SE INSERTA EN SU VARIABLE.
                    correcto = true;
                } catch (Exception e) {
                    throw new Exception("Error en el tipo irpf.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
            
            this.horasExtras = new int[12]; // SE CREA UN ARRAY PARA INTRODUCIR LAS HORAS EXTRAS DE LOS MESES DEL AÑO.
            this.tmAsignaturas = new TreeMap<String, String>(); // SE LE AÑADE AL PROFESOR EL MAP DE ASIGNATURAS.

        } while (!correcto);

    }
    
    /**
     * Agrega ,borra o muestra las asignaturas que imparte un profesor
     */
    
    public void asignaturasProfesor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica la asignaruta que va a impartir: ");
        String key = "";	// SE CREA UNA VARIABLE VACÍA PARA ALMACENAR LAS CLAVES DEL MAP PARA DESPUES MOSTRAR.
        System.out.println("Profesor: " + this.getApellidos().toUpperCase() + ", " + this.getNombre());
        int option = 0; // SE CREA LA VARIABLE CON VALOR 0 EN LA QUE SE ALMACENARA LA SELECCIÓN
        do {
            System.out.println("");
            if (this.tmAsignaturas.isEmpty() == false) {  // CONDICION QUE CONPRUEBA QUE EL TREEMAP ESTÉ VACÍO
                Iterator it = this.tmAsignaturas.keySet().iterator(); //SE ALMACENA UN ITERADOR PARA ALMACENAR 
                //LAS POSICIONES CON keySet().iterator()
                System.out.println("Asignaturas impartidas: ");
                while (it.hasNext()) { // HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
                    key = (String) it.next(); // NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
                    String nombre = this.tmAsignaturas.get(key); // SE RECOGE EL VALOR DEL MAP CON LA CLAVE RECOGIDA
                    System.out.println(" "+key + ": " + nombre+" "); // SE MUESTRA POR PANTALLA 
                }
            }
            System.out.println("");
            System.out.println("1. Añadir Asignatura.");  // SE CREA EL MENU
            System.out.println("2. Eliminar Asignatura.");
            System.out.println("0. Salir.");
            boolean correcto = false;

            do {
                try {
                    System.out.println("Opción seleccionada: ");
                    option = sc.nextInt();
                    correcto = true;
                } catch (InputMismatchException e) { // HAY QUE IMPORTAR LA LIBRERIA UTIL
                    System.out.println("Opción no admitida");
                    sc.nextLine(); // LIMPIAR EL BUFFER
                } catch (Exception e) { 	// CAPTURA CUALQUIER EXCEPCIÓN NO SEÑALADA
                    System.out.println("Ha ocurrido una excepcion: " + e.getMessage());
                }
            } while (!correcto);// MIENTRAS SEA TRUE SE REPITE.
            switch (option) {  // CREAMOS UN BUCLE PARA EL MENU.
                case 1:
                    System.out.println("");
                    sc.nextLine();
                    System.out.println("Indique el código de la asignatura: ");
                    key = sc.nextLine();
                    if (CentroEducativo.tmCCASIGNA.containsKey(key)) { //VERIFICA SI LA CLAVE PASADA EXISTE EN EL MAP
                        String nombre = CentroEducativo.tmCCASIGNA.get(key);//ALMACENA LA ASIGNATURA CON LA CLAVE INDICADA
                        System.out.println(key + ": " + nombre);  // LA MUESTRA POR PANTALLA
                        this.tmAsignaturas.put(key, nombre); // AÑADE EL ELEMENTO AL MAP, CON THIS. HACE REFERENCIA AL PROFESOR
                        									// QUE ESTÁ REALIZANDO LA CONSULTA.
                    }
                    break;
                case 2:
                    System.out.println("");
                    sc.nextLine();
                    System.out.println("Indique el código de la asignatura que desea eliminar: ");
                    key = sc.nextLine();
                    if (this.tmAsignaturas.containsKey(key)) {//VERIFICA SI LA CLAVE PASADA ESTÁ ASIGNADA EN EL PROFESOR
                        this.tmAsignaturas.remove(key);		//EN TAL CASO LA ELIMINA.
                    }
                    break;
            }

        } while (option != 0); // MIENTRAS QUE EL NÚMERO SEA DISTINTO DE 0 SIGUE EL BUCLE.
    }

    @Override
    public String toString() {   // METODO PARA IMPRIMIR EL PROFESOR
        StringBuilder sb = new StringBuilder(super.toString()); // LLAMA AL PADRE PARA HACER USO DE LOS ATRIBUTOS.
        sb.append("\nCuenta IBAN: ");
        sb.append(this.cuentaIBAN);
        String EEEE = cuentaIBAN.substring(0,4); // RECOGE LOS VALORES PARA ALMACENARLO EN BANCO
        String banco = CentroEducativo.tmEEEE.get(EEEE);
        String EEEESSSS = cuentaIBAN.substring(4,12); // RECOGE LOS VALORES PARA ALMACENARLO EN SUCURSAL
        String sucursal = CentroEducativo.tmEEEESSSS.get(EEEESSSS);
        System.out.println("\nBanco " + banco + " Sucursal " + sucursal); // MUESTRA BANCO Y SUCURSAL
        sb.append("\nSueldo Base: ");
        sb.append(this.sueldoBase);
        sb.append("\nTipo de IRPF: ");
        sb.append(this.tipoIRPF);
        return sb.toString();
        
    }
    		// IMPRIME LAS ASIGNATURAS.
    
    public String imprimeAsignaturas(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nProfesor: " + this.getNombre() + ", " + this.getApellidos());
        sb.append("\nAsignaturas: ");
        Iterator it = this.tmAsignaturas.keySet().iterator();//SE ALMACENA UN ITERADOR PARA ALMACENAR 
        //LAS POSICIONES CON keySet().iterator()
        String key;
        while(it.hasNext()){  // HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
            key = (String) it.next();  // NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
            String asig = this.tmAsignaturas.get(key);  // SE RECOGE EL VALOR DEL MAP CON LA CLAVE RECOGIDA
            sb.append(key + ": " + asig);	// SE MUESTRA POR PANTALLA 
        }
        return sb.toString();
    }
  
    /**
     * @return the sueldoBase
     */
    public double getSueldoBase() {
        return sueldoBase;
    }

    /**
     * @param sueldoBase the sueldoBase to set
     */
    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    /**
     * @return the horasExtra
     */
    public int getHorasExtra(int m) {
        return horasExtras[m]; // SE RECOGEN LAS HORAS EXTRAS POR UN ARRAY DE MESES
    }

    public void setHorasExtra(int i, int horasExtra) {
        this.horasExtras[i] = horasExtra;
    }

    /**
     * @return the tipoIRPF
     */
    public double getTipoIRPF() {
        return tipoIRPF;
    }

    /**
     * @param tipoIRPF the tipoIRPF to set
     */
    public void setTipoIRPF(double tipoIRPF) {
        this.tipoIRPF = tipoIRPF;
    }
    
    public String getCuentaIBAN() {
		return cuentaIBAN;
	}


	public void setCuentaIBAN(String cuentaIBAN) {
		this.cuentaIBAN = cuentaIBAN;
	}
	public TreeMap<String, String> getTmAsignaturas() {
		return tmAsignaturas;
	}


	public void setTmAsignaturas(TreeMap<String, String> tmAsignaturas) {
		this.tmAsignaturas = tmAsignaturas;
	}

    //Calculo del importe de las horas extras por mes    
    private double calcularImporteHorasExtras(int mes) {
        return this.horasExtras[mes] * CentroEducativo.getPagoPorHoraExtra();
    }

    //Método para calcular el sueldo bruto de un mes (sueldo base +
    //complemento por horas extras) calcularSueldoBruto() 
    private double calcularSueldoBruto(int mes) {
        return this.sueldoBase + this.calcularImporteHorasExtras(mes);
    }

    //Método para calcular las retenciones por IRPF de un mes. El porcentaje
    //de IRPF se aplica sobre el sueldo bruto calcularRetencionIrpf(mes). 
    private double calcularRetencionIrpf(int mes) {
        return (calcularSueldoBruto(mes) * this.tipoIRPF) / 100;
    }

    //Método para calcular el sueldo (sueldo bruto - retenciones)
    private double calcularSueldo(int mes) {
        return calcularSueldoBruto(mes) - calcularRetencionIrpf(mes);
    }

    //Método ImprimeProfesor() que se le llama desde el main mediante una
    //instancia de la clase Profesor y escribe sus datos personales:    
    public String ImprimeProfesor() {
        String resumen;
        resumen = "Nombre: " + this.getNombre() + "\nDNI: " + this.getDni() + "\nSueldo Base: " + this.getSueldoBase() + "\nTipo IRPF: " + this.getTipoIRPF();
        return resumen;
    }

    //Método leer profesores
    public void leerProfesores(Scanner sc) throws Exception {
        this.nuevoProfesor();
    }

    //Método ImprimirNominas(int mes) que permite seleccionar un mes e
    //imprime una nómina de la instancia que le llama en ese mes
    public String ImprimirNominas(int mes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ");
        sb.append(this.getNombre() + ", " + this.getApellidos());
        sb.append("\nDNI: ");
        sb.append(this.getDni());
        sb.append(this.toString());
        sb.append("\nCurso: ");
        sb.append(CentroEducativo.getCurso());
        sb.append("\nNomina mes: ");
        sb.append(conocerMes(mes));
        sb.append("\nSueldo base: ");
        sb.append(this.getSueldoBase());
        sb.append("\nHoras Extra: ");
        sb.append(this.getHorasExtra(mes));
        sb.append("\nSueldo Bruto: ");
        sb.append(this.calcularSueldoBruto(mes));
        sb.append("\nRetención IRPF: ");
        sb.append(this.calcularRetencionIrpf(mes));
        sb.append("\nSueldo Bruto: ");
        sb.append(this.calcularSueldoBruto(mes));
        sb.append("\nSueldo: ");
        sb.append(this.calcularSueldo(mes));
        return sb.toString();
    }

    private String conocerMes(int mes) {
        String[] meses = {"Enero", "Febrero", "marzo", "Abril", "mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }

}
