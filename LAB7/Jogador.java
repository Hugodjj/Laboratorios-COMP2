import java.util.ArrayList;

public class Jogador{

    private int pontuacao;
    private final int PONTUACAO_INICIAL = 1000;
    private final int senha;
    private final String nome;
    private boolean online;
    private boolean jogando;

    public static ArrayList<Jogador> ListaJogadores = new ArrayList<>();

    public ArrayList<Partida> HistoricoDePartidaDeCadaJogador = new ArrayList<>();

    public Jogador(String nomejogador, int senhajogador){
        this.nome= nomejogador;
        this.senha= senhajogador;
        this.pontuacao = PONTUACAO_INICIAL;
        this.online = false;
        this.jogando = false;
        ListaJogadores.add(this);
    }

    public int getPontuacao(){
        return this.pontuacao;
    }

    public boolean isOnline(){
        return this.online;
    }

    public boolean isJogando() {
            return this.jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    public void setOnline(boolean online){
        this.online = online;
    }

    public String getNome() {
        return nome;
    }

    public int getSenha(){
        return this.senha;
    }

    public void setPontuacao(boolean venceu){

        if(venceu){
            this.pontuacao +=1;
        }
        if(!venceu){
            this.pontuacao-=1;
        }
    }

    @Override
    public String toString(){
        return  ("NomeJogador: " + this.nome + " - ") +
                ("Senha: " + this.senha+ " - ") +
                ("Status Online: " + this.online+ " - ") +
                ("Está jogando: " + this.jogando + " - ") +
                ("Pontuação " + this.getPontuacao() + "\n");
    }
}