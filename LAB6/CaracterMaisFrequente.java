import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CaracterMaisFrequente {

        // Algoritmo ineficiente (quadrático):

    public static char encontrarCaracterMaisFrequenteQuadratico(String texto) {


        char maisFrequente = texto.charAt(0);
        int ocorrenciasDoMaisFrequente = 1;

        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            int contOcorrencias = 1;
            for (int j = i + 1; j < texto.length(); j++) {
                if (texto.charAt(j) == caracterDaVez) {
                    contOcorrencias++;
                }
            }

            if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                maisFrequente = caracterDaVez;
                ocorrenciasDoMaisFrequente = contOcorrencias;
            }
        }

        return maisFrequente;
    }
        // Algoritmo eficiente (linear):

    public static char encontrarCaracterMaisFrequenteLinear(String texto) {

        Map<Character, Integer> Mapa = new HashMap<>();

        //Adicionando string ao hashmap.

        for (int i =0; i< texto.length();i++){
            if(Mapa.containsKey(texto.charAt(i))){
                int k = Mapa.get(texto.charAt(i));
                Mapa.put(texto.charAt(i),k+1);
            }
            Mapa.put(texto.charAt(i),1);
        }

        // Procurando Caractere.

        char LetraMaiorOcorrente = 0;
        int Contador = 0;

        for (Character i : Mapa.keySet()){
            if (Mapa.get(i) > Contador){
                LetraMaiorOcorrente = i;
                Contador = Mapa.get(i);
            }
        }
        return LetraMaiorOcorrente;
    }
}


