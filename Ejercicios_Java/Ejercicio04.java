import  java.util.Scanner;

public class Ejercicio04 {

    public void Bienvenida(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa tu nombre: ");
        String Nombre = scanner.next();
        System.out.println("Bienvenido " + Nombre);
    }

}
