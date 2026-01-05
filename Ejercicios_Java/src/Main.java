//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Ejercicio 01
        Ejercicio01 Ej1 = new Ejercicio01();

        System.out.println("Resta de 3 - 4 = " + Ej1.restar(3,4));
        System.out.println("Suma de 3 + 4 = " + Ej1.sumar(3,4));
        System.out.println("Multiplicación de 3 * 4 = " + Ej1.multiplicar(3,4));
        System.out.println("División de 3 / 4 = " + Ej1.dividir(3,4));
        System.out.println("Resto de 3 % 4 = " + Ej1.resto(3,4));

        //Ejercicio 02
        Ejercicio02 Ej2 = new Ejercicio02();
        Ej2.comparar(3,4);
        Ej2.comparar(4,3);
        Ej2.comparar(4,4);


    }
}