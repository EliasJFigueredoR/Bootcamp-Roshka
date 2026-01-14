import java.util.Scanner;

public class Ejercicio09 {

    private String contrasena = "Paraguas";

    public void contrasena ()
    {
        Scanner scanner = new Scanner(System.in);
        String palabraUsuario;
        System.out.println("Tienes 3 intentos (Paraguas)");
        System.out.print("Ingrese la contraseña:");


        for(int i = 0; i < 3; i++)
        {
            palabraUsuario = scanner.next();
            if (contrasena.equals(palabraUsuario))
            {
                System.out.println("Correcto!");
            }
            else if( i == 2)
            {
                System.out.print("fallaste jaja");
            }
            else
            {
                System.out.println("Incorrecto, te quedan " + (2 - i) + " intentos");
                System.out.print("Intentelo de nuevo:");
            }
        }
    }
}
