package profesores;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Alumno extends Persona{ // CREAMOS
	private String curso;
	private TreeMap<String, Notas>tmAsignaturasAlumno;
	
	// METODOS GETTERS Y SETTERS
	
	public TreeMap<String, Notas> getTmAsignaturasAlumno() {
		return tmAsignaturasAlumno;
	}
	public void setTmAsignaturasAlumno(TreeMap<String, Notas> tmAsignaturasAlumno) {
		this.tmAsignaturasAlumno = tmAsignaturasAlumno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	// CONSTRUCTOR
	
	public Alumno() {
        this.curso= "";
        tmAsignaturasAlumno= new TreeMap<String,Notas>();
       
	}
	
	// PEDIMOS LOS DATOS AL ALUMNO
	public void pideDatos() {
		Scanner sc = new Scanner(System.in);
		String scurso,sCodAsig,sNomAsig, key; // CREAMOS LAS VARIABLES AUXILIARES
		boolean Salir= true;
		Notas n= new Notas(); // CREAMOS UN OBJETO NOTAS PARA ALMACENARLAS EN UN ARRAY
		int opcion=0; // INICIALIZAMOS OPCION EN 0 PARA EL MENU
		Iterator it; // CREAMOS UN ITERADOR PARA RECORRER EL MAP
		
		super.pideDatos(); // LLAMAMOS AL METODO PADRE PARA RECOGER DATOS
		System.out.println("Indique el código del curso: ");
		this.curso = sc.nextLine();
		 do { 
			 try { 
				 System.out.println("\n\nSELECCIONE" + "UNA OPCIÓN\n"); // CREAMOS EL MENU
				 System.out.println("\t1. Añadir Asignatura.");
				 System.out.println("\t0. Salir.");
				 System.out.println("\n\n\t\tOpción Seleccionada.");
				 opcion= sc.nextInt(); // ALMACENAMOS LA OPCION SELECCIONADA
				 // SI LA OPCION ES MAYOR DE 1 O MENOR DE 0 LANZA UNA EXCEPCION
				 if(opcion<0 || opcion>1) throw new Exception("\t\tError -> Opción Incorrecta. Debe ser(0-1)\n");
			 }catch(InputMismatchException e) {
				 sc.nextLine();
				 Salir=false;
				 opcion=7;
				 System.out.println("Error -> Digitos Incorrectos.");
			 }catch(Exception e) {
				 sc.nextLine();
				 Salir=false;
				 System.out.println(e.getMessage());
			 }switch(opcion){

			 case 1:	// ESTE CASO ALMACENA LAS ASIGNATURAS SELECCIONADAS
				 try {
					 //LIMPIAMOS EL BUFFER
					 sCodAsig= sc.nextLine();
					 System.out.print("Código de Asignatura: ");
					 sCodAsig= sc.nextLine();	// INSERTAMOS EL CODIGO DE LA ASIGNATURA
					 sCodAsig= sCodAsig.toUpperCase();//HACEMOS MAYUSCULA LA VARIABLE
					 System.out.println("Código Asignatura: " + sCodAsig); // LA MOSTRAMOS POR PANTALLA
					 if (CentroEducativo.tmCCASIGNA.containsKey(sCodAsig)==true) { //VERIFICA SI EL CODIGO PASADO EXISTE EN EL MAP
						 sNomAsig = CentroEducativo.tmCCASIGNA.get(sCodAsig);//ALMACENA LA ASIGNATURA CON LA CLAVE INDICADA
						 System.out.println(sCodAsig+": "+sNomAsig); // MUESTRA EL CODIGO Y LA ASIGNATURA SELECCIONADA
						 this.tmAsignaturasAlumno.put(sCodAsig, n ); // LA AÑADE AL TREEMAP
						 Salir=false;
						 System.out.println("\nAsignaturas elegidas: ");
						 System.out.println("-------------------------");
						 it= this.tmAsignaturasAlumno.keySet().iterator(); // SE CREA UN ITERADOR PARA RECORRER EL MAP
						 while(it.hasNext()) { // RECORRE IT MIENTRAS HAYAN DATOS.
							 key=(String) it.next();// // NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
							 if(this.tmAsignaturasAlumno.containsKey(key)== true) {// SI LA CLAVE COINCIDE LA MUESTRA.
								 System.out.println(key +": "+ CentroEducativo.tmCCASIGNA.get(key));
							 }
							 
						 }
					 }else {
						 Salir=false;
						 throw new Exception("Error --> Asignatura Inexistente.");
					 }
					 }catch(Exception e) {
						 System.out.println(e.getMessage());
					 }
				 break;
				 }
	         	}while(opcion!=0);
		
	}
	
	public String toString() {   // METODO PARA IMPRIMIR EL PROFESOR
		
		
        StringBuilder sb = new StringBuilder(super.toString()); // LLAMA AL PADRE PARA HACER USO DE LOS ATRIBUTOS.
        sb.append("\nCurso: ");
        sb.append(this.curso);
        return sb.toString();
        
    }
	
	public String boletinNotas(String curso, int evaluacion) {
		 StringBuilder sb = new StringBuilder();
		 String key;
		 Notas n;
		 	sb.append("BOLETIN DE NOTAS "+evaluacion+ "ª EVALUACIÓN.");
	        sb.append("\nAlumno: " + this.getNombre() + ", " + this.getApellidos());
	        sb.append("\nCurso: " + this.curso);
	        
	        Iterator it = this.tmAsignaturasAlumno.keySet().iterator();//SE ALMACENA UN ITERADOR PARA ALMACENAR 
	        //LAS POSICIONES CON keySet().iterator()
	        
	        while(it.hasNext()){  // HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
	            key = (String) it.next();  // NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
	            sb.append("\nAsignatura: "+key+ " "+ tmAsignaturasAlumno.get(key).notas[evaluacion-1]);
	            }
	        return sb.toString();
	}
	
	
}
