import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JogoOnline {

    public static void CadastroJogador(String nomejogador, final int senhajogador) {

        Jogador jogador = new Jogador(nomejogador,senhajogador);

        Jogador.ListaJogadores.add(jogador);
    }

    public static void efetuarlogin(String nomejogador, int senhajogador)throws UsuarioInexistenteException, SenhaInvalidaException {

        Jogador jogador = null;

        for (int i = 0; i < Jogador.ListaJogadores.size(); i++) {
            Jogador jogadorAuxiliar = Jogador.ListaJogadores.get(i);

            if (jogadorAuxiliar.getNome().equals(nomejogador)) {

                jogador = jogadorAuxiliar;
                break;
            }
        }
        if (jogador == null) {

            throw new UsuarioInexistenteException();
        }
        if (jogador.getSenha() != senhajogador){

            throw new SenhaInvalidaException();
        }
        jogador.setOnline(true);
    }

    public static void efetuarLogout(Jogador jogador){

        if (!jogador.isOnline()){
            throw new RuntimeException();
        }

        else if (jogador.isOnline()){
             jogador.setOnline(false);
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

    // LAB 8.

    public static List<Jogador> obterRanking(){

        ComparadorDeJogadoresPorPontuacao comparadorRanking = new ComparadorDeJogadoresPorPontuacao();
        Jogador.ListaJogadores.sort(comparadorRanking);

        ArrayList<Jogador> ListaOrdenadaRanking = new ArrayList<>();

        ListaOrdenadaRanking.addAll(Jogador.ListaJogadores);

        Collections.reverse(ListaOrdenadaRanking);

        return ListaOrdenadaRanking;
    }

    public static List<Jogador> obterJogadoresEmOrdemAlfabetica() {

        ArrayList<Jogador> ListOrdenadaPorNome = new ArrayList<>();
        ComparadorDeJogadoresPorNome comparadorNome = new ComparadorDeJogadoresPorNome();

        Jogador.ListaJogadores.sort(comparadorNome);

        ListOrdenadaPorNome.addAll(Jogador.ListaJogadores);

        return ListOrdenadaPorNome;
    }
}