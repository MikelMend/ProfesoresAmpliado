package profesores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class TablasCursos {
    static void cargaCursos(TreeMap<String, String> tmCC){
    	FileReader file = null;
        String sDatos,sCodCurso = null, sDesCurso;
        
        try(BufferedReader lectura = new BufferedReader(file = new FileReader("C:\\Users\\MikelPort\\Desktop\\ProyectoCentro\\cursos.txt"))) {
        	
            sDatos = lectura.readLine();
            while (sDatos != null) {
                if (sDatos != null) sDatos = sDatos.toUpperCase();
                
                sCodCurso = sDatos.substring(0, 2);
                sDesCurso = sDatos.substring(2, sDatos.length());
                
                tmCC.put(sCodCurso,sDesCurso);
                
               System.out.println(sCodCurso + " " + sDesCurso);
                
                sDatos = lectura.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void cargaCursosAsignaturas(TreeMap<String, String> tmCCASIGNA){
    	 FileReader file = null;
         String cadena;
         
         
         try(BufferedReader lectura = new BufferedReader(file = new FileReader("C:\\Users\\MikelPort\\Desktop\\ProyectoCentro\\cursosAsignaturas.txt"))) {
             cadena= lectura.readLine();
        	 for(int i = 0; cadena!=null; i++) {
        		 String []datos;
        		 datos= cadena.split(",");
        		 System.out.println(datos[0]+" "+datos[1]);
        		 tmCCASIGNA.put(datos[0],datos[1]);
        		 cadena= lectura.readLine();
        	 }
         
   
    } catch (FileNotFoundException e) {
		System.out.println("No se ha encontrado el fichero.");
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
    }
    /*public static void pedirCurso() {
    	String curso="";
    	Scanner teclado= new Scanner(System.in);
    	System.out.println("Introduce un curso");
    	curso= teclado.nextLine();
    	String cadena="";
    	File ruta= new File("C:\\Users\\MikelPort\\Desktop\\ProyectoCentro\\centro.txt");
    	
    	try {
    		Scanner sc= new Scanner(ruta);
    		while(sc.hasNext()) {
    			
    			cadena= sc.nextLine();
    			String	[] descripcion =cadena.split(",");
    			
    			if(descripcion[0]== curso) {
    			System.out.println("El curso indicado es:" +descripcion[1]);
    			}
    			
    		}
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no exite.");
			e.printStackTrace();
		}
    }

	 */
 }

