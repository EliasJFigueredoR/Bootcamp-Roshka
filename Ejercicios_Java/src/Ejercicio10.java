import java.util.Scanner;

public class Ejercicio10 {

    public void diasSemana(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un dia de la semana: ");
        String dia = scanner.next();

        switch(dia)
        {
            case "Lunes":
                System.out.println("Dia Laboral");
                break;
            case "Martes":
                System.out.println("Dia Laboral");
                break;
            case "Miercoles":
                System.out.println("Dia Laboral");
                break;
            case "Jueves":
                System.out.println("Dia Laboral");
                break;
            case "Viernes":
                System.out.println("Dia Laboral");
                break;
            case "Sabado":
                System.out.println("Dia no Laboral");
                break;
            case "Domingo":
                System.out.println("Dia no Laboral");
                break;
            default:
                System.out.println("Introduce un dia valido");
                break;
        }

    }

}
