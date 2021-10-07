import java.util.Scanner;
import java.util.Random;

public class Lab1Teste {

    public static int obterTamanhoIntersecao(int[] lista1, int[] lista2){

        int intersecao = 0;

        for (int i = 0; i < lista1.length; i++){
            for(int j = 0; j < lista2.length; j++){

                if(lista1[i] == lista2[j]){
                    intersecao++;
                    }
                }
            }
            return intersecao;
        }

    public static void main(String[] args){

        System.out.println("Digite o Tamanho das listas:");
        Scanner Scanner = new Scanner(System.in);
        int tamanho = Scanner.nextInt();

        int[] lista1 = new int[tamanho];

        Random random = new Random();
        int[] array1 = new int[tamanho]; // Será gerado a quantidade que o usuario quiser.

        for (int i=0; i<array1.length; i++) {
            array1[i] = random.nextInt(10*tamanho); // Gera números aleatórios de 1 até o limite de 10 * o tamanho da lista.
        }

        for (int i = 0; i < array1.length; i++) {
            lista1[i] = array1[i];
        }

        System.out.println("\nLISTA 1\n");

        for(int i=0;i<lista1.length;i++) {
            System.out.println(lista1[i]);
        }


        System.out.println("\nLISTA 2\n");

        int[] array2 = new int[tamanho];

        for (int j=0; j<array2.length; j++) {
            array2[j] = random.nextInt(10*tamanho);
            System.out.println(array2[j]);
        }

        int[] lista2 = new int[tamanho];

        for (int j = 0; j < array2.length; j++) {
            lista2[j] = array2[j];
        }

        System.out.println("\nTamanho da intersecção\n");
        System.out.println(obterTamanhoIntersecao(lista1, lista2));
    }
}