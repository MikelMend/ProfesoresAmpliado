package profesores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
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
	
	public static void obtenerTreeMapDeArchivo(File fichero)throws IOException{
		String linea;
		double sueldoBase,tipoIRPF;
		int[] horasExtras = new int[12];
		int horasExtra = 0;
		String key = "";
		Profesor p= new Profesor();
		Alumno a= new Alumno();
		String tipo,nombre,apellidos,calle,codigoPostal,ciudad,dni,fechaNacimiento,cuentaIBAN,curso;
		
		try {
		FileReader f = new FileReader(fichero);
		BufferedReader fr= new BufferedReader(f);
		linea=fr.readLine();
		for(int i=0; linea!= null;i++) {
			String contenido [];
			contenido=linea.split("#");
			tipo=contenido[0];
			if(tipo.equals("P")) {// SE TRATA DE UN PROFESOR
				
				nombre=contenido[1];
				apellidos=contenido[2];
				calle=contenido[3];
				codigoPostal=contenido[4];
				ciudad=contenido[5];
				dni=contenido[6];
				fechaNacimiento=contenido[7];
				p.setNombre(nombre);
				p.setApellidos(apellidos);
				p.setCalle(calle);
				p.setCodigoPostal(codigoPostal);
				p.setCiudad(ciudad);
				sueldoBase= Double.parseDouble(contenido[8]);
				p.setSueldoBase(sueldoBase);
				for(int z=0;z<12;z++) {
				
					horasExtras[z]=Integer.parseInt(contenido[9+z]);
					System.out.println(contenido[9+z]);
					p.setHorasExtra(z+1,horasExtra);
				}
				
				tipoIRPF=Double.parseDouble(contenido[21]);
				p.setTipoIRPF(tipoIRPF);
				cuentaIBAN=contenido[22];
				p.setCuentaIBAN(cuentaIBAN);
				
				key= p.getApellidos()+", "+p.getNombre();
				CentroEducativo.lista.put(key,p);
				
				
			}else {
				
				nombre=contenido[1];
				apellidos=contenido[2];
				calle=contenido[3];
				codigoPostal=contenido[4];
				ciudad=contenido[5];
				dni=contenido[6];
				fechaNacimiento=contenido[7];
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
		}
		
	}
	
}
