import java.util.Random;

public class Mazo {
    private char[] valoresChar = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
    private char[] palos = {'S','C','H','D'};

    public Carta[] crearMazo ()
    {
        Carta[] mazo = new Carta[52];
        int contador = 0;
        int contadorValor = 0;
        for(char valorChar: valoresChar)
        {
            for(char palo: palos)
            {
                mazo[contador]= new Carta(valorChar, palo, contadorValor);
                contador++;
            }
            contadorValor++;
        }
        return mazo;
    }

    public Carta[] repartirCartas()
    {
        Random random = new Random();

        Carta[] mazo = crearMazo();
        Carta[] cartasRepartidas = new Carta[5];

        int posicion;
        int[] ContadorPosicion = {0,0,0,0,0};
        int i = 0;

        boolean bandera;

        while(i<5)
        {
            bandera = true;
            posicion = random.nextInt(52);

            for (int num : ContadorPosicion)
            {
                if (num == posicion)
                {
                    bandera = false;
                }
            }

            if (!bandera) {continue;}

            ContadorPosicion[i] = posicion;
            cartasRepartidas[i] = mazo[posicion];
            i++;
        }

        return cartasRepartidas;
    }

    public Carta[] ordenarValor(Carta[] mano) {
        int n = mano.length;
        Carta temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mano[j].getValorN() > mano[j + 1].getValorN()) {
                    temp = mano[j];
                    mano[j] = mano[j + 1];
                    mano[j + 1] = temp;
                }
            }
        }
        return mano;
    }

    public boolean Escalera(Carta[] mano) {
        int n = mano.length;
        boolean var = true;

        for (int i = 0; i < n - 2; i++) {
           if (mano[i].getValorN() - mano[i+1].getValorN() != 1 && mano[i].getValorN() - mano[i+1].getValorN() != 10)
           {
               var = false;
           }
        }
        return var ;
    }


    public int[] contarFrecuenciaValores(Carta[] mano) {
        int[] cantValores = new int[13];

        for (Carta carta : mano) {
            cantValores[carta.getValorN()-1]++;
        }

        return cantValores;
    }


    public int[] contarFrecuenciaPalos(Carta[] mano) {
        int[] cantChar = new int[4];

        for (Carta carta : mano) {
            char paloCarta = carta.getPalo();


            for (int i = 0; i < palos.length; i++) {
                if (palos[i] == paloCarta) {
                    cantChar[i]++;
                    break;
                }
            }
        }

        return cantChar;
    }

    public void EncontrarJugadas(Carta[] mano) {
        ordenarValor(mano);
        String[] jugadas = {"Poker", "Color", "Escalera", "Trio", "Par"};
        int[] jugadasP = {0,0,0,0,0};

        int [] cantValores = contarFrecuenciaValores(mano);
        int [] cantChar= contarFrecuenciaPalos(mano);

        for (int i = 0; i < cantValores.length - 1; i++) {
            if(cantValores[i]==2)
            {
                jugadasP[4]++;
                continue;
            }
            if(cantValores[i]==3)
            {
                jugadasP[3]++;
                continue;
            }
            if (cantValores[i] == 4) {
                jugadasP[1]++;
                System.out.println("La jugda que puede hacer es:");
                System.out.println(jugadas[0]);
                return;
            }
        }

        if (jugadasP[4] >= 1)
        {
            if(jugadasP[3]==1)
            {
                System.out.println("La jugda que puede hacer son:");
                System.out.println(jugadas[3]);
                System.out.println(jugadas[4]);
                System.out.println("Full");
                return;
            }
            if(jugadasP[4]==2)
            {
                System.out.println("La jugda que puede hacer son:");
                System.out.println(jugadas[4]);
                System.out.println("Doble par");
                return;
            }
            System.out.println("La jugda que puede hacer es:");
            System.out.println(jugadas[4]);
            return;

        }
        if(jugadasP[3]==1)
        {
            System.out.println("La jugda que puede hacer es:");
            System.out.println(jugadas[3]);
            return;
        }

        if (Escalera(mano))
        {
            jugadasP[2]++;
        }
        for (int i = 0; i < cantChar.length - 1; i++) {
            if(cantChar[i]==5)
            {
                jugadasP[3]++;
            }
        }

        if(jugadasP[1]==1)
        {
            if(jugadasP[2]==1)
            {
                System.out.println("La jugda que puede hacer es:");
                System.out.println(jugadas[1]);
                System.out.println(jugadas[2]);
                System.out.println("Escalera de color");
                return;
            }
            System.out.println("La jugda que puede hacer es:");
            System.out.println(jugadas[1]);
            return;
        }

        if(jugadasP[2]==1)
        {
            System.out.println("La jugda que puede hacer es:");
            System.out.println(jugadas[2]);
            return;
        }

        System.out.println("La jugada que puede hacer es:");
        System.out.println("Carta Alta");

    }

    public static void imprimirProbabilidades() {
        System.out.println("\n--- PROBABILIDADES TEÓRICAS---");

        System.out.print("1-2. Probabilidad de Escalera de Color:");
        System.out.println(" 0.00154% (1 en 64,974)");

        System.out.print("3. Probabilidad de Poker:");
        System.out.println(" 0.0240% (1 en 4,165)");

        System.out.print("4-5. Probabilidad de Full House:");
        System.out.println(" 0.1441% (1 en 694)");

        System.out.print("6. Probabilidad de Escalera:");
        System.out.println(" 0.3925% (1 en 255)");

        System.out.print("7. Probabilidad de Trio:");
        System.out.println(" 2.1128% (1 en 47)");

        System.out.print("8. Probabilidad de Par Doble:");
        System.out.println(" 4.7539% (1 en 21)");

        System.out.print("9. Probabilidad de Par:");
        System.out.println(" 42.2569% (1 en 2.4)");

        System.out.print("10-11. Probabilidad de Carta Alta (Ninguna jugada):");
        System.out.println(" 50.1177% (1 en 2)");
    }

}
