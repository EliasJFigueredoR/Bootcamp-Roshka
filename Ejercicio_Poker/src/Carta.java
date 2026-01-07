
public class Carta {
    private char valor;
    private char palo;
    private int valorN;

    public Carta(char valor, char palo, int valorN)
    {
        this.valor = valor;
        this.palo = palo;
        this.valorN = valorN;
    }

    public Carta()
    {
    }

    public int getValor() {
        return valor;
    }

    public int getValorN() {
        return valorN + 1;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public char getPalo() {
        return palo;
    }

    public void setPalo(char palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        return "Carta " + valor + palo;
    }
}
