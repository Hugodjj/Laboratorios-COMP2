public class DadosTriplos implements Sorteador{

    int I = 0,J = 0,K = 0;
    Dado dado1 = new Dado();

    @Override
    public int sortear() {

        I = dado1.sortear();
        J = dado1.sortear();
        K = dado1.sortear();

        return I+J+K;
    }
}
