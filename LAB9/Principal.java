public class Principal {

    public static void main(String[] args) {


        DadosDeGamao dado1 = new DadosDeGamao();
        DadosTriplos dado2 = new DadosTriplos();

        JogoMalucoComSorteadores joguinho1 = new JogoMalucoComSorteadores("joguinho", "jonas", "hugo",
                10000,dado1, dado2);

        System.out.println(joguinho1.Jogar());
    }
}

