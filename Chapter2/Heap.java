package Chapter2;


import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/9.
 */
public class Heap {
    public static void sort(Comparable[] a)
    {
        int N = a.length;

        //init
        for (int k = N/2; k >= 1; k--)
        {
            sink(a, k, N);
        }
        //down
        while (N > 1)
        {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }
    private static void sink(Comparable[] a, int k, int N)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j))
                break;
            exch(a, k, j);
            k = j;
         }
    }
    private static boolean less(Comparable[] a, int i, int j)
    {
        return a[i-1].compareTo(a[j-1]) < 0;
    }
    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }
    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/words3.txt");
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
        if (!SortExample.isSorted(a)) {
            System.out.println("not sorted");
            return;
        }
        SortExample.show(a);
    }
}
