import java.util.Scanner;

public class Ejercicio05 {
    public void NumeroPar()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un Numero:");
        int Numero = scanner.nextInt();
        if (Numero % 2 == 0)
        {
            System.out.println("Es par");
        } else {
            System.out.println("Es impar");
        }
    }

}
