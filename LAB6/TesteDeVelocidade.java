import java.util.ArrayList;
import java.util.Random;

public class TesteDeVelocidade {

    //TESTANDO VELOCIDADE DOS ALGORITMOS SOMA DO PAR

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();

        for(int i = 0; i < 100000; i++){
            Random random = new Random();
            lista.add(random.nextInt());
        }

        long InicioSomaQuadratica = System.currentTimeMillis();
        SomaDoPar.encontrarParComSomaDadaQuadratico(lista,0);
        long DuracaoSomaQuadratica = System.currentTimeMillis() - InicioSomaQuadratica;
        System.out.printf("\nDuracao da Soma (Quadratica) = %.3f segundos", DuracaoSomaQuadratica / 1000.0);

        long InicioSomaLinear = System.currentTimeMillis();
        SomaDoPar.encontrarParComSomaDadaLinear(lista,0);
        long DuracaoSomaLinear = System.currentTimeMillis() - InicioSomaLinear;
        System.out.printf("\nDuracao da Soma (linear) = %.3f segundos", DuracaoSomaLinear / 1000.0);

        // TESTANDO CARACTERE MAIS FREQUENTE

        String sequencia = "aasdogasgpasdgofiuqawesgiuashdgasiduh";
        String texto = "";
        for (int i = 0; i < 1000; i++){
            texto = texto + sequencia;
        }

        long InicioCaractereQuadratico = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratico(texto);
        long DuracaoCaractereQuadratico = System.currentTimeMillis() - InicioCaractereQuadratico;
        System.out.printf("\nDuracao do caractere mais frequente (Quadratica) = %.3f segundos", DuracaoCaractereQuadratico / 1000.0);

        long InicioCaractereLinear = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear(texto);
        long DuracaoCaractereLinear = System.currentTimeMillis() - InicioCaractereLinear;
        System.out.printf("\nDuracao do caractere mais frequente (Linear) = %.3f segundos", DuracaoCaractereLinear / 1000.0);
    }
}
