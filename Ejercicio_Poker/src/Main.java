//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {

        Mazo.imprimirProbabilidades();
        Mazo mazoCartas = new Mazo();
        Carta[] cartasRepartidas;
        cartasRepartidas = mazoCartas.repartirCartas();

        System.out.println("\n####Juemos Poker... >:D####");
        System.out.println("----Cartas Repartidas:-----");

        for(Carta carta: cartasRepartidas)
        {
            System.out.println(carta);
        }

        System.out.println("");
        mazoCartas.EncontrarJugadas(cartasRepartidas);

        long contador = 0;
        for(int i = 0 ; i < 10000000 ; i++){
            long inicio = System.nanoTime();

            Mazo mazoC = new Mazo();
            Carta[] cartasRepartida;
            cartasRepartida = mazoC.repartirCartas();
            long fin = System.nanoTime();

            long duracionNano = fin - inicio;
            contador = contador + duracionNano;
        }

        long duracionNanoProm = contador / 10000000 ;
        System.out.println("Duración en nanosegundos: " + duracionNanoProm);


    }
}