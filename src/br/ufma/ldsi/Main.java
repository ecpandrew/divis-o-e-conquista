package br.ufma.ldsi;

import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {




    public static void main(String[] args) {


//        questao1();
        questao2();



    }


    private static void questao2() { // nlogn + logn + n + n + n

        int [] entrada =    {12, 1, 10, 50, 5, 15, 45};
        int n = 3;
        int v = 5;

        mergeSort(entrada, entrada.length); // O(nlogn)
        List<Integer> tampas_list = Arrays.stream(entrada).boxed().toList();
        int near_V = binarySearchNearValue(entrada, v); // O(logn)
        System.out.println(Arrays.toString(entrada));

        System.out.println(near_V);

        List<Integer> menores = new ArrayList();
        List<Integer> maiores = new ArrayList();

        for (int i = 0; i < entrada.length; i++) { // O(n)
            if(entrada[i]< near_V){
                menores.add(entrada[i]);
            }if(entrada[i]> near_V){
                maiores.add(entrada[i]);
            }
        }

        for (int i = 0; i < n; i++) { // O(n)
            System.out.println(i);

        }

        System.out.println(menores);
        System.out.println(maiores);


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


    public static void questao1(){ // nlogn * (n * logn) = O(2nlogn)
        int [] potes =    {12, 1, 10, 50, 5, 15, 45};
        int [] tampas  =  {10, 1, 50, 5, 45, 15, 12};
        //                [1, 5, 10, 12, 15, 45, 50]
        //                [0, 0, 0,  12,  0,  0,  0]
        int [] potes_ordenadas = new int[potes.length];

        mergeSort(tampas, tampas.length); // O(nlogn)

        List<Integer> tampas_list = Arrays.stream(tampas).boxed().toList(); // O(n) - remover isso

        for (int i = 0; i < potes.length; i++) { // O(n)
            int pote = potes[i];
            int index_tampa = Collections.binarySearch(tampas_list, pote); // O(logn) - implementar isso
            potes_ordenadas[index_tampa] = tampas[index_tampa];
        }

        for(int i =0; i< tampas.length;++i){
            System.out.print(tampas[i]+ " ");
        }
        for(int i =0; i< potes_ordenadas.length;++i){
            System.out.print(potes_ordenadas[i]+ " ");
        }
    }

    public static void mergeSort(int [] arr, int len){
        if (len < 2){return;}

        int mid = len / 2;
        int [] left_arr = new int[mid];
        int [] right_arr = new int[len-mid];

        //Dividing array into two and copying into two separate arrays
        int k = 0;
        for(int i = 0;i<len;++i){
            if(i<mid){
                left_arr[i] = arr[i];
            }
            else{
                right_arr[k] = arr[i];
                k = k+1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        mergeSort(left_arr,mid);
        mergeSort(right_arr,len-mid);
        // Calling the merge method on each subdivision
        merge(left_arr,right_arr,arr,mid,len-mid);
    }

    public static void merge(int[] left_arr,int[] right_arr, int[] arr,int left_size, int right_size){

        int i=0,l=0,r = 0;
        //The while loops check the conditions for merging
        while(l<left_size && r<right_size){

            if(left_arr[l]<right_arr[r]){
                arr[i++] = left_arr[l++];
            }
            else{
                arr[i++] = right_arr[r++];
            }
        }
        while(l<left_size){
            arr[i++] = left_arr[l++];
        }
        while(r<right_size){
            arr[i++] = right_arr[r++];
        }
    }



}
