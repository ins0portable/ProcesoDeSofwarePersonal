package utiles;

/**
 * Esta clase ha sido creada para ser utilizada dentro de los programas PSP.
 * Contiene los 4 atributos que debe poseer un encabezado.
 * @date 02/Abril/2023
 * @author Insoportable
 */
public class Encabezado {
    // Atributos
    /**
     * Nombre del programa.
     */
    private String ProgramName;

    /**
     * Nombre del desarrollador.
     */
    private String DevName;

    /**
     * Fecha de dearrollo del programa.
     */
    private String Date;

    /**
     * Descripcion del programa.
     */
    private String Description;


    /**
     * Constructor vacio
     * @return devuelve un objeto de tipo Encabezado vacio.
     */
    public Encabezado() {
        ProgramName = null;
        DevName = null;
        Date = null;
        Description = null; 
    }


    /**
     * Getters y Setters
     */
    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public String getDevName() {
        return DevName;
    }
    
    public void setDevName(String devName) {
        DevName = devName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
