package profesores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;

public class FuncionesFicheros{

	public static void GuardarDatosPersonas(TreeMap<String, Persona> lista) throws IOException {
			
			File fichero = new File("C:\\ProyectoCentro\\Personas3.txt");
			PrintWriter salida= null;
			String key;
			char separacion='#';
			char Profesor= 'P';
			char Alumno='A';
			Persona p;
			StringBuilder cadena= new StringBuilder();
			try {
				if(!fichero.exists())fichero.createNewFile();// SI EL FICHERO NO EXISTE LO CREAMOS.
				salida= new PrintWriter(fichero);
				
				if(! lista.isEmpty()) fichero.createNewFile();
				Iterator it= lista.keySet().iterator();
				
				while(it.hasNext()) {
					key=(String) it.next();
					p=(Persona)lista.get(key);
					if(p instanceof Alumno) {
						Alumno alum = (Alumno) p;
						//cadena.append(Character.toString(Alumno)+ separacion);
						cadena.append("A"+ separacion);
						cadena.append(alum.getNombre() + separacion);
	                    cadena.append(alum.getApellidos() + separacion);
	                    cadena.append(alum.getCalle()+ separacion);
	                    cadena.append(alum.getCodigoPostal()+ separacion);
	                    cadena.append(alum.getCiudad()+ separacion);
	                    cadena.append(alum.getDni()+ separacion);
	                    cadena.append(alum.getFechaNacimiento()+ separacion);
	                    cadena.append(alum.getCurso() + separacion);
	                    
	                    alum.getTmAsignaturasAlumno().entrySet().forEach((al) -> {
	                        //Realizamos un bucle para obtener las asignaturas y las evaluaciones del alumno
	                        String clave = al.getKey();
	                        Notas valor = al.getValue();
	                        cadena.append(clave + separacion);
	                        for (int i = 0; i < valor.getNotas().length; i++) {
	                            cadena.append(Integer.toString(valor.getNotas()[i]) + separacion); //Añadimos las calificaciones de cada evaluación
	                        }
	                    });
	                    
	                    cadena.append("\n"); //Salto de linea al final de la cadena  
						
					}else if(p instanceof Profesor) {
						Profesor profe = (Profesor) p;
						//cadena.append(Character.toString(Profesor)+ separacion);
						cadena.append("P"+ separacion);
						cadena.append(profe.getNombre() + separacion);
	                    cadena.append(profe.getApellidos() + separacion);
	                    cadena.append(profe.getCalle()+ separacion);
	                    cadena.append(profe.getCodigoPostal()+ separacion);
	                    cadena.append(profe.getCiudad()+ separacion);
	                    cadena.append(profe.getDni()+ separacion);
	                    cadena.append(profe.getFechaNacimiento()+ separacion);
	                    cadena.append(Double.toString(profe.getSueldoBase()) + separacion);
				
	                    //AHORA SACAMOS EL TOTAL DE HORAS EXTRAS POR MES
	                    for(int z=0; z<12;z++) {
	                    	String horas=(Integer.toString(profe.getHorasExtra(z)).isEmpty() ?"0" : Integer.toString(profe.getHorasExtra(z)));
	                    	cadena.append(horas + separacion);
	                    }
	                    cadena.append(Double.toString(profe.getTipoIRPF())+separacion);
	                    cadena.append(profe.getCuentaIBAN()+separacion);
	                    	
	                    Iterator tm= profe.getTmAsignaturas().keySet().iterator();
	                    
	                    while(tm.hasNext()) {
	                    	String codCurso= (String) tm.next();
	                    	String nomCurso = profe.getTmAsignaturas().get(codCurso);
	                    	cadena.append(codCurso + separacion);
	                    	cadena.append(nomCurso + separacion);
	                    
	                    }
	                    cadena.append("\n");
					}
				}
				salida.println(cadena.toString());
				salida.flush();
				
			}catch(FileNotFoundException e) {
				System.out.println("Error! Fichero no encontrado.");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				if(salida!= null) {
					salida.close();
				}
			}
		
		
	}
	
	
	public static TreeMap<String, Persona> obtenerTreeMapDeArchivo(File fichero) throws IOException {
		 TreeMap<String, Persona> lista = new TreeMap<>();
	        FileReader fr = null;
	        BufferedReader entrada = null;
	        String cadena, key;
	        String registro = "";
	        char tipo;
	        int indice = 0;
	        int indiceFinal, indiceInicial;
	        
	        try {
	            if (! fichero.exists()) {
	                throw new Exception("El fichero " + fichero.getName() + " no existe.");
	            }
	            
	            fr = new FileReader(fichero);
	            entrada = new BufferedReader(fr);
	            cadena = entrada.readLine();
	            
	            while(cadena != null){
	                indice = cadena.indexOf("#"); // obtiene la primera coincidencia
	                
	                if (indice != -1) {
	                    tipo = cadena.charAt(0); //  Obtiene el caracter que define  al objeto (Alumno|Profesor)
	                    switch (tipo) {
	                        case 'A':    //Alumno                        
	                            TreeMap<String,Notas> tmAsignaturasAlumno = new TreeMap<>();
	                            
	                            ArrayList<String> al = new ArrayList<>();
	                            
	                            Alumno alumno = new Alumno();
	                            
	                            indiceInicial = indice; // Obtenemos el indice de la primer almohadilla "#". De ahi partiremos para obtener los datos del alumno
	                            
	                            int contador = 1; //Cuenta la cantidad de almohadillas que contiene cada cadena
	                            
	                            while (indiceInicial != -1) {
	                                
	                                indiceInicial++; // Aumentamos en uno +1 el punto de partida para no leer el mismo resultado
	                                
	                                indiceFinal = cadena.indexOf("#", indiceInicial); //Obtenemos el indice de la siguiente almohadilla "#" en caso que exista
	                                
	                                if (indiceFinal != -1) {
	                                    registro = cadena.substring(indiceInicial, indiceFinal ); //Obtenemos los datos
	                                    al.add(registro);
	                                   contador++;
	                                    
	                                }
	                                
	                                indiceInicial = indiceFinal;                                
	                            }
	                            
	                            alumno.setNombre(al.get(0));
	                            alumno.setApellidos(al.get(1));
	                            alumno.setCalle(al.get(2));
	                            alumno.setCodigoPostal(al.get(3));
	                            alumno.setCiudad(al.get(4));
	                            alumno.setDni(al.get(5));
	                            alumno.setFechaNacimiento(al.get(6));
	                            alumno.setCurso(al.get(7)); // Indice del array se encuentra en el valor 7
	                            
	                            int nroAsignaturas = (contador - 9) / 6; // Con esta operación se obtiene el número de asignaturas matriculadas por el alumno
	                            //Se resta el número total de almohadillas "#" menos 9 (a partir de ahí es donde comienza el registro de la primera asignatura) dividido entre 6 que es el número de almohadillas "#" que ocupa cada asignatura con sus calificaciones
	                            // 1 asignatura + 5 calificaciones
	                            
	                            Notas notas = new Notas();
	                            int[] evaluaciones = new int[5];
	                            int indiceAsignaturas;
	                            
	                            for (int i = 0; i < nroAsignaturas; i++) { //
	                                
	                                indiceAsignaturas = 7 + (1 + i * 6); //El puntero del indice del array (al) parte de 7. Le sumamos uno para ubicarnos en la posición de la primera asignatura
	                                // y luego multiplicamos por 6 (el número de las asignaturas (i) + las posiciones de las 5 calificaciones que contiene cada asignatura) en cada iteración del ciclo del bucle
	                                //para irnos situando en la posición exacta de la siguiente asignatura.
	                                
	                                for (int j = 0; j < 5; j++) {
	                                    evaluaciones[j] = Integer.parseInt(al.get(indiceAsignaturas + j + 1));
	                                }
	                                notas.setNotas(evaluaciones);
	                                
	                                tmAsignaturasAlumno.put(al.get(indiceAsignaturas), notas);                                    
	                            }
	                            
	                            alumno.setTmAsignaturasAlumno(tmAsignaturasAlumno);
	                            key = alumno.getApellidos() + ", " + alumno.getNombre(); // Creamos la clave de referencia del treeMap                            
	                            lista.put(key, alumno);//Lo añadimos el objeto alumno al treeMap
	          
	                            break;
	                            
	                        case 'P'://Profesores
	                            Profesor profesor = new Profesor();
	                            TreeMap<String,String> tmAsignaturasProfesor = new TreeMap<>();
	                            ArrayList<String> pro = new ArrayList<>();
	                            
	                            indiceInicial = indice; // Obtenemos el indice de la primer almohadilla "#". De ahi partiremos para obtener los datos del alumno
	                            
	                            contador = 1; //Cuenta la cantidad de almohadillas que contiene cada cadena
	                            
	                            while (indiceInicial != -1) {
	                                
	                                indiceInicial++; // Aumentamos en uno +1 el punto de partida para no leer el mismo resultado
	                                
	                                indiceFinal = cadena.indexOf("#", indiceInicial); //Obtenemos el indice de la siguiente almohadilla "#" en caso que exista
	                                
	                                if (indiceFinal != -1) {
	                                    registro = cadena.substring(indiceInicial, indiceFinal ); //Obtenemos los datos
	                                    pro.add(registro);
	                                   contador++;
	                                    
	                                }
	                                
	                                indiceInicial = indiceFinal;                                
	                            }
	                            
	                            profesor.setNombre(pro.get(0));
	                            profesor.setApellidos(pro.get(1));
	                            profesor.setCalle(pro.get(2));
	                            profesor.setCodigoPostal(pro.get(3));
	                            profesor.setCiudad(pro.get(4));
	                            profesor.setDni(pro.get(5));
	                            profesor.setFechaNacimiento(pro.get(6));
	                            profesor.setSueldoBase(Double.valueOf(pro.get(7))); // Indice del array se encuentra en el valor 7
	                            
	                            for (int i = 0; i < 12; i++) {  
	                                 profesor.setHorasExtra(i , Integer.valueOf(pro.get(7 + 1 + i))); // Establecemos las horas extras de los 12 meses del año
	                            }
	                            
	                            profesor.setTipoIRPF(Double.valueOf(pro.get(20)));
	                            profesor.setCuentaIBAN(pro.get(21));
	                            
	                            //Comprobamos que existen más datos en el array, lo que significa que el profesor tiene asignada X asignaturas
	                            if ( (pro.size() - 21) > 0) {
	                                
	                                String codCurso = null;
	                                String nombreCurso = null;
	                                
	                                for (int i = 22; i < pro.size(); i++) {
	                                    if (i % 2 == 0) { // los indices pares indican el codigo del curso y los impares el nombre
	                                        codCurso = pro.get(i);
	                                    }else{
	                                        nombreCurso = pro.get(i);
	                                    }
	                                    tmAsignaturasProfesor.put(codCurso, nombreCurso);
	                                }
	                            }
	                            
	                            profesor.setTmAsignaturas(tmAsignaturasProfesor);
	                            key = profesor.getApellidos() + ", " + profesor.getNombre(); // Creamos la clave de referencia del treeMap
	                            lista.put(key, profesor); //Lo añadimos al treeMap
	                            break;
	                            
	                        default:
	                            break;
	                    }
	                }
	                cadena = entrada.readLine();
	            }
	                           
	        } catch (FileNotFoundException fnf) {
	            System.out.println(fnf.getMessage());
	        } catch (Exception e) {
	            System.out.println("Se ha producido un error: " + e.getMessage());
	        } finally {
	            try {
	                if (fr != null) {
	                    fr.close();
	                }
	            } catch (IOException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        return lista;
    }
	
	/*
	public static void obtenerTreeMapDeArchivo(File fichero)throws IOException{
		String linea;
		String key = "";
		String tipo,nombre,apellidos,calle,codigoPostal,ciudad,dni,fechaNacimiento,cuentaIBAN,curso;
		
		try {
		FileReader f = new FileReader(fichero);
		BufferedReader fr= new BufferedReader(f);
		linea=fr.readLine();
		for(int i=0; linea!= null;i++) {
			String contenido[] =linea.split("#");
			
			tipo=contenido[0];
			nombre=contenido[1];
			apellidos=contenido[2];
			calle=contenido[3];
			codigoPostal=contenido[4];
			ciudad=contenido[5];
			dni=contenido[6];
			fechaNacimiento=contenido[7];
			
			System.out.println(contenido[1]);
			System.out.println(contenido[2]);
			System.out.println(contenido[3]);
			System.out.println(contenido[4]);
			System.out.println(contenido[5]);
			System.out.println(contenido[6]);
			System.out.println(contenido[7]);
			System.out.println(contenido[8]);
			System.out.println(contenido[21]);
			System.out.println(contenido[22]);
			
			
			if(tipo.equals("P")) {// SE TRATA DE UN PROFESOR
				Profesor p= new Profesor();
				int[] horasExtras = new int[12];
				int horasExtra = 0;
				double sueldoBase,tipoIRPF;
				
				p.setNombre(nombre);
				p.setApellidos(apellidos);
				p.setCalle(calle);
				p.setCodigoPostal(codigoPostal);
				p.setCiudad(ciudad);
				sueldoBase= Double.parseDouble(contenido[8]);
				p.setSueldoBase(sueldoBase);
				for(int z=0;z<12;z++) {					
					p.setHorasExtra(z,Integer.parseInt(contenido[9+z]) );
				}
				
				tipoIRPF=Double.parseDouble(contenido[21]);
				p.setTipoIRPF(tipoIRPF);
				cuentaIBAN=contenido[22];
				p.setCuentaIBAN(cuentaIBAN);
				
				key= p.getApellidos()+", "+p.getNombre();
				CentroEducativo.lista.put(key,p);
				
			}else if(tipo.equals("A")) {
				Alumno a= new Alumno();
				a.setNombre(nombre);
				a.setApellidos(apellidos);
				a.setCalle(calle);
				a.setCodigoPostal(codigoPostal);
				a.setCiudad(ciudad);
				curso=contenido[8];
				a.setCurso(curso);
				
				key=a.getApellidos()+", "+ a.getNombre();
				CentroEducativo.lista.put(key,a);
			}

			
			
			linea=fr.readLine();
		
		}
		
		f.close();
		fr.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error! Fichero no encontrado.");
		}catch(StringIndexOutOfBoundsException e) {
			System.out.println("Error! Indice fuera de rango.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//return lista;
	}*/
	
}
