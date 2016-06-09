/******************************************************************************
 *
 * File :   MergeSort.java
 * 
 * Author : Badzyuk, CPI-GVA
 *
 * Date :   7 апр. 2016 г.
 *
 * History of modifications
 * Date       Rev.    Reason
 *
 *
 *****************************************************************************/
/**
 * TODO: Add file's description.
 *
 ******************************************************************************
 * 
 *  CPI
 *
 *      U.S.A.  3222 Phoenixville Pike, suite 200 Malvern, PA 19355 
 *                      Tel:  +1-610-430-2700
 *
 *      C.H.    Ch. Pont-du-Centenaire 109, Plan-les-Ouates, P.O. Box 2650
 *                      Tel: +41-22-884-0505
 *
 *  http://www.cranepi.com
 *
 ******** (c) 2015 Crane Payment Innovations, Inc. All rights reserved. ********
 *
 * Decompilation prohibited except as permitted by law. No using, disclosing,
 * reproducing, accessing or modifying without prior written consent.
 *
 *****************************************************************************/
package application;

import java.util.Random;

/****************************************************************************
 * MergeSort
 ***************************************************************************/
/**
 * TODO: Add class's comments here.
 *
 *
 ***************************************************************************/
public class Merge {

    // This class should not be instantiated.
    private Merge() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        
        //Как меняется индексі ло - мидл - хай
        System.out.println("lowInd, middle, high == " + lo +" "+ mid +" "+ hi);
        
        //сколько раз будет выполнятся этот цикл ????
        // как определяется что не будет выхода за пределы сегмента
        int count = 0;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
            
            count++;
        }
        System.out.println("Number of cycles = " + count);
        show(a);
        
        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }
    
  
    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        

   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
   
    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
        Integer[] a = new Integer[5];
        randomInsert(a);
        show(a);
        System.out.println();
        
        Merge.sort(a);
        System.out.println();
        show(a);
    }


  private static void randomInsert(Integer[] arrayToSort) {
    Random r = new Random();
    
    for (int j = 0; j < arrayToSort.length; j++) {
//      arrayToSort[j] = r.nextInt(100);  
      arrayToSort[j] = arrayToSort.length - j;  
      
    } //for
    
  }
}