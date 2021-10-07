public class DadosDeGamao implements Sorteador{


    int I = 0,J = 0;
    Dado dado1 = new Dado();
    Dado dado2 = new Dado();

    @Override
    public int sortear() {

        I = dado1.sortear();
        J = dado2.sortear();

        if(I != J){
            return I+J;
        }
        return 2*(I+J);
    }
}
