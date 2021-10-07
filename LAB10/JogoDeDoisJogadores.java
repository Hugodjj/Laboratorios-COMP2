import java.util.ArrayList;
import java.util.Date;

public abstract class JogoDeDoisJogadores {


    protected String nomeJogo;
    protected String nomeJogador1;
    protected String nomeJogador2;
    protected int numeroDeRodadas;
    public ArrayList<ResultadoPartida> historicoResultados;


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

    public int Jogar() {

        int PontuacaoJogador1 = 0;
        int PontuacaoJogador2 = 0;
        int empates = 0;

        for (int i = 0; i < getNumeroDeRodadas(); i++) {

            int resultadoDaRodada = executarRodadaDoJogo();

            if (resultadoDaRodada == 1) {
                PontuacaoJogador1++;
            } else if (resultadoDaRodada == 2) {
                PontuacaoJogador2++;
            }
            empates++;
        }
        ResultadoPartida resultadoPartida = new ResultadoPartida(
                new Date(),
                PontuacaoJogador1,
                PontuacaoJogador2,
                this.numeroDeRodadas - PontuacaoJogador1 - PontuacaoJogador2);
        this.historicoResultados.add(resultadoPartida);

        if (PontuacaoJogador1 > PontuacaoJogador2) {

            return 1;
        } else if (PontuacaoJogador1 < PontuacaoJogador2) {

            return 2;
        }
        return 0;
    }


    public String obterResultadoUltimoJogo() {

        if (this.historicoResultados.isEmpty()) {
            return null;
        }

        ResultadoPartida ultimaPartidaJogada = this.historicoResultados.get(
                this.historicoResultados.size() - 1);

        int contRodadasVencidasJogador1 = ultimaPartidaJogada.contRodadasVencidasJogador1;
        int contRodadasVencidasJogador2 = ultimaPartidaJogada.contRodadasVencidasJogador2;

        if (contRodadasVencidasJogador1 == contRodadasVencidasJogador2) {
            return String.format("O jogo terminou em empate após %d rodadas.",
                    this.numeroDeRodadas);
        } else {

            String nomeVencedor;
            int pontosVencedor;
            int pontosPerdedor;

            if (contRodadasVencidasJogador1 > contRodadasVencidasJogador2) {
                nomeVencedor = this.nomeJogador1;
                pontosVencedor = contRodadasVencidasJogador1;
                pontosPerdedor = contRodadasVencidasJogador2;
            } else {
                nomeVencedor = this.nomeJogador2;
                pontosVencedor = contRodadasVencidasJogador2;
                pontosPerdedor = contRodadasVencidasJogador1;
            }

            return String.format("O jogador %s venceu o jogo por %d a %d",
                    nomeVencedor, pontosVencedor, pontosPerdedor);
        }
    }

    public double getPercentualVitoriasJogador1() {
        double PontuacaoJogador1 = 0;
        for (ResultadoPartida resultado : historicoResultados) {
            if (resultado.contRodadasVencidasJogador1 > resultado.contRodadasVencidasJogador2) {
                PontuacaoJogador1++;
            }
        }
        return (PontuacaoJogador1 / historicoResultados.size()*100);
    }

    public double getPercentualVitoriasJogador2() {
        double PontuacaoJogador2 = 0;
        for (ResultadoPartida resultado : historicoResultados) {
            if (resultado.contRodadasVencidasJogador1 < resultado.contRodadasVencidasJogador2) {
                PontuacaoJogador2++;
            }
        }
        return (PontuacaoJogador2 / historicoResultados.size() * 100);
    }

    public double getPercentualEmpates() {
        double Empates = 0;
        for (ResultadoPartida resultado : historicoResultados) {
            if (resultado.contRodadasVencidasJogador1 == resultado.contRodadasVencidasJogador2) {
                Empates++;
            }
        }
        return (Empates / historicoResultados.size() * 100);
    }

    private class ResultadoPartida {

        final Date data;
        final int contRodadasVencidasJogador1;
        final int contRodadasVencidasJogador2;
        final int contEmpates;

        public ResultadoPartida(Date data,
                                int contRodadasVencidasJogador1,
                                int contRodadasVencidasJogador2,
                                int contEmpates) {
            this.data = data;
            this.contRodadasVencidasJogador1 = contRodadasVencidasJogador1;
            this.contRodadasVencidasJogador2 = contRodadasVencidasJogador2;
            this.contEmpates = contEmpates;
        }
    }

}
