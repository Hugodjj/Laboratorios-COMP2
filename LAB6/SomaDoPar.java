import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SomaDoPar {

    public static Integer encontrarParComSomaDadaQuadratico (ArrayList<Integer> numeros, int somaDesejada) {

        // Algoritmo ineficiente (quadrático):

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x, y) ;
                }
            }
        }

        return null;
    }
        public static Integer encontrarParComSomaDadaLinear (ArrayList < Integer > numeros,int somaDesejada){

            // Algoritmo eficiente (linear):

            Set<Integer> ListaNumeros = new HashSet<>();
            int NumeroProcurado;

            for (Integer i : numeros) {
                ListaNumeros.add(i);
            }
            for (Integer i : ListaNumeros) {
                NumeroProcurado = somaDesejada - i;
                if (ListaNumeros.contains(NumeroProcurado)) {
                    return Math.min(i, NumeroProcurado);
                }
            }
            return null;
        }
}
