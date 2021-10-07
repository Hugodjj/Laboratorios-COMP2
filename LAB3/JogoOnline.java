import java.util.Objects;

public class JogoOnline {

    public static void CadastroJogador(String nomejogador, final int senhajogador) {

        Jogador jogador = new Jogador(nomejogador, senhajogador);
    }

    public static void efetuarlogin(String nomejogador, int senhajogador) {

        for (int i = 0; i < Jogador.ListaJogadores.size(); i++) {
            Jogador jogador = Jogador.ListaJogadores.get(i);
            if (jogador.getSenha() == (senhajogador) && Objects.equals(jogador.getNome(), nomejogador)) {
                jogador.setOnline(true);
                return;
            }
        }
    }

    public static void efetuarLogout() {

        for (int i = 0; i < Jogador.ListaJogadores.size(); i++) {
            Jogador jogador = Jogador.ListaJogadores.get(i);
            if (jogador.isOnline()) {
                jogador.setOnline(false);
                return;
            }
        }
    }

    public static Object IniciarPartida(Jogador jogador1, Jogador jogador2) {


        if (!jogador1.isOnline() || jogador1.isJogando() ||
                !jogador2.isOnline() || jogador2.isJogando()) {
            return null;
        }

        Partida Classico = new Partida(jogador1, jogador2);

            jogador1.setJogando(true);
            jogador2.setJogando(true);

            return Classico;
    }

    public static Jogador escolherAdversario(Jogador solicitante){

        for (int i = 0; i < Jogador.ListaJogadores.size();i++){
            Jogador jogador = Jogador.ListaJogadores.get(i);
            if (jogador != solicitante && jogador.isOnline() && !jogador.isJogando()){
                return jogador;
            }
        }
        return null;
    }

    public static void encerrarPartida(Partida partida, int resultado) {

        partida.setResultado(resultado);
        partida.getJogador1().setJogando(false);
        partida.getJogador2().setJogando(false);
        partida.getJogador1().HistoricoDePartidaDeCadaJogador.add(partida);
        partida.getJogador2().HistoricoDePartidaDeCadaJogador.add(partida);
        Partida.HistóricoDePartidas.add(partida);

        if (resultado == 0){
            return;
        }

        if (resultado == 1){
            partida.getJogador1().setPontuacao(true);
            partida.getJogador2().setPontuacao(false);
        }
        if (resultado == 2){
            partida.getJogador1().setPontuacao(false);
            partida.getJogador2().setPontuacao(true);
        }
    }
}