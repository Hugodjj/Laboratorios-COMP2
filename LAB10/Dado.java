import java.util.Random;

public class Dado implements Sorteador{


    Random NumeroAleatorio = new Random();

    @Override
    public int sortear() {

        return NumeroAleatorio.nextInt(7);
    }
}
