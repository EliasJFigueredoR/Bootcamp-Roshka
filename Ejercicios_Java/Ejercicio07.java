public class Ejercicio07
{
    public void mostrarNumeros ()
    {
        System.out.println("Numeros divisibles entre 2 y 3");
        for(int i = 1; i <= 100; i++)
        {
            if (i % 3 == 0 && i % 2 == 0)
            {
                System.out.println(i);
            }
        }
    }
}
