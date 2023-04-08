package pruebas;
import programas.Programa1;

public class pruebaPrograma1 {
    public static void main(String[] args) {
        Programa1 prueba1 = new Programa1();
        String salida = prueba1.operadorDeClase("D:\\INSOPORTABLE\\Java\\ProcesoDeSofwarePersonal\\src\\pruebas\\programa1_prueba1.java");
        System.out.println(salida);

        Programa1 prueba2 = new Programa1();
        String salida2 = prueba2.operadorDeClase("D:\\INSOPORTABLE\\Java\\ProcesoDeSofwarePersonal\\src\\pruebas\\programa1_prueba2.java");
        System.out.println(salida2);
    }
}
