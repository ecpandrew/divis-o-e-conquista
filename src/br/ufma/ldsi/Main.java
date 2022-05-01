package br.ufma.ldsi;

import com.sun.source.tree.BinaryTree;

import java.util.*;

public class Main {




    public static void main(String[] args) {

        System.out.println("Questao 1:");
        int [] potes =    {12, 1, 10, 50, 5, 15, 45};
        int [] tampas  =  {10, 1, 50, 5, 45, 15, 12};
        questao1(tampas, potes);


        System.out.println("\n\nQuestao 2:");
        int [] entrada = {5, 3, 8, 7, 1, 4};
        int n = 3;
        int v = 6;
        questao2(entrada, n, v);


    }

    // Na questão inicialmente ordenamos a lista de tampas utilizando o merge sort, o que implica em uma complexidade nlogn
    // apos isso, percorre-se o array de potes e para cada pode utiliza-se a busca binária para encontrar a tampa equivalente,
    // o que implica em uma complexidade n * logn
    // Ao final do algorimto, temos: nlogn * (n * logn) = O(2nlogn), o que pode ser simplificado para nlogn
    public static void questao1(int[] tampas, int[] potes){ // nlogn * (n * logn) = O(2nlogn)


        int [] potes_ordenadas = new int[potes.length];

        mergeSort(tampas, tampas.length); // O(nlogn)

        List<Integer> tampas_list = Arrays.stream(tampas).boxed().toList(); // apenas uma conversão

        for (int i = 0; i < potes.length; i++) { // O(n)
            int pote = potes[i];
            int index_tampa = Collections.binarySearch(tampas_list, pote); // O(logn)
            potes_ordenadas[index_tampa] = tampas[index_tampa];
        }

        System.out.println("Tampas:");
        for(int i =0; i< tampas.length;++i){ // apenas imprimindo os resultados
            System.out.print(tampas[i]+ " ");
        }
        System.out.println("\nPotes:");
        for(int i =0; i< potes_ordenadas.length;++i){// apenas imprimindo os resultados
            System.out.print(potes_ordenadas[i]+ " ");
        }
    }



    private static void questao2(int[] entrada, int n, int v) { // nlogn + logn + n   ---> nlogn

        System.out.println("Entrada: "+ Arrays.toString(entrada));
        mergeSort(entrada, entrada.length); // O(nlogn)
        System.out.println("Entrada Ordenada: "+ Arrays.toString(entrada));
        System.out.println("N: "+ n);
        System.out.println("V: "+ v);


        int near_V = binarySearchNearValue(entrada, v); // O(logn)

        obterOsNElementosMaisProximos(entrada, near_V, n, entrada.length  );  // n


    }








    public static void obterOsNElementosMaisProximos(int entrada[], int v, int n, int tamanho_entrada)
    {
        int l = indexOfIntArray(entrada,v); // O(n)

        int r = l+1;
        int count = 0;

        if (entrada[l] == v){
            l--;
        }
        System.out.print("Resutado: ");
        while (l >= 0 && r < tamanho_entrada && count < n) { // O(n)
            if (v - entrada[l] < entrada[r] - v){
                System.out.print(entrada[l--]+" ");
            } else{
                System.out.print(entrada[r++]+" ");
            }

            count++;
        }

        while (count < n && l >= 0){
            System.out.print(entrada[l--]+" ");
            count++;
        }
        while (count < n && r < tamanho_entrada){
            System.out.print(entrada[r++]+" ");
            count++;
        }
    }

    public static int indexOfIntArray(int[] array, int key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key == array[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    public static int binarySearchNearValue(int[] a, int value) { //https://stackoverflow.com/questions/30245166/find-the-nearest-closest-value-in-a-sorted-list
        if(value < a[0]) {
            return a[0];
        }
        if(value > a[a.length-1]) {
            return a[a.length-1];
        }
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (value < a[mid]) {
                hi = mid - 1;
            } else if (value > a[mid]) {
                lo = mid + 1;
            } else {
                return a[mid];
            }
        }
        // lo == hi + 1
        return (a[lo] - value) < (value - a[hi]) ? a[lo] : a[hi];
    }



    public static void mergeSort(int [] entrada, int tamanho){
        if (tamanho <2 ){
            return;
        }

        int indice_esquerda = tamanho / 2;
        int [] sub_lista_esquerda = new int[indice_esquerda];
        int [] sub_lista_direita = new int[tamanho - indice_esquerda];

        int aux = 0;
        for(int i = 0; i<tamanho; ++i){

            if(i<indice_esquerda){
                sub_lista_esquerda[i] = entrada[i];
            }else{
                sub_lista_direita[aux] = entrada[i];
                aux = aux+1;
            }
        }
        mergeSort(sub_lista_esquerda, indice_esquerda);
        mergeSort(sub_lista_direita,tamanho - indice_esquerda);
        merge(sub_lista_esquerda, sub_lista_direita, entrada, indice_esquerda,tamanho - indice_esquerda);
    }

    public static void merge(int[] sub_lista_esquerda,int[] sub_lista_direita, int[] entrada, int indice_esquerda, int indice_direita){
        int d=0;
        int e=0;
        int i=0;
        while(e<indice_esquerda && d<indice_direita){
            if(sub_lista_esquerda[e]<sub_lista_direita[d]){
                entrada[i++] = sub_lista_esquerda[e++];
            }else{
                entrada[i++] = sub_lista_direita[d++];
            }
        }
        while(e<indice_esquerda){
            entrada[i++] = sub_lista_esquerda[e++];
        }
        while(d<indice_direita){
            entrada[i++] = sub_lista_direita[d++];
        }
    }



}
