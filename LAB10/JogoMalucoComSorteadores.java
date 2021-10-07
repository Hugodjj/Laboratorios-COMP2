public class JogoMalucoComSorteadores <S1 extends  Sorteador, S2 extends Sorteador> extends JogoDeDoisJogadores{

    Sorteador sorteador1;
    Sorteador sorteador2;

    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2,
                                    int numeroDeRodadas, S1 sorteador1, S2 sorteador2) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);

        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {

        int numeroJogador1 = sorteador1.sortear();
        int numeroJogador2 = sorteador2.sortear();

        if (numeroJogador1 > numeroJogador2){
            return 1;
        }
        else if (numeroJogador2 > numeroJogador1){
            return 2;
        }
        return 0;
    }
}
