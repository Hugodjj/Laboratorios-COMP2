public class Principal {

    public static void main(String[] args) {


        DadosDeGamao dado1 = new DadosDeGamao();
        DadosTriplos dado2 = new DadosTriplos();

        JogoMalucoComSorteadores<DadosDeGamao, DadosTriplos> joguinho1 = new JogoMalucoComSorteadores<>("joguinho", "jonas", "hugo",
                1, dado1, dado2);

        JogoMalucoComSorteadores<DadosDeGamao, DadosTriplos> joguinho2 = new JogoMalucoComSorteadores<>("joguinho", "jonas", "hugo",
                100, dado1, dado2);

        for (int i = 1; i <= 100000; i++) {
            joguinho1.Jogar();
        }

        for (int i = 1; i <= 100000; i++) {
            joguinho2.Jogar();
        }
        System.out.println("PORCENTAGEM JOGUINHO 1\n");
        System.out.println("Porcentagem Jogador 1 = " + joguinho1.getPercentualVitoriasJogador1());
        System.out.println("Porcentagem Jogador 2 =" + joguinho1.getPercentualVitoriasJogador2());
        System.out.println("Empates = " + joguinho1.getPercentualEmpates());
        System.out.println("\nPORCENTAGEM JOGUINHO 2\n");
        System.out.println("Porcentagem Jogador 1 =" + joguinho2.getPercentualVitoriasJogador1());
        System.out.println("Porcentagem Jogador 1 =" + joguinho2.getPercentualVitoriasJogador2());
        System.out.println("Empates = " + joguinho2.getPercentualEmpates());
    }
}


