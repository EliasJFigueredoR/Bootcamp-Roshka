import java.util.Scanner;

public class Ejercicio08 {
    public void numeroPostivo()
    {
        Scanner scanner = new Scanner(System.in);
        double numero;

        System.out.print("Ingrese un numero mayor o igual a 0: ");
        do{
            numero = scanner.nextDouble();
            if(numero >= 0)
            {
                System.out.println("Genial, ese es un mayor o igual a 0");
            }
            else
            {
                System.out.print("Numero no valido, vuelve a intentarlo: ");
            }

        } while(numero<0);
    }
}
