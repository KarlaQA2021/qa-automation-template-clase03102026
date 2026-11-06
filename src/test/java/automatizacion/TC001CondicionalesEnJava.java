package automatizacion;

public class TC001CondicionalesEnJava {
    
    public static void main(String[] args){

        //Declare Variables
        String nombre = "Karla Ferman";
        int edad = 38;
        boolean estudiante = true;

        //Mostrar variables en consola
        System.out.println("===== DATOS DE LAS VARIABLES =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Es estudiante de este curso: " + estudiante);

        //Condicionales IF - ELSE
         System.out.println("\n===== EJEMPLOS CONDICIONALES =====");

         if (edad >= 18) {
            System.out.println(nombre + " es mayor de edad ");
         } else {
            System.out.println(nombre + " es menor de edad ");
         }

         //Ejemplo numero 2
         if(estudiante){
            System.out.println(nombre + " Si es estudiante de este curso ");
         }
         else {
            System.out.println(nombre + " No es estudiante de este curso ");
         }

         //Ejemplo ciclo FOR
          System.out.println("\n===== EJEMPLO CICLO FOR Y WHILE =====");
         for(int i = 1; i <= 5; i++){
             System.out.println("Iteracion FOR numero: " + i);
         }

         //Ejemplo ciclo while
         System.out.println("\n===== EJEMPLO CICLO WHILE =====");
         int contador = 1;
         while (contador <= 5){
            System.out.println("Iteracion HILE numero; " + contador);
            contador++;
         }

         System.out.println("\\n=====FINALIZAN LOS EJEMPLOS JAVA =====");
    }
}
