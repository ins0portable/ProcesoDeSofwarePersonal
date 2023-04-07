package programas;
import utiles.Encabezado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;

public class Programa1{
    //TODO: Comentar las secciones logicas del codigo
    private Encabezado encabezado = new Encabezado();
    private String RutaAbsoluta;

    private boolean analizarEncabezado(String rutaAbsoluta){
        File archivo = new File(rutaAbsoluta);
        String programname;
        String devname;
        String date;
        String description;
        try {
            // Se crea un objeto Scanner para leer el archivo
            Scanner lector = new Scanner(archivo);

            // Se lee linea por linea el archivo
            while (lector.hasNextLine()){
                String linea = lector.nextLine();
                if (linea.contains("/* Program Name:")){
                    encabezado.setProgramName(linea);
                } else if (linea.contains("/* Dev Name:")){
                    encabezado.setDevName(linea);
                } else if(linea.contains("/* Date: ")){
                    encabezado.setDate(linea);
                } else if (linea.contains("/* Description: ")){
                    encabezado.setDescription(linea);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        programname = encabezado.getProgramName();
        devname = encabezado.getDevName();
        date = encabezado.getDate();
        description = encabezado.getDescription();
        if(programname != "" && devname != "" &&
            date != "" && description != "" ){
                return true;
            } else {
                return false;
            }
    }

    private String indicarCamposFaltantes(){
        StringBuilder camposFaltantes = new StringBuilder();
        String programname = encabezado.getProgramName();
        String devname = encabezado.getDevName();
        String date = encabezado.getDate();
        String description = encabezado.getDescription();

        if(programname == ""){
           camposFaltantes.append("Falta el nombre del programa.\n");
        } if (devname == ""){
            camposFaltantes.append("Falta el nombre del programadoe.\n");
        } if (date == ""){
            camposFaltantes.append("Falta la fecha de desarrollo.\n");
        } if (description == ""){
            camposFaltantes.append("Falta la descripcion del programa.\n");
        }
        return camposFaltantes.toString();
    }

    private void solicitarCamposFaltantes(){
        Scanner lectura = new Scanner(System.in);
        String programname = encabezado.getProgramName();
        String devname = encabezado.getDevName();
        String date = encabezado.getDate();
        String description = encabezado.getDescription();

        if(programname == ""){
          System.out.println("Introduce el nombre del programa: ");
          programname = lectura.nextLine();
          String nombrePrograma = "/*Program Name: ";
          nombrePrograma += programname + "*/";
          encabezado.setProgramName(nombrePrograma);
        } if (devname == ""){
            System.out.println("Introduce el nombre del programador: ");
            devname = lectura.nextLine();
            String developerName = "/*Developer Name: ";
            developerName += devname + "*/";
            encabezado.setDevName(developerName);
        } if (date == ""){
            System.out.println("Introduce la fecha de dearrollo: ");
            date = lectura.nextLine();
            String fecha = "/*Date: " + date + "*/";
            encabezado.setDate(fecha);
        } if (description == ""){
            System.out.println("Introduce una decripcion para el programa: ");
            description = lectura.nextLine();
            String descripcion = "/*Description: "+description+"*/";
            encabezado.setDescription(descripcion);
        }

    }

    private String completarEncabezado(){
        StringBuilder encabezadoCorregido = new StringBuilder();
        encabezadoCorregido.append("/*************************************************************************************/\n");
        encabezadoCorregido.append(encabezado.getProgramName());
        encabezadoCorregido.append(encabezado.getDevName());
        encabezadoCorregido.append(encabezado.getDate());
        encabezadoCorregido.append(encabezado.getDescription());
        encabezadoCorregido.append("/*************************************************************************************/\n");
        return encabezadoCorregido.toString();
    }

    private String obtenerContenido(String rutaArchivo){
        File archivo = new File(rutaArchivo);
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            //Se omite el encabezado
            for(int i =0; i <6; i++){
                br.readLine();
            }

            //Se obtiene el contenido restante
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = br.readLine()) != "" ){
                contenido.append(linea+"\n");
            }

            // se cierra el bufferedReader y fileReader
            br.close();
            fr.close();
            return contenido.toString();
        } catch (Exception e) {
           e.printStackTrace();
           return "";
        }
    }

    private String archivoRev(String encabezadoCorregido, String contenido){
        StringBuilder archivoRevisado = new StringBuilder();
        archivoRevisado.append(encabezadoCorregido);
        archivoRevisado.append(contenido);

        int indice = RutaAbsoluta.indexOf(".java");
        String nombreParcial = RutaAbsoluta.substring(0,indice);
        nombreParcial += "_rev.java";
        
        // Se crea un objeto de tipo File
        File archivo_rev = new File(nombreParcial);
        try {
            if(archivo_rev.createNewFile()){
                // El archivo se creo con exito
                FileWriter escritor = new FileWriter(archivo_rev);
                escritor.write(archivoRevisado.toString());
                escritor.close();
                return("Archivo creado con exito.\n"+nombreParcial+"\n");
            } else {
                return("El archivo ya existe.\n");
            }
        } catch (Exception e) {
            // Ocurrio un error en la creacion
           e.printStackTrace();
        }
        return("Error durante la creacion.\n");
    }

    public String operadorDeClase(String rutaAbsoluta){
        // Se agrega la ruta absoluta dada por el usuario.
        RutaAbsoluta = rutaAbsoluta;
        // Se analiza el encabezado
        boolean analisisEncabezado = analizarEncabezado(rutaAbsoluta);

        if(analisisEncabezado == false){
            String camposFaltantes = indicarCamposFaltantes();
            System.out.println(camposFaltantes);
            solicitarCamposFaltantes();
            String encabezadoFull = completarEncabezado();
            String contenido = obtenerContenido(rutaAbsoluta);
            String archivoRev = archivoRev(encabezadoFull, contenido);
            return(archivoRev);
        } else {
            return("Encabezado completo.\n");
        }
    }
}