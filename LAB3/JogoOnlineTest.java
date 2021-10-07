import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {

    Jogador Ciclano = new Jogador("FULANINHO",134);
    Jogador Fulano = new Jogador("JACARE",222);
    Jogador BATMAN = new Jogador("BATMAN",123);
    Jogador SUPERMAN = new Jogador("FICOUFRACO",444);
    Jogador OPINGUIN = new Jogador("JOGOUCRIPTONITA",564);

    Partida Classico = new Partida(Fulano,Ciclano);
    Partida FLAFLU = new Partida(Fulano,OPINGUIN);
    Partida TERRAPLANA = new Partida(BATMAN,Ciclano);
    Partida PINGUINXBATMAN = new Partida(OPINGUIN,BATMAN);
    Partida SUPERMANXBATMAN = new Partida(SUPERMAN,BATMAN);
    Partida SUPERMANXPINGUIN = new Partida(SUPERMAN,OPINGUIN);


    @Test
    public void TestarCadastro(){

        JogoOnline.CadastroJogador("HugoNascimento",999);
        JogoOnline.CadastroJogador("Albert Einstein",9);

        assertEquals(7, Jogador.ListaJogadores.size());
    }

    @Test
    public void TestarloginSenhaErradaCerta() {

        //testando senha certa.
        JogoOnline.efetuarlogin("JACARE",222);
        assertTrue(Fulano.isOnline());

        //testando senha errada.
        JogoOnline.efetuarlogin("FULANINHO",111);
        assertFalse(Ciclano.isOnline());
    }

    @Test
    public void TestarLogout(){

        JogoOnline.efetuarlogin("JACARE",222);

        JogoOnline.efetuarLogout();
        assertFalse(Fulano.isOnline());
    }

    @Test public void TesteJogando(){

        JogoOnline.efetuarlogin("FULANINHO",134);
        JogoOnline.efetuarlogin("JACARE",222);


        JogoOnline.IniciarPartida(Ciclano,Fulano);
        assertTrue(Ciclano.isJogando());
        assertTrue(Fulano.isJogando());
    }

    @Test
    public void TestarHistoricoDePartidasEJogadores(){

        JogoOnline.efetuarlogin("FULANINHO",134);
        JogoOnline.efetuarlogin("JACARE",222);
        JogoOnline.efetuarlogin("BATMAN",123);
        JogoOnline.efetuarlogin("FICOUFRACO",444);
        JogoOnline.efetuarlogin("JOGOUCRIPTONITA",564);

        JogoOnline.IniciarPartida(Fulano,Ciclano);
        JogoOnline.IniciarPartida(Fulano,OPINGUIN);
        JogoOnline.IniciarPartida(BATMAN,Ciclano);
        JogoOnline.IniciarPartida(OPINGUIN,BATMAN);
        JogoOnline.IniciarPartida(SUPERMAN,BATMAN);
        JogoOnline.IniciarPartida(SUPERMAN,OPINGUIN);

        JogoOnline.encerrarPartida(Classico,1);
        JogoOnline.encerrarPartida(FLAFLU,1);
        JogoOnline.encerrarPartida(TERRAPLANA,2);
        JogoOnline.encerrarPartida(PINGUINXBATMAN,0);
        JogoOnline.encerrarPartida(SUPERMANXBATMAN,1);
        JogoOnline.encerrarPartida(SUPERMANXPINGUIN,2);

        assertEquals(2,Fulano.HistoricoDePartidaDeCadaJogador.size());
        assertEquals(3,OPINGUIN.HistoricoDePartidaDeCadaJogador.size());
        assertEquals(2,Ciclano.HistoricoDePartidaDeCadaJogador.size());
        assertEquals(3,BATMAN.HistoricoDePartidaDeCadaJogador.size());
        assertEquals(2,SUPERMAN.HistoricoDePartidaDeCadaJogador.size());

        assertEquals(6,Partida.HistóricoDePartidas.size());
    }

    @Test
    public void TestarPontuacao(){

        JogoOnline.efetuarlogin("FICOUFRACO",444);
        JogoOnline.efetuarlogin("JOGOUCRIPTONITA",564);

        JogoOnline.IniciarPartida(SUPERMAN,OPINGUIN);
        JogoOnline.encerrarPartida(SUPERMANXPINGUIN,2);

        assertEquals(999,SUPERMAN.getPontuacao());
        assertEquals(1001,OPINGUIN.getPontuacao());

        JogoOnline.IniciarPartida(SUPERMAN,OPINGUIN);
        JogoOnline.encerrarPartida(SUPERMANXPINGUIN,1);

        assertEquals(1000,SUPERMAN.getPontuacao());
        assertEquals(1000,OPINGUIN.getPontuacao());

        JogoOnline.IniciarPartida(SUPERMAN,OPINGUIN);
        JogoOnline.encerrarPartida(SUPERMANXPINGUIN,0);

        assertEquals(1000,SUPERMAN.getPontuacao());
        assertEquals(1000,OPINGUIN.getPontuacao());
    }

    @Test
    public void TestarGetResultado(){

        JogoOnline.efetuarlogin("JACARE",222);
        JogoOnline.efetuarlogin("FULANINHO",134);

        Partida Classico = new Partida(Fulano,Ciclano);
        JogoOnline.encerrarPartida(Classico,1);

        assertEquals(1,Classico.getResultado());
    }

    @Test
    public void Escolheradversárioteste(){

        JogoOnline.efetuarlogin("FULANINHO",134);
        JogoOnline.efetuarlogin("JACARE",222);
        JogoOnline.efetuarlogin("BATMAN",123);
        JogoOnline.efetuarlogin("FICOUFRACO",444);
        JogoOnline.efetuarlogin("JOGOUCRIPTONITA",564);

        assertTrue(JogoOnline.escolherAdversario(Fulano).isOnline());
        assertFalse(JogoOnline.escolherAdversario(Fulano).isJogando());
    }
}
