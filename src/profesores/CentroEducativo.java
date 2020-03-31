package profesores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class CentroEducativo {
	private static String curso;
	private static double pagoPorHoraExtra;
    static TreeMap<String, Persona> lista = new TreeMap<String, Persona>(); // MAP CON PERSONAS
    static TreeMap<String, String> tmEEEE = new TreeMap<String, String>();//Map con entidades bancarias
    static TreeMap<String, String> tmEEEESSSS = new TreeMap<String, String>();//Map con sucursales bancarias
    static TreeMap<String, String> tmCC = new TreeMap<String, String>();//Map con nombres de cursos
    static TreeMap<String, String> tmCCASIGNA = new TreeMap<String, String>();//Map con curso+asignatura

    
    
	
    public static void setCurso(String curso) {
        CentroEducativo.curso = curso;
    }

    public static void setPagoPorHoraExtra(double pagoPorHoraExtra) {
        CentroEducativo.pagoPorHoraExtra = pagoPorHoraExtra;
    }

    public static void setLista(TreeMap<String, Persona> lista) {
        CentroEducativo.lista = lista;
    }

    public static void setTmEEEE(TreeMap<String, String> tmEEEE) {
        CentroEducativo.tmEEEE = tmEEEE;
    }

    public static void setTmEEEESSSS(TreeMap<String, String> tmEEEESSSS) {
        CentroEducativo.tmEEEESSSS = tmEEEESSSS;
    }

    public static void setTmCC(TreeMap<String, String> tmCC) {
        CentroEducativo.tmCC = tmCC;
    }

    public static void setTmCCASIGNA(TreeMap<String, String> tmCCASIGNA) {
        CentroEducativo.tmCCASIGNA = tmCCASIGNA;
    }

    public static String getCurso() {
        return curso;
    }

    public static double getPagoPorHoraExtra() {
        return pagoPorHoraExtra;
    }

    public static TreeMap<String, Persona> getLista() {
        return lista;
    }

    public static TreeMap<String, String> getTmEEEE() {
        return tmEEEE;
    }

    public static TreeMap<String, String> getTmEEEESSSS() {
        return tmEEEESSSS;
    }

    public static TreeMap<String, String> getTmCC() {
        return tmCC;
    }

    public static TreeMap<String, String> getTmCCASIGNA() {
        return tmCCASIGNA;
    }

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception{
    	try {
    		TablasCursos.cargaGlobales();
    	}catch(Exception e) {
    		System.out.println("Carga globales no funciona.");
    	}
    	
        Cuenta.cargaEntidadesBancarias(tmEEEE); // SE CARGAN LOS TREEMAP
        Cuenta.cargaSucursalesBancarias(tmEEEESSSS);
        TablasCursos.cargaCursos(tmCC);
        TablasCursos.cargaCursosAsignaturas(tmCCASIGNA);
        try {
        	
        
        File rutaPersonas3 = new File("C:\\ProyectoCentro\\Personas3.txt");
    	lista =FuncionesFicheros.obtenerTreeMapDeArchivo(rutaPersonas3);
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
        
        System.out.println();
        boolean correcto = false;        

       /* 
        // OBJETOS CREADOS PARA PROBAR EL PROGRAMA.
        Profesor p = new Profesor(1500.0,10.0, "ES2400811152680006077615", "Nombre1", "Apellidos1","calle1","03202", 
                           "Elche", "54060067Q", "12/12/1985"); 
        String key = p.getApellidos() + ", " + p.getNombre();
        lista.put(key, p);
        
        p = new Profesor(1200.0,10.0, "ES2400811152680006077615", "Cristian", "Rubio","calle3","03300", 
                           "Cuenca", "45727569G", "19/10/1988"); 
        key = p.getApellidos() + ", " + p.getNombre();
        lista.put(key, p);
        
        p = new Profesor(1500.0,10.0, "ES2400811152680006077615", "Mikel", "Apellidos2","calle2","035555", 
                           "Alicante", "22222222J", "10/07/1979"); 
        key = p.getApellidos() + ", " + p.getNombre();
        lista.put(key, p);
        
        p = new Profesor(1600.0,10.0, "ES2400811152680006077615", "Susana", "Gonzalez","Plaza Castilla","38680", 
                           "Guia de Isora", "21309422z", "03/06/1979"); 
        
        key = p.getApellidos() + ", " + p.getNombre();
        lista.put(key, p);
        */
        String key;
        Profesor p = new Profesor();
        boolean salirMenu = false;
        int opcion= 0;
        boolean salirProfesor = false;
        int option = 0;		//SE INICIA OPCION EN 0
        boolean salirAlumno= false;
    	int opcionAlumno= 0;
        
        do {
        	try {
        	System.out.println("\n*************MENU PRINCIPAL*************");
        	System.out.println("1. MANTENIMIENTO ALUMNOS");
        	System.out.println("2. MANTENIMIENTO PROFESORES");
        	System.out.println("3. LISTADO DE NOMBRES DE PROFESORES Y ALUMNOS");
        	System.out.println("4. LISTADO DE NOMBRE DE PROFESORES");
        	System.out.println("5. LISTADO DE NOMBRE DE ALUMNOS");
        	System.out.println("0. FIN DE LA APLICACIÓN");
        	
        	System.out.print("Opción seleccionada: ");
        	opcion= sc.nextInt();
        	if (opcion<0 || opcion>5) throw new 
            Exception("\t\tERROR --> Opción Incorrecta. Debe ser (0 - 5) \n"); 
    		 }
            catch (InputMismatchException e) {
                sc.nextLine();
                salirMenu=false;
                System.out.println("ERROR --> NO ha introducido un "
                        + "Opción Numérica.");
            }
            catch (Exception e) {
                sc.nextLine();
                salirMenu=false;
                System.out.println(e.getMessage());
            }
        	switch(opcion) {
        	
        case 1:
        	

        	
        	do {
        		try {
        		System.out.println("\n************************MANTENIMIENTO DE ALUMNOS************************");
        		System.out.println("1. ALTA DE UN ALUMNO");
        		System.out.println("2. BAJA DE UN ALUMNO");
        		System.out.println("3. CONSULTA DE DATOS PERSONALES DE UN ALUMNO");
        		System.out.println("4. INTRODUCIR NOTAS DE UNA ASIGNATURA Y EVALUACIÓN A TODOS LOS MATRICULADOS");
        		System.out.println("5. LISTADO DE ALUMNOS DE UN GRUPO. DATOS PERSONALES");
        		System.out.println("6. LISTADO E ALUMNOS MATRICULADOS EN UNA ASIGNATURA");
        		System.out.println("7. LISTADO DE BOLETINES DE NOTAS DE UNA EVALUACIÓN Y CURSO");
        		System.out.println("0. VUELTA AL MENÚ PRINCIPAL");

        		System.out.print("Opción seleccionada: ");
        		opcionAlumno= sc.nextInt();
        		
             
        		
        		if (opcionAlumno<0 || opcionAlumno>7) throw new 
                Exception("\t\tERROR --> Opción Incorrecta. Debe ser (0 - 7) \n"); 
        		 }
                catch (InputMismatchException e) {
                    sc.nextLine();
                    salirAlumno=false;
                    System.out.println("ERROR --> NO ha introducido un "
                            + "Opción Numérica.");
                }
                catch (Exception e) {
                    sc.nextLine();
                    salirAlumno=false;
                    System.out.println(e.getMessage());
                }
        		
        		switch(opcionAlumno) {
        		
        		case 1:
        			System.out.println("Opción seleccionada: Alta alumno");
        			 try{
                        Alumno alum = new Alumno(); // SE INSTANCIA UN OBJETO.
                         alum.pideDatos();	// LLAMAMOS A LA FUNCION PARA AÑADIR LOS DATOS A PROFESOR
						key = alum.getApellidos() + ", " + alum.getNombre(); // SE CREA LA CLAVE CON LOS DATOS RECOGIDOS
                         // COMPRUEBA SI EN PERSONAS EXISTEN ESTOS DATOS, SI EXISTEN SALTA UNA EXEPCION
                         if(lista.containsKey(key)) throw new Exception("Este nombre ya existe. No puedo grabarlo");
                         lista.put(key, alum);	//AÑADE LOS DATOS RECOGIDOS Y EL OBJETO AL TREEMAP LISTA
                     }catch(Exception e){
                         System.out.println(e.getMessage());
                     }                    
        			
        			break;
        		case 2:
        			System.out.println("Opción seleccionada: Baja alumno");
        			sc.nextLine();
        			boolean salir= false;
        			do {
        			try {
        				System.out.println("Nombre del alumno a dar de baja: ");
        				String NombreA= sc.nextLine();
        				System.out.println("Apellido del alumno a dar de baja: ");
        				String ApellidoA = sc.nextLine();
        				key= ApellidoA+", "+NombreA;
        				if(lista.containsKey(key)==false) throw new Exception("El alumno que deseas eliminar no existe."); 
        				salir=true;// si es correcto sale del bucle.
        					if(lista.get(key) instanceof Alumno) {// sacamos a que clase pertenece obteniendo el key de la lista PERSONA
        						lista.remove(key);// eliminamos
        						System.out.println("El alumno se ha eliminado correctamente.");
        					
        					}
        				
        					
        					
        			
        			}catch(Exception e) {
        				System.out.println(e.getMessage());
        				salir= false;
        				
        			}
        			}while(salir!=false);
        			
        			break;
        		case 3:
        			System.out.println("Opción seleccionada: Consultar datos personales alumno");
        			Scanner sc = new Scanner(System.in);
        	        String sNombre = null, sApellidos = null;
        	        
        	        if (lista.isEmpty()==true) 
        	            throw new Exception("La Lista se encuentra vacía.");
        	        
        	            System.out.print("Nombre de Alumnno: ");
        	            sNombre = sc.nextLine();
        	            
        	            System.out.print("Apellidos de Alumno: ");
        	            sApellidos = sc.nextLine();    
        	                   
        	       key  = sApellidos + ", " + sNombre;

        	        System.out.println("\nKEY: " + key);
        	        
        	        if (lista.containsKey(key) != true) throw new Exception ("ERROR --> Alumno Inexistente.");
        	       
        	            System.out.println (lista.get(key).toString());
        	   
        			break;
        		case 4:
        			System.out.println("Opción seleccionada: Introducir notas de asignatura y evaluación");
        			 sc = new Scanner (System.in);
        		    String sCodAsig, sNomAsig;
        		    int nEvaluacion,notasAux;
        		    correcto = false;
        		    Alumno alu = new Alumno();
        		    Notas n = new Notas();
        		    TreeMap<String,Notas> tmAsigAlum = new TreeMap <>();
        		    Iterator it;
        		    
        		    if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONA está VACÍA.");
        		    else {
        		        try {
        		            System.out.print("Código de Asignatura: ");
        		            sCodAsig = sc.nextLine();
        		            sCodAsig = sCodAsig.toUpperCase();
        		        
        		            if (tmCCASIGNA.containsKey(sCodAsig) == true) {
        		                sNomAsig = tmCCASIGNA.get(sCodAsig);
        		                System.out.println ("Curso: " + curso + "    Asignatura: " + sNomAsig);
        		            
        		                it = lista.keySet().iterator();
        		                while (it.hasNext()) {
        		                    key = (String) it.next();
        		                    if (lista.get(key) instanceof Alumno) {
        		                        alu = (Alumno) (lista.get(key));
        		                        tmAsigAlum = alu.getTmAsignaturasAlumno();
        		                        if (tmAsigAlum.containsKey(sCodAsig) == true) {
        		                            System.out.print ("Evaluación: " );
        		                            nEvaluacion = sc.nextInt();
        		                            if (nEvaluacion<0 || nEvaluacion>5) 
        		                                throw new Exception ("ERROR --> Evaluación Incorrecta.");
        		                            do {
        		                                notasAux=0;
        		                                correcto=false;
        		                                try {
        		                                    System.out.print("\n" + key + "         NOTA: ");
        		                                    notasAux = sc.nextInt();
        		                                    if (notasAux<0 || notasAux>10) 
        		                                        throw new Exception ("ERROR --> Valores entre (0 - 10)");
        		                                    correcto=true;
        		                                    tmAsigAlum.get(sCodAsig).notas[nEvaluacion-1] = notasAux;
        		                                    alu.setTmAsignaturasAlumno(tmAsigAlum);
        		                                }
        		                                catch (InputMismatchException e) {
        		                                    sc.nextLine();
        		                                    System.out.println("ERROR --> Dígitos Incorrectos.");
        		                                }
        		                                catch (Exception e) {
        		                                    System.out.println(e.getMessage());
        		                                }
        		                            }while (correcto==false);        
        		                        } 
        		                    }
        		                }
        		            }
        		            else throw new Exception("ERROR --> La Asignatura NO EXISTE.");
        		        }
        		    
        		        catch (InputMismatchException e) {
        		            System.out.println ("ERROR --> Opción Incorrecta.");
        		        }
        		        catch (Exception e) {
        		            System.out.println(e.getMessage());
        		        }
        		    }
        			
        			break;
        		case 5:
        			System.out.println("Opción seleccionada: Listado datos de un grupo de alumnos");
        			sc = new Scanner(System.in);
        	        String sCodGrupo, sNomGrupo;
        	        
        	        Alumno alum = new Alumno();
        	        
        	        if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONAS está VACÍA.");
        	        else {
        	            try {
        	                System.out.print ("Introduzca el Grupo: ");
        	                sCodGrupo = sc.nextLine();
        	                sCodGrupo = sCodGrupo.toUpperCase();
        	            
        	                if (tmCC.containsKey(sCodGrupo) == true) {
        	                    sNomGrupo = tmCC.get(sCodGrupo);
        	                    System.out.println("\n\n----------------------------------------");
        	                    System.out.println("  Grupo: " + sCodGrupo + "  " + sNomGrupo);
        	                    System.out.println("----------------------------------------");
        	                    System.out.println("           LISTADO DE ALUMNOS ");
        	                    System.out.println("----------------------------------------");
        	                    System.out.println("");
        	                    it = lista.keySet().iterator();
        	                    while (it.hasNext()) {
        	                        key = (String) it.next();
        	                        if (lista.get(key) instanceof Alumno) {
        	                            alum = (Alumno) (lista.get(key));
        	                        
        	                            if (alum.getCurso().compareTo(sCodGrupo) == 0) {
        	                                System.out.println(key);
        	                            }
        	                        }
        	                    }
        	                    System.out.println("----------------------------------------");
        	                }
        	                else throw new Exception ("ERROR --> El GRUPO no EXISTE.");
        	            }
        	            catch (Exception e) {
        	                System.out.println (e.getMessage());
        	            }
        	        }
        			
        			break;
        		case 6:
        			System.out.println("Opción seleccionada: Alumnos matriculados en una asignatura");
        			sc = new Scanner (System.in);
        	        sNomGrupo = null;
        	       
        	        correcto = false;
        	        
        	        
        	        
        	        
        	        
        	        if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONAS está VACÍA.");
        	        else {
        	            try {
        	                System.out.print("Código de Asignatura: ");
        	                sCodAsig = sc.nextLine();
        	                sCodAsig = sCodAsig.toUpperCase();
        	            
        	                if (tmCCASIGNA.containsKey(sCodAsig) == true) {
        	                    System.out.println("----------------------------------------");
        	                    sNomAsig = tmCCASIGNA.get(sCodAsig);
        	                    System.out.println ("Curso Académico: " + curso);
        	                
        	                    System.out.print ("Curso: " + sCodAsig.substring(0, 2));
        	                    if (tmCC.containsKey(sCodAsig.substring(0, 2)) == true) {
        	                        sNomGrupo = tmCC.get(sCodAsig.substring(0,2));
        	                    }
        	                    System.out.println ("  " + sNomGrupo + "    Asignatura: " + sNomAsig);
        	                    System.out.println("----------------------------------------");
        	                    System.out.println ("APELLIDOS / NOMBRE " );
        	                    System.out.println("----------------------------------------");
        	                    System.out.println("");
        	                
        	                    it = lista.keySet().iterator();
        	                    while (it.hasNext()) {
        	                        key = (String) it.next();
        	                        if (lista.get(key) instanceof Alumno) {
        	                            alum = (Alumno) (lista.get(key));
        	                            tmAsigAlum = alum.getTmAsignaturasAlumno();
        	                            if (tmAsigAlum.containsKey(sCodAsig) == true) {
        	                                System.out.print(key);
        	                            }
        	                        }
        	                    }
        	                }
        	                else throw new Exception("ERROR --> La Asignatura NO EXISTE.");
        	            }
        	            catch (InputMismatchException e) {
        	                System.out.println ("ERROR --> Opción Incorrecta.");
        	            }
        	            catch (Exception e) {
        	                System.out.println(e.getMessage());
        	            } 
        	        }
        			
        			break;
        		case 7:
        			System.out.println("Opción seleccionada: Boletines de notas de evaluación y curso");
        			sc = new Scanner (System.in);
        	        sCodGrupo = null;
        	        sNomGrupo = null;
        	        nEvaluacion = 0;
        	        correcto = false;
        	        
        	        
        	        if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONAS está VACÍA.");
        	        else {
        	            do {
        	                try {
        	                    System.out.print("\n\nCurso: ");
        	                    sCodGrupo = sc.nextLine();
        	                    sCodGrupo = sCodGrupo.toUpperCase();
        	                
        	                    if (CentroEducativo.tmCC.containsKey(sCodGrupo) == true) {
        	                        sNomGrupo = CentroEducativo.tmCC.get(sCodGrupo);
        	                        System.out.println("Nombre Curso: " + sNomGrupo);
        	                        correcto = true;
        	                    }
        	                    else throw new Exception ("ERROR --> El Curso NO EXISTE.");
        	                }
        	                catch (Exception e) {
        	                    System.out.println(e.getMessage());
        	                }
        	            }while (correcto == false);    
        	        
        	            correcto=false;
        	            do {
        	                try {
        	                    System.out.print("Evaluación (1 - 5): ");
        	                    nEvaluacion = sc.nextInt();
        	                    System.out.println("");
        	                    if (nEvaluacion<0 || nEvaluacion>5) 
        	                        throw new Exception ("ERROR --> EVALUACIÓN INCORRECTA.");
        	                    correcto=true;
        	                }
        	                catch (InputMismatchException e) {
        	                    System.out.println ("ERROR --> Ha introducido Dígitos Incorrectos.");
        	                }
        	                catch (Exception e) {
        	                    System.out.println(e.getMessage());
        	                }
        	            }while (correcto == false);  
        	            System.out.println("----------------------------------------");
        	            
        	            it = lista.keySet().iterator();
        	            while (it.hasNext()) {
        	                key = (String) it.next();
        	                if (lista.get(key) instanceof Alumno) {
        	                    alum = (Alumno) (lista.get(key));
        	                    //System.out.println("ALU.CURSO:" + alu.getCurso() + " SCODGRUPO: " + sCodGrupo );
        	                    if (alum.getCurso().compareTo(sCodGrupo) == 0){
        	                        System.out.println(alum.boletinNotas(sCodGrupo, nEvaluacion));
        	                    }
        	                }
        	            }
        	            System.out.println("----------------------------------------");
        	        }
        	    
        			
        			break;
        		default:
        			salirAlumno=true;
        			opcionAlumno = 0;
        			
        		
        		}
        		}while(opcionAlumno!=0||salirAlumno==false);
        		
        		break;
        		
        case 2:
        
       
        do{
        	try {
            System.out.println("\n*********************MANTENIMIENTO PROFESORES********************** "); // SE CREA EL MENU
            System.out.println("1. ALTA DE UN PROFESOR:");
            System.out.println("2. BAJA DE UN PROFESOR:");
            System.out.println("3. CONSULTA DE DATOS PERSONALES DE UN PROFESOR:");
            System.out.println("4. INTRODUCIR HORAS EXTRAORDINARIA DE UN MES:");
            System.out.println("5. LISTADO DE PROFESORES. DATOS PERSONALES:");
            System.out.println("6. LISTADO DE PROFESORES. CLASES QUE IMPARTEN:");
            System.out.println("7. LISTADO DE NOMINASDE UN MES:");
            System.out.println("8. MANTENIMIENTO DE ASIGNATURAS IMPARTIDAS POR CADA PROFESOR:");
            System.out.println("0. VUELTA AL MENÚ PRINCIPAL:");
            
            System.out.print("Opción seleccionada: ");
            option = sc.nextInt(); // LIMPIAMOS EL BUFFER
            if (option<0 || option>8) throw new 
            Exception("\t\tERROR --> Opción Incorrecta. Debe ser (0 - 8) \n"); 
    		 }
            catch (InputMismatchException e) {
                sc.nextLine();
                salirProfesor=false;
                System.out.println("ERROR --> NO ha introducido un "
                        + "Opción Numérica.");
            }
            catch (Exception e) {
                sc.nextLine();
                salirProfesor=false;
                System.out.println(e.getMessage());
            }
    		
            
            Profesor profe; // IMPLEMENTAMOS UN OBJETO PROFESOR VACÍO
            Persona per = null; // IMPLEMENTAMOS UN OBJETO PERSONA CON VALOR NULL
            
            switch(option){ // BUCLE PARA EL MENU
                case 1 :
                    
                    try{
                    	System.out.println("Opcion seleccionada: Alta profesor");
                        profe = new Profesor(); // SE INSTANCIA UN OBJETO.
                        profe.nuevoProfesor();	// LLAMAMOS A LA FUNCION PARA AÑADIR LOS DATOS A PROFESOR
                        key = profe.getApellidos() + ", " + profe.getNombre(); // SE CREA LA CLAVE CON LOS DATOS RECOGIDOS
                        // COMPRUEBA SI EN PERSONAS EXISTEN ESTOS DATOS, SI EXISTEN SALTA UNA EXEPCION
                        if(!lista.containsKey(key)) {
                        	lista.put(key, profe);
                        }else {
                        	throw new Exception("El profesor existe.");
                        }
                        }catch(Exception e){
                        System.out.println("El profesor existe.");
                    }                    
                    break;
                case 2:
                    correcto = false;
                    do{
                        try{
                            System.out.println("Opción seleccionada: Baja Profesor");
                            System.out.println("indique el nombre del profesor:");
                            String n = sc.nextLine();
                            System.out.println("indique el apellido del profesor:");
                            String a = sc.nextLine();
                            key = a + ", " + n;// ALMACENA LA CADENA EN LA KEY
                            // COMPRUEBA SI EN LISTA EXISTEN ESTOS DATOS Y DEVUELVE UN BOOLEANO 
                            if(lista.containsKey(key) == false) throw new Exception("El nombre que desea eliminar no existe");
                            lista.remove(key);// SI ES TRUE ELIMINA LA KEY
                            correcto = true;
                        }catch(Exception e){
                            System.out.println(e.getMessage());        
                        }
                    }while(! correcto);  // MIENTRAS SEA DISTINTO DE CORRECTO NO SALE DEL BUCLE
                    break;
                case 3:
                    correcto = false;
                    System.out.println("Opcion seleccionada: Imprimir datos profesor");
                    do{
                        try{
                            sc.nextLine(); // SE LIMPIA EL BUFFER
                            System.out.println("Indique el nombre del profesor: ");
                            String n = sc.nextLine();
                            System.out.println("Indique los apellidos del profesor: ");
                            String a = sc.nextLine();
                            key = a + ", " + n;  // ALMACENA LA CADENA EN LA KEY
                            
                            if(lista.containsKey(key)){// COMPRUEBA SI EN LISTA EXISTEN ESTOS DATOS Y DEVUELVE UN BOOLEANO
                                per = lista.get(key); // PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                            }else{
                                throw new Exception("El profesor no existe.");
                            }
                            if(per instanceof Profesor){// CON INSTANCEOF DETERMINA DE QUE CLASE ES EL TOSTRING
                                profe = (Profesor) per; // SE CREA EL CATCH PROFESOR A PER
                                System.out.println(lista.get(key).toString()); // SE MUESTRA POR PANTALLA
                            }
                            else{
                               throw new Exception("No se trata de un profesor. ");     
                            }
                            correcto = true;
                        }catch(Exception e){
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        
                    }while(! correcto); // NO SALE DEL BUCLE MIENTRAS SEA DISTINTO DE CORRECTO
                    break;
                    
                case 4:
                    correcto = false;
                    int mes, horas;
                    do{
                        try{
                            System.out.println("Opcion seleccionada: Introducir horas extras por mes: ");
                            System.out.println("Horas extraordinarias por los profesores en el mes de: ");
                            mes = sc.nextInt();
                            if(mes < 0 && mes > 12) throw new Exception("Mes incorrecto.");
                            
                            //CREAMOS UN ITERADOR PARA RECORRER LA LISTA
                            Iterator it = lista.keySet().iterator();
                            
                            //// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
                            while(it.hasNext()){
                                key = (String) it.next();// NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
                                //COMPRUEBA QUE LA CLAVE PASADA PERTENEZCA A UN PROFESOR PARA OBTENERLA
                                if(lista.get(key) instanceof Profesor){
                                    profe = (Profesor) lista.get(key); //ALMACENA EN PROFE LA INFORMACION DEL TREEMAP CON LA CLAVE PASADA
                                    System.out.println("Nombre del profesor: ");
                                    System.out.println(key);
                                    System.out.println("Horas realizadas: ");
                                    horas = sc.nextInt();
                                    if(horas > 20 ) throw new Exception("No se puede exceder de mas de 20 horas al mes");
                                    profe.setHorasExtra(mes,horas);
                                }                         
                            }
                            correcto = true;
                        }catch(Exception e){
                            System.out.println("Error: " + e.getMessage());
                            sc.nextLine();
                        }
                        
                    }while(! correcto);
                    break;
                    
                case 5:
                    System.out.println("Opción seleccionada: Imprime datos personales de los profesores.");
                    Iterator it = lista.keySet().iterator();  //CREAMOS UN ITERADOR PARA RECORRER LA LISTA
                    
                    while(it.hasNext()){		 //// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
                        System.out.println(it.next());
                            key = (String) it.next();    // NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
                            per = lista.get(key);  //PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                        if(per instanceof Profesor){ // LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                            profe =  (Profesor) per; // PROFE ALMACENA EL VALOR DE PER QUE RECOGIO LOS DATOS ANTERIORMENTE.
                            System.out.println(profe.toString());
                            System.out.println();
                        }
                    }
                    break;
                    
                case 6:
                    System.out.println("Opción seleccionada: Datos de las clases que imparten los profesores.");
                    it = lista.keySet().iterator();
                    
                    while(it.hasNext()){		//// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
                        key = (String) it.next();	// NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
                        if(lista.get(key) instanceof Profesor){	// LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                            profe = (Profesor) lista.get(key);
                            System.out.println(profe.imprimeAsignaturas());
                            System.out.println();
                        }
                    }
                    break;
                
                case 7:
                    correcto = false;
                    do{
                        System.out.println("Opción seleccionada: Imprimir nomina de cada profesor según el mes: ");
                        try{
                            System.out.println("Nómnias del mes: ");
                            mes = sc.nextInt();
                            if(mes < 0 || mes > 12) throw new InputMismatchException("Error al introducir el mes del año: ");
                            it = lista.keySet().iterator();
                            while(it.hasNext()){	//// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
                                key = (String) it.next();	// NEXT DEVUELVE EL SIGUIENTE ELEMENTO DEL ITERATOR Y SE ALMACENA EN "key"
                                per = lista.get(key);		//PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                                if(per instanceof Profesor){	// LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                                    profe = (Profesor) per;	// PROFE ALMACENA EL VALOR DE PER QUE RECOGIO LOS DATOS ANTERIORMENTE.
                                    System.out.println(profe.ImprimirNominas(mes));
                                }
                                System.out.println();
                            }
                            correcto = true;
                        }catch(InputMismatchException e){
                            System.out.println("Error: " + e.getMessage());
                        }catch(Exception e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    }while(! correcto);
                    break;
                case 8:
                    correcto = false;
                    
                    do{
                        sc.nextLine();
                        System.out.println("Nombre del profesor: "); 
                        String n = sc.nextLine();
                        System.out.println("Apellidos del Profesor: ");
                        String a = sc.nextLine();
                        key = a + ", " + n;		// ALMACENA LA CADENA EN LA KEY
                        per = lista.get(key);		//PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                        if(per instanceof Persona){		// LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                            profe = (Profesor) per;		// PROFE ALMACENA EL VALOR DE PER QUE RECOGIO LOS DATOS ANTERIORMENTE.
                            System.out.println(profe.toString());
                            profe.asignaturasProfesor();
                            correcto = true;
                        }
                    }while(! correcto);
                    System.out.println("");
                    break;
                default:
                    salirProfesor = true;
                    option=0;
                    break;
            	}
        	}while(!salirProfesor||option!=0);
            
        case 3:
        	System.out.println("\n LISTADO DE NOMBRES DE PROFESORES Y ALUMNOS");
        	System.out.println("Curso Académico: " +getCurso());
        	System.out.println("LISTADO DE PROFESORES Y ALUMNOS");
            System.out.println("APELLIDOS/NOMBRE");
            Iterator it = lista.keySet().iterator();  //CREAMOS UN ITERADOR PARA RECORRER LA LISTA
            
            while(it.hasNext()){		 //// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
              
                key = (String) it.next();
                    Persona per = lista.get(key);  //PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                if(per instanceof Profesor){ // LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                    Profesor profe = (Profesor) per; // PROFE ALMACENA EL VALOR DE PER QUE RECOGIO LOS DATOS ANTERIORMENTE.
                    System.out.println("(P)"+key);
                    System.out.println();
                }else if(lista.get(key) instanceof Alumno) {
                		Alumno alum=(Alumno) lista.get(key);
                		System.out.println("("+alum.getCurso()+")"+alum.getNombre()+ ","+alum.getApellidos());
                
                	
                }
            }
            
        	break;
        case 4:
        	System.out.println("\n LISTADO DE NOMBRES DE PROFESORES");
        	System.out.println("Curso Académico: " +getCurso());
        	System.out.println("LISTADO DE PROFESORES");
            System.out.println("APELLIDOS/NOMBRE");
        	
        	 it = lista.keySet().iterator();  //CREAMOS UN ITERADOR PARA RECORRER LA LISTA
            
            while(it.hasNext()){		 //// HASNEXT DEVUELVE TRUE MIENTRAS QUEDEN MAS ELEMENTOS PARA LEER EN "it"
              key=(String) it.next();

                    Persona per = lista.get(key);  //PER QUE ES NULL ALMACENA  EL VALOR QUE RECOGE DE LA KEY PASADA
                if(per instanceof Profesor){ // LOS COMPARA Y SI CUMPLE SE LE HACE UN CATCH
                    Profesor profe = (Profesor) per; // PROFE ALMACENA EL VALOR DE PER QUE RECOGIO LOS DATOS ANTERIORMENTE.
                    
                    
                    System.out.print(key);
                    System.out.println();
                }
            }
        	break;
        case 5:
        	System.out.println("\n LISTADO DE NOMBRES DE ALUMNOS");

        	listadoAlumnosGrupo();
        	
        	break;
        default:
        	salirMenu=true;
        	opcion=0;
        	try {
        		FuncionesFicheros.GuardarDatosPersonas(lista);
        	}catch(IOException e) {
        		System.out.println("Error!! " +e.getMessage());
        	}
        	break;
        	}
        }while(!salirMenu|| opcion!=0);

    }

    public static void listadoBoletines () throws Exception {
        Scanner sc = new Scanner (System.in);
        String sCodGrupo = null,key, sNomGrupo = null;
        int nEvaluacion = 0;
        boolean correcto = false;
        Alumno alu = new Alumno(); 
        Iterator it;
        
        if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONAS está VACÍA.");
        else {
            do {
                try {
                    System.out.print("\n\nCurso: ");
                    sCodGrupo = sc.nextLine();
                    sCodGrupo = sCodGrupo.toUpperCase();
                
                    if (CentroEducativo.tmCC.containsKey(sCodGrupo) == true) {
                        sNomGrupo = CentroEducativo.tmCC.get(sCodGrupo);
                        System.out.println("Nombre Curso: " + sNomGrupo);
                        correcto = true;
                    }
                    else throw new Exception ("ERROR --> El Curso NO EXISTE.");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }while (correcto == false);    
        
            correcto=false;
            do {
                try {
                    System.out.print("Evaluación (1 - 5): ");
                    nEvaluacion = sc.nextInt();
                    System.out.println("");
                    if (nEvaluacion<0 || nEvaluacion>5) 
                        throw new Exception ("ERROR --> EVALUACIÓN INCORRECTA.");
                    correcto=true;
                }
                catch (InputMismatchException e) {
                    System.out.println ("ERROR --> Ha introducido Dígitos Incorrectos.");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }while (correcto == false);  
            System.out.println("----------------------------------------");
            
            it = lista.keySet().iterator();
            while (it.hasNext()) {
                key = (String) it.next();
                if (lista.get(key) instanceof Alumno) {
                    alu = (Alumno) (lista.get(key));
                    //System.out.println("ALU.CURSO:" + alu.getCurso() + " SCODGRUPO: " + sCodGrupo );
                    if (alu.getCurso().compareTo(sCodGrupo) == 0){
                        System.out.println(alu.boletinNotas(sCodGrupo, nEvaluacion));
                    }
                }
            }
            System.out.println("----------------------------------------");
        }
    }


public static void listadoAlumnosGrupo () throws Exception {
    Scanner sc = new Scanner(System.in);
    String sCodGrupo, sNomGrupo, key;
    Iterator it;
    Alumno alu = new Alumno();
    
    if (lista.isEmpty() == true) throw new Exception ("La LISTA de PERSONAS está VACÍA.");
    else {
        try {
            System.out.print ("Introduzca el Grupo: ");
            sCodGrupo = sc.nextLine();
            sCodGrupo = sCodGrupo.toUpperCase();
        
            if (tmCC.containsKey(sCodGrupo) == true) {
                sNomGrupo = tmCC.get(sCodGrupo);
                System.out.println("\n\n----------------------------------------");
                System.out.println("  Grupo: " + sCodGrupo + "  " + sNomGrupo);
                System.out.println("----------------------------------------");
                System.out.println("           LISTADO DE ALUMNOS ");
                System.out.println("----------------------------------------");
                System.out.println("");
                it = lista.keySet().iterator();
                while (it.hasNext()) {
                    key = (String) it.next();
                    if (lista.get(key) instanceof Alumno) {
                        alu = (Alumno) (lista.get(key));
                    
                        if (alu.getCurso().compareTo(sCodGrupo) == 0) {
                            System.out.println(key);
                        }
                    }
                }
                System.out.println("----------------------------------------");
            }
            else throw new Exception ("ERROR --> El GRUPO no EXISTE.");
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
    }
}
public static void introducirNotasAsignaturas() throws Exception{
    
}

}