import java.util.Scanner;

public class Ejercicio10 {

    public void diasSemana(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un dia de la semana: ");
        String dia = scanner.next();

        switch(dia)
        {
            case "Lunes", "Martes", "Miercoles", "Jueves", "Viernes":
                System.out.println("Dia Laboral");
                break;
            case "Sabado", "Domingo":
                System.out.println("Dia no Laboral");
                break;
            default:
                System.out.println("Introduce un dia valido");
                break;
        }
    }
}
