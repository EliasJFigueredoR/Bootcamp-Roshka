//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int NroEjercicio;

        do {
            //Mostrar el Menú
            System.out.println("\n----Menu----- ");
            System.out.println("0. Salir");
            System.out.println("1. Ejercicio 1");
            System.out.println("2. Ejercicio 2");
            System.out.println("3. Ejercicio 3");
            System.out.println("4. Ejercicio 4");
            System.out.println("4. Ejercicio 5");
            System.out.print("Seleccione una opción: ");
            NroEjercicio = scanner.nextInt();

            switch (NroEjercicio) {
                case 0:
                    System.out.println("\nSaliendo....");
                    break;
                case 1:
                    //Ejercicio 01
                    System.out.println("\n---Ejercicio 1---");
                    Ejercicio01 Ej1 = new Ejercicio01();

                    System.out.println("Resta: " + Ej1.restar(3, 4));
                    System.out.println("Suma: " + Ej1.sumar(3, 4));
                    System.out.println("Multiplicación: " + Ej1.multiplicar(3, 4));
                    System.out.println("División: " + Ej1.dividir(3, 4));
                    System.out.println("Resto: " + Ej1.resto(3, 4));
                    break;
                case 2:
                    //Ejercicio 02
                    System.out.println("\n---Ejercicio 2---");
                    Ejercicio02 Ej2 = new Ejercicio02();
                    Ej2.comparar(3, 4);
                    Ej2.comparar(4, 3);
                    Ej2.comparar(4, 4);
                    break;
                case 3:
                    //Ejercicio 03
                    System.out.println("\n---Ejercicio 3---");
                    Ejercicio03 Ej3 = new Ejercicio03();
                    Ej3.bienvenida();
                    break;
                case 4:
                    //Ejercicio 04
                    System.out.println("\n---Ejercicio 4---");
                    Ejercicio04 Ej4 = new Ejercicio04();
                    Ej4.Bienvenida();
                    break;
                case 5:
                    //Ejercicio 05
                    System.out.println("\n---Ejercicio 5---");
                    Ejercicio05 Ej5 = new Ejercicio05();
                    Ej5.NumeroPar();
                    break;
                default:
                    System.out.println("\nColoca una opción valida");
            }
        }while(NroEjercicio != 0 );
    }


}