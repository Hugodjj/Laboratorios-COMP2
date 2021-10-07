import java.util.ArrayList;

public class Partida {

    public static ArrayList<Partida> HistóricoDePartidas = new ArrayList<>();

    private final Jogador jogador1;
    private final Jogador jogador2;
    private int resultado;

    public Partida(Jogador jogador1, Jogador jogador2){
        this.jogador1=jogador1;
        this.jogador2=jogador2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado){
        this.resultado = resultado;
    }

    public Jogador getJogador1(){
        return this.jogador1;
    }

    public Jogador getJogador2(){
        return this.jogador2;
    }


    @Override
    public String toString(){
       return ("Resultado: " + this.resultado) + " " +
               ("Jogador1: " + this.jogador1.getNome()) + " " +
               ("Pontuação: " + this.jogador1.getPontuacao()) + " " +
               ("Jogador2: " + this.jogador2.getNome())+ " " +
               ("Pontuação: " + this.jogador2.getPontuacao()) +  "\n";

    }
}
