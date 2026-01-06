import java.util.Scanner;

public class Ejercicio06 {

    private final double IVA = 0.1;

    public void calcularPrecio()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el precio de un producto:");
        double precioProducto = scanner.nextDouble();
        double precioFinal = (precioProducto * IVA) + precioProducto;
        System.out.println("El precio final es: " + precioFinal);
    }

}
