import java.util.*;

public class Lab1 {

    public static int obterTamanhoIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {


        lista1.retainAll(lista2);

        ArrayList<Integer> lista3 = new ArrayList<>();

        for (int i = 0; i < lista1.size();i++) {
            if (!lista3.contains(i)) {
                lista3.add(i);
            }
        }

        return lista3.size();
    }
    public static void main (String[] args){

        ArrayList<Integer> lista1= new ArrayList<>();

        System.out.println("Digite o Tamanho das listas:");
        Scanner Scanner = new Scanner(System.in);
        int tamanho = Scanner.nextInt();

        Random random_lista1 = new Random();

        for(int i = 0; i < tamanho; i++) {
            lista1.add(random_lista1.nextInt(10*tamanho));
        }

        System.out.println("LISTA 1\n");
        System.out.println(lista1);

        ArrayList<Integer> lista2 = new ArrayList<>();

        Random random_lista2 = new Random();

        for(int i = 0; i < tamanho; i++) {
            lista2.add(random_lista2.nextInt(10*tamanho));
        }

        System.out.println("\nLISTA 2\n");
        System.out.println(lista2);

        System.out.print("\nQUANTIDADE DE NUMEROS NA INTERSECCAO: ");
        System.out.println(obterTamanhoIntersecao(lista1,lista2));
    }
}

