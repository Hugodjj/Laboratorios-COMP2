import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {

    protected int PontuacaoJogador1 = 0;
    protected int PontuacaoJogador2 = 0;
    protected String nomeJogo;
    protected String nomeJogador1;
    protected String nomeJogador2;
    protected int numeroDeRodadas;
    public ArrayList<Integer> historicoResultados;


    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
        this.historicoResultados = new ArrayList<>();
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public int getNumeroDeRodadas() {
        return numeroDeRodadas;
    }

    protected abstract int executarRodadaDoJogo();

    public String Jogar() {

        for (int i = 0; i < getNumeroDeRodadas(); i++) {
            executarRodadaDoJogo();

            historicoResultados.add(executarRodadaDoJogo());
        }


        for (Integer historicoResultado : historicoResultados) {
            if (historicoResultado == 1) {
                PontuacaoJogador1++;
            } else if (historicoResultado == 2) {
                PontuacaoJogador2++;
            }
        }

        if (PontuacaoJogador1 > PontuacaoJogador2){
            return "O jogador " + getNomeJogador1() + " venceu o " + getNomeJogo() + " por " + PontuacaoJogador1 + " a " + PontuacaoJogador2;
        }
        else if (PontuacaoJogador1 < PontuacaoJogador2){
            return "O jogador " + getNomeJogador2() + " venceu o "+ getNomeJogo() +" por " + PontuacaoJogador2 + " a " + PontuacaoJogador1;
        }
        return "O jogo terminou em empate após " + numeroDeRodadas + " rodadas.";
    }
}
