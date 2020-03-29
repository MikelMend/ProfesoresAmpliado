package profesores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TablasCursos {
    static void cargaCursos(TreeMap<String, String> tmCC){
    	FileReader file = null;
        String cadena;
        
        try(BufferedReader lectura = new BufferedReader(file = new FileReader("C:\\ProyectoCentro\\cursos.txt"))) {
        	
            cadena = lectura.readLine();
            for(int i= 0; cadena!= null; i++) {
            	String[] datos = cadena.split(",");
            	tmCC.put(datos[0], datos[1]);
            	System.out.println(datos[0]+ datos[1]);// Prueba de lectura
            	cadena= lectura.readLine();
            
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero.");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void cargaCursosAsignaturas(TreeMap<String, String> tmCCASIGNA){
    	 FileReader file = null;
         String cadena;
         
         
         try(BufferedReader lectura = new BufferedReader(file = new FileReader("C:\\ProyectoCentro\\cursosAsignaturas.txt"))) {
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
    
    

    public static void cargaGlobales() throws IOException{
    	File cursoRuta = new File("C:\\ProyectoCentro");
        File cursoFichero = new File(cursoRuta, "datosGlobales.txt");
        
        if (! cursoFichero.exists()) {

            System.out.println("El fichero " + cursoFichero.getAbsolutePath() + " no existe.");

            if (!cursoRuta.exists()) {
                System.out.println("El directorio " + cursoRuta.getAbsolutePath() + " no existe.");

                if (cursoRuta.mkdir()) {
                    System.out.println("Se ha creado el directorio " + cursoRuta.getAbsolutePath());

                    if (cursoFichero.createNewFile()) {
                        System.out.println("Se ha creado el fichero " + cursoFichero.getName());
                    } else {
                        throw new IOException("No se ha podido crear el fichero " + cursoFichero.getName());
                    }

                } else {
                    throw new IOException("No se ha podido crear la ruta " + cursoRuta.getAbsolutePath());
                }
            } else {

                if (cursoFichero.createNewFile()) {
                    System.out.println("Se ha creado el fichero " + cursoFichero.getName());
                } else {
                    throw new IOException("No se ha podido crear el fichero " + cursoFichero.getName());
                }

            }
        }
        Scanner sc = new Scanner(System.in);
        RandomAccessFile fichero = null;
        String curso;
        String importeHorasExtras;
        String cadena;
        
        try {          
            fichero = new RandomAccessFile(cursoFichero, "rw");
            
            if (fichero.length() == 0) {
                
                boolean correcto = true;
                
                do {    
                        System.out.println("\n\tAún no se ha establecido los datos del curso y del importe de las horas extras");
                        System.out.println("\nIndique el curso académico actual: ");
                        curso = sc.nextLine();
                        System.out.println("Indique el precio de importe de las horas extras:");
                        importeHorasExtras = sc.nextLine();                        
                        
                    try {                        
                        if((curso.trim().isEmpty()) || importeHorasExtras.trim().isEmpty()) throw new Exception("Debe introducir los valores.");
                        if (! curso.contains("/")) throw new Exception("Debe introducir correctamente el formato del curso academico: Año/Año");
                        
                        if (importeHorasExtras.contains(",")) { // Si contiene una coma "," la reemplazamos por un punto "."
                            
                            importeHorasExtras = importeHorasExtras.replace(",", ".");
                        }
                        
                        cadena = curso + "#" + importeHorasExtras + "\n";
                        fichero.writeBytes(cadena);
                        CentroEducativo.setCurso(curso); //Establecemos el curso en la variable global
                        double importe = Double.parseDouble(importeHorasExtras); // Convertimos el String en double
                        CentroEducativo.setPagoPorHoraExtra(importe); //Establecemos el importe de las horas extras en la variable global
                        System.out.println("Los datos se han registrado correctamente. ");
                        correcto = true;
                        System.out.println();
                        
                    }catch (IOException ioe) {
                        System.out.println("Ha ocurrido un error de fichero: " + ioe.getMessage());
                        correcto = false;                    
                    } catch (Exception e) {
                        System.out.println("Ha ocurrido un error: " + e.getMessage());
                        correcto = false;
                    }
                    
                } while (! correcto);

            }else{
                
                fichero.seek(0); // Llevamos el puntero al inicio del documento
                cadena = fichero.readLine(); // Obtenemos la lectura de la cadena
                
                if (cadena.length() > 0) { //Se comprueba que existe datos en la cadena
                    int indice = cadena.indexOf("#"); //Se obtiene el indice de la almohadilla "#" que divide las dos variables Curso e importeHorasExtra
                    CentroEducativo.setCurso(cadena.substring(0, indice)); //Obtenemos la cadena del curso y establecemos la variable global 
                    Double iHorasExtras = Double.parseDouble(cadena.substring(indice + 1, cadena.length())); //Obtenemos la cadena del importe de horas extras y lo convertimos en double
                    CentroEducativo.setPagoPorHoraExtra(iHorasExtras);
                }
            }

        }catch(IOException ioe){
            System.out.println("Error: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurido un error: " + e.getMessage());
        }finally{
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException ex) {
                    Logger.getLogger(TablasCursos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
 }

